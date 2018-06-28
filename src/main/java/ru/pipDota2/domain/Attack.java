package ru.pipDota2.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor
@Entity(name = "attacks")
public class Attack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "attacks")
    private List<Hero> heroes;
}
