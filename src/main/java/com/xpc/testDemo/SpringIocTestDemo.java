package com.xpc.testDemo;

import com.xpc.beans.factory.DefaultListableBeanFactory;

import java.io.File;

/**
 * Created by xinpc on 2019-02-28
 *
 * @desc
 */
public class SpringIocTestDemo {

	public static void main(String[] args) throws Exception {
		String location = "C:\\纳里健康\\xin_work\\SpringImpl\\src\\main\\resources\\application.xml";

		//xml配置文件扫描读取
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory(location);
		System.out.println(beanFactory.getBean("beana"));

		//读取后将对象初始化 并放入内存中

		//输出某个对象
	}
}
