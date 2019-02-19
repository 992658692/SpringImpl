package com.lonton.core.io;

import java.io.File;

/**
 * Created by xinpc on 2019-02-19
 *
 * @desc
 */
public class DocumentResource extends FileSystemResource {

	public DocumentResource(File file) {
		super(file);
	}

	public DocumentResource(String path) {
		super(path);
	}

	@Override
	public String getDescription () {
		return "这是一个稳健Resource:" + super.getDescription();
	}
}
