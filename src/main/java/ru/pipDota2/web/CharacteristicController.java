package ru.pipDota2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.pipDota2.domain.Characteristic;
import ru.pipDota2.domain.Comment;
import ru.pipDota2.repository.CommentRepository;
import ru.pipDota2.service.CharacteristicServiceImpl;
import ru.pipDota2.service.CommentServiceImpl;
import ru.pipDota2.service.HeroServiceImpl;
import ru.pipDota2.service.UserService;
import ru.pipDota2.web.forms.Error;
import ru.pipDota2.web.forms.Result;
import ru.pipDota2.web.forms.Success;

@RestController
public class CharacteristicController {
    private final CharacteristicServiceImpl characteristicService;
    private final HeroServiceImpl heroService;
    private final CommentServiceImpl commentService;
    private final UserService userService;

    @Autowired
    public CharacteristicController(final CharacteristicServiceImpl characteristicService,
                                    final HeroServiceImpl heroService,
                                    final CommentServiceImpl commentService,
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
    public Result deleteCommentToCharacteristic(@RequestParam("comment_id") int commentId){
        if (commentService.deleteComment(commentId)){
            return new Success<String>("This comment was successfully deleted");
        } else {
            return new Error("Comment with id: " + commentId + " doesn't exist");
        }
    }
}
