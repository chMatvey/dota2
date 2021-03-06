package ru.pipDota2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pipDota2.domain.Hero;
import ru.pipDota2.domain.Type;
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
    public Iterable<Hero> saveHero(Iterable<Hero> heroes){
        return repository.saveAll(heroes);
    }

    @Override
    public Iterable<Hero> getHeroesLimitOffset(int limit, int offset) {
        return repository.findAllLimitOffset(limit, offset);
    }

    @Override
    public Iterable<Hero> getHeroesByTypeLimitOffset(int typeId, int limit, int offset) {
        return repository.findAllByType(typeId, limit, offset);
    }

    @Override
    public Iterable<Hero> getHeroesByNameLike(String name, int limit, int offset) {
        return repository.findAllByNameLike(name + "%", limit, offset);
    }

    @Override
    public Hero getHeroById(int id) {
        return repository.findById(id).orElse(null);
    }
}
