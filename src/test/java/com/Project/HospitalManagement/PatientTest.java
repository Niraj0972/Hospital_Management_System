package com.Project.HospitalManagement;


import com.Project.HospitalManagement.Entity.Patient;
import com.Project.HospitalManagement.Entity.Type.BloodGroupType;
import com.Project.HospitalManagement.Service.PatientService;
import com.Project.HospitalManagement.dto.BloodGroupCountResponseEntity;
import com.Project.HospitalManagement.dto.GenderCountResponseEntity;
import com.Project.HospitalManagement.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest
public class PatientTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatient(){
        List<Patient> patient = patientRepository.findAll();
        System.out.println(patient);
     }

     @Test
     public void testTransactionMethods(){
//        Patient patient = patientService.getPatientById(1L);

        Patient patient = patientRepository.findPatientByName("Niraj");

        List<Patient> patientList= patientRepository.findPatientByBirthDateOrEmail(LocalDate.of(2004,05,23),"niraj@gmail.com");

        List<Patient> patientList1 = patientRepository.findPatientByNameContaining("aj");
        List<Patient> patientList2 = patientRepository.findPatientByNameContainingOrderByIdDesc("ay");

        List<Patient>patientList3  = patientRepository.findPatientByBloodGroup(BloodGroupType.O_POSITIVE);

        List<Patient> patientList4 = patientRepository.findPatientBYBornAt(LocalDate.of(2003,9,03));

        List<BloodGroupCountResponseEntity> bloodGroupCount = patientRepository.countEachBloodGroup();

        List<GenderCountResponseEntity> gendercount = patientRepository.countGender();

        Page<Patient> allPatient = patientRepository.findAllPatient(PageRequest.of(1,2));

        int updateCount = patientRepository.updateNameById("Ashwini" , 3L);

         int updateCount1 = patientRepository.updateEmailById("ashwini@gmail.com" , 3L);


//         for(Patient patient1 : patientList3){
//             System.out.println(patient1);
//         }

//         for(Patient patient1 : patientList4){
//             System.out.println(patient1);
//         }

         for(BloodGroupCountResponseEntity bloodGroupCountResponse : bloodGroupCount){
             System.out.println(bloodGroupCountResponse);
         }

         for(GenderCountResponseEntity genderCountResponse : gendercount){
             System.out.println(genderCountResponse);
         }

         for(Patient patient1 : allPatient){
             System.out.println(patient1);
         }

         System.out.println(updateCount);




//        System.out.println(patient);
//         System.out.println(patientList);
//         System.out.println(patientList1);
//         System.out.println(patientList2);

    }






}
