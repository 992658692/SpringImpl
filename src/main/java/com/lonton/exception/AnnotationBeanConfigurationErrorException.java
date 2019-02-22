package com.lonton.exception;

/**
 * Created by xinpc on 2019-02-22
 *
 * @desc
 */
public class AnnotationBeanConfigurationErrorException extends ConfigurationErrorException {
	private static final long serialVersionUID = 6788103996695381277L;

	public AnnotationBeanConfigurationErrorException(String message) {
		super(message);
	}
}
