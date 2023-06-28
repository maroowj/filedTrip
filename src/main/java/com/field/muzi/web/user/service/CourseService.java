package com.field.muzi.web.user.service;

import com.field.muzi.domain.entity.FirstCourseEntity;
import com.field.muzi.repository.FirstCourseRepository;
import com.field.muzi.repository.SecondCourseRepository;
import com.field.muzi.web.user.dto.course.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {

    private final FirstCourseRepository firstCourseRepository;
    private final SecondCourseRepository secondCourseRepository;

    @Transactional
    public AllCourseListResponse allCourseListForDiy(AllCourseListRequest request) {
        AllCourseListResponse response = new AllCourseListResponse();

//        Date today = new Date();
//        request.setSearchDate(today);

        response.setFirstCourseList(firstCourseRepository.firstCourseListForDiy(request));
        response.setSecondCoursesList(secondCourseRepository.secondCourseListForDiy(request));


        return response;
    }

    @Transactional
    public AllCourseListResponse allCourseList(AllCourseListRequest request) {
        AllCourseListResponse response = new AllCourseListResponse();

        Date today = new Date();
        request.setSearchDate(today);

        response.setFirstCourseList(firstCourseRepository.userFirstCourseList(request));
        response.setSecondCoursesList(secondCourseRepository.userSecondCourseList(request));


        return response;
    }

    @Transactional
    public AllCourseListResponse allCourseListForReservation(AllCourseListRequest request) {
        AllCourseListResponse response = new AllCourseListResponse();
        List<AllCourseListResponse.FirstCourse> emptyFirstCourseList = new ArrayList<>();
        List<AllCourseListResponse.SecondCourse> emptySecondCourseList = new ArrayList<>();
        String category="";

        List<AllCourseListResponse.FirstCourse> firstCourseList = firstCourseRepository.userFirstCourseList(request);
        for(AllCourseListResponse.FirstCourse firstCourse:firstCourseList) {
            List<String> reservationDays = firstCourse.getReservationDay();
            for(String day:reservationDays) {
                if(request.getReservationDay().equals(day)){
                    emptyFirstCourseList.add(firstCourse);
                }
            }
        }

        List<AllCourseListResponse.SecondCourse> secondCourseList = secondCourseRepository.userSecondCourseList(request);
        for(AllCourseListResponse.SecondCourse secondCourse:secondCourseList) {
            List<String> reservationDays = secondCourse.getReservationDay();
            for(String day:reservationDays) {
                if(request.getReservationDay().equals(day)){
                    emptySecondCourseList.add(secondCourse);
                }
            }
        }

        response.setFirstCourseList(emptyFirstCourseList);
        response.setSecondCoursesList(emptySecondCourseList);

        return response;
    }

    @Transactional
    public FirstCourseDetailResponse firstCourseDetail(String firstCourseSeq) {
        return firstCourseRepository.firstCourseDetail(firstCourseSeq);
    }

    @Transactional
    public SecondCourseDetailResponse secondCourseDetail(String secondCourseSeq) {
        return secondCourseRepository.secondCourseDetail(secondCourseSeq);
    }

    @Transactional
    public List<CategoryGroup> categoryGroupList() {

        Date today = new Date();

        List<CategoryGroup> categoryGroupList = new ArrayList<>();

        List<CategoryGroup> originList = secondCourseRepository.categoryGroup();
        for(CategoryGroup cg:originList) {
            AllCourseListRequest request = new AllCourseListRequest();
            request.setCategory(cg.getCategory());
            request.setSearchDate(today);
            List<AllCourseListResponse.SecondCourse> list = secondCourseRepository.userSecondCourseList(request);
            if(list.size()>0) {
                CategoryGroup category = cg;
                categoryGroupList.add(category);
            }
        }

        return categoryGroupList;
    }
}
