package ru.pipDota2.service;

import ru.pipDota2.domain.Hero;
import ru.pipDota2.domain.Type;

import java.util.List;

public interface HeroService {
    public boolean saveHero(Iterable<Hero> heroes);

    public Iterable<Hero> getHeroesLimitOffset(int limit, int offset);

    public Iterable<Hero> getHeroesByTypeLimitOffset(int typeId, int limit, int offset);

    public Iterable<Hero> getHeroesByNameLike(String name, int limit, int offset);

    public Hero getHeroById(int id);
}
