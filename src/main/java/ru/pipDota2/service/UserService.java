package ru.pipDota2.service;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.pipDota2.domain.Role;
import ru.pipDota2.domain.User;
import ru.pipDota2.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService{
    private final UserRepository repository;

    @Autowired
    public UserService(final UserRepository repository){
        this.repository = repository;
    }

    @PostConstruct
    public void init(){
        if (!repository.findByUsername("user").isPresent()){
            repository.save(User.of(
                    "user",
                    new BCryptPasswordEncoder().encode("user"),
                    ImmutableList.of(Role.USER),
                    true,
                    true,
                    true,
                    true
            ));
        }
        if (!repository.findByUsername("admin").isPresent()){
            repository.save(User.of(
                    "admin",
                     new BCryptPasswordEncoder().encode("admin!"),
                    ImmutableList.of(Role.ADMIN),
                    true,
                    true,
                    true,
                    true
            ));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(() ->
        new UsernameNotFoundException("user" + username + " was not found"));
    }

    public Optional<User> getUserById(int id){
        return Optional.of(repository.findOne(id));
    }
}
