package com.example.activiti.demo.servies;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

@Service
public class MyListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println(delegateTask);
    }
}
