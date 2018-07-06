package ru.pipDota2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;

@Data
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor
@Entity(name = "users")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(unique = true)
    private String username;

    @NonNull
    @JsonIgnore
    private String password;

    @NonNull
    @ElementCollection(targetClass = Role.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role")
    @Column(name = "role")
    private List<Role> authorities;

    @NonNull
    @JsonIgnore
    private boolean accountNonExpired;

    @NonNull
    @JsonIgnore
    private boolean accountNonLocked;

    @NonNull
    @JsonIgnore
    private boolean credentialsNonExpired;

    @NonNull
    @JsonIgnore
    private boolean enabled;
}
