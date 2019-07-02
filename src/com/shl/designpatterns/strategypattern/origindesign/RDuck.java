package com.shl.designpatterns.strategypattern.origindesign;

/**
 * 红头鸭子
 *
 * @author admin
 */
public class RDuck extends Duck {
    @Override
    public void display() {
        System.out.println(">>>>>>>>红头鸭子 当然 变现的是红头喽");
    }
}
