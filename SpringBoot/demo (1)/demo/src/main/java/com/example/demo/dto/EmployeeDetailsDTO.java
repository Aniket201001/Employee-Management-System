package com.example.demo.dto;

public class EmployeeDetailsDTO {
    private Long id;
    private String name;
    private String email;
    private long salary;
    private long bonus;
    private String address;
    private long phone;
    
    
	public Long getId() {
		return id;
	}

	
	  public void setId(Long id) { 
		  this.id = id; 
		  }
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	public long getBonus() {
		return bonus;
	}
	public void setBonus(long bonus) {
		this.bonus = bonus;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}

    // Constructors, getters, and setters
    
}