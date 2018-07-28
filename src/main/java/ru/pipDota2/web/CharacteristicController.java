package ru.pipDota2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.pipDota2.domain.Characteristic;
import ru.pipDota2.domain.Comment;
import ru.pipDota2.domain.Role;
import ru.pipDota2.service.CharacteristicService;
import ru.pipDota2.service.CommentService;
import ru.pipDota2.service.HeroService;
import ru.pipDota2.service.UserService;
import ru.pipDota2.web.forms.Error;
import ru.pipDota2.web.forms.Result;
import ru.pipDota2.web.forms.Success;

@RestController
public class CharacteristicController {
    private final CharacteristicService characteristicService;
    private final HeroService heroService;
    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public CharacteristicController(final CharacteristicService characteristicService,
                                    final HeroService heroService,
                                    final CommentService commentService,
                                    final UserService userService){
        this.characteristicService = characteristicService;
        this.heroService = heroService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/get/characteristic")
    public Characteristic getCharacteristicByHeroId(@RequestParam("hero_id") int heroId){
        return characteristicService.getByHero(heroService.getHeroById(heroId));
    }

    @GetMapping("/add/comment")
    public Characteristic addCommentToCharacteristic(@RequestParam("charact_id") int charactId,
                                                     @RequestParam("content") String content){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
        String username = user.getUsername();

        Characteristic characteristic = characteristicService.getById(charactId);
        Comment comment = Comment.of(content);
        comment.setUser(userService.findUserByName(username).orElseThrow(() ->
                new UsernameNotFoundException("user: " + username + " was not found")));
        characteristic.addComment(comment);
        characteristicService.saveCharacteristics(characteristic);
        return characteristic;
    }

    @GetMapping("/delete/comment")
    @PreAuthorize("hasRole('ADMIN')")
    public Result deleteCommentToCharacteristic(@RequestParam("comment_id") int commentId){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            org.springframework.security.core.userdetails.User user =
                    (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
            if (!user.getAuthorities().contains(Role.ADMIN)){
                return new Error("Недостаточно прав");
            }

            if (commentService.deleteComment(commentId)){
                return new Success<String>("This comment was successfully deleted");
            } else {
                return new Error("Comment with id: " + commentId + " doesn't exist");
            }
        } catch (Exception e){
            return new Error(e.getMessage());
        }
    }
}
