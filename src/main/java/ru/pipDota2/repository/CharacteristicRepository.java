package ru.pipDota2.repository;

import org.springframework.data.repository.CrudRepository;
import ru.pipDota2.domain.Characteristic;
import ru.pipDota2.domain.Hero;

import java.util.Optional;

public interface CharacteristicRepository extends CrudRepository<Characteristic, Integer> {
    Optional<Characteristic> findFirstByHeroEquals(Hero hero);
}
