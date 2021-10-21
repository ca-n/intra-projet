package com.example.intra.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Guess {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private int guess;
    private int actual;
}
