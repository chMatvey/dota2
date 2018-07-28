package ru.pipDota2.service;

import ru.pipDota2.domain.Type;

public interface TypeService {
    public Type getTypeById(int id);

    public Type getTypeByImg(String img);

    public Type saveType(Type type);
}
