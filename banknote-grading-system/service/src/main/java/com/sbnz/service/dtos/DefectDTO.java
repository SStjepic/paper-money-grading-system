package com.sbnz.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefectDTO {
    private String banknoteId;
    private String attributeName;
    private String detectedValue;
    private String penaltyMessage;
}
