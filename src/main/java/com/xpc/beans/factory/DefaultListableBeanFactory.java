package com.xpc.beans.factory;

import com.xpc.beans.config.BeanDefinition;
import com.xpc.tools.XmlParser;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xinpc on 2019-02-28
 *
 * @desc
 */
public class DefaultListableBeanFactory {

	private static final Logger logger = LoggerFactory.getLogger(DefaultListableBeanFactory.class);

	private Map<String, Object> completedBeanPool = new HashMap<String, Object>();

	private Map<String, Object> babyBeanPool = new HashMap<String, Object>();

	private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>(256);

	public DefaultListableBeanFactory () {

	}

	public DefaultListableBeanFactory (String location) throws Exception {
		SAXBuilder builder = new SAXBuilder();
		Document document = builder.build(new File(location));
		beanDefinitionMap = XmlParser.parser(document);

	}

	public <T> T  getBean(String beanName ,Class<T> classBean) throws Exception {
		return doGetBean(beanName, classBean);
	}

	public Object getBean(String beanName) throws Exception {
		return doGetBean(beanName, Object.class);
	}

	private <T> T doGetBean (String name, Class<T> classBean) throws Exception {
		babyBeanPool.put(name, classBean);
		Object bean;
		BeanDefinition beanDefinition = getBeanDefinition(name);
		if (beanDefinition == null) {
			logger.error("bean不存在");
			return null;
		}
		List<String> depends = beanDefinition.getDepends();
		if (depends != null && depends.size() > 0) {
			for (String depend : depends) {
				//如果依赖的bean不是全限定名则跳过
				if (depend.indexOf(".") == 0) {
					continue;
				}

				if (containsBeanDefinition(depend)) {
					logger.error("依赖的bean："+ depend + "not found");
					continue;
				}

				//一个getbean操作内有2个相同的依赖则抛出异常
				if (babyBeanPool.get(depend) != null) {
					throw new Exception("从IOC中getBean失败，该并出现重复依赖！");
				}
				//递归注入依赖
				getBean(depend);
			}
		}

		bean = create
	}

	private Object createBean(String beanName, BeanDefinition beanDefinition) throws Exception {
		List<String> depends = beanDefinition.getDepends();
		Class<?> clazz = beanDefinition.getBeanClass();
		Object bean = null;
		try {
			bean = clazz.newInstance();
		} catch (Exception el) {
			logger.error("create bean Exception");
		}

		if (depends != null && depends.size() > 0) {
			babyBeanPool.put(beanName, bean);
			for (String ) {

			}
		}
	}

	private BeanDefinition getBeanDefinition(String beanDefinitionName) {
		return beanDefinitionMap.get(beanDefinitionName);
	}

	private boolean containsBeanDefinition(String beanDefinitionName) {
		return beanDefinitionMap.get(beanDefinitionName) == null;
	}

}
