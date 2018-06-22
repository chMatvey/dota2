package ru.pipDota2.repository;

import org.springframework.data.repository.Repository;
import ru.pipDota2.domain.User;

public interface UserRepository extends Repository<User, Integer> {
    User findByUsername(String username);
    User save(User user);
}
