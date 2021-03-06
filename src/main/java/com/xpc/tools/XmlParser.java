package com.xpc.tools;

import com.xpc.beans.config.BeanDefinition;
import com.xpc.beans.config.DefaultBeanDefinition;
import org.jdom.Document;
import org.jdom.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xinpc on 2019-02-28
 *
 * @desc
 */
public class XmlParser {

	//bean标签对应的容器
	private static Map<String, BeanDefinition> beanDefinitions = new HashMap<String, BeanDefinition>();

	//注解扫描对应的容器
	private static List<String> componentPackageNames = new ArrayList<String>();

	public final static Map<String, BeanDefinition> parser(Document doc) throws Exception {
		//输出xml文件的父节点
		Element rootElement = doc.getRootElement();
		//将父节点中的子节点输出并形成一个子节点的数组
		List<Element> childElements = rootElement.getChildren();
		//遍历子节点
		for (Element element : childElements) {

			BeanDefinition beanDefinition = new DefaultBeanDefinition();
			String packageName = element.getAttributeValue("packagename");

			//如果该结点为扫描标签 则处理完改节点后则跳过
			//因为不会存在一个节点是一个扫描标签又是一个bean标签
			if (packageName != null) {
				componentPackageNames.add(packageName);
				continue;
			}

			//若果标签属性中不包含ID属性 则跳过
			String beanDefinitionName = element.getAttributeValue("id");
			if (beanDefinitionName == null) {
				continue;
			}

			//如果该标签包含了id和class属性 则实例化该对象并存放到beanDefinition中
			String classpath = element.getAttributeValue("class");
			Class<?> beanClass = null;
			if (classpath != null) {
				beanClass = Class.forName(classpath);
				beanDefinition.setBeanClass(beanClass);
			}

			List<Element> proElements = element.getChildren("property");

			if (proElements != null && proElements.size() > 0) {
				for (Element e : proElements) {
					String proName = e.getAttributeValue("name");
					String ref = e.getAttributeValue("ref");
					String value = e.getAttributeValue("value");
					String type = e.getAttributeValue("type");

					if (value == null && ref != null) {
						beanDefinition.addDepend(ref);
					}

					if (value != null && ref == null) {
					}

					if ((value == null && ref == null) || (value != null && ref != null)) {
						throw  new Exception("baseType or ref only ，or add value or ref");
					}
				}
			}
			beanDefinitions.put(beanDefinitionName, beanDefinition);
		}
		return beanDefinitions;
	}

	public static List<String> getComponentPackageNames (){
		return componentPackageNames;
	}

}
