package ru.pipDota2.domain;

import lombok.Data;

@Data
public class Subject {
    private int id;
    private String description;
    private String type;
    private int parenId;
}
