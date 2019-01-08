package org.mvnsearch.actor.remote;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * akka remote client
 *
 * @author linux_china
 */
public class AkkaRemoteClient {

    public static void main(String[] args) throws Exception {
        Config config = ConfigFactory.parseURL(AkkaRemoteClient.class.getResource("/akka/client.conf"));
        ActorSystem actorSystem = ActorSystem.create("sampleActorSystem", config);
        ActorSelection ref = actorSystem.actorSelection("akka.tcp://sampleActorSystem@127.0.0.1:2552/user/sampleActor");
        ref.tell("hello", ActorRef.noSender());
        Thread.sleep(1000);
    }
}
