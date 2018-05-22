package com.example.activiti.demo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Person implements Serializable {
    private static final long serialVersionUID = 9058110335651086815L;
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Date birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Wife wife;

    public Person(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
}