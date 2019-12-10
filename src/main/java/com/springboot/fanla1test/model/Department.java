package com.springboot.fanla1test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {

	private String depId;
	private String depName;

	public Department() {

	}

	public Department(String depId, String depName) {
		this.depId = depId;
		this.depName = depName;
	}

	@Id
	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	@Column(name = "depName", nullable = false)
	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	@Override
	public String toString() {
		return "Employee [depId=" + depId + ", depName=" + depName + "]";
	}
}
