package org.mvnsearch.actor.remote;

import akka.actor.AbstractActor;

/**
 * sample actor
 *
 * @author linux_china
 */
public class SampleActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, System.out::println)
                .matchAny(obj -> {
                    System.out.println("Unknown:" + obj.toString());
                })
                .build();
    }
}
