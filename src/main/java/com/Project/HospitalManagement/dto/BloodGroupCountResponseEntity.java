package com.Project.HospitalManagement.dto;

import com.Project.HospitalManagement.Entity.Type.BloodGroupType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BloodGroupCountResponseEntity {

    private BloodGroupType bloodGroupType;
    private Long count;
}
