package com.lonton.context;

import com.lonton.beans.factory.DefaultListableBeanFactory;
import com.lonton.beans.factory.support.BeanDefinitionRegistry;
import com.lonton.core.io.AnnotationBeanDefinitionReader;
import com.lonton.core.io.AutowireCapableBeanFactory;
import com.lonton.core.io.Resource;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xinpc on 2019-02-21
 *
 * @desc
 */
public class AutowireApplicationContext extends DefaultListableBeanFactory implements AutowireCapableBeanFactory {

	private static final Logger logger = LoggerFactory.getLogger(AutowireApplicationContext.class);

	static {
		PropertyConfigurator.configure("log4j.properties");
	}


	public AutowireApplicationContext(Resource resource) throws Exception {
		super(resource);
		refresh();
	}

	public AutowireApplicationContext(String location) throws Exception {
		super(location);
		refresh();
	}

	@Override
	public void autowireBean() {

	}

	private class autowireAnnotationBeanDefinition extends AnnotationBeanDefinitionReader {
		public autowireAnnotationBeanDefinition(BeanDefinitionRegistry registry) {
			super(registry);
		}
	}

	@Override
	protected void refresh() throws Exception{
		int count = new autowireAnnotationBeanDefinition(this).loadBeanDefinitions(resource);
		logger.info("一共初注册了:" + count + "个beanDefinition");
	}

}
