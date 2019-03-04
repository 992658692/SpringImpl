package com.xpc.beans;

/**
 * Created by xinpc on 2019-02-22
 *
 * @desc
 */
public class BeanA {

	private BeanB beanb;

	public BeanB getBeanb() {
		return beanb;
	}

	public void setBeanb(BeanB beanb) {
		this.beanb = beanb;
	}

	public BeanA () {

	}

	public BeanA (BeanB beanB) {
		this.beanb = beanB;
	}

	public String aaa() {
		return null;
	}
}
