package org.mvnsearch.actor.cluster;

import akka.actor.ActorPath;
import akka.actor.ActorPaths;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.cluster.client.ClusterClient;
import akka.cluster.client.ClusterClientSettings;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * akka actor consumer
 *
 * @author linux_china
 */
public class AkkaActorConsumer {

    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Config config = ConfigFactory.parseURL(AkkaActorConsumer.class.getResource("/akka/cluster/cluster.conf"));
        ActorSystem actorSystem = ActorSystem.create("ClusterSystem", config);
        final ActorRef clusterClient = actorSystem.actorOf(ClusterClient.props(
                ClusterClientSettings.create(actorSystem).withInitialContacts(initialContacts())),
                "clusterClient");
        clusterClient.tell(new ClusterClient.Send("/user/sampleActor", "hello", true), ActorRef.noSender());
        Thread.sleep(2000);
        latch.await();
    }

    private static Set<ActorPath> initialContacts() {
        return new HashSet<>(Arrays.asList(
                ActorPaths.fromString("akka.tcp://ClusterSystem@127.0.0.1:2551/system/receptionist"),
                ActorPaths.fromString("akka.tcp://ClusterSystem@127.0.0.1:2552/system/receptionist")
        ));
    }
}
