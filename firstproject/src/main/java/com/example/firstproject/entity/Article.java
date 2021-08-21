package com.example.firstproject.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;

@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Article {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @GeneratedValue
    private Long Id;

    @Column
    private String title;

    @Column
    private String content;

}
