package com.xpc.beans.factory;

import com.xpc.beans.config.BeanDefinition;
import com.xpc.tools.XmlParser;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean的抽象基类
 * 解析xml的配置文件，并保存在自己的map容器中
 * Created by xinpc on 2019-03-13
 *
 * @desc
 */
public abstract class BeanFactory {

	private Map<String, BeanDefinition> iocBeanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

	private List<String> componentPackageNames = new ArrayList<String>();

	public BeanFactory() {

	}

	public BeanFactory(String ResourceLocation) throws Exception {
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(new File(ResourceLocation).getAbsoluteFile());
		//将xml中的bean标签解析出来存入map容器中
		iocBeanDefinitionMap = XmlParser.parser(document);
		//存放component-scan要扫描的包路径
		componentPackageNames = XmlParser.getComponentPackageNames();
	}

}
