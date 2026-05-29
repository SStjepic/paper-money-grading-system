package com.sbnz.service.controllers;

import com.sbnz.model.enums.IBNSGrade;
import com.sbnz.service.dtos.BanknoteGradingRequestDTO;
import com.sbnz.service.dtos.BanknoteGradingResponseDTO;
import com.sbnz.service.dtos.GradeCheckRequestDTO;
import com.sbnz.service.dtos.GradingRequirementDTO;
import com.sbnz.service.services.BanknoteGradingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/banknote")
@AllArgsConstructor
public class BanknoteGradingController {

    private final BanknoteGradingService banknoteGradingService;

    @PostMapping("/grade")
    public ResponseEntity<BanknoteGradingResponseDTO> evaluateBanknote(@RequestBody BanknoteGradingRequestDTO request) {
        return ResponseEntity.ok(banknoteGradingService.evaluateBanknoteForward(request));
    }

    @PostMapping("/check-achievable")
    public ResponseEntity<Boolean> evaluateBanknote(@RequestBody GradeCheckRequestDTO request) {
        return ResponseEntity.ok(banknoteGradingService.isGradeAchievable(request));
    }

    @GetMapping("/requirements/{grade}")
    public ResponseEntity<List<GradingRequirementDTO>> getRequirementsForGrade(@PathVariable IBNSGrade grade) {
        return ResponseEntity.ok(banknoteGradingService.getRequirementsForGrade(grade));
    }

    @PostMapping("/missing-inputs")
    public ResponseEntity<List<String>> getMissingInputs(@RequestBody GradeCheckRequestDTO request) {
        return ResponseEntity.ok(banknoteGradingService.findMissingInputs(request));
    }
}
