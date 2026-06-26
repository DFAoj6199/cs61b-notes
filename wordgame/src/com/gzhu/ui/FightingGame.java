package com.gzhu.ui;

import com.gzhu.domain.*;

import javax.swing.*;
import java.net.http.HttpConnectTimeoutException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FightingGame {
    public void gameStart(String user_name){
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("    \uD83C\uDFAE "+user_name+",欢迎来到文字格斗游戏 \uD83C\uDFAE   ");
        System.out.println("╚════════════════════════════════════════╝");


        HeroCharacter hc=creatCharacter(user_name);
        hc.showInfo();
        hc.skillList.add("普通攻击");hc.skillList.add("强力一击");hc.skillList.add("生命汲取");
        hc.showSkill();
        System.out.println();
        //敌人
//        | 初级战士 | 80   | 15   | 10   | 猛击（150%伤害）                                       |
//| -------- | ---- | ---- | ---- | ------------------------------------------------------ |
//                | 敏捷刺客 | 60   | 20   | 5    | 快速攻击（2次50%伤害）                                 |
//| 重装坦克 | 120  | 10   | 20   | 防御姿态（下回合伤害减半） buff（ boolean defendding） |
//| 神秘法师 | 70   | 25   | 8    | 火球术（180%伤害）                                     |
        ArrayList<EnemyCharacter> al=new ArrayList<>();
        al.add(new EnemyCharacter("初级战士",80,15,10,"猛击"));
        al.add(new EnemyCharacter("敏捷刺客",60,20,5,"快速攻击"));
        al.add(new EnemyCharacter("重装坦克",120,10,20,"防御姿态buff"));
        al.add(new EnemyCharacter("神秘法师",70,25,8,"火球术"));

        ArrayList<Consumable> consumableForUse=new ArrayList<>();
        consumableForUse.add(new ConsumableForHP("桃子",10,"H"));
        consumableForUse.add(new ConsumableForHP("煎蛋",20,"H"));
        consumableForUse.add(new ConsumableForHP("花酿鸡",30,"H"));
        consumableForUse.add(new ConsumableForHP("黑背鲈鱼",40,"H"));
        consumableForUse.add(new ConsumableForHP("白玉汤",50,"H"));
        consumableForUse.add(new ConsumableForMP("草莓茶杯蛋糕",10,"M"));
        consumableForUse.add(new ConsumableForMP("苹果派",20,"M"));
        consumableForUse.add(new ConsumableForMP("胡萝卜蛋糕",30,"M"));
        consumableForUse.add(new ConsumableForMP("普通炖汤",40,"M"));
        consumableForUse.add(new ConsumableForMP("暖心浓汤",50,"M"));

        ArrayList<Consumable> bag=new ArrayList<>();

        System.out.println("敌人列表：");
        for (EnemyCharacter character : al) {
            character.showInfo();
        }
        
        int count=0;
        int win=0;
        Random r=new Random();
        System.out.println();
        while(hc.isAlive()){
            if(win!=0){
                System.out.println("═══════════════════════════════════════");
                System.out.println("继续下一场战斗（y/n，输入其他默认为n）？：");
                Scanner sc=new Scanner(System.in);
                String choose=sc.next();
                System.out.println("═══════════════════════════════════════");
                if(!choose.equalsIgnoreCase("y")){
                    break;
                }

            }
            count++;
            EnemyCharacter enemy=new EnemyCharacter(al.get(r.nextInt(al.size())));
            System.out.println("═══════════════════════════════════════");
            System.out.println("⚔\uFE0F 第"+count+"场游戏开始！对手："+enemy.name);
            hc.showInfo();
            enemy.showInfo();

            int round=1;
            if(win!=0){
                for (EnemyCharacter enemyCharacter : al) {
                    enemyCharacter.maxHP+=10;
                    enemyCharacter.HP= enemyCharacter.maxHP;
                    enemyCharacter.attack+=3;
                    enemyCharacter.defense+=2;
                }
            }
            while (hc.isAlive()){
                System.out.println("---------------------------------------");
                System.out.println("⚔️ 第 "+round+" 回合开始！");
//            - 5.1 重置敌人的属性，敌人属性每场HP+10, ATK+3, DEF+2（敌人：越来越难打）（第二场的时候）
//            - 5.2 随机选择敌人(Random)

                showLiveBar(hc.name,hc.maxHP,hc.HP,"H");
                showLiveBar(hc.name,hc.maxMP,hc.MP,"M");
                showLiveBar(enemy.name,enemy.maxHP, enemy.HP,"H");


                System.out.println("===== 你的回合 =====");
//                1. 普通攻击
//                2. 强力一击 (消耗10HP)
//                3. 生命汲取 (消耗5HP，恢复生命)
                ArrayList<String> skillList=new ArrayList<>();
                skillList.add("普通攻击");
                int stage=1;
                int dam3=calculateDamage((int)(hc.attack*1.8),enemy.defense);
                int mp3=30;
                int dam1=calculateDamage(hc.attack,enemy.defense);
                int mp2=20;
                if(enemy.defending){
                    System.out.println("你的对手"+enemy.name+"拥有防御姿态buff，所有收到的伤害将减半");
                }
                System.out.println("1. 普通攻击（将造成 "+dam1+" 点伤害）");
                if (hc.HP>5&&hc.MP>mp2){
                    skillList.add("生命汲取");
                    System.out.println((skillList.indexOf("生命汲取")+1)+". 生命汲取 (消耗 "+mp2+" MP，恢复0~20点生命)");
                    stage++;
                }
                if(hc.HP>10&&hc.MP>mp3){
                    skillList.add("强力一击");
                    System.out.println((skillList.indexOf("强力一击")+1)+". 强力一击 (消耗 "+mp3+" MP,将造成 "+dam3+" 点伤害)");
                    stage++;
                }
                if(!bag.isEmpty()){
                    skillList.add("使用道具");
                    System.out.println((skillList.indexOf("使用道具")+1)+". 使用道具");
                }

                //我方攻击
                while(true){
                    System.out.println("选择行动 (1~"+skillList.size()+"):");
                    Scanner sc=new Scanner(System.in);
                    String choose;
                    if(sc.hasNextInt()){
                        int index=sc.nextInt();
                        if(index<0||index>skillList.size()){
                            System.out.println("无效的选择！请重新选择！");
                            continue;
                        }
                        choose= skillList.get(index-1);
                    }else{
                        choose="Error";
                        sc.next();
                    }

                    switch (choose){
                        case "普通攻击":
                            System.out.println("⚔️ 你对 "+enemy.name+" 使用了普通攻击，造成 "+dam1+" 点伤害！");
                            enemy.damage(dam1);
                            break;
                        case "强力一击":
                            hc.damage(10);
                            hc.takeMP(mp3);
                            System.out.println("\uD83D\uDCA5 消耗 "+mp3+" MP，你对 "+enemy.name+" 使用了强力一击，造成 "+dam3+" 点伤害！");
                            enemy.damage(dam3);
                            break;
                        case "生命汲取":
                            hc.damage(5);
                            int hea=r.nextInt(21);
                            hc.takeMP(mp2);
                            System.out.println("\uD83D\uDC9A 你使用了生命汲取，消耗了 "+mp2+" MP，恢复了 "+hea+" 点生命");
                            hc.heal(hea);
                            break;
                        case "使用道具":
                            System.out.println("---------------道具列表---------------");
                            for(int i=0;i<bag.size();i++){
                                Consumable cs=bag.get(i);
                                System.out.print((i+1)+".");
                                cs.showInfo();
                            }
                            System.out.println("--------------------------------------");

                            int index=-1;
                            while(true){
                                System.out.println("请输入想要使用的道具的序号（1~"+bag.size()+"）：");
                                if(sc.hasNextInt()){
                                    index=sc.nextInt();
                                    if(index<0||index>bag.size()){
                                        System.out.println("请输入正确的序号！");
                                        continue;
                                    }
                                    break;
                                }else{
                                    System.out.println("请输入正确的序号！");
                                    sc.next();
                                }
                            }
                            Consumable useItem=bag.get(index-1);
                            useItem.use(hc);
                            useItem.afterUseShow();
                            bag.remove(useItem);
                            break;
                        default:
                            System.out.println("无效的选择！请重新选择！");
                            continue;
                    }
                    break;
                }
                if(!enemy.isAlive()){
                    System.out.println("\uD83C\uDF89 你击败了 "+enemy.name+"！");
                    win++;
                    int hea=r.nextInt(10,hc.maxHP/3);
                    hc.heal(hea);
                    hc.addMP((int)(hc.maxMP*0.3));
                    System.out.println("💚 战斗结束！你恢复了 "+hea+" 点生命值与 "+(int)(hc.maxMP*0.3)+" 蓝量");
                    if(r.nextInt(10)<3){
                        System.out.println("-------------------------------------");
                        System.out.print("\uD83C\uDF81 恭喜获得道具：");
                        int itemA=r.nextInt(consumableForUse.size());
                        if(r.nextInt(10)<1){
                            int itemB=r.nextInt(consumableForUse.size());
                            System.out.print(consumableForUse.get(itemB).name+"、");
                            bag.add(consumableForUse.get(itemB));
                        }
                        System.out.println(consumableForUse.get(itemA).name);
                        bag.add(consumableForUse.get(itemA));
                        System.out.println("-------------------------------------");
                    }
                    System.out.println("\uD83C\uDFC6 当前胜场: "+win);
                    if(win%3==0){
                        System.out.println("\uD83C\uDF89 属性提升！最大生命+50 蓝量+30 攻击+5 防御+3");
                        hc.maxHP+=50;
                        hc.heal(50);
                        hc.attack+=5;hc.defense+=3;
                        hc.maxMP+=30;
                        hc.addMP(30);
                    }
                    break;
                }

                //敌方攻击
                System.out.println("===== "+enemy.name+"的回合 =====");
                String gj="普通攻击";
                if(r.nextBoolean()){
                    gj=enemy.skill;
                }
                int dmg=0;
                switch (gj){
                    case "普通攻击":
                        dmg=calculateDamage(enemy.attack,hc.defense);
                        System.out.println("⚔️ "+enemy.name+" 对你使用了普通攻击，造成 "+dmg+" 点伤害！");
                        hc.damage(dmg);
                        break;
                    case "猛击":
                        dmg=calculateDamage((int)(enemy.attack*1.5),hc.defense);
                        System.out.println("\uD83D\uDCA5 "+enemy.name+"对 你 使用了猛击，造成 "+dmg+" 点伤害！");
                        hc.damage(dmg);
                        break;
                    case "快速攻击":
                        for (int i = 0; i < 2; i++) {
                            dmg+=calculateDamage((int)(enemy.attack*0.5),hc.defense);
                        }
                        System.out.println("\uD83D\uDCA5 "+enemy.name+"对 你 使用了快速攻击，造成 "+dmg+" 点伤害！");
                        hc.damage(dmg);
                        break;
                    case "防御姿态buff":
                        System.out.println("\uD83D\uDEE1 "+enemy.name+" 上了防御姿态buff，下回合所受伤害减半！");
                        enemy.defending=true;
                        break;
                    case "火球术":
                        dmg=calculateDamage((int)(enemy.attack*1.8),hc.defense);
                        System.out.println("\uD83D\uDCA5 "+enemy.name+"对 你 使用了火球术，造成 "+dmg+" 点伤害！");
                        hc.damage(dmg);
                        break;
                }

                round++;
            }

        }
        System.out.println();
        System.out.println("═══════════════════════════════════════");
        System.out.println("你死了...");
        System.out.println("\uD83C\uDFC6 总胜场："+win);
        System.out.println("感谢游玩！");
        System.out.println("═══════════════════════════════════════");
        
    }

    public HeroCharacter creatCharacter(String user_name){
        System.out.println("创建你的角色：");
        System.out.println("你的角色名为："+user_name);
        System.out.println("请分配属性点（共30点）：");
        System.out.println("1.生命值（初始100，每点+10HP）");
        System.out.println("2.攻击力（初始10，每点+2ATK）");
        System.out.println("3.防御力（初始0，每点+1DEF）");
        System.out.println("4.蓝量（初始50，每点+10MP）");

        String[] attribution={"生命值","攻击力","防御力","蓝量"};
        Scanner sc=new Scanner(System.in);
        int[] value=new int[4];
        int ttl=50;
        for (int i = 0; i < attribution.length; ) {
            System.out.print("分配点数到 "+attribution[i]+"（剩余点数："+ttl+"）:");
            int point=sc.nextInt();
            if(point>ttl){
                System.out.println("点数不足，请重新分配！");
                continue;
            }
            if(point<0){
                System.out.println("分配点数不能小于0，请重新分配！");
                continue;
            }
            value[i]=point;
            ttl-=point;
            i++;
        }
        System.out.println("角色创建成功！");
        return new HeroCharacter(user_name,100+10*value[0],10+2*value[1],value[2],50+10*value[3]);
    }

    public void showLiveBar(String name,int maxHP,int HP,String T){
        int ttl=20;
        int cnt=(int)(ttl*(1.0*HP/maxHP));
        System.out.print(name+":\t[");
        for (int i = 0; i < ttl; i++) {
            if(i<cnt){
                System.out.print("█");
            }else{
                System.out.print(" ");
            }
        }
        if("H".equals(T)){
            System.out.println("] "+HP+"/"+maxHP+" HP");
        }else{
            System.out.println("] "+HP+"/"+maxHP+" MP");
        }

    }

    public int calculateDamage(int attack,int defense){
        if(attack-defense<1)return 1;
        return attack-defense;
    }
}
