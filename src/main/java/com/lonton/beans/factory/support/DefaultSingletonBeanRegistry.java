package com.lonton.beans.factory.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xinpc on 2019-02-15
 *
 * @desc
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSingletonBeanRegistry.class);

	private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(64);

	@Override
	public void registerSingleton(String beanName, Object singletonObject) {
		singletonObjects.put(beanName, singletonObject);
	}

	@Override
	public Object getSingleton(String beanName) {
		return singletonObjects.get(beanName);
	}

	@Override
	public boolean containsSingleton(String beanName) {
		return singletonObjects.containsKey(beanName);
	}

	@Override
	public int getSingletonCount() {
		return singletonObjects.size();
	}
}
