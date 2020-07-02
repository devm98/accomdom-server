package com.energy.accomdom.model.request;

import javax.validation.constraints.NotNull;

public class AccomdomModelRequest {
	private Long id;

	@NotNull(message = "Tile can not be null")
	private String title;

	@NotNull(message = "Content can not be null")
	private String content;

	@NotNull(message = "Address can not be null")
	private String address;

	private int area;
	private Long user_id;
	private Long type_id;
	private Long rice;
	private String image;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

	public Long getRice() {
		return rice;
	}

	public void setRice(Long rice) {
		this.rice = rice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
