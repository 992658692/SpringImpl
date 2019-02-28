package com.xpc.beans.config;

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
}
