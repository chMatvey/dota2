package ru.pipDota2.repository;

import org.springframework.data.repository.CrudRepository;
import ru.pipDota2.domain.Attack;

public interface AttackRepository extends CrudRepository<Attack, Integer> {
    Attack findFirstByName(String name);
}
