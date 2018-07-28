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
    public Type getTypeById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Type getTypeByImg(String img) {
        return repository.findFirstByImg(img).orElse(null);
    }

    @Override
    public Type saveType(Type type) {
        return repository.save(type);
    }
}
