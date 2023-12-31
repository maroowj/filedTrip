package com.field.muzi.web.admin.dto.unitCost;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitCostUpdateRequest {

    private int busFare;
    private int guideFee;
    private int insuranceFee;
}
