package com.gzhu.ui;

import com.gzhu.domain.User;
import com.gzhu.domain.creatYZM;

import java.util.ArrayList;
import java.util.Scanner;

public class login {
    public static void begin(){
        ArrayList<User> arr=new ArrayList<>();
        Scanner sc=new Scanner(System.in);

        while(true){
            System.out.println("╔════════════════════════════════╗");
            System.out.println("    🎮 欢迎来到文字格斗游戏 🎮   ");
            System.out.println("╚════════════════════════════════╝");
            System.out.println("请选择操作：1登录 2注册 3忘记密码 4退出");
            String choose=sc.next();
            switch (choose){
                case "1" -> log(arr);
                case "2" -> register(arr);
                case "3" -> reset(arr);
                case "4" -> {
                    System.out.println("感谢游玩~");
                    System.exit(0);
                }
                default -> System.out.println("没有这个选项，请重试~");
            }
        }


    }

    public static void register(ArrayList<User> arr){
        System.out.println("=======欢迎进入注册界面=======");
        Scanner sc=new Scanner(System.in);
        User newUser=new User();

        //用户名录入
        String s;
        while(true){
            System.out.println("请输入用户名：");
            s=sc.next();
            boolean has=false;
            for(User u:arr){
                if(u.getUserName().equals(s)){
                    System.out.println("此用户名已被注册，请选择接下来的操作：");
                    System.out.println("1.登录 2.重新输入");
                    while(true){
                        String c=sc.next();
                        if(c.equals("1")){
                            log(arr);
                            return;
                        }else if(c.equals("2")){
                            has=true;
                            break;
                        }else{
                            System.out.println("没有这个选项！请重新输入：");
                        }
                    }
                }
            }
            if(!has){
                break;
            }
        }
        while(!newUser.setUserName(s)){
            System.out.println("请重新输入用户名：");
            s=sc.next();
        }

        //密码录入 需要输入两次，若不一致重设密码
        while(true){
            System.out.println("请输入密码：");
            s=sc.next();
            while(!newUser.setPassWord(s)){
                System.out.println("请重新输入密码：");
                s=sc.next();
            }
            System.out.println("请再次输入密码，若不一致将重新设置密码：");
            String aga=sc.next();
            if(aga.equals(s)){
                break;
            }
            System.out.println("两次密码不一致，请重新设置密码！");
        }

        //输入手机号
        do {
            System.out.println("请输入手机号码：");
        } while (!newUser.setPhoneNumber(sc.next()));

        //注册成功，输出用户信息
        System.out.println("注册成功！以下为您的信息，请妥善保管！");
        newUser.showInfo();
        arr.add(newUser);
        System.out.println("========感谢您的注册========");
    }

    public static void log(ArrayList<User> arr){
        System.out.println("=======欢迎进入登录界面=======");
        Scanner sc=new Scanner(System.in);
        User loggingUser = null;
        //输入用户名
        while(true){
            System.out.println("请输入用户名：");
            String name=sc.next();
            boolean hasUser=false;
            for(User u:arr){
                if(u.getUserName().equals(name)){
                    hasUser=true;
                    loggingUser=u;
                    if(!loggingUser.getState()){
                        System.out.println("此用户被锁定，请尝试其它用户！");
                        return;
                    }
                    break;
                }
            }
            if(!hasUser){
                System.out.println("没有找到此用户！");
                boolean again=false;
                label:
                while(true){
                    System.out.println("你接下来想要？（输入对应数字）");
                    System.out.println("1.重新输入");
                    System.out.println("2.去注册一个账号");
                    System.out.println("3.返回主界面");
                    String c=sc.next();
                    switch (c) {
                        case "1":
                            again = true;
                            break label;
                        case "2":
                            register(arr);
                            break label;
                        case "3":
                            break label;
                        default:
                            System.out.println("没有这个选项！");
                            break;
                    }
                }
                if(!again)return;
            }else{
                break;
            }
        }
        
        //输入密码
        boolean succeed=false;
        for (int i = 0; i < 3; i++) {
            System.out.println("请输入密码（你还有"+(3-i)+"次尝试机会）：");
            String nowPass=sc.next();
            //验证码
            while(true){
                String yzm=creatYZM.get();
                System.out.println("验证码为："+yzm);
                System.out.println("请输入验证码：");
                String input=sc.next();
                if(input.equalsIgnoreCase(yzm)) {
                    break;
                }
                System.out.println("验证码错误，请重试！");
            }
            if(!nowPass.equals(loggingUser.getPassWord())){
                System.out.println("密码错误，请重试！");
            }else{
                succeed=true;
                break;
            }
        }
        if(!succeed){
            System.out.println("由于您密码多次错误，账号已锁定，请联系客服1145141919810解锁！");
            loggingUser.setState(false);
            return;
        }
        System.out.println("密码正确！");
        System.out.println("恭喜你登陆成功！");

        FightingGame fg=new FightingGame();
        fg.gameStart(loggingUser.getUserName());
    }

    public static void reset(ArrayList<User> arr){
        Scanner sc=new Scanner(System.in);
        System.out.println("======欢迎进入账号找回页面======");
        while(true){
            System.out.println("请输入账号：");
            String reUser=sc.next();
            boolean find=false;
            for(User u:arr){
                if(u.getUserName().equals(reUser)){
                    find=true;
                    if(!u.getState()){
                        System.out.println("该账号已被锁定，无法找回！");
                        return;
                    }

                    while(true){
                        System.out.print("请输入注册时使用的手机号：");
                        String checkNumber=sc.next();
                        if(checkNumber.equals(u.getPhoneNumber())){
                            System.out.println("认证成功！");
                            do{
                                System.out.print("请重新设置密码：");
                            }while (!u.setPassWord(sc.next()));
                            System.out.println("设置成功！请妥善保管新密码，多次重设密码将锁定账号！");
                            break;
                        }else{
                            System.out.println("认证失败！");
                            System.out.println("你想要？");
                            System.out.println("1.重新输入 2.返回主界面(输入其他选项默认返回主界面)");
                            String choose=sc.next();
                            if("1".equals(choose)){
                                continue;
                            }
                            break;
                        }
                    }
                    break;
                }
            }
            if(!find){
                System.out.println("未找到账号，你想要？");
                System.out.println("1.重新输入 2.返回主菜单(输入其他内容默认返回主菜单)");
                String choose=sc.next();
                if(!"1".equals(choose)){
                    break;
                }
            }else{
                break;
            }
        }

    }
}
