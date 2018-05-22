package com.example.activiti.demo.servies.impl;

import com.example.activiti.demo.model.Person;
import com.example.activiti.demo.model.Wife;
import com.example.activiti.demo.repo.PersonRepository;
import com.example.activiti.demo.repo.WifeRepository;
import com.example.activiti.demo.servies.PersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements PersionService {
    @Autowired
    private PersonRepository repository;
    @Autowired
    private WifeRepository wifeRepository;

    @Override
    @Transactional
    public Person save(Person person) {
        Wife wife = new Wife();
        wife.setName("dddd");
        wifeRepository.save(wife);
        person.setWife(wife);
        return repository.save(person);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
