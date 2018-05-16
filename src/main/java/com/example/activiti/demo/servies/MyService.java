package com.example.activiti.demo.servies;

import com.example.activiti.demo.model.Person;
import com.example.activiti.demo.repo.PersonRepository;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class MyService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private MyListener myListener;

    public String startProcess(String assignee) {
        Person person = personRepository.findByName(assignee);
        Person person2 = personRepository.findByName("David");

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("person", person);
        variables.put("timePerson", person2);
        variables.put("duration", "PT1M");
        variables.put("listenerName", myListener);
        runtimeService.startProcessInstanceByKey("testProcess2", variables);

        return processInfo();
    }

    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    public void completeTask(String taskId) {
        taskService.complete(taskId);
    }

    public void createPersons() {
        if (personRepository.findAll().size() == 0) {
            personRepository.save(new Person("John", new Date()));
            personRepository.save(new Person("David", new Date()));
            personRepository.save(new Person("Katherin", new Date()));
        }
    }

    private String processInfo() {
        List<Task> tasks = taskService.createTaskQuery().orderByTaskCreateTime().asc().list();

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Number of process definitions : "
                + repositoryService.createProcessDefinitionQuery().count() + "--> Tasks >> ");

        for (Task task : tasks) {
            stringBuilder
                    .append(task + " | Assignee: " + task.getAssignee() + " | Description: " + task.getDescription());
        }

        return stringBuilder.toString();
    }
}