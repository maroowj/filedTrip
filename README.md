# 스쿨트립

* 🔊 프로젝트 소개
  * (초기 개발)인천시 초중고 학교를 대상으로 인천의 환경기관 견학 및 각종 체험학습 프로그램을 신청받고 관리하는 웹페이지형 프로젝트입니다.
  * (리뉴얼 후 추가) 체험 학습 프로그램을 학교에서 직접 DIY로 제작하여 신청 할 수 있습니다.
  * (리뉴얼 후 추가) 항공권, 차량, 여행자 보험에 관한 문의를 할 수 있습니다.
  * 디자인과 퍼블리싱을 제외한 기존 DB, 백엔드, 프론트엔드까지 모두 담당했습니다.
  * 백엔드는 Restful-API를 개발하고, 프론트엔드는 Ajax(jQuery)로 API를 호출하는 방식으로 개발되었습니다.

* 🏗️개발 기간 및 인원 
  * 2022.02 ~ 2022.04 (1차 개발)
  * 풀스택 2명, 프론트엔드 1명 총 3명 중 풀스택 담당 (도중 풀스택 1명 퇴사)
  * 2022.12 ~ 2023.02 (리뉴얼)
  * 풀스택 1명, 디자인 퍼블리싱 1명 총 2명 중 풀스택 담당
  
* 🛠️사용 기술
  * Back-End: Spring Boot, Java, JPA, MySql, QueryDsl
  * Front-End: JavaScript, jQuery

* 📅 DB
  * 테이블 정의서 : https://docs.google.com/spreadsheets/d/1EfvZt1cfAv256R47ds3g9veVCGauua2B/edit?usp=sharing&ouid=113326887780897626343&rtpof=true&sd=true
  * ERD
![schoolTrip_erd](https://github.com/maroowj/filedTrip/assets/77284101/9688ce2f-b1c7-4f03-a96e-dcf43f6eba1e)

* ✏️ 시나리오
  * 관리자
    * 체험 학습의 1차코스를 주관하는 기관, 1차코스, 2차코스를 등록하고 관리 할 수 있습니다.
    * 체험 학습을 신청한 학교의 신청 내역을 확인할 수 있습니다.
    * 사용자가 체험학습 신청 시 기본적으로 부과되는 금액을 설정 할 수 있습니다.
    * (리뉴얼) 항공권, 차량, 여행자보험의 문의 내역을 확인 할 수 있습니다.
    * 전체 회원을 관리합니다.
        
  * 사용자
    * 체험학습을 신청하려면 1차코스는 필수로 설정해야 합니다. (2차코스는 선택)
    * 코스 선택하고 신청 내용을 작성 후 신청합니다.
    * 체험 학습 이외에 항공권, 차량, 여행자보험의 관한 문의를 할 수 있습니다.    
    
   
* 💻구동 화면
  * 사용자
![user_index](https://github.com/maroowj/filedTrip/assets/77284101/14ae8ef9-a45c-4096-a1e1-5370269f1ef0)
![user_trip_list_resize](https://github.com/maroowj/filedTrip/assets/77284101/a5a9e192-dec8-4970-83bd-18ac6ff6e726)
![user_course_details](https://github.com/maroowj/filedTrip/assets/77284101/3398a7e5-3f03-43a5-915e-d64b75a6e82b)
![user_course_reservation](https://github.com/maroowj/filedTrip/assets/77284101/03f1e00a-5c58-4894-86d9-691ec4fa3125)

  * 관리자
![admin_course_list](https://github.com/maroowj/filedTrip/assets/77284101/5dc676ff-9685-4230-b7a7-9ee757c58b5a)
![admin_course_details](https://github.com/maroowj/filedTrip/assets/77284101/4aabfc2f-2144-4af9-8daa-aff248e53fff)

* 💡부가기능
  * ckEditor를 이용한 코스 상세 정보 저장
  * MySql5.7 이상 버전으로 JSON COLUMN 사용하여 데이터 저장
