package com.lonton.core.io;

import com.lonton.beans.config.BeanDefinition;
import com.lonton.beans.factory.support.BeanDefinitionRegistry;

/**
 * Created by xinpc on 2019-02-19
 *
 * @desc
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

	protected  final BeanDefinitionRegistry registry;

	private ResourceLoader resourceLoader;

	public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
		this.registry = registry;
		this.resourceLoader = (ResourceLoader)this.registry;
	}

	@Override
	public BeanDefinitionRegistry getBeanDefinitionRegistry() {
		return this.registry;
	}

	@Override
	public ResourceLoader getResourceLoader() {
		return this.resourceLoader;
	}

	@Override
	public int loadBeanDefinitions(Resource... resources) throws Exception {
		int count = 0;
		for (Resource resource : resources) {
			count += loadBeanDefinitions(resource);
		}
		return count;
	}

}
