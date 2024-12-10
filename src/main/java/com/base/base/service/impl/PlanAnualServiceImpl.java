package com.base.base.service.impl;

import com.base.base.dto.request.ConsultaPlanAnualRequest;
import com.base.base.model.PlanAnual;
import com.base.base.repository.PlanAnualRepository;
import com.base.base.service.PlanAnualService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanAnualServiceImpl implements PlanAnualService {

private final PlanAnualRepository planAnualRepository;
    @Override
    public List<PlanAnual> buscarPlanes(ConsultaPlanAnualRequest consultaGenerico) {
        return planAnualRepository.buscarPlanes(consultaGenerico);
    }

    @Override
    public int insertarPlan(ConsultaPlanAnualRequest consultaGenerico) {
        planAnualRepository.insertarPlan(consultaGenerico);
        return 0;
    }

}
