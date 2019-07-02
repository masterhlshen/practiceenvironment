package com.shl.designpatterns.strategypattern.seconddesign;

/**
 * 鸭子 的顶级父类
 * @author admin
 */
public abstract class Duck {

    protected AbstractFlyAbble flyAbble;

    protected AbstractDisplayAbble displayAbble;


    public void display() {
        if (this.displayAbble == null) {
            throw new RuntimeException("你这只鸭子不能见人");
        }
        this.displayAbble.display();
    }

    public void fly() {
        if (this.flyAbble == null) {
            throw new RuntimeException("你这只鸭子不能飞哦");
        }
        this.flyAbble.fly();
    }
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



}
