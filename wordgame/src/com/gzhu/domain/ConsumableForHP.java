package com.gzhu.domain;

public class ConsumableForHP extends Consumable{
    public ConsumableForHP() {
    }

    public ConsumableForHP(String name, int num, String T) {
        super(name, num, T);
    }


    @Override
    public void use(HeroCharacter hc){
        hc.heal(num);
    }


}
