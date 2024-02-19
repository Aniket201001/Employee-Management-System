package com.example.demo.service;

import com.example.demo.model.Employee;

public class UpdateEmployeeResult {
    private boolean success;
    private String message;
    private Employee employee;

    public UpdateEmployeeResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public UpdateEmployeeResult(boolean success, String message, Employee employee) {
        this.success = success;
        this.message = message;
        this.employee = employee;
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
