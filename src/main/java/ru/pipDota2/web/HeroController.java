package ru.pipDota2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.pipDota2.domain.Hero;
import ru.pipDota2.service.HeroService;

@RestController
public class HeroController {
    private final HeroService heroService;

    @Autowired
    public HeroController(final HeroService heroService){
        this.heroService = heroService;
    }

    @GetMapping("/get/heroes")
    public Iterable<Hero> getHeroes(@RequestParam("limit") int limit,
                                    @RequestParam("offset") int offset){
        return heroService.getHeroesLimitOffset(limit, offset);
    }

    @GetMapping("/get/heroes/type")
    public Iterable<Hero> getHeroesByTypeId(@RequestParam("type_id") int id,
                                            @RequestParam("limit") int limit,
                                            @RequestParam("offset") int offset){
        return heroService.getHeroesByTypeLimitOffset(id, limit, offset);
    }

    @GetMapping("/get/heroes/like")
    public Iterable<Hero> getHeroesByNameLike(@RequestParam("name") String name,
                                              @RequestParam("limit") int limit,
                                              @RequestParam("offset") int offset){
        return heroService.getHeroesByNameLike(name, limit, offset);
    }
}
