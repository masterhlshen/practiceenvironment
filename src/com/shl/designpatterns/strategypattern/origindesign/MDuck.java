package com.shl.designpatterns.strategypattern.origindesign;

/**
 * 绿头鸭子
 * @author admin
 */
public class MDuck extends Duck{
    @Override
    public void display() {
        System.out.println(">>>>>>>>>绿头鸭子  当然 表现为绿头喽");
    }
}
