package com.lonton.core.io;

import com.lonton.beans.config.BeanDefinition;
import com.lonton.beans.config.DefaultBeanDefinition;
import com.lonton.beans.factory.support.BeanDefinitionRegistry;
import com.lonton.beans.factory.support.XmlParser;
import com.lonton.ioc.annotation.Autowired;
import com.lonton.ioc.annotation.Component;
import com.lonton.tools.Assert;
import com.lonton.tools.PackageUtil;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by xinpc on 2019-02-21
 *
 * @desc
 */
public class AnnotationBeanDefinitionReader extends XmlBeanDefinitionReader {

	public AnnotationBeanDefinitionReader(BeanDefinitionRegistry registry) {
		super(registry);
	}

	@Override
	public int loadBeanDefinitions(Resource resource) throws Exception{
		return doLoadBeanDefinitionsFromAnnotation(resource);
	}

	public int loadBeanDefinitions() throws Exception{
		return doLoadBeanDefinitions(null);
	}

	public int doLoadBeanDefinitionsFromAnnotation(Resource resource) throws Exception {

		int count = super.doLoadBeanDefinitions(resource);
		List<String> packageNames = XmlParser.getCompomentPackageNames();
		if (Assert.isNotEmpty(packageNames)) {
			for (String packageName : packageNames) {
				List<String> classNames = PackageUtil.getClassName(packageName);
				if (Assert.isNotEmpty(classNames)) {
					for (String className : classNames) {
						BeanDefinition beanDefinition = new DefaultBeanDefinition();
						Class<?> beanClass = Class.forName(className);
						Component com = beanClass.getAnnotation(Component.class);
						if (com != null) {
							beanDefinition.setBeanClass(beanClass);
							Field[] fields = beanClass.getDeclaredFields();
							if (fields.length > 0) {
								for (Field f :fields) {
									Autowired autowired = f.getAnnotation(Autowired.class);
									if (autowired != null) {
										beanDefinition.addDepend(autowired.value());
									}
								}
							}

							String beanDefinitionName = (className.substring(className.lastIndexOf(".")+1)).toLowerCase();
							beanDefinitions.put(beanDefinitionName, beanDefinition);
							count++;
						}
					}
				}
			}
		}
		return count;
	}

}
