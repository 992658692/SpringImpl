package com.lonton.beans.config;

/**
 * Created by xinpc on 2019-02-12
 *
 * @desc
 */
public class DefaultBeanDefinition extends AbstractBeanDefinition {

	@Override
	public String getDescription() {
		return getBeanClass().getName();
	}

	public DefaultBeanDefinition () {

	}
}
