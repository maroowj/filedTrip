package com.field.muzi.web.admin.dto.diy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDiyUpdateRequest {

    private List<String> diySeqList;
    private String state;
}
