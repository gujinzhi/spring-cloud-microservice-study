package com.daqsoft.pojo;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="data_data")
public class DataData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String data;
	@Column
	private Long dataPassageId;
	@Column
	private Integer project;
	@Column
	private String param;
	@Column
	private Date saveTime;
	public DataData() {
	}

	public DataData(String data,Long id,String param,Integer project) {
		this.project = project;
		this.param = param;
		this.dataPassageId = id;
		this.data = data;
		this.saveTime = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Long getDataPassageId() {
		return dataPassageId;
	}

	public void setDataPassageId(Long dataPassageId) {
		this.dataPassageId = dataPassageId;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Date getSaveTime() {
		return saveTime;
	}

	public void setSaveTime(Date saveTime) {
		this.saveTime = saveTime;
	}

	public Integer getProject() {
		return project;
	}

	public void setProject(Integer project) {
		this.project = project;
	}
}
