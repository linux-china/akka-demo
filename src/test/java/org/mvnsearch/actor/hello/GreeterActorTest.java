package org.mvnsearch.actor.hello;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

/**
 * Greeter actor test
 *
 * @author linux_china
 */
public class GreeterActorTest {
    private final static ActorSystem actorSystem = ActorSystem.create("greeterAkka");

    @AfterAll
    public static void tearDown() {
        actorSystem.terminate();
    }

    @Test
    public void testHello() throws Exception {
        final ActorRef printerActor = actorSystem.actorOf(PrinterActor.props(), "printerActor");
        final ActorRef helloGreeter = actorSystem.actorOf(GreeterActor.props("Hello", printerActor), "greeterActor");

        helloGreeter.tell(new NamedGreeter("Java"), ActorRef.noSender());
        helloGreeter.tell(new AnonymousGreeter(), ActorRef.noSender());
        Thread.sleep(1000);
        actorSystem.actorSelection("/user/greeterActor").tell("Akka", ActorRef.noSender());
        Thread.sleep(1000);
    }

}
