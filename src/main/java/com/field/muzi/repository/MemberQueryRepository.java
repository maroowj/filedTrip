package com.field.muzi.repository;

import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.domain.user.Role;
import com.field.muzi.web.admin.dto.member.AdminMemberListRequest;
import com.field.muzi.web.admin.dto.member.AdminMemberListResponse;
import com.field.muzi.web.common.dto.member.LoginRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MemberQueryRepository {

    // 로그인 권한별 확인
    Optional<MemberEntity> findByMemberIdAndRole(LoginRequest request, List<Role> role);
    Optional<MemberEntity> findByMemberIdAndRole(String id, Role role);

    Page<AdminMemberListResponse> memberList(Pageable pageable, AdminMemberListRequest request);
}
