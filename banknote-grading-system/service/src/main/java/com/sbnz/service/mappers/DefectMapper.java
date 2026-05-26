package com.sbnz.service.mappers;

import com.sbnz.model.models.Defect;
import com.sbnz.service.dtos.DefectDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DefectMapper {

    DefectDTO toDefectDTO(Defect defect);
}
