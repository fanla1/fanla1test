package com.springboot.fanla1test.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long empNo;
	@Column(name = "empName", nullable = false)
	private String empName;
	@Column(name = "depId", nullable = false)
	private String depId;
	@OneToOne
	@JoinColumn(name = "depId", insertable = false, updatable = false)
	private Department department;
	@Column(name = "empSex", nullable = false)
	private String empSex;
	@Column(name = "empPhone", nullable = false)
	private String empPhone;
	@Column(name = "empAddress", nullable = false)
	private String empAddress;
	@Column(name = "empAge", nullable = false)
	private int empAge;
	@Column(name = "createDatetime", columnDefinition = "DATETIME")
	private LocalDateTime createDatetime;
	@Column(name = "updateDatetime", columnDefinition = "DATETIME")
	private LocalDateTime updateDatetime;
	//@CreatedDate、@CreatedBy、@LastModifiedDate、@LastModifiedBy

	public Employee() {

	}

	public Employee(String empName, String depId, String empSex, String empPhone, String empAddress, int empAge) {
		this.empName = empName;
		this.depId = depId;
		this.empSex = empSex;
		this.empPhone = empPhone;
		this.empAddress = empAddress;
		this.empAge = empAge;
		this.createDatetime = LocalDateTime.now();
		this.updateDatetime = LocalDateTime.now();
	}

	public long getEmpNo() {
		return empNo;
	}

	public void setEmpNo(long empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getEmpSex() {
		return empSex;
	}

	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}

	public int getEmpAge() {
		return empAge;
	}

	public void setEmpAge(int empAge) {
		this.empAge = empAge;
	}

	public LocalDateTime getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(LocalDateTime createDatetime) {
		this.createDatetime = createDatetime;
	}

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(LocalDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", empName=" + empName + ", depId=" + depId + ", empSex=" + empSex
				+ ", empPhone=" + empPhone + ", empAddress=" + empAddress + ", empAge=" + empAge + ", createDateTime="
				+ createDatetime.toString() + ", updateDateTime=" + updateDatetime.toString() + "]";
	}

}
