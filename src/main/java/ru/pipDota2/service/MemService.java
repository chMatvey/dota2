package ru.pipDota2.service;

import org.springframework.stereotype.Service;
import ru.pipDota2.domain.Mem;

@Service
public interface MemService {
    Iterable<Mem> getAllMem();

    Mem saveMem(Mem mem);

    Mem getMemById(int id);
}
