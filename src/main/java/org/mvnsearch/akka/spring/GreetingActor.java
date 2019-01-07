package org.mvnsearch.akka.spring;

import akka.actor.AbstractActor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * greeting actor
 *
 * @author linux_china
 */
@ActorComponent
public class GreetingActor extends AbstractActor {
    @Autowired
    private GreetingService greetingService;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, text -> {
                    System.out.println(greetingService.greet(text));
                })
                .build();
    }

}