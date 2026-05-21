package com.sbnz.service.dtos;

import com.sbnz.model.enums.IBNSGrade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BanknoteGradingResponseDTO {
    private String banknoteId;
    private IBNSGrade finalGrade;
    private String reportSummary;
}
