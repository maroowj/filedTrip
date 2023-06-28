package com.field.muzi.web.admin.controller.API;

import com.field.muzi.web.admin.dto.diy.AdminDiyDetailsResponse;
import com.field.muzi.web.admin.dto.diy.AdminDiyListRequest;
import com.field.muzi.web.admin.dto.diy.AdminDiyListResponse;
import com.field.muzi.web.admin.dto.diy.AdminDiyUpdateRequest;
import com.field.muzi.web.admin.service.AdminDiyService;
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
public class AdminDiyAPI {

    private final AdminDiyService adminDiyService;

    @GetMapping("/diy")
    public Page<AdminDiyListResponse> adminDiyList(@PageableDefault(sort = "diySeq", direction = Sort.Direction.DESC) Pageable pageable,
                                                   AdminDiyListRequest request) {
        return adminDiyService.adminDiyList(pageable, request);
    }

    @GetMapping("/diy/{diySeq}")
    public AdminDiyDetailsResponse adminDiyDetails(@PathVariable("diySeq") String diySeq) {
        return adminDiyService.adminDiyDetails(diySeq);
    }

    @PutMapping("/diy/update")
    public void updateState(@RequestBody AdminDiyUpdateRequest request) {
        adminDiyService.updateState(request);
    }
}
