package org.mvnsearch.akka.spring;

import org.springframework.stereotype.Component;

/**
 * greeting service
 *
 * @author linux_china
 */
@Component
public class GreetingService {

    public String greet(String name) {
        return "Hello, " + name;
    }
}
