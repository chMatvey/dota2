package ru.pipDota2.service;

import ru.pipDota2.domain.Characteristic;
import ru.pipDota2.domain.Comment;
import ru.pipDota2.domain.Hero;

public interface CharacteristicService {
    public boolean saveCharacteristics(Characteristic characteristics);

    public Characteristic getByHero(Hero hero);

    public Characteristic getById(int id);
}
