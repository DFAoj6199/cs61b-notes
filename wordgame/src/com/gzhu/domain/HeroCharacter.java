package com.gzhu.domain;

import java.util.ArrayList;

public class HeroCharacter extends Character{
    public int MP;
    public int maxMP;
    public ArrayList<String> skillList;

    public HeroCharacter() {
        skillList=new ArrayList<>();
    }

    public HeroCharacter(String name, int HP, int attack, int defense,int MP) {
        super(name, HP, attack, defense);
        this.MP=MP;
        this.maxMP=MP;
        skillList=new ArrayList<>();
    }

    public void addMP(int cnt){
        if(MP+cnt>maxMP)MP=maxMP;
        else MP+=cnt;
    }

    public void takeMP(int cnt){
        if(MP-cnt<1){
            MP=0;
        }else MP-=cnt;
    }

    public void showSkill(){
        System.out.println("\uD83C\uDF1F 拥有技能：");
        for (int i = 0; i < skillList.size(); i++) {
            System.out.print(skillList.get(i));
            if(i!=skillList.size()-1){
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    @Override
    public void showInfo(){
        System.out.println("\uD83C\uDF1F "+name+"属性：[HP:"+HP+"/"+maxHP+",MP:"+MP+"/"+maxMP+",ATK:"+attack+",DEF:"+defense+"]");
    }
}
