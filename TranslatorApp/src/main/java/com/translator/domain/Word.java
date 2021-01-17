package com.translator.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WordsTable {

    @Id
    @GeneratedValue
    private Long id;
    private Language language;
    private String word;
    private Long wordId;
}
