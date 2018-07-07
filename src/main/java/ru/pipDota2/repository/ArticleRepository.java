package ru.pipDota2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.pipDota2.domain.Article;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
    @Query(value = "SELECT * FROM articles LIMIT ?1 OFFSET ?2", nativeQuery = true)
    Iterable<Article> findAllLimitOffset(int limit, int offset);

    @Query(value = "SELECT * FROM articles WHERE title LIKE ?1 LIMIT ?2 OFFSET ?3", nativeQuery = true)
    Iterable<Article> findAllByNameLike(String title, int limit, int offset);
}
