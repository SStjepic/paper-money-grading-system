package com.sbnz.model.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Defect {
    private String banknoteId;
    private String attributeName;
    private String detectedValue;
    private String penaltyMessage;
}
