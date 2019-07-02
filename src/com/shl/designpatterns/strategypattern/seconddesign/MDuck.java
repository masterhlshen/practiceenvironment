package com.shl.designpatterns.strategypattern.seconddesign;

/**
 * 绿头鸭子
 * @author admin
 */
public class MDuck extends Duck {


    @Override
    public void display() {
        displayAbble = new AbstractDisplayAbble(){
            @Override
            public void display() {
                System.out.println(">>>>>>>>>>>>>>绿头鸭表现得不错");
            }
        };
        super.display();
    }
}
