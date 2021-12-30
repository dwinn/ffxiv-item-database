package com.dwinn.ffxivitemdatabase.dto;

import com.dwinn.ffxivitemdatabase.persistence.ItemEntity;
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
public class ItemDetails {

	@JsonProperty("id")
	private int id;

	@JsonProperty("name")
	private String nameEn;

	@JsonProperty("equip_level")
	private int levelEquip;

	@JsonProperty("item_level")
	private int levelItem;

	@JsonProperty("class_job_category")
	private String classJobCategory;

	@JsonProperty("icon")
	private String icon;

	public ItemDetails(ItemEntity itemEntity) {
		this.id = itemEntity.getId();
		this.nameEn = itemEntity.getName();
		this.levelEquip = itemEntity.getLevelEquip();
		this.levelItem = itemEntity.getLevelItem();
		this.classJobCategory = itemEntity.getClassJobCategory();
		this.icon = itemEntity.getIcon();
	}

	public ItemDetails(int id, String nameEn, int levelEquip, int levelItem, String classJobCategory, String icon) {
		this.id = id;
		this.nameEn = nameEn;
		this.levelEquip = levelEquip;
		this.levelItem = levelItem;
		this.classJobCategory = classJobCategory;
		this.icon = icon;
	}
}
