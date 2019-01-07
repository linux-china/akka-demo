package org.mvnsearch.actor.hello;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

/**
 * greeter actor
 *
 * @author linux_china
 */
public class GreeterActor extends AbstractActor {
    static public Props props(String message, ActorRef printerActor) {
        return Props.create(GreeterActor.class, () -> new GreeterActor(message, printerActor));
    }

    private final String message;
    private final ActorRef printerActor;
    private String greeting = "";

    public GreeterActor(String message, ActorRef printerActor) {
        this.message = message;
        this.printerActor = printerActor;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(NamedGreeter.class, wtg -> {
                    this.greeting = message + ", " + wtg.who;
                    System.out.println(greeting);
                })
                .match(AnonymousGreeter.class, x -> {
                    printerActor.tell(new Greeting(greeting), getSelf());
                })
                .build();
    }
}
