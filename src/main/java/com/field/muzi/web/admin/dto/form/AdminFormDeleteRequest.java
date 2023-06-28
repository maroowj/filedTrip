package com.field.muzi.web.admin.dto.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminFormDeleteRequest {

    private List<String> formSeqList;
}
