package com.Project.HospitalManagement.repository;

import com.Project.HospitalManagement.Entity.Patient;

import com.Project.HospitalManagement.Entity.Type.BloodGroupType;
import com.Project.HospitalManagement.dto.BloodGroupCountResponseEntity;
import com.Project.HospitalManagement.dto.GenderCountResponseEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {


    Patient findPatientByName(String name);

    List<Patient> findPatientByBirthDateOrEmail(LocalDate birthDate, String email);

//    List<Patient> findPatientByBirthDateBetween(LocalDate startDate , LocalDate endDate);

    List<Patient> findPatientByNameContaining(String query);

    List<Patient> findPatientByNameContainingOrderByIdDesc(String query);

    @Query("select p from Patient p where p.bloodGroup = ?1")
    List<Patient> findPatientByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroup);

    @Query("select p from Patient p where p.birthDate > :birthDate")
    List<Patient> findPatientBYBornAt(@Param("birthDate") LocalDate birthDate);

    @Query("select new com.Project.HospitalManagement.dto.BloodGroupCountResponseEntity (p.bloodGroup , Count(p)) from Patient p group by p.bloodGroup")
//    List<Object[]> countEachBloodGroup();
    List<BloodGroupCountResponseEntity> countEachBloodGroup();

    @Query("select new com.Project.HospitalManagement.dto.GenderCountResponseEntity(p.gender, count(p)) from Patient p group by p.gender")
    List<GenderCountResponseEntity> countGender();

    @Query(value = "select * from patient", nativeQuery = true)
    Page<Patient> findAllPatient(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Patient p set p.name = :name where id = :id")
    int updateNameById(@Param("name")String name, @Param("id")Long id);

    @Transactional
    @Modifying
    @Query("update Patient p set p.email = :email where id = :id")
    int updateEmailById(@Param("email")String email, @Param("id")Long id);

}
