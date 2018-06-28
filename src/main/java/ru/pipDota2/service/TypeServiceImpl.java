package ru.pipDota2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pipDota2.domain.Type;
import ru.pipDota2.repository.TypeRepository;

@Service
public class TypeServiceImpl implements TypeService{
    private final TypeRepository repository;

    @Autowired
    public TypeServiceImpl(final TypeRepository repository){
        this.repository = repository;
    }

    @Override
    public boolean saveType(Type type) {
        return repository.save(type) != null;
    }

    @Override
    public Type getTypeById(int id) {
        return repository.findOne(id);
    }

    @Override
    public int getTypeId(String name) {
        if (name.equals("/img/icon-str.png")){
            return 1;
        } else if (name.equals("/img/icon-agi.png")){
            return 2;
        } else if (name.equals("/img/icon-int.png")){
            return 3;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
