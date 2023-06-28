package com.field.muzi.repository;


import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.web.admin.dto.form.AdminFormListRequest;
import com.field.muzi.web.admin.dto.form.AdminFormListResponse;
import com.field.muzi.web.user.dto.form.FormListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FormQueryRepository {
    Page<FormListResponse> formListByMember(Pageable pageable, MemberEntity member);

    Page<AdminFormListResponse> adminFormList(Pageable pageable, AdminFormListRequest request);
}
