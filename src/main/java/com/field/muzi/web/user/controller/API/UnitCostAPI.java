package com.field.muzi.web.user.controller.API;

import com.field.muzi.domain.entity.UnitCostEntity;
import com.field.muzi.web.admin.service.UnitCostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common")
public class UnitCostAPI {

    private final UnitCostService unitCostService;

    @GetMapping("/unit")
    public UnitCostEntity unitCost() {
       return unitCostService.unitCost();
    }

}
