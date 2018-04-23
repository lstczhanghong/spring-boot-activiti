package com.example.activiti.demo;

import com.example.activiti.demo.servies.MyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ActivitiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(final MyService myService) {
        return new CommandLineRunner() {
            public void run(String... strings) throws Exception {
                myService.createPersons();
            }
        };
    }
}
