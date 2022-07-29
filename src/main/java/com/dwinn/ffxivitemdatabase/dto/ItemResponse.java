package com.dwinn.ffxivitemdatabase.dto;

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

	public int getId() {
		return id;
	}

	public String getNameEn() {
		return nameEn;
	}

	public int getLevelEquip() {
		return levelEquip;
	}

	public int getLevelItem() {
		return levelItem;
	}

	public ClassJob getClassJobCategory() {
		return classJobCategory;
	}

	public String getIcon() {
		return icon;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public void setLevelEquip(int levelEquip) {
		this.levelEquip = levelEquip;
	}

	public void setLevelItem(int levelItem) {
		this.levelItem = levelItem;
	}

	public void setClassJobCategory(ClassJob classJobCategory) {
		this.classJobCategory = classJobCategory;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
