package com.daqsoft.pojo;

import javax.persistence.*;


@Entity
@Table(name="data_passage")
public class DataPassage {

	public DataPassage() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String type;
	@Column
	private String url;

	@Column
	private Integer updateBoo;

	@Column
	private String name;
	@Column
	private String nameCode;

	@Column
	private Integer tolerance;
	@Column
	private Integer project;

	@Column
	private Integer status;
	@Column
	private String param;
	@Column
	private String resolveReturnData;
	@Column
	private String dateType;


	public DataPassage(String type, Integer status) {
		this.type = type;
		this.status = status;
	}

	public DataPassage(Integer status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public Integer getTolerance() {
		return tolerance;
	}

	public void setTolerance(Integer tolerance) {
		this.tolerance = tolerance;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameCode() {
		return nameCode;
	}

	public void setNameCode(String nameCode) {
		this.nameCode = nameCode;
	}

	public Integer getProject() {
		return project;
	}

	public void setProject(Integer project) {
		this.project = project;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getResolveReturnData() {
		return resolveReturnData;
	}

	public void setResolveReturnData(String resolveReturnData) {
		this.resolveReturnData = resolveReturnData;
	}

	public Integer getUpdateBoo() {
		return updateBoo;
	}

	public void setUpdateBoo(Integer updateBoo) {
		this.updateBoo = updateBoo;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
}
