package com.sbnz.model.enums;

public class DerivedStatus {

    public enum PaperStatus {
        PREMIUM, STRONG, SOFTENED, DEGRADED
    }

    public enum FoldingLevel {
        NO_FOLDS, MINIMAL_FOLDS, LIGHTLY_FOLDED, HEAVILY_FOLDED
    }

    public enum Integrity {
        INTACT, MINOR_DAMAGE, MODERATE_DAMAGE, SEVERE_DAMAGE
    }

    public enum Cleanliness {
        IMMACULATE, LIGHTLY_SOILED, CONTAMINATED
    }

    public enum PhysicalWear {
        LOW, MEDIUM, HIGH
    }

    public enum GlobalStatus {
        COLLECTOR_GRADE, EXCELLENT_CONDITION, CIRCULATED, POOR_CONDITION
    }

    public enum GradeLimit {
        MAX_VERY_GOOD, MAX_FAIR, MAX_VERY_FINE
    }
}