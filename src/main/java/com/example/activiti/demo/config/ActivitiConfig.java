package com.example.activiti.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.DeploymentBuilder;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableConfigurationProperties(ActivitiProperties.class)
@Slf4j
public class ActivitiConfig {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private ResourcePatternResolver resourceLoader;
    @Autowired
    private ActivitiProperties properties;

    @Bean
    public StandaloneProcessEngineConfiguration processEngineConfiguration() {
        StandaloneProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        configuration.setAsyncExecutorActivate(true);
        return configuration;
    }

    @Bean
    public ProcessEngine processEngine() {
        return processEngineConfiguration().buildProcessEngine();
    }

    @Bean
    public RepositoryService repositoryService() {
        return processEngine().getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService() {
        return processEngine().getRuntimeService();
    }

    @Bean
    public TaskService taskService() {
        return processEngine().getTaskService();
    }

    /**
     * 部署流程
     */
    @PostConstruct
    public void initProcess() throws IOException {
        DeploymentBuilder builder = repositoryService().createDeployment().enableDuplicateFiltering().name("activitiAutoDeploy");
        List<Resource> resourceList = new ArrayList<>();
        //读资源
        for (String suffix : properties.getProcessDefinitionLocationSuffixes()) {
            Resource[] resources = resourceLoader.getResources(properties.getProcessDefinitionLocationPrefix() + suffix);
            resourceList.addAll(Arrays.asList(resources));
        }

        if (CollectionUtils.isEmpty(resourceList)) {
            log.info("there no process file to deploy");
           return;
        }
        for (Resource resource : resourceList) {
            builder.addInputStream(resource.getFilename(), resource.getInputStream());
        }
        builder.deploy();
        log.info("deploy success");
    }


}
