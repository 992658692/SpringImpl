package com.lonton.beans.factory.support;


import com.lonton.beans.config.BeanDefinition;

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

	private static List<String> ComponentPackageNames = new ArrayList<String>();
}
