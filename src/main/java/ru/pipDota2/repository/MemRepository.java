package ru.pipDota2.repository;

import org.springframework.data.repository.CrudRepository;
import ru.pipDota2.domain.Mem;

public interface MemRepository extends CrudRepository<Mem, Integer> {
}
