package com.lonton.core.io;

import com.lonton.beans.factory.support.BeanDefinitionRegistry;

/**
 * Created by xinpc on 2019-02-19
 *
 * @desc
 */
public interface BeanDefinitionReader {

	BeanDefinitionRegistry getBeanDefinitionRegistry();

	ResourceLoader getResourceLoader();

	int loadBeanDefinitions(Resource... resources) throws Exception;

	int loadBeanDefinitions(Resource resource) throws Exception;
}
