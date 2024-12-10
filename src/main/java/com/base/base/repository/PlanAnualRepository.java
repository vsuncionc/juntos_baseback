package com.base.base.repository;


import com.base.base.dto.request.ConsultaPlanAnualRequest;
import com.base.base.model.PlanAnual;

import java.util.List;

public interface PlanAnualRepository {
    List<PlanAnual> buscarPlanes(ConsultaPlanAnualRequest consultaGenerico );
    void insertarPlan(ConsultaPlanAnualRequest consultaGenerico);
}
