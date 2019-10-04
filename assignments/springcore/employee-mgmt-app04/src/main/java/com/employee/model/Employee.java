package com.employee.model;

public class Employee {
	
	private int id;
	private String name;
	private String des;
	private String sal;
	
	public Employee()
	{
		
	}
	public Employee(int id, String name, String des, String sal){
		this.id = id;
		this.name = name;
		this.des = des;
		this.sal = sal;
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getSal() {
		return sal;
	}

	public void setSal(String sal) {
		this.sal = sal;
	}
	

}