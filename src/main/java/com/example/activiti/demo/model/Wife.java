package com.example.activiti.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Wife {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;
}
