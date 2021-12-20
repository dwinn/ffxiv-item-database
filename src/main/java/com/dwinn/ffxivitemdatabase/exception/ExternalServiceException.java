package com.dwinn.ffxivitemdatabase.exception;

/**
 * Error thrown when a response can't be received from an external service.
 *
 * @author David Winn
 */
public class ExternalServiceException extends RuntimeException {

	private static final String DEFAULT_MESSAGE = "The API call failed.";

	public ExternalServiceException() {
		super(DEFAULT_MESSAGE);
	}

	public ExternalServiceException(Throwable cause) {
		super(DEFAULT_MESSAGE, cause);
	}

}
