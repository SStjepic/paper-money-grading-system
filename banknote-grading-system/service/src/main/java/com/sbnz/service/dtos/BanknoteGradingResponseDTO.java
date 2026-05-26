package com.sbnz.service.dtos;

import com.sbnz.model.enums.IBNSGrade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BanknoteGradingResponseDTO {
    private String banknoteId;
    private IBNSGrade finalGrade;
    private List<DefectDTO> reportSummary;
}
