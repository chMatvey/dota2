package ru.pipDota2.repository;

import org.springframework.data.repository.CrudRepository;
import ru.pipDota2.domain.Type;

public interface TypeRepository extends CrudRepository<Type, Integer> {
    @Override
    <S extends Type> S save(S type);

    @Override
    Type findOne(Integer id);

    Type findFirstByImg(String img);
}
