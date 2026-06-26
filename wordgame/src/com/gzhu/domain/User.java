package com.gzhu.domain;

import java.util.Random;

public class User {
    Random r = new Random();
    private final String id = "Gzhu" + r.nextInt(10000, 100000);
    private String userName;
    private String passWord;
    private boolean state = true;
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean setPhoneNumber(String phoneNumber) {
        if(phoneNumber.length()!=11||phoneNumber.charAt(0)!='1'){
            System.out.println("手机号错误！");
            return false;
        }
        for(int i=0;i<phoneNumber.length();i++){
            if(!(phoneNumber.charAt(i)>='0'&&phoneNumber.charAt(i)<='9')){
                System.out.println("手机号错误！");
                return false;
            }
        }
        this.phoneNumber = phoneNumber;
        return true;
    }

    public User() {
    }

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public boolean setUserName(String userName) {
        if (userName.length() < 3 || userName.length() > 16) {
            System.out.println("用户名长度需在 3 ~ 16 位！");
            return false;
        }
        boolean hasLetter = false;
        for (int i = 0; i < userName.length(); i++) {
            boolean smallLetter = userName.charAt(i) >= 'a' && userName.charAt(i) <= 'z';
            boolean bigLetter = userName.charAt(i) >= 'A' && userName.charAt(i) <= 'Z';
            boolean number = userName.charAt(i) >= '0' && userName.charAt(i) <= '9';
            if (smallLetter || bigLetter) {
                hasLetter = true;
            }
            if (!(smallLetter || bigLetter || number)) {
                System.out.println("用户名只能由 字母 和 数字 组成，且不能为纯数字！");
                return false;
            }
        }
        if(!hasLetter){
            System.out.println("用户名不能为纯数字！");
            return false;
        }
        this.userName = userName;
        return true;
    }

    public String getPassWord() {
        return passWord;
    }

    public boolean setPassWord(String passWord) {
        if(passWord.length()<3||passWord.length()>8){
            System.out.println("密码的长度需要在 3 ~ 8 位！");
            return false;
        }
        boolean hasLetter=false;
        boolean hasNumber=false;
        for (int i = 0; i < passWord.length(); i++) {
            char nowWord=passWord.charAt(i);
            boolean letter= (nowWord>='a'&&nowWord<='z')||(nowWord>='A'&&nowWord<='Z');
            boolean number= nowWord>='0'&&nowWord<='9';
            if(letter)hasLetter=true;
            if(number)hasNumber=true;
            if(!letter&&!number){
                System.out.println("密码必须由数字和字母组成！");
                return false;
            }
        }
        if(!hasLetter||!hasNumber){
            System.out.println("密码必须同时包含字母和数字！");
            return false;
        }
        this.passWord = passWord;
        return true;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void showInfo(){
        System.out.println("╔═════════════用户信息═════════════╗");
        System.out.println("\t\tid：\t"+id);
        System.out.println("\t\t用户名：\t"+userName);
        System.out.println("\t\t手机号：\t"+phoneNumber);
        System.out.println("\t\t密码：\t"+passWord);
        System.out.println("╚═════════════════════════════════╝");
    }
}
