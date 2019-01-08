package org.mvnsearch.actor.remote;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.concurrent.CountDownLatch;

/**
 * Akka remote server
 *
 * @author linux_china
 */
public class AkkaRemoteServer {

    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Config config = ConfigFactory.parseURL(AkkaRemoteClient.class.getResource("/akka/server.conf"));
        ActorSystem actorSystem = ActorSystem.create("sampleActorSystem", config);
        ActorRef ref = actorSystem.actorOf(Props.create(SampleActor.class), "sampleActor");
        ref.tell("hello", ActorRef.noSender());
        latch.await();

    }
}
