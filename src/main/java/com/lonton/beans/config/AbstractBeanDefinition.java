package com.lonton.beans.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinpc on 2019-02-12
 *
 * @desc
 */
public abstract class AbstractBeanDefinition implements BeanDefinition {

	private final String SCOPE_DEFAULT = "single";
	private String scope = SCOPE_DEFAULT;
	private Object beanClass;

	List<String> dependentBeanDefinitions = new ArrayList<String>();


	@Override
	public List<String> getDepends() {
		return dependentBeanDefinitions;
	}

	@Override
	public void addDepends(String depend) {
		dependentBeanDefinitions.add(depend);
	}

	@Override
	public String getScope() {
		return scope;
	}

	@Override
	public void setScope(String scope) {
		this.scope = scope;
	}

	@Override
	public boolean isSingleton() {
		return this.scope.equals(SCOPE_DEFAULT);
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public Class<?> getBeanClass() {
		Object beanClassObject = this.beanClass;
		if (beanClassObject == null) {
			throw new IllegalStateException("No bean class specified on bean definition");
		}

		if (!(beanClassObject instanceof Class)) {
			throw  new IllegalStateException("Bean class name [" + beanClassObject + "] has not bean resolved into an actualClass ");
		}
		return (Class<?>)beanClassObject;
	}

	public void setBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}
}
