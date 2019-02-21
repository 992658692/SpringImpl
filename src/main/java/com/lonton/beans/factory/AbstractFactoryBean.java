package com.lonton.beans.factory;

/**
 * Created by xinpc on 2019-02-21
 *
 * @desc
 */
public abstract class AbstractFactoryBean<T> implements FactoryBean<T>{

	private boolean isSingleton = true;

	@Override
	public boolean isSingleton() {
		return isSingleton;
	}
}
