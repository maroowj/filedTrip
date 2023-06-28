package com.field.muzi.web.admin.dto.member;

import com.field.muzi.domain.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminMemberDetailsResponse {

    private String memberSeq;
    private String kakaoEmail;
    private String memberName;
    private String ageRange;
    private String gender;
    private String birth;
    private String createDate;

    public AdminMemberDetailsResponse(MemberEntity member) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.setMemberSeq(member.getMemberSeq());
        this.setKakaoEmail(member.getMemberInfo().getEmail());
        this.setMemberName(member.getMemberInfo().getMemberName());
        this.setAgeRange(member.getMemberInfo().getAgeRange());
        this.setGender(member.getMemberInfo().getGender());
        this.setBirth(member.getMemberInfo().getBirth());
        this.setCreateDate(sdf.format(member.getMemberInfo().getCreateDate()));
    }
}
