package com.lonton.core.io;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;

import java.io.File;

/**
 * Created by xinpc on 2019-02-19
 *
 * @desc
 */
public class XmlDocumentResource extends DocumentResource{
	public XmlDocumentResource(File file) {
		super(file);
	}

	public XmlDocumentResource(String path) {
		super(path);
	}

	public Document getDocument() throws Exception{
		SAXBuilder builder = new SAXBuilder();
		Document document = builder.build(getFile());
		return document;
	}
}
