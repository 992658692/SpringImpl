package com.lonton.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xinpc on 2019-02-19
 *
 * @desc
 */
public class FileSystemResource extends AbstractResource {

	private final File file;

	private final String path;

	public FileSystemResource(File file) {
		this.file = file;
		this.path = file.getAbsolutePath();
	}

	public FileSystemResource (String path) {
		this.path = path;
		this.file = new File(path);
	}

	@Override
	public String getDescription() {
		return "资源描述：" + file.getName() + "地址为：" + path;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new FileInputStream(this.file);
	}

	@Override
	public File getFile() throws IOException{
		return this.file;
	}
}
