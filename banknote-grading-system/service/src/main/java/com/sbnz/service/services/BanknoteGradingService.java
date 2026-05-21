package com.sbnz.service.services;

import com.sbnz.model.models.Banknote;
import com.sbnz.model.models.EvaluationResult;
import com.sbnz.model.models.FactConclusion;
import com.sbnz.service.dtos.BanknoteGradingRequestDTO;
import com.sbnz.service.dtos.BanknoteGradingResponseDTO;
import com.sbnz.service.mappers.BanknoteMapper;
import lombok.AllArgsConstructor;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

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
}
