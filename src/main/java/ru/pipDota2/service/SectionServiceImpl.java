package ru.pipDota2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pipDota2.domain.Section;
import ru.pipDota2.repository.SectionRepository;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {
    private final SectionRepository repository;

    @Autowired
    public SectionServiceImpl(final SectionRepository repository){
        this.repository = repository;
    }

    @Override
    public boolean saveSections(Iterable<Section> sections) {
        return repository.save(sections) != null;
    }

    @Override
    public Iterable<Section> getAllSections() {
        return repository.findAll();
    }
}
