package ru.pipDota2.domain;

import lombok.Data;

@Data
public class Hero {
    private int id;
    private String name;
    private String power;
    private String agility;
    private String intelligence;
    private String damage;
    private String protection;
    private String speed;
}
