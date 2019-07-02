package com.shl.designpatterns.strategypattern.seconddesign;

/**
 * 红头鸭子
 *
 * @author admin
 */
public class RDuck extends Duck {
    @Override
    public void fly() {
        this.flyAbble = new AbstractFlyAbble() {
            @Override
            public void fly() {
                System.out.println(">>>>>>>>>>>>红头鸭飞得不错");
            }
        };
        super.fly();
    }
}
