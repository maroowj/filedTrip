package com.field.muzi.web.admin.controller.API;

import com.field.muzi.web.admin.dto.diy.AdminDiyDetailsResponse;
import com.field.muzi.web.admin.dto.diy.AdminDiyListRequest;
import com.field.muzi.web.admin.dto.diy.AdminDiyListResponse;
import com.field.muzi.web.admin.dto.diy.AdminDiyUpdateRequest;
import com.field.muzi.web.admin.dto.form.*;
import com.field.muzi.web.admin.service.AdminDiyService;
import com.field.muzi.web.admin.service.AdminFormService;
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
public class AdminFormAPI {

    private final AdminFormService adminFormService;

    @GetMapping("/form")
    public Page<AdminFormListResponse> adminDiyList(@PageableDefault(sort = "formSeq", direction = Sort.Direction.DESC) Pageable pageable,
                                                    AdminFormListRequest request) {
        return adminFormService.adminFormList(pageable, request);
    }

    @GetMapping("/form/{formSeq}")
    public AdminFormDetailsResponse adminDiyDetails(@PathVariable("formSeq") String formSeq) {
        return adminFormService.adminFormDetails(formSeq);
    }

    @DeleteMapping("/form/delete")
    public void deleteForm(@RequestBody AdminFormDeleteRequest request) {
        adminFormService.deleteForm(request);
    }

    @PutMapping("/form/answer")
    public void updateAnswer(@RequestBody AdminFormUpdateRequest request) {
        adminFormService.updateAnswer(request);
    }
}
