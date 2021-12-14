package com.dwinn.javarestservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <description>.
 *
 * @author David Winn (dwinn@turnitin.com)
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassJob {

	@JsonProperty("Name_en")
	private String nameEn;

}
