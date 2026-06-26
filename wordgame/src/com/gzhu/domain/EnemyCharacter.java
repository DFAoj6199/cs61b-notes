package com.gzhu.domain;

public class EnemyCharacter extends Character{
    public String skill;
    public boolean defending;

    public EnemyCharacter() {
        defending=false;
    }

    public EnemyCharacter(String name, int HP, int attack, int defense, String skill) {
        super(name, HP, attack, defense);
        this.skill = skill;
        defending = false;
    }

    public EnemyCharacter(EnemyCharacter other){
        super(other.name,other.HP,other.attack,other.defense);
        this.skill=other.skill;
        this.defending=false;
    }

    @Override
    public void damage(int att){
        if(defending){
            att=att/2>0?att/2:1;
            defending=false;
        }
        super.damage(att);
    }
}
