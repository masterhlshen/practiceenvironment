package com.shl.designpatterns.strategypattern.Test;

import com.shl.designpatterns.strategypattern.origindesign.Duck;
import com.shl.designpatterns.strategypattern.origindesign.MDuck;
import com.shl.designpatterns.strategypattern.origindesign.RDuck;

/**
 * 第一版本 测试
 *
 * @author admin
 */
public class OriginDesignTest {
    public static void main(String[] args) {
        Duck mDuck = new MDuck();
        Duck rDuck = new RDuck();
        mDuck.quack();
        mDuck.swim();
        mDuck.display();
        rDuck.swim();
        rDuck.quack();
        rDuck.display();
        System.out.println("----------------增加会飞的方法后------------");
        rDuck.fly();
        mDuck.fly();
    }
}
