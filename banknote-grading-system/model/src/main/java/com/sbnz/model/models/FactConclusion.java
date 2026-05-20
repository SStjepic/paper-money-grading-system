package com.sbnz.model.models;

import com.sbnz.model.enums.DerivedStatus.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FactConclusion {
    private String banknoteId;

    private PaperStatus paperStatus;
    private FoldingLevel foldingLevel;
    private Integrity integrity;
    private Cleanliness cleanliness;
    private PhysicalWear physicalWear;
    private GlobalStatus globalStatus;
    private GradeLimit gradeLimit;

    public FactConclusion(String banknoteId) {
        this.banknoteId = banknoteId;
    }
}