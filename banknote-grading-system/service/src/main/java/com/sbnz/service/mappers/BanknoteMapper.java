package com.sbnz.service.mappers;

import com.sbnz.model.models.Banknote;
import com.sbnz.model.models.EvaluationResult;
import com.sbnz.service.dtos.BanknoteGradingRequestDTO;
import com.sbnz.service.dtos.BanknoteGradingResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { DefectMapper.class })
public interface BanknoteMapper {

    Banknote toBanknote(BanknoteGradingRequestDTO dto);

    BanknoteGradingResponseDTO toBanknoteGradingResponseDTO(EvaluationResult result);
}
