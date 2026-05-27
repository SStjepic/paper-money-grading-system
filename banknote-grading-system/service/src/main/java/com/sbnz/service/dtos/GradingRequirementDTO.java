package com.sbnz.service.dtos;

import com.sbnz.model.enums.IBNSGrade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradingRequirementDTO {
    private IBNSGrade target;
    private String requirement;
    private String level;
    private String explanation;
}
