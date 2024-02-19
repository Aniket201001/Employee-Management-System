package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PersonalInfo;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {

	Optional<PersonalInfo> findByEmployeeId(Long empId);
}

