package com.field.muzi.web.admin.service;

import com.field.muzi.domain.entity.DiyEntity;
import com.field.muzi.repository.DiyDetailsRepository;
import com.field.muzi.repository.DiyOptionsRepository;
import com.field.muzi.repository.DiyRepository;
import com.field.muzi.repository.MemberRepository;
import com.field.muzi.utils.EntityUtils;
import com.field.muzi.web.admin.dto.banner.BannerListResponse;
import com.field.muzi.web.admin.dto.diy.AdminDiyDetailsResponse;
import com.field.muzi.web.admin.dto.diy.AdminDiyListRequest;
import com.field.muzi.web.admin.dto.diy.AdminDiyListResponse;
import com.field.muzi.web.admin.dto.diy.AdminDiyUpdateRequest;
import com.field.muzi.web.user.dto.diy.DiyDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminDiyService {

    private final MemberRepository memberRepository;
    private final DiyRepository diyRepository;
    private final DiyDetailsRepository diyDetailsRepository;
    private final DiyOptionsRepository diyOptionsRepository;

    @Transactional
    public Page<AdminDiyListResponse> adminDiyList(Pageable pageable, AdminDiyListRequest request) {
        Date endDate = null;

        if(request.getEndDate()!=null) {
            endDate = request.getEndDate();
            endDate.setDate(endDate.getDate()+1);
            request.setEndDate(endDate);
        }

        Page<AdminDiyListResponse> result = diyRepository.adminDiyList(pageable, request);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int totalCount = (int) result.getTotalElements();

        int dec = pageSize * pageNumber;

        for (AdminDiyListResponse response : result.getContent()) {
            response.setRowNum(totalCount - dec);
            dec++;
        }

        return result;
    }

    @Transactional
    public AdminDiyDetailsResponse adminDiyDetails(String diySeq) {
        AdminDiyDetailsResponse response = new AdminDiyDetailsResponse();
        DiyEntity diy = EntityUtils.diyThrowable(diyRepository, diySeq);
        response.setDiy(diyRepository.adminDiy(diy));
        response.setDetails(diyDetailsRepository.adminDiyDetails(diy));
        response.setOptions(diyOptionsRepository.adminDiyOptions(diy));
        return response;
    }

    @Transactional
    public void updateState(AdminDiyUpdateRequest request) {
        for(String diySeq : request.getDiySeqList()) {
            DiyEntity diy = EntityUtils.diyThrowable(diyRepository, diySeq);
            diy.updateState(request.getState());
        }
    }
}
