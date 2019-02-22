package com.lonton.beans.factory;

import com.lonton.beans.config.BeanDefinition;
import com.lonton.beans.factory.support.BeanDefinitionRegistry;
import com.lonton.core.io.FileSystemResource;
import com.lonton.core.io.Resource;
import com.lonton.core.io.XmlBeanDefinitionReader;
import com.lonton.enums.BasicType;
import com.lonton.exception.BeansException;
import com.lonton.exception.CircularDependException;
import com.lonton.exception.XmlConfigurationErrorException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xinpc on 2019-02-14
 *
 * @desc
 */
public class DefaultListableBeanFactory extends AbstractBeanFactory implements BeanDefinitionRegistry, ResourceLoaderBeanFactory, ListableBeanFactory{

	private static Logger logger = LoggerFactory.getLogger(DefaultListableBeanFactory.class);

	protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>(256);
	protected Resource resource;

	public DefaultListableBeanFactory (Resource resource) throws Exception {
		this.resource = resource;
		refresh();
	}

	public DefaultListableBeanFactory (String location) throws Exception{
		this.resource = getResource(location);
		refresh();
	}

	protected class ResourceReaderBeanFactory extends XmlBeanDefinitionReader {
		public ResourceReaderBeanFactory(BeanDefinitionRegistry registry) {
			super(registry);
		}
	}

	@Override
	public boolean containsBeanDefinition(String beanDefinitionName) {
		return beanDefinitionMap.get(beanDefinitionName) != null;
	}


	@Override
	protected Object createBean(String beanName, BeanDefinition beanDefinition) throws CircularDependException {
		List<String> depends = beanDefinition.getDepends();
		Class<?> cl = beanDefinition.getBeanClass();
		Object bean = null;

		try {
			bean = cl.newInstance();
		} catch (Exception e1){
			logger.error("反射异常");
		}

		if (depends != null && depends.size() > 0) {
			babyBeanPool.put(beanName, bean);
			for (String depend :depends) {
				if (babyBeanPool.get(depend) != null) {
					logger.error("beanDefinition中存在重复依赖");
					throw  new CircularDependException();
				}

				if (isBaiscType(depend)) {
					try {
						bean = beanBasicTypeAutowired(bean, depend);
					} catch (Exception e) {

					}
				} else {
					String methodName = "set" + depend.substring(0, 1).toUpperCase() + depend.substring(1);
					try {
						Method method = cl.getMethod(methodName, completedBeanPool.get(depend).getClass());
						method.invoke(bean, completedBeanPool.get(bean));
					} catch (NoSuchMethodException e) {
						logger.error("需要获取的bean中没有" + methodName + "方法");
					} catch (Exception e) {
						logger.error("获取到了set方法，没能获取到需要注入的bean:"+ depend);
					}
				}
			}
		}
		return bean;
	}

	@Override
	public BeanDefinition getBeanDefinition(String beanDefinitioinName) {
		return beanDefinitionMap.get(beanDefinitioinName);
	}

	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
		logger.info("正在注册" + beanName + beanDefinition);
		beanDefinitionMap.put(beanName, beanDefinition);
	}

	@Override
	public void removeBeanDefinition(String beanName) {
		if (beanDefinitionMap.get(beanName) != null) {
			beanDefinitionMap.remove(beanName);
		} else {
			logger.warn("需要移除的bean不存在!");
		}
	}

	@Override
	public Resource getResource(String location) {
		return new FileSystemResource(location);
	}

	protected void refresh() throws Exception {
		int count = new ResourceReaderBeanFactory(this).loadBeanDefinitions(resource);
		logger.info("一共注册了" + count + "个beanDefinition");
	}

	private boolean isBaiscType(String depend) {
		return depend.charAt(0) ==  '.';
	}

	private Object invokeMethod(Object bean, String methodName, Class<?> parameterTypes, Object args) {
		try {
			Method method = bean.getClass().getMethod(methodName, parameterTypes);
			method.invoke(bean, args);
		} catch (Exception e) {
			logger.error("基本类型注入时方法调用错误，可能原因:属性名配置错误，类型不匹配\n 方法名："+
			methodName + "参数:" + args );
		}
		return null;
	}

	private Object beanBasicTypeAutowired(Object bean, String depend) throws XmlConfigurationErrorException{
		String realDepend = depend.substring(1);
		String[] values = realDepend.split("\\+");
		String name = values[0];
		String type = values[1];
		String value = values[2];
		String methodName = "set" + name.substring(0,1).toUpperCase() + name.substring(1);
		try {
			Class<?> typeClass = Class.forName(type);
			String typeName = typeClass.getSimpleName();
			if (typeName.equals(BasicType.Boolean.simpleTypeName)) {
				if (value.equals("true") || value.equals("0")) {
					Boolean bool = true;
					invokeMethod(bean, methodName, Boolean.class, bool);
				} else if (value.equals("false") || value.equals("1")) {
					Boolean bool = false;
					invokeMethod(bean, methodName, Boolean.class, bool);
				} else {
					throw new XmlConfigurationErrorException("xml配置的属性值和其他类型不匹配!");
				}
			} else if (typeName.equals(BasicType.String.simpleTypeName)) {
				String str = value;
				invokeMethod(bean, methodName, String.class, str);
			} else if (typeName.equals(BasicType.Long.simpleTypeName)) {
				Long l = Long.parseLong(value);
				invokeMethod(bean, methodName, Long.class, l);
			} else if (typeName.equals(BasicType.Character.simpleTypeName)) {
				if (value.length() > 1) {
					throw new XmlConfigurationErrorException("xml配置的属性值和类型不匹配！");
				}
				char ch = value.charAt(0);
				invokeMethod(bean, methodName, Character.class, ch);
			} else if (typeName.equals(BasicType.Integer.simpleTypeName)) {
				Integer i = Integer.parseInt(value);
				invokeMethod(bean, methodName, Integer.class, i);
			} else if (typeName.equals(BasicType.Byte.simpleTypeName)) {
				Byte b = value.getBytes()[0];
				invokeMethod(bean, methodName, Byte.class, b);
			} else if (typeName.equals(BasicType.Float.simpleTypeName)) {
				Float f = Float.parseFloat(value);
				invokeMethod(bean, methodName, Float.class, f);
			} else if (typeName.equals(BasicType.Double.simpleTypeName)) {
				Double d = Double.parseDouble(value);
				invokeMethod(bean, methodName, Double.class, d);
			} else {
				throw  new XmlConfigurationErrorException("beanName:" + bean.getClass().getSimpleName()
				+ "的类型配置错误");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return bean;
	}
}
