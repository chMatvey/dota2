package ru.pipDota2.service;

import ru.pipDota2.domain.Type;

public interface TypeService {
    public boolean saveType(Type type);

    public Type getTypeById(int id);

    public int getTypeId(String name);
}
