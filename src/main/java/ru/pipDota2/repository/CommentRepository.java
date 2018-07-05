package ru.pipDota2.repository;

import org.springframework.data.repository.CrudRepository;
import ru.pipDota2.domain.Comment;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
