package com.lonton.core.io;

import com.lonton.beans.config.BeanDefinition;
import com.lonton.beans.config.DefaultBeanDefinition;
import com.lonton.beans.factory.support.BeanDefinitionRegistry;
import org.jdom.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xinpc on 2019-02-19
 *
 * @desc
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

	protected Map<String, BeanDefinition> beanDefinitions = new HashMap<String, BeanDefinition>();

	private BeanDefinition beanDefinition;

	public XmlBeanDefinitionReader (BeanDefinitionRegistry registry) {
		super(registry);
		beanDefinition = new DefaultBeanDefinition();
	}

	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, BeanDefinition beanDefinition) {
		super(registry);
		this.beanDefinition = beanDefinition;
	}

	@Override
	public int loadBeanDefinitions(Resource resource) throws Exception {
		return 0;
	}

	protected Document doLoadDocument(Resource resource) throws Exception {
		return new XmlDocumentResource(resource.getFile()).getDocument();
	}
}
