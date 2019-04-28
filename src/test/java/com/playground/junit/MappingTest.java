package com.playground.junit;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MappingTest {
    String desc() default "";

    String type() default "";

    String rule() default "";
}
