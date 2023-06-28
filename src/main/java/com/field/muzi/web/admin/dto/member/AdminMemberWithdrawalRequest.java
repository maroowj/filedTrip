package com.field.muzi.web.admin.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminMemberWithdrawalRequest {

    private List<String> memberSeqList;
}
