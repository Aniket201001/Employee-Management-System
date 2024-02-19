package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Salary {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "salary_id") 
	    private Long salaryId;

	    @OneToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
	    private Employee employee;

	    @Column(name = "salary_amount") 
	    private long salary;
	    
	    @Column(name = "bonus_amount") 
	    private long bonus;
    
    
	public Long getSalaryId() {
		return salaryId;
	}
	public void setSalaryId(Long salaryId) {
		this.salaryId = salaryId;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
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
	

    
}