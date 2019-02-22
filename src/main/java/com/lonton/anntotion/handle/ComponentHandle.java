package com.lonton.anntotion.handle;

import com.lonton.beans.config.BeanDefinition;
import com.lonton.beans.config.DefaultBeanDefinition;
import com.lonton.exception.AnnotationBeanConfigurationErrorException;
import com.lonton.ioc.annotation.Autowired;
import com.lonton.ioc.annotation.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xinpc on 2019-02-22
 *
 * @desc
 */
public class ComponentHandle {

	private static Logger logger = LoggerFactory.getLogger(ComponentHandle.class);

	protected static Map<String, BeanDefinition> beanDefinitions = new HashMap<String, BeanDefinition>();

	public static Map<String, BeanDefinition> getBeanDefinitionMap(List<Class<?>> componentClasses) {
		for (Class<?> beanClass : componentClasses)  {
			if (beanClass == null) {
				try {
					throw  new AnnotationBeanConfigurationErrorException("在洗component注释的过程中，传入了空的Class的对象！");
				} catch (AnnotationBeanConfigurationErrorException e) {
					logger.error("在解析component注解的过程中，传入了空的class对象");
				}
			}

			Component component = beanClass.getAnnotation(Component.class);
			if (component != null) {
				BeanDefinition beanDefinition = new DefaultBeanDefinition();
				String beanName = null;
				try {
					beanName = beanClass.getName().split("\\.")[beanClass.getName().split("\\.").length -1];
					if (beanClass != null && beanName != null) {
						beanDefinition.setBeanClass(beanClass);
						Method[] methods = beanClass.getDeclaredMethods();
						for (Method method : methods) {
							Autowired autowired = method.getAnnotation(Autowired.class);
							if (autowired != null) {
								beanDefinition.addDepend(autowired.value());
							}
						}
					} else {
						throw new InstantiationException("注释解析异常");
					}
				} catch (InstantiationException e) {
					logger.error("无法通过空的构造方法获取bean实例！");
				}
				beanDefinitions.put(beanName, beanDefinition);
			}
		}
		return beanDefinitions;
	}
}
