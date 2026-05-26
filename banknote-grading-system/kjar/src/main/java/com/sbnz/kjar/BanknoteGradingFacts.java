package com.sbnz.kjar;

import com.sbnz.model.models.Banknote;
import com.sbnz.model.models.Fact;

import java.util.ArrayList;
import java.util.List;

public final class BanknoteGradingFacts {

    public static List<Fact> createGradingGoals() {
        List<Fact> goals = new ArrayList<>();

        addInputFeatures(goals);
        addIntermediateFacts(goals);
        addGlobalStatusAndLimits(goals);
        addFinalGradingGoals(goals);

        return goals;
    }

    public static List<Fact> createGradingGoals(Banknote banknote) {
        List<Fact> goals = new ArrayList<>();

        addUserInputFeatures(goals, banknote);
        addIntermediateFacts(goals);
        addGlobalStatusAndLimits(goals);
        addFinalGradingGoals(goals);

        return goals;
    }

    private static void addUserInputFeatures(List<Fact> goals, Banknote banknote) {
        // 1. Paper status
        if (banknote.getPaper() != null) {
            switch (banknote.getPaper()) {
                case FIRM:
                    addGoal(goals, "INPUT:Paper.FIRM", "INPUT:paper == FIRM", "L0_INPUT", "Paper is firm.");
                    break;
                case CRISP:
                    addGoal(goals, "INPUT:Paper.CRISP", "INPUT:paper == CRISP", "L0_INPUT", "Paper is crisp.");
                    break;
                case SOME_SOFTNESS_WRINKLED:
                    addGoal(goals, "INPUT:Paper.SOME_SOFTNESS_WRINKLED", "INPUT:paper == SOME_SOFTNESS_WRINKLED", "L0_INPUT", "Paper has some softness/wrinkles.");
                    break;
                case LIMP:
                    addGoal(goals, "INPUT:Paper.LIMP", "INPUT:paper == LIMP", "L0_INPUT", "Paper is limp.");
                    break;
                case TOTALLY_LIMP:
                    addGoal(goals, "INPUT:Paper.TOTALLY_LIMP", "INPUT:paper == TOTALLY_LIMP", "L0_INPUT", "Paper is totally limp.");
                    break;
            }
        }

        // 2. Sheen
        if (banknote.getSheen() != null) {
            switch (banknote.getSheen()) {
                case ORIGINAL_SHEEN:
                    addGoal(goals, "INPUT:Sheen.ORIGINAL_SHEEN", "INPUT:sheen == ORIGINAL_SHEEN", "L0_INPUT", "Original natural sheen present.");
                    break;
                case LOST_SHEEN:
                    addGoal(goals, "INPUT:Sheen.LOST_SHEEN", "INPUT:sheen == LOST_SHEEN", "L0_INPUT", "Original sheen is lost.");
                    break;
            }
        }

        // 3. Folds
        if (banknote.getFolds() != null) {
            switch (banknote.getFolds()) {
                case NO_FOLDS:
                    addGoal(goals, "INPUT:Folds.NO_FOLDS", "INPUT:folds == NO_FOLDS", "L0_INPUT", "No folds.");
                    break;
                case ONE_LIGHT_FOLD:
                    addGoal(goals, "INPUT:Folds.ONE_LIGHT_FOLD", "INPUT:folds == ONE_LIGHT_FOLD", "L0_INPUT", "Exactly one light fold.");
                    break;
                case UP_TO_3_LIGHT_FOLDS:
                    addGoal(goals, "INPUT:Folds.UP_TO_3_LIGHT_FOLDS", "INPUT:folds == UP_TO_3_LIGHT_FOLDS", "L0_INPUT", "Up to 3 light folds.");
                    break;
                case MORE_THAN_3_LIGHT_FOLDS:
                    addGoal(goals, "INPUT:Folds.MORE_THAN_3_LIGHT_FOLDS", "INPUT:folds == MORE_THAN_3_LIGHT_FOLDS", "L0_INPUT", "More than 3 light folds.");
                    break;
                case MANY_FOLDS:
                    addGoal(goals, "INPUT:Folds.MANY_FOLDS", "INPUT:folds == MANY_FOLDS", "L0_INPUT", "Many folds.");
                    break;
            }
        }

        // 4. Creases
        if (banknote.getCreases() != null) {
            switch (banknote.getCreases()) {
                case NO_CREASES:
                    addGoal(goals, "INPUT:Creases.NO_CREASES", "INPUT:creases == NO_CREASES", "L0_INPUT", "No creases.");
                    break;
                case ONE_CREASE:
                    addGoal(goals, "INPUT:Creases.ONE_CREASE", "INPUT:creases == ONE_CREASE", "L0_INPUT", "Exactly one crease.");
                    break;
                case MORE_THAN_1_CREASE:
                    addGoal(goals, "INPUT:Creases.MORE_THAN_1_CREASE", "INPUT:creases == MORE_THAN_1_CREASE", "L0_INPUT", "More than one crease.");
                    break;
                case MANY_CREASES:
                    addGoal(goals, "INPUT:Creases.MANY_CREASES", "INPUT:creases == MANY_CREASES", "L0_INPUT", "Many creases.");
                    break;
            }
        }

        // 5. Wrinkles
        if (banknote.getWrinkles() != null) {
            switch (banknote.getWrinkles()) {
                case NO_WRINKLES:
                    addGoal(goals, "INPUT:Wrinkles.NO_WRINKLES", "INPUT:wrinkles == NO_WRINKLES", "L0_INPUT", "No wrinkles.");
                    break;
                case FEW_WRINKLES:
                    addGoal(goals, "INPUT:Wrinkles.FEW_WRINKLES", "INPUT:wrinkles == FEW_WRINKLES", "L0_INPUT", "Few wrinkles.");
                    break;
            }
        }

        // 6. Tears
        if (banknote.getTears() != null) {
            switch (banknote.getTears()) {
                case NO_TEARS:
                    addGoal(goals, "INPUT:Tears.NO_TEARS", "INPUT:tears == NO_TEARS", "L0_INPUT", "No tears.");
                    break;
                case MINOR_MARGINS_ONLY:
                    addGoal(goals, "INPUT:Tears.MINOR_MARGINS_ONLY", "INPUT:tears == MINOR_MARGINS_ONLY", "L0_INPUT", "Minor tears on margins only.");
                    break;
                case MINOR_INTO_DESIGN:
                    addGoal(goals, "INPUT:Tears.MINOR_INTO_DESIGN", "INPUT:tears == MINOR_INTO_DESIGN", "L0_INPUT", "Minor tears extending into design.");
                    break;
                case LARGE_TEARS:
                    addGoal(goals, "INPUT:Tears.LARGE_TEARS", "INPUT:tears == LARGE_TEARS", "L0_INPUT", "Large structural tears.");
                    break;
            }
        }

        // 7. Holes
        if (banknote.getHoles() != null) {
            switch (banknote.getHoles()) {
                case NO_HOLES:
                    addGoal(goals, "INPUT:Holes.NO_HOLES", "INPUT:holes == NO_HOLES", "L0_INPUT", "No holes.");
                    break;
                case CENTER_HOLE_ONLY:
                    addGoal(goals, "INPUT:Holes.CENTER_HOLE_ONLY", "INPUT:holes == CENTER_HOLE_ONLY", "L0_INPUT", "Center hole only.");
                    break;
                case CENTER_AND_INTERSECTIONS:
                    addGoal(goals, "INPUT:Holes.CENTER_AND_INTERSECTIONS", "INPUT:holes == CENTER_AND_INTERSECTIONS", "L0_INPUT", "Holes at center and fold intersections.");
                    break;
                case LARGE_HOLES:
                    addGoal(goals, "INPUT:Holes.LARGE_HOLES", "INPUT:holes == LARGE_HOLES", "L0_INPUT", "Large destructive structural holes.");
                    break;
            }
        }

        // 8. Pieces Missing
        if (banknote.getPiecesMissing() != null) {
            switch (banknote.getPiecesMissing()) {
                case NO_PIECES:
                    addGoal(goals, "INPUT:Pieces.NO_PIECES", "INPUT:piecesMissing == NO_PIECES", "L0_INPUT", "No missing pieces.");
                    break;
                case SMALL_PIECE_MISSING:
                    addGoal(goals, "INPUT:Pieces.SMALL_PIECE_MISSING", "INPUT:piecesMissing == SMALL_PIECE_MISSING", "L0_INPUT", "Small piece missing on margin.");
                    break;
                case LARGE_PIECE_MISSING:
                    addGoal(goals, "INPUT:Pieces.LARGE_PIECE_MISSING", "INPUT:piecesMissing == LARGE_PIECE_MISSING", "L0_INPUT", "Large piece missing/half torn off.");
                    break;
                case MULTIPLE_PIECES_MISSING:
                    addGoal(goals, "INPUT:Pieces.MULTIPLE_PIECES_MISSING", "INPUT:piecesMissing == MULTIPLE_PIECES_MISSING", "L0_INPUT", "Multiple large missing pieces.");
                    break;
            }
        }

        // 9. Staples / Pin holes
        if (banknote.getStaplePinHoles() != null) {
            switch (banknote.getStaplePinHoles()) {
                case NONE:
                    addGoal(goals, "INPUT:Staples.NONE", "INPUT:staplePinHoles == NONE", "L0_INPUT", "No staple or pin holes.");
                    break;
                case ONE_TWO_HOLES:
                    addGoal(goals, "INPUT:Staples.ONE_TWO_HOLES", "INPUT:staplePinHoles == ONE_TWO_HOLES", "L0_INPUT", "One or two staple holes.");
                    break;
                case MULTIPLE_HOLES:
                    addGoal(goals, "INPUT:Staples.MULTIPLE_HOLES", "INPUT:staplePinHoles == MULTIPLE_HOLES", "L0_INPUT", "Multiple staple holes.");
                    break;
            }
        }

        // 10. Dirt
        if (banknote.getDirt() != null) {
            switch (banknote.getDirt()) {
                case NO_DIRT:
                    addGoal(goals, "INPUT:Dirt.NO_DIRT", "INPUT:dirt == NO_DIRT", "L0_INPUT", "No dirt.");
                    break;
                case MINIMAL:
                    addGoal(goals, "INPUT:Dirt.MINIMAL", "INPUT:dirt == MINIMAL", "L0_INPUT", "Minimal dirt.");
                    break;
                case EXCESSIVE_DIRT:
                    addGoal(goals, "INPUT:Dirt.EXCESSIVE_DIRT", "INPUT:dirt == EXCESSIVE_DIRT", "L0_INPUT", "Excessive dirt.");
                    break;
            }
        }

        // 11. Stains
        if (banknote.getStains() != null) {
            switch (banknote.getStains()) {
                case NO_STAINS:
                    addGoal(goals, "INPUT:Stains.NO_STAINS", "INPUT:stains == NO_STAINS", "L0_INPUT", "No stains.");
                    break;
                case STAINS_PRESENT:
                    addGoal(goals, "INPUT:Stains.STAINS_PRESENT", "INPUT:stains == STAINS_PRESENT", "L0_INPUT", "Stains present.");
                    break;
            }
        }

        // 12. Graffiti
        if (banknote.getGraffiti() != null) {
            switch (banknote.getGraffiti()) {
                case NO_GRAFFITI:
                    addGoal(goals, "INPUT:Graffiti.NO_GRAFFITI", "INPUT:graffiti == NO_GRAFFITI", "L0_INPUT", "No graffiti.");
                    break;
                case GRAFFITI_PRESENT:
                    addGoal(goals, "INPUT:Graffiti.GRAFFITI_PRESENT", "INPUT:graffiti == GRAFFITI_PRESENT", "L0_INPUT", "Graffiti present.");
                    break;
            }
        }

        // 13. Rust
        if (banknote.getRust() != null) {
            switch (banknote.getRust()) {
                case NO_RUST:
                    addGoal(goals, "INPUT:Rust.NO_RUST", "INPUT:rust == NO_RUST", "L0_INPUT", "No rust.");
                    break;
                case RUST_PRESENT:
                    addGoal(goals, "INPUT:Rust.RUST_PRESENT", "INPUT:rust == RUST_PRESENT", "L0_INPUT", "Rust present.");
                    break;
            }
        }

        // 14. Colour
        if (banknote.getColour() != null) {
            switch (banknote.getColour()) {
                case SMUDGING:
                    addGoal(goals, "INPUT:Colour.SMUDGING", "INPUT:colour == SMUDGING", "L0_INPUT", "Color smudging present.");
                    break;
                default:
                    break;
            }
        }

        // 15. Corners
        if (banknote.getCorners() != null) {
            switch (banknote.getCorners()) {
                case SHARP_AND_SQUARE:
                    addGoal(goals, "INPUT:Corners.SHARP_AND_SQUARE", "INPUT:corners == SHARP_AND_SQUARE", "L0_INPUT", "Corners are sharp and square.");
                    break;
                case WORN_BUT_NOT_ROUNDED:
                    addGoal(goals, "INPUT:Corners.WORN_BUT_NOT_ROUNDED", "INPUT:corners == WORN_BUT_NOT_ROUNDED", "L0_INPUT", "Corners worn but not rounded.");
                    break;
                case ROUNDED_OR_MISSING:
                    addGoal(goals, "INPUT:Corners.ROUNDED_OR_MISSING", "INPUT:corners == ROUNDED_OR_MISSING", "L0_INPUT", "Corners heavily rounded or missing.");
                    break;
            }
        }

        // 16. Wear
        if (banknote.getWear() != null) {
            switch (banknote.getWear()) {
                case NO_WEAR:
                    addGoal(goals, "INPUT:Wear.NO_WEAR", "INPUT:wear == NO_WEAR", "L0_INPUT", "No physical wear.");
                    break;
                case SHOWS_WEAR:
                    addGoal(goals, "INPUT:Wear.SHOWS_WEAR", "INPUT:wear == SHOWS_WEAR", "L0_INPUT", "Shows general wear.");
                    break;
                case DAMAGED_PAPER:
                    addGoal(goals, "INPUT:Wear.DAMAGED_PAPER", "INPUT:wear == DAMAGED_PAPER", "L0_INPUT", "Severe substrate wear (damaged paper).");
                    break;
            }
        }

        // 17. Handling
        if (banknote.getHandling() != null) {
            switch (banknote.getHandling()) {
                case NO_HANDLING:
                    addGoal(goals, "INPUT:Handling.NO_HANDLING", "INPUT:handling == NO_HANDLING", "L0_INPUT", "Perfectly preserved, no handling signs.");
                    break;
                case MINOR:
                    addGoal(goals, "INPUT:Handling.MINOR", "INPUT:handling == MINOR", "L0_INPUT", "Minor evidence of handling.");
                    break;
            }
        }

        // 18. Foil Features
        if (banknote.getFoilFeatures() != null) {
            switch (banknote.getFoilFeatures()) {
                case DAMAGED_FOLDS_BROKEN_SURFACE:
                    addGoal(goals, "INPUT:Foil.DAMAGED_FOLDS_BROKEN_SURFACE", "INPUT:foilFeatures == DAMAGED_FOLDS_BROKEN_SURFACE", "L0_INPUT", "Foil broken along fold lines.");
                    break;
                case SIGNIFICANTLY_DAMAGED:
                    addGoal(goals, "INPUT:Foil.SIGNIFICANTLY_DAMAGED", "INPUT:foilFeatures == SIGNIFICANTLY_DAMAGED", "L0_INPUT", "Foil significantly damaged.");
                    break;
                default:
                    break;
            }
        }
    }
    private static void addInputFeatures(List<Fact> goals) {
        addGoal(goals, "INPUT:Paper.FIRM", "INPUT:paper == FIRM", "L0_INPUT", "Paper is firm.");
        addGoal(goals, "INPUT:Paper.CRISP", "INPUT:paper == CRISP", "L0_INPUT", "Paper is crisp.");
        addGoal(goals, "INPUT:Paper.SOME_SOFTNESS_WRINKLED", "INPUT:paper == SOME_SOFTNESS_WRINKLED", "L0_INPUT", "Paper has some softness/wrinkles.");
        addGoal(goals, "INPUT:Paper.LIMP", "INPUT:paper == LIMP", "L0_INPUT", "Paper is limp.");
        addGoal(goals, "INPUT:Paper.TOTALLY_LIMP", "INPUT:paper == TOTALLY_LIMP", "L0_INPUT", "Paper is totally limp.");
        addGoal(goals, "INPUT:Sheen.ORIGINAL_SHEEN", "INPUT:sheen == ORIGINAL_SHEEN", "L0_INPUT", "Original natural sheen present.");
        addGoal(goals, "INPUT:Sheen.LOST_SHEEN", "INPUT:sheen == LOST_SHEEN", "L0_INPUT", "Original sheen is lost.");

        addGoal(goals, "INPUT:Folds.NO_FOLDS", "INPUT:folds == NO_FOLDS", "L0_INPUT", "No folds.");
        addGoal(goals, "INPUT:Folds.ONE_LIGHT_FOLD", "INPUT:folds == ONE_LIGHT_FOLD", "L0_INPUT", "Exactly one light fold.");
        addGoal(goals, "INPUT:Folds.UP_TO_3_LIGHT_FOLDS", "INPUT:folds == UP_TO_3_LIGHT_FOLDS", "L0_INPUT", "Up to 3 light folds.");
        addGoal(goals, "INPUT:Folds.MORE_THAN_3_LIGHT_FOLDS", "INPUT:folds == MORE_THAN_3_LIGHT_FOLDS", "L0_INPUT", "More than 3 light folds.");
        addGoal(goals, "INPUT:Folds.MANY_FOLDS", "INPUT:folds == MANY_FOLDS", "L0_INPUT", "Many folds.");
        addGoal(goals, "INPUT:Creases.NO_CREASES", "INPUT:creases == NO_CREASES", "L0_INPUT", "No creases.");
        addGoal(goals, "INPUT:Creases.ONE_CREASE", "INPUT:creases == ONE_CREASE", "L0_INPUT", "Exactly one crease.");
        addGoal(goals, "INPUT:Creases.MORE_THAN_1_CREASE", "INPUT:creases == MORE_THAN_1_CREASE", "L0_INPUT", "More than one crease.");
        addGoal(goals, "INPUT:Creases.MANY_CREASES", "INPUT:creases == MANY_CREASES", "L0_INPUT", "Many creases.");
        addGoal(goals, "INPUT:Wrinkles.NO_WRINKLES", "INPUT:wrinkles == NO_WRINKLES", "L0_INPUT", "No wrinkles.");
        addGoal(goals, "INPUT:Wrinkles.FEW_WRINKLES", "INPUT:wrinkles == FEW_WRINKLES", "L0_INPUT", "Few wrinkles.");

        addGoal(goals, "INPUT:Tears.NO_TEARS", "INPUT:tears == NO_TEARS", "L0_INPUT", "No tears.");
        addGoal(goals, "INPUT:Tears.MINOR_MARGINS_ONLY", "INPUT:tears == MINOR_MARGINS_ONLY", "L0_INPUT", "Minor tears on margins only.");
        addGoal(goals, "INPUT:Tears.MINOR_INTO_DESIGN", "INPUT:tears == MINOR_INTO_DESIGN", "L0_INPUT", "Minor tears extending into design.");
        addGoal(goals, "INPUT:Tears.LARGE_TEARS", "INPUT:tears == LARGE_TEARS", "L0_INPUT", "Large structural tears.");
        addGoal(goals, "INPUT:Holes.NO_HOLES", "INPUT:holes == NO_HOLES", "L0_INPUT", "No holes.");
        addGoal(goals, "INPUT:Holes.CENTER_HOLE_ONLY", "INPUT:holes == CENTER_HOLE_ONLY", "L0_INPUT", "Center hole only.");
        addGoal(goals, "INPUT:Holes.CENTER_AND_INTERSECTIONS", "INPUT:holes == CENTER_AND_INTERSECTIONS", "L0_INPUT", "Holes at center and fold intersections.");
        addGoal(goals, "INPUT:Holes.LARGE_HOLES", "INPUT:holes == LARGE_HOLES", "L0_INPUT", "Large destructive structural holes.");
        addGoal(goals, "INPUT:Pieces.NO_PIECES", "INPUT:piecesMissing == NO_PIECES", "L0_INPUT", "No missing pieces.");
        addGoal(goals, "INPUT:Pieces.SMALL_PIECE_MISSING", "INPUT:piecesMissing == SMALL_PIECE_MISSING", "L0_INPUT", "Small piece missing on margin.");
        addGoal(goals, "INPUT:Pieces.LARGE_PIECE_MISSING", "INPUT:piecesMissing == LARGE_PIECE_MISSING", "L0_INPUT", "Large piece missing/half torn off.");
        addGoal(goals, "INPUT:Pieces.MULTIPLE_PIECES_MISSING", "INPUT:piecesMissing == MULTIPLE_PIECES_MISSING", "L0_INPUT", "Multiple large missing pieces.");
        addGoal(goals, "INPUT:Staples.NONE", "INPUT:staplePinHoles == NONE", "L0_INPUT", "No staple or pin holes.");
        addGoal(goals, "INPUT:Staples.ONE_TWO_HOLES", "INPUT:staplePinHoles == ONE_TWO_HOLES", "L0_INPUT", "One or two staple holes.");
        addGoal(goals, "INPUT:Staples.MULTIPLE_HOLES", "INPUT:staplePinHoles == MULTIPLE_HOLES", "L0_INPUT", "Multiple staple holes.");

        addGoal(goals, "INPUT:Dirt.NO_DIRT", "INPUT:dirt == NO_DIRT", "L0_INPUT", "No dirt.");
        addGoal(goals, "INPUT:Dirt.MINIMAL", "INPUT:dirt == MINIMAL", "L0_INPUT", "Minimal dirt.");
        addGoal(goals, "INPUT:Dirt.EXCESSIVE_DIRT", "INPUT:dirt == EXCESSIVE_DIRT", "L0_INPUT", "Excessive dirt.");
        addGoal(goals, "INPUT:Stains.NO_STAINS", "INPUT:stains == NO_STAINS", "L0_INPUT", "No stains.");
        addGoal(goals, "INPUT:Stains.STAINS_PRESENT", "INPUT:stains == STAINS_PRESENT", "L0_INPUT", "Stains present.");
        addGoal(goals, "INPUT:Graffiti.NO_GRAFFITI", "INPUT:graffiti == NO_GRAFFITI", "L0_INPUT", "No graffiti.");
        addGoal(goals, "INPUT:Graffiti.GRAFFITI_PRESENT", "INPUT:graffiti == GRAFFITI_PRESENT", "L0_INPUT", "Graffiti present.");
        addGoal(goals, "INPUT:Rust.NO_RUST", "INPUT:rust == NO_RUST", "L0_INPUT", "No rust.");
        addGoal(goals, "INPUT:Rust.RUST_PRESENT", "INPUT:rust == RUST_PRESENT", "L0_INPUT", "Rust present.");
        addGoal(goals, "INPUT:Colour.SMUDGING", "INPUT:colour == SMUDGING", "L0_INPUT", "Color smudging present.");
        addGoal(goals, "INPUT:Corners.SHARP_AND_SQUARE", "INPUT:corners == SHARP_AND_SQUARE", "L0_INPUT", "Corners are sharp and square.");
        addGoal(goals, "INPUT:Corners.WORN_BUT_NOT_ROUNDED", "INPUT:corners == WORN_BUT_NOT_ROUNDED", "L0_INPUT", "Corners worn but not rounded.");
        addGoal(goals, "INPUT:Corners.ROUNDED_OR_MISSING", "INPUT:corners == ROUNDED_OR_MISSING", "L0_INPUT", "Corners heavily rounded or missing.");
        addGoal(goals, "INPUT:Wear.NO_WEAR", "INPUT:wear == NO_WEAR", "L0_INPUT", "No physical wear.");
        addGoal(goals, "INPUT:Wear.SHOWS_WEAR", "INPUT:wear == SHOWS_WEAR", "L0_INPUT", "Shows general wear.");
        addGoal(goals, "INPUT:Wear.DAMAGED_PAPER", "INPUT:wear == DAMAGED_PAPER", "L0_INPUT", "Severe substrate wear (damaged paper).");
        addGoal(goals, "INPUT:Handling.NO_HANDLING", "INPUT:handling == NO_HANDLING", "L0_INPUT", "Perfectly preserved, no handling signs.");
        addGoal(goals, "INPUT:Handling.MINOR", "INPUT:handling == MINOR", "L0_INPUT", "Minor evidence of handling.");
        addGoal(goals, "INPUT:Foil.DAMAGED_FOLDS_BROKEN_SURFACE", "INPUT:foilFeatures == DAMAGED_FOLDS_BROKEN_SURFACE", "L0_INPUT", "Foil broken along fold lines.");
        addGoal(goals, "INPUT:Foil.SIGNIFICANTLY_DAMAGED", "INPUT:foilFeatures == SIGNIFICANTLY_DAMAGED", "L0_INPUT", "Foil significantly damaged.");
    }

    private static void addIntermediateFacts(List<Fact> goals) {
        addAllRequiredGroup(goals, "DERIVED:PaperStatus.PREMIUM", "Paper Status - PREMIUM", "INPUT:Paper.FIRM", "INPUT:Sheen.ORIGINAL_SHEEN");
        addAllRequiredGroup(goals, "DERIVED:PaperStatus.STRONG", "Paper Status - STRONG", "INPUT:Paper.CRISP", "INPUT:Sheen.LOST_SHEEN");
        addGoal(goals, "DERIVED:PaperStatus.SOFTENED", "INPUT:Paper.SOME_SOFTNESS_WRINKLED", "L_CONDITION", "Paper Status - SOFTENED");
        addGoal(goals, "DERIVED:PaperStatus.DEGRADED", "INPUT:Paper.LIMP", "L_CONDITION", "Paper Status - DEGRADED (Limp)");
        addGoal(goals, "DERIVED:PaperStatus.DEGRADED", "INPUT:Paper.TOTALLY_LIMP", "L_CONDITION", "Paper Status - DEGRADED (Totally Limp)");

        addAllRequiredGroup(goals, "DERIVED:FoldingLevel.NO_FOLDS", "Folding Level - NO_FOLDS", "INPUT:Folds.NO_FOLDS", "INPUT:Creases.NO_CREASES", "INPUT:Wrinkles.NO_WRINKLES");
        addGoal(goals, "DERIVED:FoldingLevel.MINIMAL_FOLDS", "INPUT:Folds.ONE_LIGHT_FOLD", "L_CONDITION", "Folding Level - MINIMAL_FOLDS (1 light fold)");
        addGoal(goals, "DERIVED:FoldingLevel.MINIMAL_FOLDS", "INPUT:Wrinkles.FEW_WRINKLES", "L_CONDITION", "Folding Level - MINIMAL_FOLDS (Few wrinkles)");
        addGoal(goals, "DERIVED:FoldingLevel.LIGHTLY_FOLDED", "INPUT:Folds.UP_TO_3_LIGHT_FOLDS", "L_CONDITION", "Folding Level - LIGHTLY_FOLDED (Up to 3 light folds)");
        addGoal(goals, "DERIVED:FoldingLevel.LIGHTLY_FOLDED", "INPUT:Creases.ONE_CREASE", "L_CONDITION", "Folding Level - LIGHTLY_FOLDED (1 crease)");
        addGoal(goals, "DERIVED:FoldingLevel.HEAVILY_FOLDED", "INPUT:Folds.MORE_THAN_3_LIGHT_FOLDS", "L_CONDITION", "Folding Level - HEAVILY_FOLDED (More than 3 light folds)");
        addGoal(goals, "DERIVED:FoldingLevel.HEAVILY_FOLDED", "INPUT:Folds.MANY_FOLDS", "L_CONDITION", "Folding Level - HEAVILY_FOLDED (Many folds)");
        addGoal(goals, "DERIVED:FoldingLevel.HEAVILY_FOLDED", "INPUT:Creases.MORE_THAN_1_CREASE", "L_CONDITION", "Folding Level - HEAVILY_FOLDED (More than 1 crease)");
        addGoal(goals, "DERIVED:FoldingLevel.HEAVILY_FOLDED", "INPUT:Creases.MANY_CREASES", "L_CONDITION", "Folding Level - HEAVILY_FOLDED (Many creases)");

        addAllRequiredGroup(goals, "DERIVED:Integrity.INTACT", "Integrity - INTACT", "INPUT:Tears.NO_TEARS", "INPUT:Holes.NO_HOLES", "INPUT:Pieces.NO_PIECES", "INPUT:Staples.NONE");
        addGoal(goals, "DERIVED:Integrity.MINOR_DAMAGE", "INPUT:Tears.MINOR_MARGINS_ONLY", "L_CONDITION", "Integrity - MINOR_DAMAGE (Tears)");
        addGoal(goals, "DERIVED:Integrity.MINOR_DAMAGE", "INPUT:Staples.ONE_TWO_HOLES", "L_CONDITION", "Integrity - MINOR_DAMAGE (Staple/Pin holes)");
        addGoal(goals, "DERIVED:Integrity.MODERATE_DAMAGE", "INPUT:Tears.MINOR_INTO_DESIGN", "L_CONDITION", "Integrity - MODERATE_DAMAGE (Tears into design)");
        addGoal(goals, "DERIVED:Integrity.MODERATE_DAMAGE", "INPUT:Holes.CENTER_HOLE_ONLY", "L_CONDITION", "Integrity - MODERATE_DAMAGE (Center hole)");
        addGoal(goals, "DERIVED:Integrity.MODERATE_DAMAGE", "INPUT:Pieces.SMALL_PIECE_MISSING", "L_CONDITION", "Integrity - MODERATE_DAMAGE (Small piece missing)");
        addGoal(goals, "DERIVED:Integrity.MODERATE_DAMAGE", "INPUT:Staples.MULTIPLE_HOLES", "L_CONDITION", "Integrity - MODERATE_DAMAGE (Multiple staple holes)");
        addGoal(goals, "DERIVED:Integrity.SEVERE_DAMAGE", "INPUT:Tears.LARGE_TEARS", "L_CONDITION", "Integrity - SEVERE_DAMAGE (Large tears)");
        addGoal(goals, "DERIVED:Integrity.SEVERE_DAMAGE", "INPUT:Holes.LARGE_HOLES", "L_CONDITION", "Integrity - SEVERE_DAMAGE (Large holes)");
        addGoal(goals, "DERIVED:Integrity.SEVERE_DAMAGE", "INPUT:Holes.CENTER_AND_INTERSECTIONS", "L_CONDITION", "Integrity - SEVERE_DAMAGE (Center and intersections)");
        addGoal(goals, "DERIVED:Integrity.SEVERE_DAMAGE", "INPUT:Pieces.LARGE_PIECE_MISSING", "L_CONDITION", "Integrity - SEVERE_DAMAGE (Large piece missing)");
        addGoal(goals, "DERIVED:Integrity.SEVERE_DAMAGE", "INPUT:Pieces.MULTIPLE_PIECES_MISSING", "L_CONDITION", "Integrity - SEVERE_DAMAGE (Multiple pieces missing)");

        addAllRequiredGroup(goals, "DERIVED:Cleanliness.IMMACULATE", "Cleanliness - IMMACULATE", "INPUT:Dirt.NO_DIRT", "INPUT:Stains.NO_STAINS", "INPUT:Graffiti.NO_GRAFFITI", "INPUT:Rust.NO_RUST");
        addGoal(goals, "DERIVED:Cleanliness.LIGHTLY_SOILED", "INPUT:Dirt.MINIMAL", "L_CONDITION", "Cleanliness - LIGHTLY_SOILED (Minimal dirt)");
        addGoal(goals, "DERIVED:Cleanliness.LIGHTLY_SOILED", "INPUT:Colour.SMUDGING", "L_CONDITION", "Cleanliness - LIGHTLY_SOILED (Smudging)");
        addGoal(goals, "DERIVED:Cleanliness.CONTAMINATED", "INPUT:Dirt.EXCESSIVE_DIRT", "L_CONDITION", "Cleanliness - CONTAMINATED (Excessive dirt)");
        addGoal(goals, "DERIVED:Cleanliness.CONTAMINATED", "INPUT:Stains.STAINS_PRESENT", "L_CONDITION", "Cleanliness - CONTAMINATED (Stains)");
        addGoal(goals, "DERIVED:Cleanliness.CONTAMINATED", "INPUT:Graffiti.GRAFFITI_PRESENT", "L_CONDITION", "Cleanliness - CONTAMINATED (Graffiti)");
        addGoal(goals, "DERIVED:Cleanliness.CONTAMINATED", "INPUT:Rust.RUST_PRESENT", "L_CONDITION", "Cleanliness - CONTAMINATED (Rust)");

        addAllRequiredGroup(goals, "DERIVED:PhysicalWear.LOW", "Physical Wear - LOW", "INPUT:Corners.SHARP_AND_SQUARE", "INPUT:Wear.NO_WEAR");
        addAllRequiredGroup(goals, "DERIVED:PhysicalWear.MEDIUM", "Physical Wear - MEDIUM", "INPUT:Corners.WORN_BUT_NOT_ROUNDED", "INPUT:Wear.SHOWS_WEAR");
        addGoal(goals, "DERIVED:PhysicalWear.HIGH", "INPUT:Corners.ROUNDED_OR_MISSING", "L_CONDITION", "Physical Wear - HIGH (Corners)");
        addGoal(goals, "DERIVED:PhysicalWear.HIGH", "INPUT:Wear.DAMAGED_PAPER", "L_CONDITION", "Physical Wear - HIGH (Paper)");
    }

    private static void addGlobalStatusAndLimits(List<Fact> goals) {
        addAllRequiredGroup(goals, "STATUS:COLLECTOR_GRADE", "Global Status - COLLECTOR_GRADE", "DERIVED:PaperStatus.PREMIUM", "DERIVED:FoldingLevel.NO_FOLDS", "DERIVED:Integrity.INTACT", "DERIVED:Cleanliness.IMMACULATE");
        addAllRequiredGroup(goals, "STATUS:EXCELLENT_CONDITION", "Global Status - EXCELLENT_CONDITION (Minimal Folds)", "DERIVED:FoldingLevel.MINIMAL_FOLDS", "DERIVED:Integrity.INTACT");
        addAllRequiredGroup(goals, "STATUS:EXCELLENT_CONDITION", "Global Status - EXCELLENT_CONDITION (Lightly Folded)", "DERIVED:FoldingLevel.LIGHTLY_FOLDED", "DERIVED:Integrity.INTACT");
        addGoal(goals, "STATUS:CIRCULATED", "DERIVED:PaperStatus.STRONG", "L_CONDITION", "Global Status - CIRCULATED (Strong Paper)");
        addGoal(goals, "STATUS:CIRCULATED", "DERIVED:FoldingLevel.HEAVILY_FOLDED", "L_CONDITION", "Global Status - CIRCULATED (Heavily Folded)");
        addGoal(goals, "STATUS:POOR_CONDITION", "DERIVED:PaperStatus.DEGRADED", "L_CONDITION", "Global Status - POOR_CONDITION (Degraded Paper)");
        addGoal(goals, "STATUS:POOR_CONDITION", "DERIVED:Cleanliness.CONTAMINATED", "L_CONDITION", "Global Status - POOR_CONDITION (Contaminated)");

        addGoal(goals, "LIMIT:MAX_VERY_GOOD", "DERIVED:Integrity.MODERATE_DAMAGE", "L_CONDITION", "Grade Limit - MAX_VERY_GOOD (Moderate Damage)");
        addGoal(goals, "LIMIT:MAX_VERY_GOOD", "INPUT:Corners.ROUNDED_OR_MISSING", "L_CONDITION", "Grade Limit - MAX_VERY_GOOD (Rounded Corners)");
        addGoal(goals, "LIMIT:MAX_FAIR", "DERIVED:Integrity.SEVERE_DAMAGE", "L_CONDITION", "Grade Limit - MAX_FAIR (Severe Damage)");
        addGoal(goals, "LIMIT:MAX_FAIR", "INPUT:Wear.DAMAGED_PAPER", "L_CONDITION", "Grade Limit - MAX_FAIR (Damaged Paper)");
        addGoal(goals, "LIMIT:MAX_VERY_FINE", "INPUT:Foil.DAMAGED_FOLDS_BROKEN_SURFACE", "L_CONDITION", "Grade Limit - MAX_VERY_FINE (Foil Broken Surface)");
        addGoal(goals, "LIMIT:MAX_VERY_FINE", "INPUT:Foil.SIGNIFICANTLY_DAMAGED", "L_CONDITION", "Grade Limit - MAX_VERY_FINE (Foil Significantly Damaged)");

        addGoal(goals, "LIMIT:NO_LIMIT", "NOT:LIMIT:MAX_VERY_GOOD AND NOT:LIMIT:MAX_FAIR AND NOT:LIMIT:MAX_VERY_FINE", "L1_CONDITION", "No active evaluation limit restricts the grade.");
    }

    private static void addFinalGradingGoals(List<Fact> goals) {
        addAllRequiredGroup(goals, "GRADE:UNCIRCULATED", "Grade - Uncirculated (UNCIRCULATED)",
                "INPUT:Handling.NO_HANDLING", "STATUS:COLLECTOR_GRADE", "DERIVED:PhysicalWear.LOW", "LIMIT:NO_LIMIT");

        addAllRequiredGroup(goals, "GRADE:ABOUT_UNCIRCULATED", "Grade - About Uncirculated (ABOUT_UNCIRCULATED - Handling)",
                "INPUT:Handling.MINOR", "STATUS:EXCELLENT_CONDITION", "LIMIT:NO_LIMIT");

        addAllRequiredGroup(goals, "GRADE:ABOUT_UNCIRCULATED", "Grade - About Uncirculated (ABOUT_UNCIRCULATED - Minimal Folds)",
                "STATUS:EXCELLENT_CONDITION", "DERIVED:FoldingLevel.MINIMAL_FOLDS", "LIMIT:NO_LIMIT");

        addAllRequiredGroup(goals, "GRADE:EXTREMELY_FINE", "Grade - Extremely Fine (EXTREMELY_FINE)",
                "STATUS:EXCELLENT_CONDITION", "DERIVED:FoldingLevel.LIGHTLY_FOLDED", "LIMIT:NO_LIMIT");

        addAllRequiredGroup(goals, "GRADE:VERY_FINE", "Grade - Very Fine (VERY_FINE)",
                "INPUT:Corners.WORN_BUT_NOT_ROUNDED", "STATUS:CIRCULATED", "DERIVED:PaperStatus.STRONG", "DERIVED:Cleanliness.LIGHTLY_SOILED", "LIMIT:NO_LIMIT");

        addAllRequiredGroup(goals, "GRADE:FINE", "Grade - Fine (FINE)",
                "STATUS:CIRCULATED", "DERIVED:PaperStatus.SOFTENED", "DERIVED:Integrity.MINOR_DAMAGE");

        addAllRequiredGroup(goals, "GRADE:VERY_GOOD", "Grade - Very Good (VERY_GOOD)",
                "INPUT:Holes.CENTER_HOLE_ONLY", "DERIVED:PaperStatus.DEGRADED", "DERIVED:Integrity.MODERATE_DAMAGE", "LIMIT:MAX_VERY_GOOD");

        addAllRequiredGroup(goals, "GRADE:GOOD", "Grade - Good (GOOD)",
                "INPUT:Pieces.SMALL_PIECE_MISSING", "INPUT:Holes.CENTER_AND_INTERSECTIONS", "DERIVED:PaperStatus.DEGRADED", "LIMIT:MAX_VERY_GOOD");

        addAllRequiredGroup(goals, "GRADE:FAIR", "Grade - Fair (FAIR)",
                "INPUT:Pieces.LARGE_PIECE_MISSING", "INPUT:Tears.LARGE_TEARS", "STATUS:POOR_CONDITION");

        addAllRequiredGroup(goals, "GRADE:POOR", "Grade - Poor (POOR - Pieces)",
                "INPUT:Pieces.MULTIPLE_PIECES_MISSING", "STATUS:POOR_CONDITION", "LIMIT:MAX_FAIR");

        addAllRequiredGroup(goals, "GRADE:POOR", "Grade - Poor (POOR - Holes)",
                "INPUT:Holes.LARGE_HOLES", "STATUS:POOR_CONDITION", "LIMIT:MAX_FAIR");
    }

    private static void addAllRequiredGroup(List<Fact> goals, String goal, String description, String... conditions) {
        String group = "ALL:" + description;
        addGoal(goals, goal, group, "L_AND_GROUP", "All requirements in this group must hold: " + description);
        for (String condition : conditions) {
            addGoal(goals, group, condition, "L_CONDITION", "Condition for: " + description);
        }
    }

    private static void addGoal(List<Fact> goals, String goal, String requirement, String level, String explanation) {
        goals.add(new Fact(goal, requirement, level, explanation));
    }
}