package com.dwinn.ffxivitemdatabase.dto;

import java.util.ArrayList;
import java.util.List;

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
public class ItemResponse {

	@JsonProperty("ID")
	private int id;

	@JsonProperty("Name_en")
	private String nameEn;

	@JsonProperty("LevelEquip")
	private int levelEquip;

	@JsonProperty("LevelItem")
	private int levelItem;

	@JsonProperty("ClassJobCategory")
	private ClassJob classJobCategory;

	@JsonProperty("Icon")
	private String icon;
}
