package com.base.base.service;


import com.base.base.dto.request.ConsultaPlanAnualRequest;
import com.base.base.model.PlanAnual;

import java.util.List;

public interface PlanAnualService {
    List<PlanAnual> buscarPlanes(ConsultaPlanAnualRequest consultaGenerico );
    int insertarPlan(ConsultaPlanAnualRequest consultaGenerico);

}
