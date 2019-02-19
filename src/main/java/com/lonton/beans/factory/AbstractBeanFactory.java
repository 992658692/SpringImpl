package com.lonton.beans.factory;

import com.lonton.beans.config.BeanDefinition;
import com.lonton.beans.factory.support.DefaultSingletonBeanRegistry;
import com.lonton.exception.BeansException;
import com.lonton.exception.CircularDependException;
import com.lonton.exception.NoSuchBeanDefinitionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xinpc on 2019-02-14
 *
 * @desc
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory{

	private static final Logger logger = LoggerFactory.getLogger(AbstractBeanFactory.class);

	protected Map<String, Object> completedBeanPool = new HashMap<String, Object>();

	protected Map<String, Object> babyBeanPool = new HashMap<String, Object>();

	@Override
	public Object getBean(String name) throws BeansException {
		return doGetBean(name, Object.class);
	}

	@Override
	public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return doGetBean(name, requiredType);
	}

	@Override
	public boolean isSingleton(String beanDefinitionName) throws BeansException {
		if (!containsBeanDefinition(beanDefinitionName)){
			throw new NoSuchBeanDefinitionException("容器中不存在该beanDefinition");
		} else {
			return getBeanDefinition(beanDefinitionName).isSingleton();
		}
	}

	protected <T> T doGetBean(String name, Class<T> requiredType) throws BeansException {
		babyBeanPool.put(name, requiredType);
		Object bean;
		if ((bean = getSingleton(name)) != null) {
			if (requiredType == null || !requiredType.isInstance(bean)) {
				logger.error("传入的requiredType == null || 不是所要求的类型实例");
				throw new ClassCastException("类型转换错误!");
			}
		} else {
			BeanDefinition beanDefinition = getBeanDefinition(name);
			if (beanDefinition == null) {
				try {
					throw  new NoSuchBeanDefinitionException("");
				} catch (NoSuchBeanDefinitionException el) {
					logger.error("bean不存在:" + name);
					return null;
				}
			}

			//优先处理对应bean的依赖bean
			List<String> depends = beanDefinition.getDepends();
			if (depends != null &&depends.size() > 0){
				for (String depend : depends) {
					if (depend.indexOf('.') > 0) {
						continue;
					}

					if (!containsBeanDefinition(depend)) {
						logger.warn("beanName:" + name + "       message:may be you will create a" +
						"incomlete bean, 依赖的bean：" + depend + "不存在");
						continue;
					} else {
						if (babyBeanPool.get(depend) != null) {
							logger.error("beanDefinition中存在循环依赖， 请检查你配置文件！");
							throw new CircularDependException();
						}

						getBean(depend);
					}
				}
			}

			bean = createBean(name, beanDefinition);
			addToCompletedBeanPoolAndRemoveFromBabyBeanPool(name, bean);
		}

		return (T)bean;
	}

	private synchronized void addToCompletedBeanPoolAndRemoveFromBabyBeanPool(String name, Object bean) {
		if (completedBeanPool.get(name) == null) {
			completedBeanPool.put(name, bean);
		}
		babyBeanPool.remove(name);
	}

	protected  abstract  Object createBean(String beanName, BeanDefinition beanDefinition) throws
			CircularDependException;

}
