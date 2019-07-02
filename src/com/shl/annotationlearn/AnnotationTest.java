package com.shl.annotationlearn;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AnnotationTest {
    public static void main(String[] args) {
        Class clazz = AnnotationTest.class;
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyTag {
    String name();
    int age();
    int domain() default 0;
}
