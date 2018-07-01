package ru.pipDota2.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor
@Entity(name = "Subject")
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String name;

    @NonNull
    private String img;

    @NonNull
    private String price;

    @NonNull
    @Column(length = 1000)
    private String description;

    @NonNull
    private String history;

//    @NonNull
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "section_id")
//    private Section section;
}
