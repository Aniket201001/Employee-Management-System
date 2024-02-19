package com.example.demo.model;

import java.util.List;

public class MultiEntityResponse {
    private List<Employee> employees;
    private List<Salary> salaries;
    private List<PersonalInfo> personalInfos;

    public MultiEntityResponse(List<Employee> employees, List<Salary> salaries, List<PersonalInfo> personalInfos) {
        this.employees = employees;
        this.salaries = salaries;
        this.personalInfos = personalInfos;
    }

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Salary> getSalaries() {
		return salaries;
	}

	public void setSalaries(List<Salary> salaries) {
		this.salaries = salaries;
	}

	public List<PersonalInfo> getPersonalInfos() {
		return personalInfos;
	}

	public void setPersonalInfos(List<PersonalInfo> personalInfos) {
		this.personalInfos = personalInfos;
	}
}
