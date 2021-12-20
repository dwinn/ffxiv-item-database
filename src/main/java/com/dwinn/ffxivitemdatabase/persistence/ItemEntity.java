package com.dwinn.ffxivitemdatabase.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This entity represents ItemResponse table.
 *
 * @author David Winn
 */
@Entity
@Table(name = "item")
public class ItemEntity {

	@Id
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "level_equip")
	private int levelEquip;

	@Column(name = "level_item")
	private int levelItem;

	@Column(name = "class_job_category")
	private String classJobCategory;

	private String icon;

	public ItemEntity(int id, String name, int levelEquip, int levelItem, String classJobCategory, String icon) {
		this.id = id;
		this.name = name;
		this.levelEquip = levelEquip;
		this.levelItem = levelItem;
		this.classJobCategory = classJobCategory;
		this.icon = icon;
	}

	public int getId() {
		return id;
	}

	public ItemEntity setId(int id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public ItemEntity setName(String name) {
		this.name = name;
		return this;
	}

	public int getLevelEquip() {
		return levelEquip;
	}

	public ItemEntity setLevelEquip(int levelEquip) {
		this.levelEquip = levelEquip;
		return this;
	}

	public int getLevelItem() {
		return levelItem;
	}

	public ItemEntity setLevelItem(int levelItem) {
		this.levelItem = levelItem;
		return this;
	}

	public String getClassJobCategory() {
		return classJobCategory;
	}

	public ItemEntity setClassJobCategory(String classJobCategory) {
		this.classJobCategory = classJobCategory;
		return this;
	}

	public String getIcon() {
		return icon;
	}

	public ItemEntity setIcon(String icon) {
		this.icon = icon;
		return this;
	}
}
