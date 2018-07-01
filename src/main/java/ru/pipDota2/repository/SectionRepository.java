package ru.pipDota2.repository;

import org.springframework.data.repository.CrudRepository;
import ru.pipDota2.domain.Section;

public interface SectionRepository extends CrudRepository<Section, Integer> {
    @Override
    <S extends Section> Iterable<S> save(Iterable<S> iterable);

    @Override
    Iterable<Section> findAll();
}
