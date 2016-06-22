package com.jpmorgan.techinterview;

import javax.management.InvalidAttributeValueException;

public class InvalidParameterException extends InvalidAttributeValueException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidParameterException(String exception_message){
		System.out.println(exception_message);
	}

}
