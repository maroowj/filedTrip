package com.field.muzi.web.admin.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminMemberListResponse {

    private int rowNum;
    private String memberSeq;
    private String kakaoEmail;
    private String memberName;
    private String createDate;
}
