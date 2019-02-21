package com.lonton.beans.factory;

/**
 * Created by xinpc on 2019-02-21
 *
 * @desc
 */
public interface FactoryBean<T> {

	boolean isSingleton();

	/**
	 *  return an instance (possibly shared or independent) of the object
	 * @return
	 * @throws Exception
	 */
	T getObject() throws Exception;

	/**
	 *  return the type of object that this FactoryBean creates;
	 * @return
	 */
	Class<T> getObjectType();
}
