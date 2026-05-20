package com.sbnz.model.models;

import lombok.Data;
import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.List;

import com.sbnz.model.enums.IBNSGrade;

@Data
@AllArgsConstructor
public class EvaluationResult {
    private String banknoteId;
    private IBNSGrade finalGrade;
    private List<Defect> accumulatedDefects;

    public EvaluationResult(String banknoteId) {
        this.accumulatedDefects = new ArrayList<>();
        this.banknoteId = banknoteId;
    }
}