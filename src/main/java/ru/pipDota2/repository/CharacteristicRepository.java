package ru.pipDota2.repository;

import org.springframework.data.repository.CrudRepository;
import ru.pipDota2.domain.Characteristic;

public interface CharacteristicRepository extends CrudRepository<Characteristic, Integer> {
}
