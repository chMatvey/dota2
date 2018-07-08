package ru.pipDota2.service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pipDota2.domain.Article;
import ru.pipDota2.domain.User;
import ru.pipDota2.repository.ArticleRepository;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository repository;

    @Autowired
    public ArticleServiceImpl(final ArticleRepository repository){
        this.repository = repository;
    }

    @Override
    public Iterable<Article> getArticlesLimitOffset(int limit, int offset) {
        return repository.findAllLimitOffset(limit, offset);
    }

    @Override
    public Iterable<Article> getArticlesByTitleLike(String title, int limit, int offset) {
        return repository.findAllByNameLike(title + "%", limit, offset);
    }

    @Override
    public boolean deleteArticleByUser(int id, User user) {
        Article article = repository.findOne(id);
        if (article == null){
            return false;
        }
//        if (article.getUser().getId() != user.getId()) {
//            return false;
//        } else {
//            article.setUser(null);
//            repository.save(article);
//            repository.delete(id);
//            return true;
//        }
        article.setUser(null);
        repository.save(article);
        repository.delete(id);
        return true;
    }

    @Override
    public boolean saveArticle(@NonNull String title, @NonNull String content, User user) {
        Article article = Article.of(title, content);
        article.setUser(user);
        repository.save(article);
        return true;
    }
}
