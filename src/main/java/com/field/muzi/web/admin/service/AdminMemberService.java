package com.field.muzi.web.admin.service;

import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.repository.MemberInfoRepository;
import com.field.muzi.repository.MemberRepository;
import com.field.muzi.utils.EntityUtils;
import com.field.muzi.web.admin.dto.banner.BannerListResponse;
import com.field.muzi.web.admin.dto.member.AdminMemberDetailsResponse;
import com.field.muzi.web.admin.dto.member.AdminMemberListRequest;
import com.field.muzi.web.admin.dto.member.AdminMemberListResponse;
import com.field.muzi.web.admin.dto.member.AdminMemberWithdrawalRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminMemberService {

    private final MemberRepository memberRepository;
    private final MemberInfoRepository memberInfoRepository;

    @Transactional
    public Page<AdminMemberListResponse> memberList(Pageable pageable, AdminMemberListRequest request) {
        Page<AdminMemberListResponse> result = memberRepository.memberList(pageable, request);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int totalCount = (int) result.getTotalElements();

        int dec = pageSize * pageNumber;

        for (AdminMemberListResponse response : result.getContent()) {
            response.setRowNum(totalCount - dec);
            dec++;
        }

        return result;
    }

    @Transactional
    public AdminMemberDetailsResponse memberDetails(String memberSeq) {
        MemberEntity member = EntityUtils.memberThrowable(memberRepository, memberSeq);
        return new AdminMemberDetailsResponse(member);
    }

    @Transactional
    public void memberWithdrawal(AdminMemberWithdrawalRequest request) {
        for(String memberSeq : request.getMemberSeqList()) {
            MemberEntity member = EntityUtils.memberThrowable(memberRepository, memberSeq);
            member.memberWithdrawal();
        }
    }
}
