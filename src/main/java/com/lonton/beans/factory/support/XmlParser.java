package com.lonton.beans.factory.support;


import com.lonton.beans.config.BeanDefinition;
import com.lonton.beans.config.DefaultBeanDefinition;
import com.lonton.exception.XmlConfigurationErrorException;
import com.lonton.tools.Assert;
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

	public final static Map<String, BeanDefinition> parser(Document doc) throws Exception{

		Element root = doc.getRootElement();
		List<Element> list = root.getChildren();
		for (Element elements : list) {
			BeanDefinition beanDefinition = new DefaultBeanDefinition();
			String packageName = elements.getAttributeValue("packagename");
			if (packageName != null) {
				CompomentPackageNames.add(packageName);
			}

			String beanDefinitionName = elements.getAttributeValue("id");
			if (beanDefinitionName == null) {
				continue;
			}

			String classpath = elements.getAttributeValue("class");
			Class<?> beanClass = null;
			if (classpath != null && beanDefinitionName != null) {
				beanClass = Class.forName(classpath);
				beanDefinition.setBeanClass(beanClass);
			}

			List<Element> elementPros = elements.getChildren("property");
			if (elementPros != null && elementPros.size() > 0) {
				for (Element e : elementPros) {
					String proName = e.getAttributeValue("name");
					String beanDepend = e.getAttributeValue("ref");
					String value = e.getAttributeValue("value");
					String type = e.getAttributeValue("type");
					if (beanDepend == null && value != null) {
						if (Assert.isEffectiveString(proName) && Assert.isEffectiveString(type)) {
							beanDefinition.addDepends("." + proName + "+" + type + "+" + value);
						}
					}

					if (beanDepend != null && value == null) {
						beanDefinition.addDepends(beanDepend);
					}

					if ((beanDepend == null && value == null) || (beanDepend != null &&value != null)) {
						logger.error("请检查property元素配置的正确性， ref和value不能同时为空或者同时有值");
						throw new XmlConfigurationErrorException("At XmlParser, 请删除property元素或者添加可用的属性值");
					}

				}
			}

			beanDefinitions.put(beanDefinitionName, beanDefinition);
		}
		return beanDefinitions;
	}

	public static BeanDefinition getBeanDefinition(String name) {
		return beanDefinitions.get(name);
	}

	public static List<String> getCompomentPackageNames() {
		return CompomentPackageNames;
	}

}
