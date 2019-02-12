package com.lonton.beans.factory.support;


import com.lonton.beans.config.BeanDefinition;
import org.jdom.Document;
import org.jdom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xinpc on 2019-02-12
 *
 * @desc
 */
public class XmlParser {

	private static Map<String, BeanDefinition> beanDefinitions = new HashMap<String, BeanDefinition>();

	private static List<String> CompomentPackageNames = new ArrayList<String>();

	private static Logger logger = LoggerFactory.getLogger(XmlParser.class);

	public final static Map<String, BeanDefinition> parser(Document doc){

		Element root = doc.getRootElement();
		List<Element> list = root.getChildren();
		for (Element elements : list) {
			BeanDefinition beanDefinition
		}
	}
}
