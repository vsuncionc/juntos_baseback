package com.base.base.convert;

import com.base.base.dto.response.ConsultaPlanAnualResponse;
import com.base.base.model.PlanAnual;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PlanAnualConvert {

public ConsultaPlanAnualResponse convertConsultaPlanAnualToDto(PlanAnual planAnual){
  return  new ConsultaPlanAnualResponse(
          planAnual.getPlananual(),
          planAnual.getCtitulo(),
          planAnual.getCdescripcion(),
          planAnual.getCperiodo(),
          planAnual.getCanio(),
          planAnual.getNcantpreguntas(),
          planAnual.getFcreacion()
  ) ;
}

    public List<ConsultaPlanAnualResponse> convertConsultaPlanAnualToDto(List<PlanAnual> planAnual){
        return planAnual.stream()
                .map(this::convertConsultaPlanAnualToDto)
                .toList();
    }





}
