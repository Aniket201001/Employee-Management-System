package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Salary;
import com.example.demo.repository.SalaryRepository;

@Service
public class SalaryService {
    
    @Autowired
    private SalaryRepository salaryRepository;

    public Salary addSalary(Salary salary) {
        return salaryRepository.save(salary);
    }

    public List<Salary> getAllSalaries() {
        return salaryRepository.findAll();
    }

    public Salary getSalaryByEmployeeId(Long empId) {
        return salaryRepository.findByEmployeeId(empId)
                .orElseThrow(() -> new RuntimeException("Salary not found for employee with id: " + empId));
    }

 
    public Salary updateSalaryByEmployeeId(Long empId, Salary salaryDetails) {
        Optional<Salary> salaryOptional = salaryRepository.findByEmployeeId(empId);
        if (salaryOptional.isEmpty()) {
            throw new RuntimeException("Salary not found for employee with id: " + empId);
        }
        
        Salary salary = salaryOptional.get();
        salary.setSalary(salaryDetails.getSalary());
        salary.setBonus(salaryDetails.getBonus());
        
        return salaryRepository.save(salary);
    }

    public Salary updateSalary(Salary salary) {
        // You can directly call save() method of your repository
        // as it will update the record if it exists or insert a new one if it doesn't
        return salaryRepository.save(salary);
    }


    public void deleteSalary(Long id) {
        Salary salary = salaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salary not found with id: " + id));
        
        salaryRepository.delete(salary);
    }
}

