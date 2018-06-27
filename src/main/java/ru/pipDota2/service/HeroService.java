package ru.pipDota2.service;

import ru.pipDota2.domain.Hero;

import java.util.List;

public interface HeroService {
    public boolean saveHero(Iterable<Hero> heroes);

    public Iterable<Hero> getHeroes();

    public List<Hero> getHeroesLimitOffset(int limit, int offset);
}
