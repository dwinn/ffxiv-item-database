package com.dwinn.ffxivitemdatabase.controller.advice;

import com.dwinn.ffxivitemdatabase.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <description>.
 *
 * @author David Winn
 */
@RestControllerAdvice
public class ItemControllerAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handle(HttpMessageNotReadableException e) {
		LOGGER.warn("Returning HTTP 400 Bad Request", e);
		throw e;
	}
}
