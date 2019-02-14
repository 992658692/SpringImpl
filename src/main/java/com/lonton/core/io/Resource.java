package com.lonton.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xinpc on 2019-02-14
 *
 * @desc
 */
public interface Resource extends InputStreamSource{

	boolean exists();

	File getFile() throws IOException;

	String getDescription();
}
