package com.lonton.enums;

/**
 * Created by xinpc on 2019-02-12
 *
 * @desc
 */
public enum ConfigurableBeanFactory {

	SOCPE_SINGLETION("singleton"),
	SOCPE_PROTOTYPE("prototype");

	private String BeanScope;

	ConfigurableBeanFactory(String beanScope){
		this.BeanScope = beanScope;
	}

	public String getBeanScope(){
		return BeanScope;
	}
}
