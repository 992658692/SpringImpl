package com.lonton.beans.factory;

import com.lonton.beans.config.BeanDefinition;
import com.lonton.beans.factory.support.BeanDefinitionRegistry;
import com.lonton.core.io.Resource;
import com.lonton.exception.BeansException;
import com.lonton.exception.CircularDependException;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xinpc on 2019-02-14
 *
 * @desc
 */
public class DefaultListableBeanFactory extends AbstractBeanFactory implements BeanDefinitionRegistry, ResourceLoaderBeanFactory, ListableBeanFactory{

	private static Logger logger = LoggerFactory.getLogger(DefaultListableBeanFactory.class);

	protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>(256);
	protected Resource resource;

	static {
		PropertyConfigurator.configure("log4j.properties");
	}

	public DefaultListableBeanFactory (Resource resource) throw Exception {
		this.resource = resource;
		ref
	}

	public DefaultListableBeanFactory (String location) throws Exception{
		this.resource = getResource(location);
		re
	}

	protected class ResourceReaderBeanFactory extends xmlbeand


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
	protected Object createBean(String beanName, BeanDefinition beanDefinition) throws CircularDependException {
		return null;
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
