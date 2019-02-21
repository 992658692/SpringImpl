package com.lonton.core.io;

/**
 * Created by xinpc on 2019-02-21
 *
 * @desc
 */
public class DefaultResourceLoader implements ResourceLoader {
	@Override
	public Resource getResource(String location) {
		return new FileSystemResource(location);
	}
}
