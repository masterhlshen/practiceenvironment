package com.shl.designpatterns.strategypattern.Test;

import com.shl.designpatterns.strategypattern.seconddesign.Duck;
import com.shl.designpatterns.strategypattern.seconddesign.MDuck;
import com.shl.designpatterns.strategypattern.seconddesign.ModelDuck;
import com.shl.designpatterns.strategypattern.seconddesign.RDuck;

public class SecondDesignTest {
    public static void main(String[] args) {
        Duck rDuck = new RDuck();
        Duck moDuck = new ModelDuck();
        Duck mDuck = new MDuck();

        rDuck.fly();
        mDuck.display();

        moDuck.fly();
    }
}
