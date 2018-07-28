package ru.pipDota2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.pipDota2.domain.Article;
import ru.pipDota2.domain.Role;
import ru.pipDota2.service.ArticleService;
import ru.pipDota2.service.UserService;
import ru.pipDota2.web.forms.Error;
import ru.pipDota2.web.forms.Result;
import ru.pipDota2.web.forms.Success;

@RestController
public class ArticleController {
    private final ArticleService articleService;
    private final UserService userService;

    @Autowired
    public  ArticleController(final  ArticleService articleService,
                              final UserService userService){
        this.articleService = articleService;
        this.userService = userService;
    }

    @GetMapping("/get/articles")
    public Iterable<Article> getArticles(@RequestParam("limit")int limit,
                                         @RequestParam("offset")int offset){
        return articleService.getArticlesLimitOffset(limit, offset);
    }

    @GetMapping("/get/articles/like")
    public Iterable<Article> getArticlesLike(@RequestParam("title")String title,
                                             @RequestParam("limit")int limit,
                                             @RequestParam("offset")int offset){
        return articleService.getArticlesByTitleLike(title, limit, offset);
    }

    @GetMapping("/delete/article")
    @PreAuthorize("hasRole('ADMIN')")
    public Result deleteArticle(@RequestParam("article_id") int articleId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            org.springframework.security.core.userdetails.User user =
                    (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
            if (!user.getAuthorities().contains(Role.ADMIN)){
                return new Error("Недостаточно прав");
            }

            String username = user.getUsername();
            if (articleService.deleteArticleByUser(articleId,
                    userService.findUserByName(username).orElseThrow(() ->
                    new UsernameNotFoundException("user " + username + " was not found")))){

                return new Success<String>("Success");
            } else {
                return new Error("This article does not belong to the user");
            }
        } catch (Exception e){
            return new Error(e.getMessage());
        }
    }

    @GetMapping("/save/article")
    @PreAuthorize("hasRole('ADMIN')")
    public Result SaveArticle(@RequestParam("title")String title,
                               @RequestParam("content")String content){
        try {
            String username = userService.checkAdmin();
            if(articleService.saveArticle(title, content,
                    userService.findUserByName(username).orElseThrow(() ->
                            new UsernameNotFoundException("user " + username + " was not found")))){
                return new Success<String>("Article was successfully loaded");
            } else{
                return new Error("Article wasn't loaded");
            }
        }
        catch (Exception e){
            return new Error(e.getMessage());
        }
    }
}
