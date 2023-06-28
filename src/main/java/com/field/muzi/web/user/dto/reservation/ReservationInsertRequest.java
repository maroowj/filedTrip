package com.field.muzi.web.user.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationInsertRequest {

    private String firstCourseSeq;
    private List<String> secondCourseSeq;
    private String applicant;
    private String applicantDetail;
    private String guestType;
    private int guestNumber;
    private String managerPhone;
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String reservationDate;
    private int cost;
    private String vehicleType;
    private String vehicleNumber;
    private String etc;
    private String password;
    private String terms1;
    private String terms2;
    private String terms3;
    private String terms4;
    private String terms5;
    private String email;
    private int busFare;
    private int guideFee;
    private int insuranceFee;
}
