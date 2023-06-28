package com.field.muzi.web.admin.dto.diy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDiyListResponse {

    private int rowNum;
    private String DiySeq;
    private String DiyTitle;
    private String startDate;
    private String endDate;
    private String content;
    private String etc;
    private String state;
    private String kakaoEmail;
    private String memberName;
    private String phone;
}
