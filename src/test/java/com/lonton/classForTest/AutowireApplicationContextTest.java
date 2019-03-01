/*
package com.lonton.classForTest;

import com.lonton.beans.config.DefaultBeanDefinition;
import com.lonton.context.AutowireApplicationContext;
import com.lonton.core.io.FileSystemResource;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

*/
/**
 * Created by xinpc on 2019-02-22
 *
 * @desc
 *//*

public class AutowireApplicationContextTest {

	private static Logger log = LoggerFactory.getLogger(AutowireApplicationContext.class);
	DefaultBeanDefinition defaultBeanDefinition;


	@Test
	public void testAutowireApplicationContext() throws IOException {
		System.out.println(new File("").getCanonicalPath());
		FileSystemResource fsr = new FileSystemResource("src/resource/application.xml");
		try {
			AutowireApplicationContext aac = new AutowireApplicationContext(fsr);
			AutowiredBean s = aac.getBean("autowiredbean", AutowiredBean.class);
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
*/
