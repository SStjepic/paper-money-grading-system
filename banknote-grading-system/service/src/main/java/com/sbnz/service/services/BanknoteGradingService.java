package com.sbnz.service.services;

import com.sbnz.kjar.BanknoteGradingFacts;
import com.sbnz.model.enums.IBNSGrade;
import com.sbnz.model.models.Banknote;
import com.sbnz.model.models.EvaluationResult;
import com.sbnz.model.models.Fact;
import com.sbnz.model.models.FactConclusion;
import com.sbnz.service.dtos.BanknoteGradingRequestDTO;
import com.sbnz.service.dtos.BanknoteGradingResponseDTO;
import com.sbnz.service.dtos.GradeCheckRequestDTO;
import com.sbnz.service.dtos.GradingRequirementDTO;
import com.sbnz.service.mappers.BanknoteMapper;
import lombok.AllArgsConstructor;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.kie.api.runtime.rule.Variable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BanknoteGradingService {
    private final KieContainer kieContainer;
    private final BanknoteMapper banknoteMapper;


    public BanknoteGradingResponseDTO evaluateBanknoteForward(BanknoteGradingRequestDTO requestDTO) {
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");

        Banknote banknote = banknoteMapper.toBanknote(requestDTO);

        FactConclusion conclusion = new FactConclusion(banknote.getId());
        EvaluationResult result = new EvaluationResult(banknote.getId());

        kieSession.insert(banknote);
        kieSession.insert(conclusion);
        kieSession.insert(result);


        kieSession.getAgenda().getAgendaGroup("final-grading").setFocus();
        kieSession.getAgenda().getAgendaGroup("global-status-limits").setFocus();
        kieSession.getAgenda().getAgendaGroup("intermediate-facts").setFocus();


        kieSession.fireAllRules();

        BanknoteGradingResponseDTO responseDTO = banknoteMapper.toBanknoteGradingResponseDTO(result);

        kieSession.dispose();

        return responseDTO;
    }

    public boolean isGradeAchievable(GradeCheckRequestDTO requestDTO) {
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");

        boolean isGradeAchievable = false;
        Banknote banknote = banknoteMapper.toBanknote(requestDTO.getBanknote());
        for(Fact fact: BanknoteGradingFacts.createGradingGoals(banknote)) {
            kieSession.insert(fact);
        }

        QueryResults results = kieSession.getQueryResults("isGradeAchievable", requestDTO.getGrade().toString());
        if (results.size() > 0) {
            isGradeAchievable = true;
        }
        kieSession.dispose();

        return isGradeAchievable;
    }

    public List<GradingRequirementDTO> getRequirementsForGrade(IBNSGrade targetGradeCode) {
        List<GradingRequirementDTO> requirements = new ArrayList<>();

        KieSession kieSession = kieContainer.newKieSession("ksession-rules");
        for(Fact fact: BanknoteGradingFacts.createGradingGoals()) {
            kieSession.insert(fact);
        }
        QueryResults results = kieSession.getQueryResults("requirementsForBanknoteGrade", targetGradeCode.toString(), Variable.v, Variable.v, Variable.v);
        for (QueryResultsRow row : results) {
            String requirement = (String) row.get("$requirement");
            String level = (String) row.get("$level");
            String explanation = (String) row.get("$explanation");

            requirements.add(new GradingRequirementDTO(targetGradeCode, requirement, level, explanation));
        }

        kieSession.dispose();

        return requirements;
    }
}
