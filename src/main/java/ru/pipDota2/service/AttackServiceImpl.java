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
    public boolean saveAttack(Attack attack) {
        return repository.save(attack) != null;
    }

    @Override
    public Attack getAttackById(int id) {
        return repository.findOne(id);
    }

    @Override
    public int getIdAttack(String name) {
        if (name.equals("melee")){
            return 1;
        } else if(name.equals("range")){
            return 2;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
