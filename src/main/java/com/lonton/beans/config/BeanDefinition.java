package com.lonton.beans.config;

import com.lonton.enums.ConfigurableBeanFactory;

import java.util.List;

/**
 * Created by xinpc on 2019-02-12
 *
 * @desc
 */
public interface BeanDefinition {

	String SCOPE_SINGLETON = ConfigurableBeanFactory.SOCPE_SINGLETION.getBeanScope();
	String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SOCPE_PROTOTYPE.getBeanScope();


	List<String> getDepends();

	void addDepend(String depend);

	String getScope();

	void setScope(String scope);

	boolean isSingleton();

	String getDescription();

	Class<?> getBeanClass();

	void setBeanClass(Class<?> beanClass);
}
