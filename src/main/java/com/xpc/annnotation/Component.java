package com.xpc.annnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xinpc on 2019-03-12
 *
 * @desc
 */
@Target(ElementType.TYPE)//元注解 来标识该注解的作用范围，这里作用在类上
@Retention(RetentionPolicy.RUNTIME)//元注解 来标注该注解类的生命周期, 这里表示会保持在class编译文件中
public @interface Component {
}
