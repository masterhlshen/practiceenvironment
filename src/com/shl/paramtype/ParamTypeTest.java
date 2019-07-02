package com.shl.paramtype;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ParamTypeTest {
    public static void main(String[] args) {
        Apple<Integer> intApp = new Apple<>(10);
        System.out.println(">>intApp.info = " + intApp.getInfo());
        Apple<String> stringApp = new Apple<>(20 + "");
        System.out.println("stringApp.info = " + stringApp.getInfo());

        List<String> list = new LinkedList<>();
        list.add("希望");
        list.add("飞公子");
        list.add("大葱汪");
        list.add("康老师");
        test(list);
    }

    List<?> list2 = new LinkedList<>();

    static void test(List<?> list) {
        list.forEach(obj -> System.out.println(">>" + obj.toString()));
    }

    static <T extends Serializable> void  fromArrtoCollection(T[] a, Collection<T> collection) {

    }
}

class A<T extends String> extends Apple<T> {

}
class Apple<T extends Serializable> {
    private T info;
    public Apple(){}

    public Apple(T info) {
        this.info = info;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }


}
