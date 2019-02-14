package com.lonton.beans.factory;

import com.lonton.beans.config.BeanDefinition;
import com.lonton.beans.factory.support.BeanDefinitionRegistry;
import com.lonton.core.io.Resource;
import com.lonton.exception.BeansException;

/**
 * Created by xinpc on 2019-02-14
 *
 * @desc
 */
public class DefaultListableBeanFactory extends AbstractBeanFactory implements BeanDefinitionRegistry, ResourceLoaderBeanFactory, ListableBeanFactory{
	@Override
	public Object getBean(String name) throws BeansException {
		return null;
	}

	@Override
	public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return null;
	}

	@Override
	public boolean containsBeanDefinition(String beanDefinitionName) {
		return false;
	}

	@Override
	public boolean isSingleton(String beanDefinitionName) throws BeansException {
		return false;
	}

	@Override
	public BeanDefinition getBeanDefinition(String beanDefinitioinName) {
		return null;
	}

	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {

	}

	@Override
	public void removeBeanDefinition(String beanName) {

	}

	@Override
	public Resource getResource(String location) {
		return null;
	}
}
