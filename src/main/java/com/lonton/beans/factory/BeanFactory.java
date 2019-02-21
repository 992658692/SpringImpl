package com.lonton.beans.factory;

import com.lonton.beans.config.BeanDefinition;
import com.lonton.exception.BeansException;

/**
 * 容器基本接口定义
 * Created by xinpc on 2019-02-14
 *
 * @desc
 */
public interface BeanFactory {

	Object getBean(String name) throws BeansException;

	<T> T getBean(String name, Class<T> requiredType)throws BeansException;

	boolean containsBeanDefinition(String beanDefinitionName);

	boolean isSingleton(String beanDefinitionName) throws BeansException;

	BeanDefinition getBeanDefinition(String beanDefinitioinName);
}
