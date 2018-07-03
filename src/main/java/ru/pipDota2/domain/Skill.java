package ru.pipDota2.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity(name = "skills")
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String name;

    @NonNull
    private String img;

    @NonNull
    @Column(length = 1000)
    private String description;
}
