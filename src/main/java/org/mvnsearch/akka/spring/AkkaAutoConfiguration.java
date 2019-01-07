package org.mvnsearch.akka.spring;

import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Akka auto configuration for Spring
 *
 * @author linux_china
 */
@Configuration
@ComponentScan
public class AkkaAutoConfiguration {
    @Autowired
    private ApplicationContext applicationContext;

    @Bean(destroyMethod = "terminate")
    public ActorSystem actorSystem() {
        ActorSystem system = ActorSystem.create("akka-spring-demo");
        SpringAkkaExtension.SPRING_EXTENSION_PROVIDER.get(system)
                .initialize(applicationContext);
        return system;
    }
}
