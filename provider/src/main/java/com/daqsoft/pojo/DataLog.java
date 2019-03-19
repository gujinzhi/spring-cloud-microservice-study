package com.daqsoft.pojo;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="data_log")
public class DataLog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String mess;

	public DataLog() {
	}
	@Column
	private Long dataPassageId;

	public DataLog(Long dataPassageId,String mess) {
		this.mess = mess;
		this.dataPassageId = dataPassageId;
		this.saveTime = new Date();
	}

	@Column
	private Date saveTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

	public Date getSaveTime() {
		return saveTime;
	}

	public void setSaveTime(Date saveTime) {
		this.saveTime = saveTime;
	}

	public Long getDataPassageId() {
		return dataPassageId;
	}

	public void setDataPassageId(Long dataPassageId) {
		this.dataPassageId = dataPassageId;
	}
}
