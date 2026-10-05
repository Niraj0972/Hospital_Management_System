package com.Project.HospitalManagement.Entity;

import com.Project.HospitalManagement.Entity.Type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Changelog;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Setter
@Table(
//        here  @UniqueConstraint cause we have to apply it on multiple columns
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_patient_name_birthDate" , columnNames = {"name","birthDate"})
        },

        indexes = {
                @Index(name = "idx_patient_birthDate", columnList = "birthDate")
        }

)

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

//    @ToString.Exclude
    private LocalDate birthDate;

//    here we use unique for only one column with in @Columnn
    @Column(unique = true, nullable = false)
    private String email;

    private String gender;

    @Changelog.Timestamp
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;
}
