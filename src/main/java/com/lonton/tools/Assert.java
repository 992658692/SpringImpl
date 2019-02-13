package com.lonton.tools;

import java.util.Collection;

/**
 * Created by xinpc on 2019-02-13
 *
 * @desc
 */
public class Assert {

	public static boolean isNotEmpty(Collection<?> c) {
		return c != null && c.size() >0;
	}

	public static String isNull (Object object) {
		return "Object is null?" + (object == null? true : false);
	}

	public static boolean isEffectiveString (String value){
		return value != null && !value.equals("");
	}
}
