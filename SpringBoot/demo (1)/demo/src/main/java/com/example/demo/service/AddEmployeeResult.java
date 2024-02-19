package com.example.demo.service;

import com.example.demo.model.Employee;

public class AddEmployeeResult {
	private final Employee employee;
    private final boolean success;
    private final String message;

    public AddEmployeeResult(Employee employee,boolean success, String message) {
    	this.employee=employee;
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

	public Employee getEmployee() {
		return employee;
	}
	
}

