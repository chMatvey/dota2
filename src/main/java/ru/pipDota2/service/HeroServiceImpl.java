package ru.pipDota2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pipDota2.domain.Hero;
import ru.pipDota2.repository.HeroRepository;

import java.util.List;

@Service
public class HeroServiceImpl implements HeroService{
    private final HeroRepository repository;

    @Autowired
    public HeroServiceImpl(final HeroRepository repository){
        this.repository = repository;
    }

    @Override
    public boolean saveHero(Iterable<Hero> heroes){
        return repository.save(heroes) != null;
    }

    @Override
    public Iterable<Hero> getHeroes() {
        return repository.findAll();
    }

    @Override
    public List<Hero> getHeroesLimitOffset(int limit, int offset) {
        return repository.findAllLimitOffset(limit, offset);
    }

}
