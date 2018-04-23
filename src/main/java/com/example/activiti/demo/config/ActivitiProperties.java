package com.example.activiti.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

//@ConfigurationProperties("spring.activiti")
//@Getter
//@Setter
public class ActivitiProperties {
    private String processDefinitionLocationPrefix = "classpath:/processes/";
    private List<String> processDefinitionLocationSuffixes = Arrays.asList("**.bpmn20.xml", "**.bpmn");
}
