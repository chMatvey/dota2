package ru.pipDota2.domain;

import lombok.Data;

@Data
public class Skill {
    private int id;
    private String name;
    private String description;
    private String cooldown;
    private String mana;
}
