package ru.pipDota2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.pipDota2.domain.Hero;

import java.util.List;

public interface HeroRepository extends CrudRepository<Hero, Integer>{
    @Override
    <S extends Hero> Iterable<S> save(Iterable<S> iterable);

    @Override
    Iterable<Hero> findAll();

    @Query(value = "SELECT * FROM heroes LIMIT ?1 OFFSET ?2", nativeQuery = true)
    public List<Hero> findAllLimitOffset(int limit, int offset);
}
