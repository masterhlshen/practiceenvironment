package com.shl.designpatterns.strategypattern.origindesign;

/**
 * 鸭子 的顶级父类
 * @author admin
 */
public abstract class Duck {

    // 鸭子的公共方法

    /**
     * 所有的鸭子都会叫
     */
    public  void quack() {
        System.out.println(">>>>>>>>Duck quack 呱呱");
    }

    /**
     * 所有的鸭子都会游泳
     */
    public void swim() {
        System.out.println(">>>>>Duck swim  ");
    }

    /**
     * 所有鸭子 的表现 由其自己表现
     */
    public abstract void display();

    /**
     * 第一个需求  鸭子会飞
     * 但是 会引入一个问题  在顶级父类上增加一个方法 则 所有的子类都具有了执行该方法的能力
     */
    public void fly() {
        System.out.println(">>>>>>>鸭子 会飞哦");
    }
}
