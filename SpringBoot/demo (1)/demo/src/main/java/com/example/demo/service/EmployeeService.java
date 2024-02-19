package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDetailsDTO;
import com.example.demo.model.Employee;
import com.example.demo.model.PersonalInfo;
import com.example.demo.model.Salary;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private PersonalInfoService personalInfoService;
    

    public AddEmployeeResult addEmployee(Employee employee) {
        if (employeeRepository.existsByName(employee.getName())){
            return new AddEmployeeResult(null,false, "Employee with the same name already exists");
        }else if(employeeRepository.existsByEmail(employee.getEmail())) {
        	return new AddEmployeeResult(null,false, "Employee with the email already exists");
        }else {

        employeeRepository.save(employee);
        return new AddEmployeeResult(employee,true, "Employee added successfully");
    }
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        
        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        
        employeeRepository.delete(employee);
    }
    
    public EmployeeDetailsDTO getEmployeeDetailsById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        // Create an instance of SalaryService and call the method
        Salary salary = salaryService.getSalaryByEmployeeId(id);
        // Create an instance of PersonalInfoService and call the method
        PersonalInfo personalInfo = personalInfoService.getPersonalInfoById(id);

        EmployeeDetailsDTO employeeDetailsDTO = new EmployeeDetailsDTO();
        employeeDetailsDTO.setId(employee.getId());
        employeeDetailsDTO.setName(employee.getName());
        employeeDetailsDTO.setEmail(employee.getEmail());
        if (salary != null) {
            employeeDetailsDTO.setSalary(salary.getSalary());
            employeeDetailsDTO.setBonus(salary.getBonus());
        }
        if (personalInfo != null) {
            employeeDetailsDTO.setAddress(personalInfo.getAddress());
            employeeDetailsDTO.setPhone(personalInfo.getPhoneNo());
        }

        return employeeDetailsDTO;
    }
    
    
    
}