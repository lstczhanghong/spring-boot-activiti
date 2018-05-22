package com.example.activiti.demo.servies;

import com.example.activiti.demo.model.Person;

public interface PersionService {
    Person save(Person person);

    void delete(Long id);
}
