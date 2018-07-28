package ru.pipDota2.repository;

import org.springframework.data.repository.CrudRepository;
import ru.pipDota2.domain.Attack;

import java.util.Optional;

public interface AttackRepository extends CrudRepository<Attack, Integer> {
    Optional<Attack> findFirstByName(String name);
}
