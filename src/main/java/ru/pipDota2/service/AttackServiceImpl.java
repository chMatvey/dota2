package ru.pipDota2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pipDota2.domain.Attack;
import ru.pipDota2.repository.AttackRepository;

@Service
public class AttackServiceImpl implements AttackService {
    private final AttackRepository repository;

    @Autowired
    public AttackServiceImpl(final AttackRepository repository){
        this.repository = repository;
    }

    @Override
    public Attack saveAttack(Attack attack) {
        return repository.save(attack);
    }

    @Override
    public Attack getAttackById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Attack getAttackByName(String name) {
        return repository.findFirstByName(name).orElse(null);
    }
}
