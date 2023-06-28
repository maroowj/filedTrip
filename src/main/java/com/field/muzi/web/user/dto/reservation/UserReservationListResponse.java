package com.field.muzi.web.user.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReservationListResponse {

    private String reservationSeq;
    private String applicant;
    private String applicantDetail;
    private String reservationDate;
    private String bookingStatus;
    private String firstCourseTitle;
}
