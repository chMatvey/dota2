package ru.pipDota2.domain;

import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor
@Entity(name = "heroes")
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String name;

    @NonNull
    private String role;

    @NonNull
    private String img;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "attack_id")
    private Attack attack;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;
}
