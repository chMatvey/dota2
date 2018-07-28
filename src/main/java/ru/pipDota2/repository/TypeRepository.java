package ru.pipDota2.repository;

import org.springframework.data.repository.CrudRepository;
import ru.pipDota2.domain.Type;

import java.util.Optional;

public interface TypeRepository extends CrudRepository<Type, Integer> {
    Optional<Type> findFirstByImg(String img);
}
