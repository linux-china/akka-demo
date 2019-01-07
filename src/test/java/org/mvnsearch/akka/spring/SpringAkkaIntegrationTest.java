package org.mvnsearch.akka.spring;

import akka.actor.ActorRef;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * spring Akka integration test
 *
 * @author linux_china
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AkkaAutoConfiguration.class)
public class SpringAkkaIntegrationTest extends AkkaSpringSupport {

    @Test
    public void testActorDemo() throws Exception {
        ActorRef greeter = actorOf(GreetingActor.class);
        greeter.tell("Jackie", ActorRef.noSender());
        Thread.sleep(1000);
    }
}
