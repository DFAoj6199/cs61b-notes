package com.gzhu.domain;

public abstract class Consumable {
    public String name;
    public int num;
    public String T;

    public Consumable() {
    }

    public Consumable(String name, int num,String T) {
        this.name = name;
        this.T=T;
        this.num = num;
    }

    public abstract void use(HeroCharacter hc);

    public void showInfo(){
        System.out.println(name+"：+"+num+T+"P");
    }

    public void afterUseShow(){
        System.out.println("你使用了 "+name+" ，增加了"+num+T+"P");
    }
}
