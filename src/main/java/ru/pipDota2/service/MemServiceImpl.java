package ru.pipDota2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pipDota2.domain.Mem;
import ru.pipDota2.repository.MemRepository;

@Service
public class MemServiceImpl implements MemService {
    private final MemRepository repository;

    @Autowired
    public MemServiceImpl(final MemRepository repository){
        this.repository = repository;
    }

    @Override
    public Iterable<Mem> getAllMem() {
        return null;
    }

    @Override
    public Mem saveMem(Mem mem) {
        return repository.save(mem);
    }

    @Override
    public Mem getMemById(int id) {
        return repository.findOne(id);
    }
}
