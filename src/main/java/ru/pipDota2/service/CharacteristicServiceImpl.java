package ru.pipDota2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pipDota2.domain.Characteristic;
import ru.pipDota2.domain.Comment;
import ru.pipDota2.domain.Hero;
import ru.pipDota2.repository.CharacteristicRepository;

@Service
public class CharacteristicServiceImpl implements CharacteristicService {
    private final CharacteristicRepository repository;

    @Autowired
    public CharacteristicServiceImpl(final CharacteristicRepository repository){
        this.repository = repository;
    }

    @Override
    public Characteristic saveCharacteristics(Characteristic characteristics) {
        return repository.save(characteristics);
    }

    @Override
    public Characteristic getByHero(Hero hero) {
        return repository.findFirstByHeroEquals(hero).orElse(null);
    }

    @Override
    public Characteristic getById(int id) {
        return repository.findById(id).orElse(null);
    }
}
