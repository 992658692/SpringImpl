package com.xpc.beans.factory;

import com.xpc.annnotation.Autowired;
import com.xpc.annnotation.Component;
import com.xpc.beans.config.BeanDefinition;
import com.xpc.beans.config.DefaultBeanDefinition;
import com.xpc.tools.XmlParser;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xinpc on 2019-03-12
 *
 * @desc
 */
public class AutowireApplicationContext {

	private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();

	public AutowireApplicationContext (String location) throws Exception {
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(new File(location).getCanonicalFile());
		XmlParser.parser(document);
		loadBeanDefinitionsFromAnnotation();
	}

	public int loadBeanDefinitionsFromAnnotation() throws IOException, ClassNotFoundException {
		List<String> packageNames = XmlParser.getComponentPackageNames();
		if (packageNames != null &&packageNames.size() > 0){
			for (String packageName : packageNames) {
				//解析扫描路径下所有的class文件
				List<String> classNames = urlJoin(packageName);
				if (classNames != null && classNames.size() > 0) {
					for (String className : classNames) {
						BeanDefinition beanDefinition = new DefaultBeanDefinition();
						//根据解析出来的全限定名的className创建Class对象
						Class<?> clazz = Class.forName(className);
						//获取该类的Component注解
						Component component =  clazz.getAnnotation(Component.class);
						if (component != null) {
							beanDefinition.setBeanClass(clazz);
							Field[] fields = clazz.getDeclaredFields();
							if (fields.length > 0) {
								for (Field field : fields) {
									Autowired autowired = field.getAnnotation(Autowired.class);
									if (autowired != null) {
										beanDefinition.addDepend(autowired.value());
									}
								}
							}

							String clazzName = clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1).toLowerCase();
							beanDefinitionMap.put(clazzName, beanDefinition);
						}
					}
				}
			}
		}
		return 0;
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		String url = XmlParser.class.getClass().getResource("/").getPath();
		url = URLDecoder.decode(url, "UTF-8");
		File file = new File(url);
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.getPath().indexOf(".xml") > 0) {
				continue;
			}
			files = f.listFiles();
			for (File ff : files) {
				for (File fff: ff.listFiles()) {
					for (File ffff: fff.listFiles()) {
						try {
							String classPath = ffff.getPath().substring(ffff.getPath().indexOf("\\classes") + 9);
							classPath = classPath.substring(0, classPath.lastIndexOf("."));
							classPath = classPath.replace("\\", ".");
							Class c = Class.forName(classPath);
							System.out.println(c);
						} catch (Exception e) {

						}
					}
				}
			}
		}
	}

	private List<String> urlJoin (String packageName) throws IOException {
		//获取项目全路径
		String projectUrl = XmlParser.class.getClass().getResource("/").getPath();
		projectUrl = URLDecoder.decode(projectUrl, "UTF-8");
		System.out.println("----------local project url = " + projectUrl);
		projectUrl += packageName;
		return addClassName(projectUrl);
	}


	private List<String> addClassName (String packageName) throws IOException {
		List<String> list = new ArrayList<String>();
		File file = new File(packageName);
		File[] files = file.listFiles();
		if (files != null && files.length > 0){
			for (File f : files) {
				if (f.isDirectory()) {//如果是个文件夹则进入文件夹递归
					list.addAll(addClassName(f.getPath()));
				} else if (! (f.getPath().indexOf(".class") >0)) {//如果不是class文件则跳过
					continue;
				} else {
					//获取class文件的全路径
					String classPath = f.getPath();
					classPath = classPath.substring(classPath.indexOf("\\classes") + 9);
					classPath = classPath.substring(0,classPath.indexOf("."));
					classPath = classPath.replace("\\", ".");
					list.add(classPath);
				}
			}
		}
		return list;
	}
}
