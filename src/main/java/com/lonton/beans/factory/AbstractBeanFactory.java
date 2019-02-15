package com.lonton.beans.factory;

import com.lonton.beans.factory.support.DefaultSingletonBeanRegistry;
import com.lonton.exception.BeansException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by xinpc on 2019-02-14
 *
 * @desc
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory{

	private static final Logger logger = LoggerFactory.getLogger(AbstractBeanFactory.class);

	protected Map<String, Object> comp

	@Override
	public Object getBean(String name) throws BeansException {
		return null;
	}

	@Override
	public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return null;
	}

	@Override
	public boolean isSingleton(String beanDefinitionName) throws BeansException {
		return false;
	}

}
