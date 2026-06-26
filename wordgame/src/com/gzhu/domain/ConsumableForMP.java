package com.gzhu.domain;

public class ConsumableForMP extends Consumable{
    public ConsumableForMP() {
    }

    public ConsumableForMP(String name, int num, String T) {
        super(name, num,T);
    }

    @Override
    public void use(HeroCharacter hc){
        hc.addMP(num);
    }


}
