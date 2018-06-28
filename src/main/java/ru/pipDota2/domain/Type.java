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
@Entity(name = "types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String img;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "types")
    private List<Hero> heroes;
}
