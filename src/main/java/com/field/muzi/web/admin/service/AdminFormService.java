package com.field.muzi.web.admin.service;

import com.field.muzi.domain.entity.DiyEntity;
import com.field.muzi.domain.entity.FormEntity;
import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.repository.*;
import com.field.muzi.utils.EntityUtils;
import com.field.muzi.web.admin.dto.banner.BannerListResponse;
import com.field.muzi.web.admin.dto.diy.AdminDiyDetailsResponse;
import com.field.muzi.web.admin.dto.diy.AdminDiyListRequest;
import com.field.muzi.web.admin.dto.diy.AdminDiyListResponse;
import com.field.muzi.web.admin.dto.diy.AdminDiyUpdateRequest;
import com.field.muzi.web.admin.dto.form.*;
import com.field.muzi.web.user.dto.form.FormDetailsResponse;
import com.field.muzi.web.user.dto.form.FormListResponse;
import com.field.muzi.web.user.dto.form.FormSaveRequest;
import com.field.muzi.web.user.dto.form.FormUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminFormService {

    private final MemberRepository memberRepository;
    private final FormRepository formRepository;

    @Transactional
    public Page<AdminFormListResponse> adminFormList(Pageable pageable, AdminFormListRequest request) {
        Page<AdminFormListResponse> result = formRepository.adminFormList(pageable, request);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int totalCount = (int) result.getTotalElements();

        int dec = pageSize * pageNumber;

        for (AdminFormListResponse response : result.getContent()) {
            response.setRowNum(totalCount - dec);
            dec++;
        }

        return result;
    }

    @Transactional
    public AdminFormDetailsResponse adminFormDetails(String formSeq) {
        FormEntity form = EntityUtils.formThrowable(formRepository, formSeq);
        return new AdminFormDetailsResponse(form);
    }

    @Transactional
    public void deleteForm(AdminFormDeleteRequest request) {
        for(String formSeq : request.getFormSeqList()) {
            FormEntity form  = EntityUtils.formThrowable(formRepository, formSeq);
            form.delete();
        }
    }

    @Transactional
    public void updateAnswer(AdminFormUpdateRequest request) {
        FormEntity form  = EntityUtils.formThrowable(formRepository, request.getFormSeq());
        form.updateAnswer(request.getAnswer());
    }
}
