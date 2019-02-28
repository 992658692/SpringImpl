package com.xpc.beans.factory;

import com.xpc.beans.config.BeanDefinition;
import com.xpc.tools.XmlParser;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xinpc on 2019-02-28
 *
 * @desc
 */
public class DefaultListableBeanFactory {

	private Map<String, Object> completedBeanPool = new HashMap<String, Object>();

	private Map<String, Object> babyBeanPool = new HashMap<String, Object>();

	private map

	public DefaultListableBeanFactory () {

	}

	public DefaultListableBeanFactory (String location) throws Exception {
		SAXBuilder builder = new SAXBuilder();
		Document document = builder.build(new File(location));
		Map<String, BeanDefinition> m = XmlParser.parser(document);
	}

	public <T> T  getBean(String beanName ,Class<T> classBean) {

	}

	public Object getBean(String beanName) {

	}

	private <T> T doGetBean (String name, Class<T> classBean) {
		babyBeanPool.put(name, classBean);
		Object bean;
		if (()) {

		}
	}


}
