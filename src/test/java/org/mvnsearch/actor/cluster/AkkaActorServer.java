package org.mvnsearch.actor.cluster;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.client.ClusterClientReceptionist;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.mvnsearch.actor.remote.AkkaRemoteClient;

import java.util.concurrent.CountDownLatch;

/**
 * Akka actor server
 *
 * @author linux_china
 */
public class AkkaActorServer {

    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Config config = ConfigFactory.parseURL(AkkaRemoteClient.class.getResource("/akka/cluster/cluster.conf"));
        ActorSystem actorSystem = ActorSystem.create("ClusterSystem", config);
        ActorRef ref = actorSystem.actorOf(Props.create(SampleActor.class), "sampleActor");
        //register actor on cluster
        ClusterClientReceptionist.get(actorSystem).registerService(ref);
        ref.tell("hello", ActorRef.noSender());
        latch.await();
    }
}
