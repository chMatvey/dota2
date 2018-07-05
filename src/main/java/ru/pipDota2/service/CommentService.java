package ru.pipDota2.service;

import ru.pipDota2.domain.Comment;

public interface CommentService{
    void deleteComment(int id);

    Comment findById(int id);
}
