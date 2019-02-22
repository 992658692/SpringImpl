package com.lonton.anntotion.handle;

import com.lonton.beans.factory.BeanFactory;
import com.lonton.exception.BeansException;
import com.lonton.ioc.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xinpc on 2019-02-22
 *
 * @desc
 */
public class AutowiredHandle {

	private static final Logger logger = LoggerFactory.getLogger(AutowiredHandle.class);

	public static void autowiredHandleMethod(Class<?> autowiredClass, BeanFactory beanFactory, String beanName) throws Exception{
		for (Method m : autowiredClass.getDeclaredMethods()) {
			Autowired t = m.getAnnotation(Autowired.class);
			if (t != null){
				String proName = t.value();
				Object bean = beanFactory.getBean(t.value());
				System.out.println(t.value() + bean);
				String methodName = "set" + proName.substring(0,1).toUpperCase() + proName.substring(1);
				Class<?> cl = beanFactory.getBean(beanName).getClass();
				Method method = cl.getMethod(methodName, bean.getClass());
				if (method != null) {
					method.invoke(beanFactory.getBean(beanName), bean);
				} else {
					logger.error("调用方法不存在!");
				}
			}
		}
	}
}
