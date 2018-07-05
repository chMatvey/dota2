package ru.pipDota2.service;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.pipDota2.domain.Role;
import ru.pipDota2.domain.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Role> list = new ArrayList<Role>();
        return User.builder()
                .username("user")
                .password("user")
                .authorities(ImmutableList.of(Role.USER))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }
}
