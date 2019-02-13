package com.lonton.exception;

/**
 * Created by xinpc on 2019-02-13
 *
 * @desc
 */
public class ConfigurationErrorException extends Exception {

	private static final long serialVersionUID = 4330901264452806135L;

	public ConfigurationErrorException (String message){
		super(message);
	}
}
