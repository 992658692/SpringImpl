package com.lonton.context;

import com.lonton.beans.factory.DefaultListableBeanFactory;
import com.lonton.core.io.AutowireCapableBeanFactory;
import com.lonton.core.io.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xinpc on 2019-02-21
 *
 * @desc
 */
public class AutowireApplicationContext extends DefaultListableBeanFactory implements AutowireCapableBeanFactory {

	private static final Logger logger = LoggerFactory.getLogger(AutowireApplicationContext.class);

	public AutowireApplicationContext(Resource resource) throws Exception {
		super(resource);
		refresh();
	}

	public AutowireApplicationContext(String location) throws Exception {
		super(location);
		refresh();
	}

	private class autowireAnnotationBeanDefinition extends annotationbean
}
