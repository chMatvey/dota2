package ru.pipDota2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.pipDota2.domain.Hero;
import ru.pipDota2.service.AttackServiceImpl;
import ru.pipDota2.service.HeroServiceImpl;
import ru.pipDota2.service.TypeServiceImpl;

import java.util.List;

@RestController
public class HeroController {
    private final HeroServiceImpl heroService;

    @Autowired
    public HeroController(final HeroServiceImpl heroService){
        this.heroService = heroService;
    }

    @GetMapping("/get/heroes")
    public List<Hero> getHeroes(@RequestParam("limit") int limit,
                                @RequestParam("offset") int offset){
        return heroService.getHeroesLimitOffset(limit, offset);
    }
}
