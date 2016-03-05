package com.ruling.cost.timemanage.domain;

/**
 * 工作时间分配
 * 
 * @author FXStudio.com.cn(Ajaxfan)
 */
public class CWorkTime {
	private Integer id;
	private Integer userId;
	private Integer itemId;
	private String created;
	private Integer timelong;
	private Integer usedTime;
	private Integer residTime;

	public Integer getResidTime() {
		return residTime;
	}

	public void setResidTime(Integer residTime) {
		this.residTime = residTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public Integer getTimelong() {
		return timelong;
	}

	public void setTimelong(Integer timelong) {
		this.timelong = timelong;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(Integer usedTime) {
		this.usedTime = usedTime;
	}
}
