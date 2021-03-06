package ru.pipDota2.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Characteristics")
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@Table(name = "characteristics")
public class Characteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String img;

    @NonNull
    @Column(length = 10000)
    private String bio;

    @NonNull
    private String video;

    @NonNull
    private String power;

    @NonNull
    private String agility;

    @NonNull
    private String intelligence;

    @NonNull
    private String damage;

    @NonNull
    private String defence;

    @NonNull
    private String speed;

    @NonNull
    private String ability1;

    @NonNull
    private String ability2;

    @NonNull
    private String ability3;

    @NonNull
    private String ability4;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "stat_id")
    private Stat stat;

    @NonNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "characteristic_id")
    private List<Skill> skill;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "hero_id")
    private Hero hero;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "characteristic_id")
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment){
        this.comments.add(comment);
        //comment.setCharacteristic(this);
    }

    public void removeComment(Comment comment){
        this.comments.remove(comment);
        //comment.setCharacteristic(null);
    }
}
