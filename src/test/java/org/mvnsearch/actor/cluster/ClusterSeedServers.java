package org.mvnsearch.actor.cluster;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ClusterSeedServers {

    public static void main(String[] args) {
        if (args.length == 0)
            startup(new String[]{"2551", "2552"});
        else
            startup(args);
    }

    /**
     * start server with multi ports
     *
     * @param ports port list
     */
    private static void startup(String[] ports) {
        Config regularConfig = ConfigFactory.parseURL(ClusterSeedServers.class.getResource("/akka/cluster/cluster-seed-server.hocon"));
        for (String port : ports) {
            Config myConfig = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + port);
            Config combined = myConfig.withFallback(regularConfig);
            // Create an Akka system
            ActorSystem system = ActorSystem.create("ClusterSystem", combined);
            // Create an actor that handles cluster domain events
            system.actorOf(Props.create(SimpleClusterListener.class),
                    "clusterListener");
        }

    }
}
