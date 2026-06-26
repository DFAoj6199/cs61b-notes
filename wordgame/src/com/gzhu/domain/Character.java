package com.gzhu.domain;

public class Character {
    public String name;
    public int HP;
    public int maxHP;
    public int attack;
    public int defense;

    public Character() {
    }

    public Character(String name, int HP, int attack, int defense) {
        this.name = name;
        this.HP = HP;
        this.maxHP = HP;
        this.attack = attack;
        this.defense = defense;
    }

    public boolean isAlive() {
        return HP > 0;
    }

    //回血
    public void heal(int getHP) {
        HP += getHP;
        if (HP > maxHP) HP = maxHP;
    }

    //扣血
    public void damage(int amount) {
        HP -= amount;
        if (HP < 0) HP = 0;
    }

    //展示信息
    public void showInfo(){
        System.out.println("\uD83C\uDF1F "+name+"属性：[HP:"+HP+"/"+maxHP+",ATK:"+attack+",DEF:"+defense+"]");
    }
}
