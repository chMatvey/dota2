package ru.pipDota2.repository;

import org.springframework.data.repository.CrudRepository;
import ru.pipDota2.domain.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Integer> {
}
