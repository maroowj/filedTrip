package com.field.muzi.web.admin.controller.API;

import com.field.muzi.web.admin.dto.member.AdminMemberDetailsResponse;
import com.field.muzi.web.admin.dto.member.AdminMemberListRequest;
import com.field.muzi.web.admin.dto.member.AdminMemberListResponse;
import com.field.muzi.web.admin.dto.member.AdminMemberWithdrawalRequest;
import com.field.muzi.web.admin.service.AdminMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminMemberAPI {

    private final AdminMemberService adminMemberService;

    @GetMapping("/member")
    public Page<AdminMemberListResponse> memberList(@PageableDefault(sort = "memberSeq", direction = Sort.Direction.DESC) Pageable pageable,
                                                    AdminMemberListRequest request) {
        return adminMemberService.memberList(pageable, request);
    }

    @GetMapping("/member/{memberSeq}")
    public AdminMemberDetailsResponse memberDetails(@PathVariable("memberSeq") String memberSeq) {
        return adminMemberService.memberDetails(memberSeq);
    }

    @DeleteMapping("/member/withdrawal")
    public void memberWithdrawal(@RequestBody AdminMemberWithdrawalRequest request) {
        adminMemberService.memberWithdrawal(request);
    }
}
