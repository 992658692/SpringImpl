package com.lonton.beans.factory.support;

import com.lonton.beans.config.BeanDefinition;

/**
 * Created by xinpc on 2019-02-14
 *
 * @desc
 */
public interface BeanDefinitionRegistry {

	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

	void removeBeanDefinition(String beanName);
}
