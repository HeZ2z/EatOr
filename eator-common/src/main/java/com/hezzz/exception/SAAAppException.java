package com.hezzz.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SAAAppException extends RuntimeException {

	private final static Logger logger = LoggerFactory.getLogger(SAAAppException.class);

	public SAAAppException(String msg) {
		super(msg);
		logger.error("Spring AI Playground app exception: {}", msg);
	}

}
