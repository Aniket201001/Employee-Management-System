package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PersonalInfo;
import com.example.demo.repository.PersonalInfoRepository;

@Service
public class PersonalInfoService {
    
    @Autowired
    private PersonalInfoRepository personalInfoRepository;

    public PersonalInfo addPersonalInfo(PersonalInfo personalInfo) {
        return personalInfoRepository.save(personalInfo);
    }

    public List<PersonalInfo> getAllPersonalInfo() {
        return personalInfoRepository.findAll();
    }

    public PersonalInfo getPersonalInfoById(Long id) {
        return personalInfoRepository.findByEmployeeId(id)
                .orElseThrow(() -> new RuntimeException("Personal info not found with id: " + id));
    }
    
    public PersonalInfo updatePersonalInfoByEmployeeId(Long empId, PersonalInfo personalInfoDetails) {
        Optional<PersonalInfo> personalInfoOptional = personalInfoRepository.findByEmployeeId(empId);
        if (personalInfoOptional.isEmpty()) {
            throw new RuntimeException("Personal info not found for employee with id: " + empId);
        }
        
        PersonalInfo personalInfo = personalInfoOptional.get();
        personalInfo.setAddress(personalInfoDetails.getAddress());
        personalInfo.setPhoneNo(personalInfoDetails.getPhoneNo());
        
        return personalInfoRepository.save(personalInfo);
    }
    
    public PersonalInfo updatePersonalInfo(PersonalInfo info) {
        // You can directly call save() method of your repository
        // as it will update the record if it exists or insert a new one if it doesn't
        return personalInfoRepository.save(info);
    }


    public void deletePersonalInfo(Long id) {
        PersonalInfo personalInfo = personalInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal Info not found with id: " + id));
        
        personalInfoRepository.delete(personalInfo);
    }
}

