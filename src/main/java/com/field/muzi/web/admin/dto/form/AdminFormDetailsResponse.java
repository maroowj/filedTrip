package com.field.muzi.web.admin.dto.form;

import com.field.muzi.domain.entity.FormEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminFormDetailsResponse {

    private String formSeq;
    private String title;
    private Map<String, Object> content;
    private String etc;
    private String createDate;
    private String confirmedDate;
    private String answer;
    private String memberName;
    private String kakaoEmail;
    private String phone;
    private String category;

    public AdminFormDetailsResponse(FormEntity form){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.setFormSeq(form.getFormSeq());
        this.setTitle(form.getTitle());
        this.setContent(form.getContent());
        this.setEtc(form.getEtc());
        Date createdDate = java.sql.Timestamp.valueOf(form.getCreateDate());
        this.setCreateDate(sdf.format(createdDate));
        if(form.getConfirmedDate()!=null) {
            this.setConfirmedDate(sdf.format(form.getConfirmedDate()));
        }
        this.setAnswer(form.getAnswer());
        if(form.getMember().getMemberInfo().getMemberName()!=null && !form.getMember().getMemberInfo().getMemberName().equals("")) {
            this.setMemberName(form.getMember().getMemberInfo().getMemberName());
        }
        if(form.getMember().getMemberInfo().getEmail()!=null && !form.getMember().getMemberInfo().getEmail().equals("")) {
            this.setKakaoEmail(form.getMember().getMemberInfo().getEmail());
        }
        this.setPhone(form.getPhone());
        this.setCategory(form.getCategory());
    }
}
