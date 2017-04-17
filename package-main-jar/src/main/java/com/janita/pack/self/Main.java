package com.janita.pack.self;

/**
 * Created by Janita on 2017-04-17 09:33
 */
public class Main {

    public static void main(String[] args){

        if (args == null || args.length == 0){
            System.out.println("******* 请输入两个参数");
            return;
        }
        String name = args[0];
        if (args.length>=1){
            String age = args[1];
        }
        System.out.println("******* 姓名 ："+name+", 年龄 ： "+args);
    }
}
