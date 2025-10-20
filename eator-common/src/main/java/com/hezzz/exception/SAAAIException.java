package com.hezzz.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SAAAIException extends RuntimeException {

	private final static Logger logger = LoggerFactory.getLogger(SAAAIException.class);

	public SAAAIException(String msg) {
		super(msg);
		logger.error("EatOr LLMs exception: {}", msg);
	}

}
