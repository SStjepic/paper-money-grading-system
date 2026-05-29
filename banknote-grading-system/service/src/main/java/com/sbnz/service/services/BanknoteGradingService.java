package com.sbnz.service.services;

import com.sbnz.kjar.BanknoteGradingFacts;
import com.sbnz.kjar.factory.ForwardChainingKieBaseFactory;
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
import org.kie.api.KieBase;
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
        KieBase kieBase = ForwardChainingKieBaseFactory.createKieBase();
        KieSession session = kieBase.newKieSession();

        Banknote banknote = banknoteMapper.toBanknote(requestDTO);

        FactConclusion conclusion = new FactConclusion(banknote.getId());
        EvaluationResult result = new EvaluationResult(banknote.getId());

        session.insert(banknote);
        session.insert(conclusion);
        session.insert(result);


        session.getAgenda().getAgendaGroup("final-grading").setFocus();
        session.getAgenda().getAgendaGroup("global-status-limits").setFocus();
        session.getAgenda().getAgendaGroup("intermediate-facts").setFocus();


        session.fireAllRules();

        BanknoteGradingResponseDTO responseDTO = banknoteMapper.toBanknoteGradingResponseDTO(result);

        session.dispose();

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

    public List<String> findMissingInputs(GradeCheckRequestDTO requestDTO) {
        Banknote banknote = banknoteMapper.toBanknote(requestDTO.getBanknote());
        IBNSGrade targetGrade = requestDTO.getGrade();
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");
        List<String> missingInputs = new ArrayList<>();

        for (Fact fact : BanknoteGradingFacts.createGradingGoals(banknote)) {
            kieSession.insert(fact);
        }

        QueryResults results = kieSession.getQueryResults("findMissingInputs", targetGrade.toString(), Variable.v);

        for (QueryResultsRow row : results) {
            String missingInput = (String) row.get("$missingInput");
            missingInputs.add(missingInput);
        }
        kieSession.dispose();
        return missingInputs;
    }
}
