package com.uyghurjava.helloworld.controller;

import com.uyghurjava.helloworld.model.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final AtomicLong counterCall = new AtomicLong();

    /**
     * The @GetMapping annotation ensures that HTTP GET requests to "/greeting" are mapped to the greeting() method. @RequestMapping(method=GET)
     * @param name username, binds the value of the query string parameter name into the name parameter of the greeting() method. If the name parameter is absent in the request, the defaultValue of World is used.
     * @return Greeting with greeting message
     */
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        logger.info("greeting() method is called the %dth time ".formatted(counterCall.incrementAndGet()));
        return new Greeting(counter.incrementAndGet(), template.formatted(name));
    }
}
