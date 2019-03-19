package com.daqsoft.pojo;

import javax.persistence.*;


@Entity
@Table(name="data_login")
public class DataLogin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String url;
	@Column
	private String username;
	@Column
	private String pwd;
	@Column
	private String pwdCode;




	@Column
	private Integer project;
	@Column
	private String session_code;

	@Column
	private String concat_url;
	@Column
	private String resolve_return_session_code;
	@Column
	private String resolve_return_session_timeout;
	@Column
	private Integer status =1;

	public DataLogin() {
	}

	public DataLogin(String session_code) {
		this.session_code = session_code;
		this.status = 1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwdCode() {
		return pwdCode;
	}

	public void setPwdCode(String pwdCode) {
		this.pwdCode = pwdCode;
	}

	public Integer getProject() {
		return project;
	}

	public void setProject(Integer project) {
		this.project = project;
	}

	public String getSession_code() {
		return session_code;
	}

	public void setSession_code(String session_code) {
		this.session_code = session_code;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getConcat_url() {
		return concat_url;
	}

	public void setConcat_url(String concat_url) {
		this.concat_url = concat_url;
	}

	public String getResolve_return_session_code() {
		return resolve_return_session_code;
	}

	public void setResolve_return_session_code(String resolve_return_session_code) {
		this.resolve_return_session_code = resolve_return_session_code;
	}

	public String getResolve_return_session_timeout() {
		return resolve_return_session_timeout;
	}

	public void setResolve_return_session_timeout(String resolve_return_session_timeout) {
		this.resolve_return_session_timeout = resolve_return_session_timeout;
	}
}
