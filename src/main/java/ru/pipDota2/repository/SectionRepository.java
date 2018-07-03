package ru.pipDota2.repository;

import org.springframework.data.repository.CrudRepository;
import ru.pipDota2.domain.Section;

public interface SectionRepository extends CrudRepository<Section, Integer> {
}
