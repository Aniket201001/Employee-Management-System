package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

	Optional<Salary> findByEmployeeId(Long empId);
}
