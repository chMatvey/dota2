package ru.pipDota2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pipDota2.domain.Subject;
import ru.pipDota2.repository.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService{
    private final SubjectRepository repository;

    @Autowired
    public SubjectServiceImpl(final SubjectRepository repository){
        this.repository = repository;
    }

    @Override
    public Subject getSubjectById(int id) {
        return repository.findOne(id);
    }
}
