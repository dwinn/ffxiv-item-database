package com.dwinn.ffxivitemdatabase.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <description>.
 *
 * @author David Winn
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemRequest {

	@JsonProperty("ID")
	private int id;

	// @JsonCreator is a newer method of doing the no arg constructor for deserialization.
	@JsonCreator
	public ItemRequest(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
