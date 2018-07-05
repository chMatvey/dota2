package ru.pipDota2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pipDota2.domain.Comment;
import ru.pipDota2.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;

    @Autowired
    public CommentServiceImpl(final CommentRepository repository){
        this.repository = repository;
    }

    @Override
    public void deleteComment(int id) {
        repository.delete(id);
    }

    @Override
    public Comment findById(int id) {
        return repository.findOne(id);
    }
}
