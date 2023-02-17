package com.hks.servlet.hibernate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name = "Employee",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true, length = 11)
	private int id;
	
	@Column(name = "NAME", length = 20, nullable = true)
	private String name;
	
	@Column(name="ROLE", length=20, nullable=true)
	private String role;
	
	@Column(name="insert_time", nullable=true)
	private Date insertTime;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getRole() {
		return role;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

}
