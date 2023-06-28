package com.field.muzi.web.admin.dto.diy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDiyDetailsResponse {

    private Diy diy;
    private List<Details> details;
    private List<Options> options;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Diy {
        private String DiySeq;
        private String DiyTitle;
        private String startDate;
        private String endDate;
        private String content;
        private String etc;
        private String state;
        private String kakaoEmail;
        private String memberName;
        private String phone;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Details {
        private Map<String, Object> content;
        private String etc;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Options {
        private Map<String, Object> content;
        private String etc;
    }
}
