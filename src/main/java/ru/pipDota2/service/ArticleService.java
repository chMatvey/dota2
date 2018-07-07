package ru.pipDota2.service;

import ru.pipDota2.domain.Article;
import ru.pipDota2.domain.User;

public interface ArticleService {
    public Iterable<Article> getArticlesLimitOffset(int limit, int offset);

    public Iterable<Article> getArticlesByTitleLike(String title, int limit, int offset);

    public boolean deleteArticleByUser(int id, User user);

    public boolean saveArticle(String title, String content, User user);
}
