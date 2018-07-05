package ru.pipDota2.web;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
public class CharacteristicController {
    private final CharacteristicServiceImpl characteristicService;
    private final HeroServiceImpl heroService;
    private final CommentServiceImpl commentService;

    @Autowired
    public CharacteristicController(final CharacteristicServiceImpl characteristicService,
                                    final HeroServiceImpl heroService,
                                    final CommentServiceImpl commentService){
        this.characteristicService = characteristicService;
        this.heroService = heroService;
        this.commentService = commentService;
    }

    @GetMapping("/get/characteristic")
    public Characteristic getCharacteristicByHeroId(@RequestParam("hero_id") int heroId){
        return characteristicService.getByHero(heroService.getHeroById(heroId));
    }

    @GetMapping("/add/comment")
    public Characteristic addCommentToCharacteristic(@RequestParam("charact_id") int charactId,
                                                     @RequestParam("content") String content){
        Characteristic characteristic = characteristicService.getById(charactId);
        characteristic.addComment(Comment.of(content));
        characteristicService.saveCharacteristics(characteristic);
        return characteristic;
    }

    @GetMapping("/delete/comment")
    public Characteristic deleteCommentToCharacteristic(@RequestParam("charact_id") int charactId,
                                                     @RequestParam("comment_id") int commentId){
        Characteristic characteristic = characteristicService.getById(charactId);
        characteristic.removeComment(commentService.findById(commentId));
        characteristicService.saveCharacteristics(characteristic);
        return characteristic;
    }
}
