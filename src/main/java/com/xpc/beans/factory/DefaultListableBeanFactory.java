package com.xpc.beans.factory;

import com.xpc.beans.config.BeanDefinition;
import com.xpc.tools.XmlParser;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Method;
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
		Document document = builder.build(new File(location).getAbsoluteFile());
		//解析xml配置文件，将beanDerfinition存放到一个map中
		beanDefinitionMap = XmlParser.parser(document);

	}

	public <T> T  getBean(String beanName ,Class<T> classBean) throws Exception {
		return doGetBean(beanName, classBean);
	}

	public Object getBean(String beanName) throws Exception {
		return doGetBean(beanName, Object.class);
	}

	//管理一个bean所需要的依赖
	private <T> T doGetBean (String name, Class<T> classBean) throws Exception {
		babyBeanPool.put(name, classBean);
		Object bean;
		BeanDefinition beanDefinition = beanDefinitionMap.get(name);
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

				if (!containsBeanDefinition(depend)) {
					logger.error("依赖的bean："+ depend + "not found");
					continue;
				}

				//一个getbean操作内有2个相同的依赖则抛出异常
				if (babyBeanPool.get(depend) != null) {
					throw new Exception("从IOC中getBean失败，该并出现重复依赖！");
				}
				//递归把依赖存放到map容器中
				getBean(depend);
			}
		}

		//利用反射对bean进行初始化
		bean = createBean(name, beanDefinition);
		addToCompletedBeanPoolAndRemoveToBabyBeanPool(name, bean);

		return (T)bean;
	}

	private Object createBean(String beanName, BeanDefinition beanDefinition) throws Exception {
		//beanDefinition中用list管理依赖的集合
		List<String> depends = beanDefinition.getDepends();
		Class<?> clazz = beanDefinition.getBeanClass();
		Object bean = null;
		try {
			bean = clazz.newInstance();
		} catch (Exception el) {
			logger.error("create bean Exception");
		}

		if (depends != null && depends.size() > 0) {
			//将map容器中管理的bean对应的value 从class路径 转换成 实例化的bean
			babyBeanPool.put(beanName, bean);
			for (String depend : depends) {

				//TODO 待定功能，检查是否重复依赖
				if (babyBeanPool.get(depend) != null) {
					logger.error("beanDefinition中存在重复依赖");
					throw new Exception("beanDefinition中存在重复依赖");
				}

				if (depend.charAt(0) == '.') {

				} else {
					String methodName = "set" + depend.substring(0, 1).toUpperCase() + depend.substring(1);
					Method method = clazz.getMethod(methodName, completedBeanPool.get(depend).getClass());
					//调用bean对象的set方法 set的参数为completedBeanPool.get("depend")
					method.invoke(bean, completedBeanPool.get(depend));
				}
			}
		}
		return  bean;
	}

	private boolean containsBeanDefinition(String beanDefinitionName) {
		return beanDefinitionMap.get(beanDefinitionName) != null;
	}

	private synchronized void addToCompletedBeanPoolAndRemoveToBabyBeanPool(String name, Object beanDefinition) {
		if (completedBeanPool.get(name) == null) {
			completedBeanPool.put(name, beanDefinition);
		}
		babyBeanPool.remove(name);
	}

}
