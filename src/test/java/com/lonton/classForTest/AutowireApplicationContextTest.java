package com.lonton.classForTest;

import com.lonton.beans.config.DefaultBeanDefinition;
import com.lonton.context.AutowireApplicationContext;
import com.lonton.core.io.FileSystemResource;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xinpc on 2019-02-22
 *
 * @desc
 */
public class AutowireApplicationContextTest {

	private static Logger log = LoggerFactory.getLogger(AutowireApplicationContext.class);
	DefaultBeanDefinition defaultBeanDefinition;

	static {
		PropertyConfigurator.configure("log4j.properties");
	}

	@Test
	public void testAutowireApplicationContext() {
		FileSystemResource fsr = new FileSystemResource("resource/application.xml");
		try {
			AutowireApplicationContext aac = new AutowireApplicationContext(fsr);
			aac.getBean("autowiredbean", AutowiredBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
