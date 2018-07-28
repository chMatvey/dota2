package ru.pipDota2.service;

import ru.pipDota2.domain.Attack;

public interface AttackService {
    public Attack saveAttack(Attack attack);

    public Attack getAttackById(int id);

    public Attack getAttackByName(String name);
}
