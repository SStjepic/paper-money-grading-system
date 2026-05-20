package com.sbnz.model.enums;

public class InputFeatures {

    public enum Paper {
        FIRM, CRISP, SOME_SOFTNESS_WRINKLED, LIMP, TOTALLY_LIMP
    }

    public enum Colour {
        NO_DISCOLOURATION, SMUDGING, CLEAR_BUT_NOT_BRIGHT, SOME_DISCOLOURATION, EXCESSIVE_DISCOLOURATION
    }

    public enum Corners {
        SHARP_AND_SQUARE, SLIGHTEST_ROUNDING, WORN_BUT_NOT_ROUNDED, WORN_AND_ROUNDED, ROUNDED_OR_MISSING
    }

    public enum Sheen {
        ORIGINAL_SHEEN, LOST_SHEEN
    }

    public enum FoilFeatures {
        MINOR_SCRATCHES_MANUFACTURE, NUMEROUS_SCRATCHES, MANY_SCRATCHES_DAMAGE, DAMAGED_FOLDS_BROKEN_SURFACE,
        SIGNIFICANTLY_DAMAGED
    }

    public enum Wrinkles {
        NO_WRINKLES, FEW_WRINKLES, PRESENT
    }

    public enum Folds {
        NO_FOLDS, ONE_LIGHT_FOLD, UP_TO_3_LIGHT_FOLDS, MORE_THAN_3_LIGHT_FOLDS, MANY_FOLDS
    }

    public enum Creases {
        NO_CREASES, ONE_CREASE, MORE_THAN_1_CREASE, MANY_CREASES
    }

    public enum Handling {
        NO_HANDLING, MINOR, LIGHT, SIGNIFICANT, CONSIDERABLE, HEAVY
    }

    public enum Wear {
        NO_WEAR, IMPERCEPTIBLE, SHOWS_WEAR, CONSIDERABLE, DAMAGED_PAPER
    }

    public enum Dirt {
        NO_DIRT, MINIMAL, NO_EXCESSIVE_DIRT, DIRT_PRESENT, EXCESSIVE_DIRT
    }

    public enum Stains {
        NO_STAINS, STAINS_PRESENT
    }

    public enum Rust {
        NO_RUST, RUST_PRESENT
    }

    public enum Tears {
        NO_TEARS, MINOR_MARGINS_ONLY, MINOR_INTO_DESIGN, LARGE_TEARS
    }

    public enum Holes {
        NO_HOLES, CENTER_HOLE_ONLY, CENTER_AND_INTERSECTIONS, LARGE_HOLES
    }

    public enum PiecesMissing {
        NO_PIECES, SMALL_PIECE_MISSING, LARGE_PIECE_MISSING, MULTIPLE_PIECES_MISSING
    }

    public enum StaplePinHoles {
        NONE, ONE_TWO_HOLES, MULTIPLE_HOLES
    }

    public enum Graffiti {
        NO_GRAFFITI, GRAFFITI_PRESENT
    }
}
