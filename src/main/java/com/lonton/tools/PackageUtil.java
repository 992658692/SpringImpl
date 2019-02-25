package com.lonton.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinpc on 2019-02-22
 *
 * @desc
 */
public class PackageUtil {

	public static List<String> getClassName(String packageName) {
		String filePath = "C:\\纳里健康\\xin_work\\SpringImpl\\target\\test-classes\\" + packageName.replace(".", "\\");
		List<String> fileNames = getClassName(filePath, null);
		return fileNames;
	}

	private static List<String> getClassName(String filePath, List<String> className) {
		List<String> myClassName = new ArrayList<String>();
		File file = new File(filePath);
		File[] childFiles = file.listFiles();
		for (File childFile : childFiles) {
			if (childFile.isDirectory()) {
				myClassName.addAll(getClassName(childFile.getPath(), myClassName));
			} else {
				String childFilePath = childFile.getPath();
				childFilePath = childFilePath.substring(childFilePath.indexOf("\\test-classes") + 14,
						childFilePath.lastIndexOf("."));
				childFilePath = childFilePath.replace("\\", ".");
				childFilePath = childFilePath.substring(4, childFilePath.length());
				myClassName.add("com." + childFilePath);
			}
		}
		return myClassName;
	}
}
