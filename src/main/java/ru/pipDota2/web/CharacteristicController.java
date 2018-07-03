package ru.pipDota2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.pipDota2.domain.Characteristic;
import ru.pipDota2.service.CharacteristicServiceImpl;
import ru.pipDota2.service.HeroServiceImpl;

@RestController
public class CharacteristicController {
    private final CharacteristicServiceImpl characteristicService;
    private final HeroServiceImpl heroService;

    @Autowired
    public CharacteristicController(final  CharacteristicServiceImpl characteristicService,
                                    final  HeroServiceImpl heroService){
        this.characteristicService = characteristicService;
        this.heroService = heroService;
    }

    @GetMapping("/get/characteristic")
    public Characteristic getCharacteristicByHeroId(@RequestParam("hero_id") int heroId){
        return characteristicService.getByHero(heroService.getHeroById(heroId));
    }
}
