package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmployeeDetailsDTO;
import com.example.demo.model.Employee;
import com.example.demo.model.MultiEntityRequest;
import com.example.demo.model.MultiEntityResponse;
import com.example.demo.model.PersonalInfo;
import com.example.demo.model.Salary;
import com.example.demo.service.AddEmployeeResult;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.PersonalInfoService;
import com.example.demo.service.SalaryService;
import com.example.demo.service.UpdateEmployeeResult;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SalaryService salaryService;

    @Autowired
    private PersonalInfoService personalInfoService;

	/*
	 * @PostMapping("/employee") public ResponseEntity<String>
	 * createAllEntities(@RequestBody MultiEntityRequest request) { Employee
	 * employee = request.getEmployee(); Salary salary = request.getSalary();
	 * PersonalInfo personalInfo = request.getPersonalInfo();
	 * 
	 * AddEmployeeResult result = employeeService.addEmployee(employee);
	 * 
	 * if (result.isSuccess()) { employee = result.getEmployee();
	 * salary.setEmployee(employee); personalInfo.setEmployee(employee);
	 * 
	 * salaryService.addSalary(salary);
	 * personalInfoService.addPersonalInfo(personalInfo);
	 * 
	 * return ResponseEntity.ok("Employee added successfully."); } else { return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getMessage());
	 * 
	 * } }
	 */
    /*@GetMapping("/employee/{empId}")
    public ResponseEntity<MultiEntityResponse> getEmployeeDetailsById(@PathVariable long empId) {
        // Retrieve employee
        Employee employee = employeeService.getEmployeeById(empId);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        Salary salary = salaryService.getSalaryByEmployeeId(empId);
        PersonalInfo personalInfo = personalInfoService.getPersonalInfoById(empId);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);

        List<Salary> salaries = new ArrayList<>();
        if (salary != null) {
            salaries.add(salary);
        }

        List<PersonalInfo> personalInfos = new ArrayList<>();
        if (personalInfo != null) {
            personalInfos.add(personalInfo);
        }

        MultiEntityResponse response = new MultiEntityResponse(employees, salaries, personalInfos);
        return ResponseEntity.ok(response);
    }*/


	/*
	 * @GetMapping("/employee") public MultiEntityResponse getAllEntities() {
	 * List<Employee> employees = employeeService.getAllEmployees(); List<Salary>
	 * salaries = salaryService.getAllSalaries(); List<PersonalInfo> personalInfos =
	 * personalInfoService.getAllPersonalInfo();
	 * 
	 * return new MultiEntityResponse(employees, salaries, personalInfos); }
	 */

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDetailsDTO>> getAllEmployeesWithDetails() {
        List<Employee> employees = employeeService.getAllEmployees();
        List<EmployeeDetailsDTO> employeeDTOs = new ArrayList<>();

        for (Employee employee : employees) {
            Long employeeId = employee.getId();
            Salary salary = salaryService.getSalaryByEmployeeId(employeeId);
            PersonalInfo personalInfo = personalInfoService.getPersonalInfoById(employeeId);

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

            employeeDTOs.add(employeeDetailsDTO);
        }

        return ResponseEntity.ok(employeeDTOs);
    }
    
    
    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDetailsDTO> updateEmployeeWithDetails(@PathVariable Long id, @RequestBody EmployeeDetailsDTO updatedEmployeeDetails) {
        // Retrieve the existing employee by ID
        Employee existingEmployee = employeeService.getEmployeeById(id);
        
        if (existingEmployee == null) {
            // If the employee with the given ID doesn't exist, return a not found response
            return ResponseEntity.notFound().build();
        }
        
        // Update the employee details with the provided data
        existingEmployee.setName(updatedEmployeeDetails.getName());
        existingEmployee.setEmail(updatedEmployeeDetails.getEmail());
        
        // Update the employee in the database
        Employee updatedEmployee = employeeService.updateEmployee(id, existingEmployee);
        
        if (updatedEmployee == null) {
            // If the update operation fails, return an internal server error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
        // Return the updated employee details DTO
        return ResponseEntity.ok(updatedEmployeeDetails);
    }



    @DeleteMapping("/employees/{empId}")
    public void deleteAllEntities(@PathVariable Long empId) {
       
        salaryService.deleteSalary(empId);
        personalInfoService.deletePersonalInfo(empId);
        employeeService.deleteEmployee(empId);

        //return ResponseEntity.ok("All entities deleted successfully for employee with id: " + empId);
    }
    
	 
    @GetMapping("/employees/{id}")
    public EmployeeDetailsDTO getEmployeeDetailsById(@PathVariable Long id) {
    	return employeeService.getEmployeeDetailsById(id);
    }
    
    @PostMapping("/employees")
    public ResponseEntity<EmployeeDetailsDTO> addEmployeeWithDetails(@RequestBody EmployeeDetailsDTO employeeDetails) {
        Employee employee = new Employee();
        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        
        // Create the employee
        AddEmployeeResult result = employeeService.addEmployee(employee);
        
        if (result.isSuccess()) {
            Salary salary = new Salary();
            salary.setSalary(employeeDetails.getSalary());
            salary.setBonus(employeeDetails.getBonus());
            salary.setEmployee(employee); // Set the employee
            
            PersonalInfo personalInfo = new PersonalInfo();
            personalInfo.setAddress(employeeDetails.getAddress());
            personalInfo.setPhoneNo(employeeDetails.getPhone());
            personalInfo.setEmployee(employee); // Set the employee
            
            // Add salary and personal info
            salaryService.addSalary(salary);
            personalInfoService.addPersonalInfo(personalInfo);
            
            return ResponseEntity.ok(employeeDetails);
        } else {
            // If employee addition fails, return an appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
