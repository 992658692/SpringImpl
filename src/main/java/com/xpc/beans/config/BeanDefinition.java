package com.xpc.beans.config;

import java.util.List;

/**
 * Created by xinpc on 2019-02-28
 *
 * @desc
 */
public interface BeanDefinition {

	//定义bean对象的范围 singleton 单例  property 非单例
	String SCOPE_SINGLETON = "singleton";
	String SCOPE_PROTOTYPE = "prototype";

	void setBeanClass(Class<?> beanClass);

	void addDepend(String depend);

	List<String> getDepends();

	Class<?> getBeanClass() throws Exception;
}
