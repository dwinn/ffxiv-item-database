package com.dwinn.ffxivitemdatabase.dto;

import java.util.List;

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
public class Item {

	@JsonProperty("ID")
	private int id;

	@JsonProperty("Name_en")
	private String nameEn;

	@JsonProperty("LevelEquip")
	private int levelEquip;

	@JsonProperty("LevelItem")
	private int levelItem;

	@JsonProperty("ClassJobCategory")
	private List<ClassJob> classJobCategory;

	@JsonProperty("ID")
	private String icon;
}
