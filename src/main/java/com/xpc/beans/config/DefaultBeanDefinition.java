package com.xpc.beans.config;

import sun.plugin.com.BeanClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinpc on 2019-02-28
 *
 * @desc
 */
public class DefaultBeanDefinition implements BeanDefinition {

	private Object beanClass;

	private List<String> dependentBeanDefinitions = new ArrayList<String>();

	@Override
	public void setBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	@Override
	public void addDepend(String depend) {
		dependentBeanDefinitions.add(depend);
	}

	@Override
	public List<String> getDepends() {
		return dependentBeanDefinitions;
	}

	@Override
	public Class<?> getBeanClass() throws Exception {
		Object beanClassObject = this.beanClass;
		if (beanClassObject == null) {
			throw  new Exception("No bean class specified on bean definition");
		}
		//判断beanClassObject 是否是Class的一个实例
		if (!(beanClassObject instanceof Class)) {
			throw new Exception("Bean class name [" + beanClassObject+ "] has not bean resolved into an actual Class");
		}
		return (Class<?>) this.beanClass;
	}

}
