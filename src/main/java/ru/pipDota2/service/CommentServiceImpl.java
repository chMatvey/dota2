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
    public boolean deleteComment(int id) {
        Comment comment = repository.findById(id).orElse(null);
        if (comment == null){
            return false;
        }
        comment.setUser(null);
        repository.save(comment);
        repository.deleteById(id);
        return true;
    }

    @Override
    public Comment findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Comment saveComment(Comment comment) {
        return repository.save(comment);
    }
}
