package com.energy.accomdom.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Types")
public class Type {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "type_id", nullable = false, updatable = false)
    private Long id;
	private String typeName;
	
	@Column(columnDefinition = "text")
	private String description;
	
	
	@OneToMany(cascade = CascadeType.ALL ,mappedBy = "type")
	private List<Accomdom> accomdoms;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<Accomdom> getAccomdoms() {
		return accomdoms;
	}


	public void setAccomdoms(List<Accomdom> accomdoms) {
		this.accomdoms = accomdoms;
	}
	
	
}
