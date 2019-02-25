package com.lonton.core.io;

import com.lonton.beans.config.BeanDefinition;
import com.lonton.beans.config.DefaultBeanDefinition;
import com.lonton.beans.factory.support.BeanDefinitionRegistry;
import com.lonton.beans.factory.support.XmlParser;
import org.jdom.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
		return doLoadBeanDefinitions(resource);
	}

	public int doLoadBeanDefinitions(Resource resource) throws Exception {
		Document doc = doLoadDocument(resource);
		beanDefinitions  = XmlParser.parser(doc);
		for (Entry<String, BeanDefinition> beandefinition : beanDefinitions.entrySet()) {
			registry.registerBeanDefinition(beandefinition.getKey(), beandefinition.getValue());
		}
		return beanDefinitions.size();
	}


	protected Document doLoadDocument(Resource resource) throws Exception {
		return new XmlDocumentResource(resource.getFile()).getDocument();
	}
}
