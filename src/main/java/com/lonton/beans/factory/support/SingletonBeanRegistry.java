package com.lonton.beans.factory.support;

/**
 * Created by xinpc on 2019-02-15
 *
 * @desc
 */
public interface SingletonBeanRegistry {

	void registerSingleton(String beanName, Object singletonObject);

	Object getSingleton(String beanName);

	boolean containsSingleton(String beanName);

	int getSingletonCount();
}
