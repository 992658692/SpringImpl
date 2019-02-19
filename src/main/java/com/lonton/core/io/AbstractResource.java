package com.lonton.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xinpc on 2019-02-19
 *
 * @desc
 */
public abstract class AbstractResource implements Resource{
	@Override
	public boolean exists() {
		return false;
	}

	@Override
	public File getFile() throws IOException {
		return null;
	}

}
