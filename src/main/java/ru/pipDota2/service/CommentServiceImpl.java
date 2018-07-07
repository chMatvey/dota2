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
        Comment comment = repository.findOne(id);
        if (comment != null){
            comment.setUser(null);
            repository.save(comment);
            repository.delete(id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Comment findById(int id) {
        return repository.findOne(id);
    }

    @Override
    public boolean saveComment(Comment comment) {
        return repository.save(comment) != null;
    }
}
