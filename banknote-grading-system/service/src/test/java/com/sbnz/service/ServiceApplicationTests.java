package com.sbnz.service;

import com.sbnz.model.enums.IBNSGrade;
import com.sbnz.model.enums.InputFeatures;
import com.sbnz.model.models.Banknote;
import com.sbnz.model.models.EvaluationResult;
import com.sbnz.model.models.FactConclusion;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServiceApplicationTests {

    @Autowired
    private KieContainer kieContainer;

    @Test
    void testUncirculatedBanknote() {
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");
        assertNotNull(kieSession, "KieSession uspešno kreiran.");

        Banknote banknote = new Banknote();
        banknote.setId("AA123456789");

        banknote.setPaper(InputFeatures.Paper.FIRM);
        banknote.setColour(InputFeatures.Colour.NO_DISCOLOURATION);
        banknote.setCorners(InputFeatures.Corners.SHARP_AND_SQUARE);
        banknote.setSheen(InputFeatures.Sheen.ORIGINAL_SHEEN);
        banknote.setFoilFeatures(InputFeatures.FoilFeatures.MINOR_SCRATCHES_MANUFACTURE);
        banknote.setWrinkles(InputFeatures.Wrinkles.NO_WRINKLES);
        banknote.setFolds(InputFeatures.Folds.NO_FOLDS);
        banknote.setCreases(InputFeatures.Creases.NO_CREASES);
        banknote.setHandling(InputFeatures.Handling.NO_HANDLING);
        banknote.setWear(InputFeatures.Wear.NO_WEAR);
        banknote.setDirt(InputFeatures.Dirt.NO_DIRT);
        banknote.setStains(InputFeatures.Stains.NO_STAINS);
        banknote.setRust(InputFeatures.Rust.NO_RUST);
        banknote.setGraffiti(InputFeatures.Graffiti.NO_GRAFFITI);
        banknote.setTears(InputFeatures.Tears.NO_TEARS);
        banknote.setHoles(InputFeatures.Holes.NO_HOLES);
        banknote.setPiecesMissing(InputFeatures.PiecesMissing.NO_PIECES);
        banknote.setStaplePinHoles(InputFeatures.StaplePinHoles.NONE);

        FactConclusion conclusion = new FactConclusion("AA123456789");
        EvaluationResult result = new EvaluationResult("AA123456789");

        kieSession.insert(banknote);
        kieSession.insert(conclusion);
        kieSession.insert(result);

        kieSession.getAgenda().getAgendaGroup("final-grading").setFocus();
        kieSession.getAgenda().getAgendaGroup("global-status-limits").setFocus();
        kieSession.getAgenda().getAgendaGroup("intermediate-facts").setFocus();

        kieSession.addEventListener(new DefaultAgendaEventListener() {
            @Override
            public void afterMatchFired(AfterMatchFiredEvent event) {
                System.out.println("RULE FIRED: "
                        + event.getMatch().getRule().getName());
            }
        });
        int firedRules = kieSession.fireAllRules();
        System.out.println("Broj aktiviranih pravila za novčanicu: " + firedRules);


        assertNotNull(result.getFinalGrade(), "Procena kvaliteta papirne novcanice.");
        assertEquals(IBNSGrade.UNCIRCULATED, result.getFinalGrade(),
                "UNCIRCULATED");

        assertFalse(result.getReportSummary().isBlank(),
                "Izvestaj nije prazan");

        assertTrue(result.getReportSummary().contains("UNCIRCULATED"),
                "Postoji UNCIRCULATED izvestaj");

        kieSession.dispose();
    }

    @Test
    void testAboutUncirculatedBanknote() {
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");
        assertNotNull(kieSession, "KieSession uspešno kreiran.");

        Banknote banknote = new Banknote();
        banknote.setId("AB123456789");

        banknote.setPaper(InputFeatures.Paper.FIRM);
        banknote.setColour(InputFeatures.Colour.NO_DISCOLOURATION);
        banknote.setCorners(InputFeatures.Corners.SHARP_AND_SQUARE);
        banknote.setSheen(InputFeatures.Sheen.ORIGINAL_SHEEN);
        banknote.setFoilFeatures(InputFeatures.FoilFeatures.NUMEROUS_SCRATCHES);
        banknote.setWrinkles(InputFeatures.Wrinkles.FEW_WRINKLES);
        banknote.setFolds(InputFeatures.Folds.ONE_LIGHT_FOLD);
        banknote.setCreases(InputFeatures.Creases.NO_CREASES);
        banknote.setHandling(InputFeatures.Handling.MINOR);
        banknote.setWear(InputFeatures.Wear.NO_WEAR);
        banknote.setDirt(InputFeatures.Dirt.NO_DIRT);
        banknote.setStains(InputFeatures.Stains.NO_STAINS);
        banknote.setRust(InputFeatures.Rust.NO_RUST);
        banknote.setGraffiti(InputFeatures.Graffiti.NO_GRAFFITI);
        banknote.setTears(InputFeatures.Tears.NO_TEARS);
        banknote.setHoles(InputFeatures.Holes.NO_HOLES);
        banknote.setPiecesMissing(InputFeatures.PiecesMissing.NO_PIECES);
        banknote.setStaplePinHoles(InputFeatures.StaplePinHoles.NONE);

        FactConclusion conclusion = new FactConclusion("AB123456789");
        EvaluationResult result = new EvaluationResult("AB123456789");

        kieSession.insert(banknote);
        kieSession.insert(conclusion);
        kieSession.insert(result);

        kieSession.getAgenda().getAgendaGroup("final-grading").setFocus();
        kieSession.getAgenda().getAgendaGroup("global-status-limits").setFocus();
        kieSession.getAgenda().getAgendaGroup("intermediate-facts").setFocus();

        kieSession.addEventListener(new DefaultAgendaEventListener() {
            @Override
            public void afterMatchFired(AfterMatchFiredEvent event) {
                System.out.println("RULE FIRED: "
                        + event.getMatch().getRule().getName());
            }
        });
        int firedRules = kieSession.fireAllRules();
        System.out.println("Broj aktiviranih pravila za novčanicu: " + firedRules);


        assertNotNull(result.getFinalGrade(), "Procena kvaliteta papirne novcanice.");
        assertEquals(IBNSGrade.ABOUT_UNCIRCULATED, result.getFinalGrade(),
                "ABOUT_UNCIRCULATED");

        assertFalse(result.getReportSummary().isBlank(),
                "Izvestaj nije prazan");

        assertTrue(result.getReportSummary().contains("ABOUT UNCIRCULATED"),
                "Postoji ABOUT_UNCIRCULATED izvestaj");

        kieSession.dispose();
    }

    @Test
    void testExtremelyFineBanknote() {
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");
        assertNotNull(kieSession, "KieSession uspešno kreiran.");

        Banknote banknote = new Banknote();
        banknote.setId("AC123456789");

        banknote.setPaper(InputFeatures.Paper.FIRM);
        banknote.setColour(InputFeatures.Colour.NO_DISCOLOURATION);
        banknote.setCorners(InputFeatures.Corners.SLIGHTEST_ROUNDING);
        banknote.setSheen(InputFeatures.Sheen.ORIGINAL_SHEEN);
        banknote.setFoilFeatures(InputFeatures.FoilFeatures.MANY_SCRATCHES_DAMAGE);
        banknote.setWrinkles(InputFeatures.Wrinkles.PRESENT);
        banknote.setFolds(InputFeatures.Folds.UP_TO_3_LIGHT_FOLDS);
        banknote.setCreases(InputFeatures.Creases.ONE_CREASE);
        banknote.setHandling(InputFeatures.Handling.LIGHT);
        banknote.setWear(InputFeatures.Wear.IMPERCEPTIBLE);
        banknote.setDirt(InputFeatures.Dirt.NO_DIRT);
        banknote.setStains(InputFeatures.Stains.NO_STAINS);
        banknote.setRust(InputFeatures.Rust.NO_RUST);
        banknote.setGraffiti(InputFeatures.Graffiti.NO_GRAFFITI);
        banknote.setTears(InputFeatures.Tears.NO_TEARS);
        banknote.setHoles(InputFeatures.Holes.NO_HOLES);
        banknote.setPiecesMissing(InputFeatures.PiecesMissing.NO_PIECES);
        banknote.setStaplePinHoles(InputFeatures.StaplePinHoles.NONE);

        FactConclusion conclusion = new FactConclusion("AC123456789");
        EvaluationResult result = new EvaluationResult("AC123456789");

        kieSession.insert(banknote);
        kieSession.insert(conclusion);
        kieSession.insert(result);

        kieSession.getAgenda().getAgendaGroup("final-grading").setFocus();
        kieSession.getAgenda().getAgendaGroup("global-status-limits").setFocus();
        kieSession.getAgenda().getAgendaGroup("intermediate-facts").setFocus();

        kieSession.addEventListener(new DefaultAgendaEventListener() {
            @Override
            public void afterMatchFired(AfterMatchFiredEvent event) {
                System.out.println("RULE FIRED: "
                        + event.getMatch().getRule().getName());
            }
        });
        int firedRules = kieSession.fireAllRules();
        System.out.println("Broj aktiviranih pravila za novčanicu: " + firedRules);


        assertNotNull(result.getFinalGrade(), "Procena kvaliteta papirne novcanice.");
        assertEquals(IBNSGrade.EXTREMELY_FINE, result.getFinalGrade(),
                "EXTREMELY_FINE");

        assertFalse(result.getReportSummary().isBlank(),
                "Izvestaj nije prazan");

        assertTrue(result.getReportSummary().contains("EXTREMELY FINE"),
                "Postoji EXTREMELY_FINE izvestaj");

        kieSession.dispose();
    }

    @Test
    void testVeryFineBanknote() {
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");
        assertNotNull(kieSession, "KieSession uspešno kreiran.");

        Banknote banknote = new Banknote();
        banknote.setId("AD123456789");

        banknote.setPaper(InputFeatures.Paper.CRISP);
        banknote.setColour(InputFeatures.Colour.SMUDGING);
        banknote.setCorners(InputFeatures.Corners.WORN_BUT_NOT_ROUNDED);
        banknote.setSheen(InputFeatures.Sheen.LOST_SHEEN);
        banknote.setFoilFeatures(InputFeatures.FoilFeatures.MANY_SCRATCHES_DAMAGE);
        banknote.setWrinkles(InputFeatures.Wrinkles.PRESENT);
        banknote.setFolds(InputFeatures.Folds.MORE_THAN_3_LIGHT_FOLDS);
        banknote.setCreases(InputFeatures.Creases.MORE_THAN_1_CREASE);
        banknote.setHandling(InputFeatures.Handling.SIGNIFICANT);
        banknote.setWear(InputFeatures.Wear.SHOWS_WEAR);
        banknote.setDirt(InputFeatures.Dirt.MINIMAL);
        banknote.setStains(InputFeatures.Stains.NO_STAINS);
        banknote.setRust(InputFeatures.Rust.NO_RUST);
        banknote.setGraffiti(InputFeatures.Graffiti.NO_GRAFFITI);
        banknote.setTears(InputFeatures.Tears.NO_TEARS);
        banknote.setHoles(InputFeatures.Holes.NO_HOLES);
        banknote.setPiecesMissing(InputFeatures.PiecesMissing.NO_PIECES);
        banknote.setStaplePinHoles(InputFeatures.StaplePinHoles.NONE);

        FactConclusion conclusion = new FactConclusion("AD123456789");
        EvaluationResult result = new EvaluationResult("AD123456789");

        kieSession.insert(banknote);
        kieSession.insert(conclusion);
        kieSession.insert(result);

        kieSession.getAgenda().getAgendaGroup("final-grading").setFocus();
        kieSession.getAgenda().getAgendaGroup("global-status-limits").setFocus();
        kieSession.getAgenda().getAgendaGroup("intermediate-facts").setFocus();

        kieSession.addEventListener(new DefaultAgendaEventListener() {
            @Override
            public void afterMatchFired(AfterMatchFiredEvent event) {
                System.out.println("RULE FIRED: "
                        + event.getMatch().getRule().getName());
            }
        });
        int firedRules = kieSession.fireAllRules();
        System.out.println("Broj aktiviranih pravila za novčanicu: " + firedRules);


        assertNotNull(result.getFinalGrade(), "Procena kvaliteta papirne novcanice.");
        assertEquals(IBNSGrade.VERY_FINE, result.getFinalGrade(),
                "VERY_FINE");

        assertFalse(result.getReportSummary().isBlank(),
                "Izvestaj nije prazan");

        assertTrue(result.getReportSummary().contains("VERY FINE"),
                "Postoji VERY_FINE izvestaj");

        kieSession.dispose();
    }

    @Test
    void testFineBanknote() {
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");
        assertNotNull(kieSession, "KieSession uspešno kreiran.");

        Banknote banknote = new Banknote();
        banknote.setId("AE123456789");

        banknote.setPaper(InputFeatures.Paper.SOME_SOFTNESS_WRINKLED);
        banknote.setColour(InputFeatures.Colour.CLEAR_BUT_NOT_BRIGHT);
        banknote.setCorners(InputFeatures.Corners.WORN_BUT_NOT_ROUNDED);
        banknote.setSheen(InputFeatures.Sheen.LOST_SHEEN);
        banknote.setFoilFeatures(InputFeatures.FoilFeatures.DAMAGED_FOLDS_BROKEN_SURFACE);
        banknote.setWrinkles(InputFeatures.Wrinkles.PRESENT);
        banknote.setFolds(InputFeatures.Folds.MANY_FOLDS);
        banknote.setCreases(InputFeatures.Creases.MANY_CREASES);
        banknote.setHandling(InputFeatures.Handling.CONSIDERABLE);
        banknote.setWear(InputFeatures.Wear.CONSIDERABLE);
        banknote.setDirt(InputFeatures.Dirt.NO_EXCESSIVE_DIRT);
        banknote.setStains(InputFeatures.Stains.NO_STAINS);
        banknote.setRust(InputFeatures.Rust.NO_RUST);
        banknote.setGraffiti(InputFeatures.Graffiti.NO_GRAFFITI);
        banknote.setTears(InputFeatures.Tears.MINOR_MARGINS_ONLY);
        banknote.setHoles(InputFeatures.Holes.NO_HOLES);
        banknote.setPiecesMissing(InputFeatures.PiecesMissing.NO_PIECES);
        banknote.setStaplePinHoles(InputFeatures.StaplePinHoles.ONE_TWO_HOLES);

        FactConclusion conclusion = new FactConclusion("AE123456789");
        EvaluationResult result = new EvaluationResult("AE123456789");

        kieSession.insert(banknote);
        kieSession.insert(conclusion);
        kieSession.insert(result);

        kieSession.getAgenda().getAgendaGroup("final-grading").setFocus();
        kieSession.getAgenda().getAgendaGroup("global-status-limits").setFocus();
        kieSession.getAgenda().getAgendaGroup("intermediate-facts").setFocus();

        kieSession.addEventListener(new DefaultAgendaEventListener() {
            @Override
            public void afterMatchFired(AfterMatchFiredEvent event) {
                System.out.println("RULE FIRED: "
                        + event.getMatch().getRule().getName());
            }
        });
        int firedRules = kieSession.fireAllRules();
        System.out.println("Broj aktiviranih pravila za novčanicu: " + firedRules);


        assertNotNull(result.getFinalGrade(), "Procena kvaliteta papirne novcanice.");
        assertEquals(IBNSGrade.FINE, result.getFinalGrade(),
                "FINE");

        assertFalse(result.getReportSummary().isBlank(),
                "Izvestaj nije prazan");

        assertTrue(result.getReportSummary().contains("FINE"),
                "Postoji FINE izvestaj");

        kieSession.dispose();
    }

    @Test
    void testVeryGoodBanknote() {
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");
        assertNotNull(kieSession, "KieSession uspešno kreiran.");

        Banknote banknote = new Banknote();
        banknote.setId("AF123456789");

        banknote.setPaper(InputFeatures.Paper.LIMP);
        banknote.setColour(InputFeatures.Colour.SOME_DISCOLOURATION);
        banknote.setCorners(InputFeatures.Corners.WORN_AND_ROUNDED);
        banknote.setSheen(InputFeatures.Sheen.LOST_SHEEN);
        banknote.setFoilFeatures(InputFeatures.FoilFeatures.DAMAGED_FOLDS_BROKEN_SURFACE);
        banknote.setWrinkles(InputFeatures.Wrinkles.PRESENT);
        banknote.setFolds(InputFeatures.Folds.MANY_FOLDS);
        banknote.setCreases(InputFeatures.Creases.MANY_CREASES);
        banknote.setHandling(InputFeatures.Handling.CONSIDERABLE);
        banknote.setWear(InputFeatures.Wear.CONSIDERABLE);
        banknote.setDirt(InputFeatures.Dirt.NO_EXCESSIVE_DIRT);
        banknote.setStains(InputFeatures.Stains.STAINS_PRESENT);
        banknote.setRust(InputFeatures.Rust.RUST_PRESENT);
        banknote.setGraffiti(InputFeatures.Graffiti.NO_GRAFFITI);
        banknote.setTears(InputFeatures.Tears.MINOR_INTO_DESIGN);
        banknote.setHoles(InputFeatures.Holes.CENTER_HOLE_ONLY);
        banknote.setPiecesMissing(InputFeatures.PiecesMissing.NO_PIECES);
        banknote.setStaplePinHoles(InputFeatures.StaplePinHoles.MULTIPLE_HOLES);

        FactConclusion conclusion = new FactConclusion("AF123456789");
        EvaluationResult result = new EvaluationResult("AF123456789");

        kieSession.insert(banknote);
        kieSession.insert(conclusion);
        kieSession.insert(result);

        kieSession.getAgenda().getAgendaGroup("final-grading").setFocus();
        kieSession.getAgenda().getAgendaGroup("global-status-limits").setFocus();
        kieSession.getAgenda().getAgendaGroup("intermediate-facts").setFocus();

        kieSession.addEventListener(new DefaultAgendaEventListener() {
            @Override
            public void afterMatchFired(AfterMatchFiredEvent event) {
                System.out.println("RULE FIRED: "
                        + event.getMatch().getRule().getName());
            }
        });
        int firedRules = kieSession.fireAllRules();
        System.out.println("Broj aktiviranih pravila za novčanicu: " + firedRules);


        assertNotNull(result.getFinalGrade(), "Procena kvaliteta papirne novcanice.");
        assertEquals(IBNSGrade.VERY_GOOD, result.getFinalGrade(),
                "VERY_GOOD");

        assertFalse(result.getReportSummary().isBlank(),
                "Izvestaj nije prazan");

        assertTrue(result.getReportSummary().contains("VERY GOOD"),
                "Postoji VERY_GOOD izvestaj");

        kieSession.dispose();
    }

    @Test
    void testGoodBanknote() {
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");
        assertNotNull(kieSession, "KieSession uspešno kreiran.");

        Banknote banknote = new Banknote();
        banknote.setId("AG123456789");

        banknote.setPaper(InputFeatures.Paper.LIMP);
        banknote.setColour(InputFeatures.Colour.EXCESSIVE_DISCOLOURATION);
        banknote.setCorners(InputFeatures.Corners.ROUNDED_OR_MISSING);
        banknote.setSheen(InputFeatures.Sheen.LOST_SHEEN);
        banknote.setFoilFeatures(InputFeatures.FoilFeatures.SIGNIFICANTLY_DAMAGED);
        banknote.setWrinkles(InputFeatures.Wrinkles.PRESENT);
        banknote.setFolds(InputFeatures.Folds.MANY_FOLDS);
        banknote.setCreases(InputFeatures.Creases.MANY_CREASES);
        banknote.setHandling(InputFeatures.Handling.HEAVY);
        banknote.setWear(InputFeatures.Wear.CONSIDERABLE);
        banknote.setDirt(InputFeatures.Dirt.DIRT_PRESENT);
        banknote.setStains(InputFeatures.Stains.STAINS_PRESENT);
        banknote.setRust(InputFeatures.Rust.RUST_PRESENT);
        banknote.setGraffiti(InputFeatures.Graffiti.GRAFFITI_PRESENT);
        banknote.setTears(InputFeatures.Tears.MINOR_INTO_DESIGN);
        banknote.setHoles(InputFeatures.Holes.CENTER_AND_INTERSECTIONS);
        banknote.setPiecesMissing(InputFeatures.PiecesMissing.SMALL_PIECE_MISSING);
        banknote.setStaplePinHoles(InputFeatures.StaplePinHoles.MULTIPLE_HOLES);

        FactConclusion conclusion = new FactConclusion("AG123456789");
        EvaluationResult result = new EvaluationResult("AG123456789");

        kieSession.insert(banknote);
        kieSession.insert(conclusion);
        kieSession.insert(result);

        kieSession.getAgenda().getAgendaGroup("final-grading").setFocus();
        kieSession.getAgenda().getAgendaGroup("global-status-limits").setFocus();
        kieSession.getAgenda().getAgendaGroup("intermediate-facts").setFocus();

        kieSession.addEventListener(new DefaultAgendaEventListener() {
            @Override
            public void afterMatchFired(AfterMatchFiredEvent event) {
                System.out.println("RULE FIRED: "
                        + event.getMatch().getRule().getName());
            }
        });
        int firedRules = kieSession.fireAllRules();
        System.out.println("Broj aktiviranih pravila za novčanicu: " + firedRules);


        assertNotNull(result.getFinalGrade(), "Procena kvaliteta papirne novcanice.");
        assertEquals(IBNSGrade.GOOD, result.getFinalGrade(),
                "GOOD");

        assertFalse(result.getReportSummary().isBlank(),
                "Izvestaj nije prazan");

        assertTrue(result.getReportSummary().contains("GOOD"),
                "Postoji GOOD izvestaj");

        kieSession.dispose();
    }

    @Test
    void testFairBanknote() {
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");
        assertNotNull(kieSession, "KieSession uspešno kreiran.");

        Banknote banknote = new Banknote();
        banknote.setId("AH123456789");

        banknote.setPaper(InputFeatures.Paper.TOTALLY_LIMP);
        banknote.setColour(InputFeatures.Colour.EXCESSIVE_DISCOLOURATION);
        banknote.setCorners(InputFeatures.Corners.ROUNDED_OR_MISSING);
        banknote.setSheen(InputFeatures.Sheen.LOST_SHEEN);
        banknote.setFoilFeatures(InputFeatures.FoilFeatures.SIGNIFICANTLY_DAMAGED);
        banknote.setWrinkles(InputFeatures.Wrinkles.PRESENT);
        banknote.setFolds(InputFeatures.Folds.MANY_FOLDS);
        banknote.setCreases(InputFeatures.Creases.MANY_CREASES);
        banknote.setHandling(InputFeatures.Handling.HEAVY);
        banknote.setWear(InputFeatures.Wear.DAMAGED_PAPER);
        banknote.setDirt(InputFeatures.Dirt.EXCESSIVE_DIRT);
        banknote.setStains(InputFeatures.Stains.STAINS_PRESENT);
        banknote.setRust(InputFeatures.Rust.RUST_PRESENT);
        banknote.setGraffiti(InputFeatures.Graffiti.GRAFFITI_PRESENT);
        banknote.setTears(InputFeatures.Tears.LARGE_TEARS);
        banknote.setHoles(InputFeatures.Holes.CENTER_AND_INTERSECTIONS);
        banknote.setPiecesMissing(InputFeatures.PiecesMissing.LARGE_PIECE_MISSING);
        banknote.setStaplePinHoles(InputFeatures.StaplePinHoles.MULTIPLE_HOLES);

        FactConclusion conclusion = new FactConclusion("AH123456789");
        EvaluationResult result = new EvaluationResult("AH123456789");

        kieSession.insert(banknote);
        kieSession.insert(conclusion);
        kieSession.insert(result);

        kieSession.getAgenda().getAgendaGroup("final-grading").setFocus();
        kieSession.getAgenda().getAgendaGroup("global-status-limits").setFocus();
        kieSession.getAgenda().getAgendaGroup("intermediate-facts").setFocus();

        kieSession.addEventListener(new DefaultAgendaEventListener() {
            @Override
            public void afterMatchFired(AfterMatchFiredEvent event) {
                System.out.println("RULE FIRED: "
                        + event.getMatch().getRule().getName());
            }
        });
        int firedRules = kieSession.fireAllRules();
        System.out.println("Broj aktiviranih pravila za novčanicu: " + firedRules);


        assertNotNull(result.getFinalGrade(), "Procena kvaliteta papirne novcanice.");
        assertEquals(IBNSGrade.FAIR, result.getFinalGrade(),
                "FAIR");

        assertFalse(result.getReportSummary().isBlank(),
                "Izvestaj nije prazan");

        assertTrue(result.getReportSummary().contains("FAIR"),
                "Postoji FAIR izvestaj");

        kieSession.dispose();
    }

    @Test
    void testPoorBanknote() {
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");
        assertNotNull(kieSession, "KieSession uspešno kreiran.");

        Banknote banknote = new Banknote();
        banknote.setId("AI123456789");

        banknote.setPaper(InputFeatures.Paper.TOTALLY_LIMP);
        banknote.setColour(InputFeatures.Colour.EXCESSIVE_DISCOLOURATION);
        banknote.setCorners(InputFeatures.Corners.ROUNDED_OR_MISSING);
        banknote.setSheen(InputFeatures.Sheen.LOST_SHEEN);
        banknote.setFoilFeatures(InputFeatures.FoilFeatures.SIGNIFICANTLY_DAMAGED);
        banknote.setWrinkles(InputFeatures.Wrinkles.PRESENT);
        banknote.setFolds(InputFeatures.Folds.MANY_FOLDS);
        banknote.setCreases(InputFeatures.Creases.MANY_CREASES);
        banknote.setHandling(InputFeatures.Handling.HEAVY);
        banknote.setWear(InputFeatures.Wear.DAMAGED_PAPER);
        banknote.setDirt(InputFeatures.Dirt.EXCESSIVE_DIRT);
        banknote.setStains(InputFeatures.Stains.STAINS_PRESENT);
        banknote.setRust(InputFeatures.Rust.RUST_PRESENT);
        banknote.setGraffiti(InputFeatures.Graffiti.GRAFFITI_PRESENT);
        banknote.setTears(InputFeatures.Tears.LARGE_TEARS);
        banknote.setHoles(InputFeatures.Holes.LARGE_HOLES);
        banknote.setPiecesMissing(InputFeatures.PiecesMissing.MULTIPLE_PIECES_MISSING);
        banknote.setStaplePinHoles(InputFeatures.StaplePinHoles.MULTIPLE_HOLES);

        FactConclusion conclusion = new FactConclusion("AI123456789");
        EvaluationResult result = new EvaluationResult("AI123456789");

        kieSession.insert(banknote);
        kieSession.insert(conclusion);
        kieSession.insert(result);

        kieSession.getAgenda().getAgendaGroup("final-grading").setFocus();
        kieSession.getAgenda().getAgendaGroup("global-status-limits").setFocus();
        kieSession.getAgenda().getAgendaGroup("intermediate-facts").setFocus();

        kieSession.addEventListener(new DefaultAgendaEventListener() {
            @Override
            public void afterMatchFired(AfterMatchFiredEvent event) {
                System.out.println("RULE FIRED: "
                        + event.getMatch().getRule().getName());
            }
        });
        int firedRules = kieSession.fireAllRules();
        System.out.println("Broj aktiviranih pravila za novčanicu: " + firedRules);


        assertNotNull(result.getFinalGrade(), "Procena kvaliteta papirne novcanice.");
        assertEquals(IBNSGrade.POOR, result.getFinalGrade(),
                "POOR");

        assertFalse(result.getReportSummary().isBlank(),
                "Izvestaj nije prazan");

        assertTrue(result.getReportSummary().contains("POOR"),
                "Postoji POOR izvestaj");

        kieSession.dispose();
    }
}
