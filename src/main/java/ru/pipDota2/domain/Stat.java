package ru.pipDota2.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "stats")
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class Stat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String level;

    @NonNull
    private String damage;

    @NonNull
    private String health;

    @NonNull
    private String mana;

    @NonNull
    private String defence;

    @NonNull
    private String timeAttack;

    @NonNull
    private String attackInSecond;

    @NonNull
    private String vision;

    @NonNull
    private String distanceAttack;
}
