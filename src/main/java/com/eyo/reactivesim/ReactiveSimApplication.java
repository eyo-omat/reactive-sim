package com.eyo.reactivesim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.tools.agent.ReactorDebugAgent;

@SpringBootApplication
public class ReactiveSimApplication {

    public static void main(String[] args) {
        ReactorDebugAgent.init();
        SpringApplication.run(ReactiveSimApplication.class, args);
    }

}
