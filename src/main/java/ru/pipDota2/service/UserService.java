package ru.pipDota2.service;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.pipDota2.domain.Role;
import ru.pipDota2.domain.User;
import ru.pipDota2.repository.UserRepository;
import ru.pipDota2.web.forms.Error;

import javax.annotation.PostConstruct;
import javax.jws.soap.SOAPBinding;
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
                    Role.USER
            ));
        }
        if (!repository.findByUsername("admin").isPresent()){
            repository.save(User.of(
                    "admin",
                     new BCryptPasswordEncoder().encode("admin!"),
                    Role.ADMIN
            ));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("user" + username + " was not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                ImmutableList.of(user.getRole())
        );
    }

    public Optional<User> findUserByName(String username){
        return repository.findByUsername(username);
    }

    public Optional<User> getUserById(int id){
        return (repository.findById(id));
    }

    public boolean createNewUser(String username, String password){
        if(findUserByName(username).isPresent()){
            return false;
        } else {
            repository.save(User.of(username, new BCryptPasswordEncoder().encode(password), Role.USER));
            return true;
        }
    }

    public String checkAdmin() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
        if (!user.getAuthorities().contains(Role.ADMIN)){
            throw new Exception("Недостаточно прав!");
        }
        return user.getUsername();
    }
}
