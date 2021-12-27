package com.dwinn.ffxivitemdatabase.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * This entity represents ItemResponse table.
 *
 * @author David Winn
 */
@Entity
@Table(name = "item")
public class ItemEntity {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "level_equip")
	private int levelEquip;

	@Column(name = "level_item")
	private int levelItem;

	@Column(name = "class_job_category")
	private String classJobCategory;

	@Column(name = "icon")
	private String icon;

	public ItemEntity() {
		// Default constructor for hibernate.
	}

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

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevelEquip() {
		return levelEquip;
	}

	public void setLevelEquip(int levelEquip) {
		this.levelEquip = levelEquip;
	}

	public int getLevelItem() {
		return levelItem;
	}

	public void setLevelItem(int levelItem) {
		this.levelItem = levelItem;
	}

	public String getClassJobCategory() {
		return classJobCategory;
	}

	public void setClassJobCategory(String classJobCategory) {
		this.classJobCategory = classJobCategory;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
