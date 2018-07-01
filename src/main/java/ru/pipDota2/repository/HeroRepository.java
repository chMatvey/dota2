package ru.pipDota2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.pipDota2.domain.Hero;
import ru.pipDota2.domain.Type;

import java.util.List;

public interface HeroRepository extends CrudRepository<Hero, Integer>{
    @Override
    <S extends Hero> Iterable<S> save(Iterable<S> iterable);

    @Query(value = "SELECT * FROM heroes LIMIT ?1 OFFSET ?2", nativeQuery = true)
    Iterable<Hero> findAllLimitOffset(int limit, int offset);

    @Query(value = "SELECT * FROM heroes INNER JOIN types t ON heroes.type_id = t.id " +
            "WHERE heroes.type_id = ?1 LIMIT ?2 OFFSET ?3", nativeQuery = true)
    Iterable<Hero> findAllByType(int typeId, int limit, int offset);

    @Query(value = "SELECT * FROM heroes WHERE NAME LIKE ?1 LIMIT ?2 OFFSET ?3", nativeQuery = true)
    Iterable<Hero> findAllByNameLike(String name, int limit, int offset);
}
