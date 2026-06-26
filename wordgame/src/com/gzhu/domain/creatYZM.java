package com.gzhu.domain;

import java.util.Random;

public class creatYZM {
    private creatYZM(){}

    public static String get(){
        Random r=new Random();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<4;i++){
            boolean big=r.nextBoolean();
            char c= (char)r.nextInt('a','z'+1);
            if(big){
                sb.append((char)(c+('A'-'a')));
            }else{
                sb.append(c);
            }
        }
        int insertIndex=r.nextInt(0,4);
        sb.insert(insertIndex, r.nextInt(0, 10));
        return sb.toString();
    }
 }
