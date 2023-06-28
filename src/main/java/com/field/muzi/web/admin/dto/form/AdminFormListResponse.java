package com.field.muzi.web.admin.dto.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminFormListResponse {

    private int rowNum;
    private String formSeq;
    private String title;
    private String category;
    private String createDate;
    private String confirmedDate;
    private String answer;
    private String memberName;

}
