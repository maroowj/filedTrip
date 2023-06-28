package com.field.muzi.web.user.controller;


import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.repository.MemberRepository;
import com.field.muzi.utils.EntityUtils;
import com.field.muzi.web.common.dto.KakaoSignupRequest;
import com.field.muzi.web.common.dto.TokenResponse;
import com.field.muzi.web.common.service.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class NewUserController {

	private final KakaoLoginService kakaoLoginService;
	private final MemberRepository memberRepository;

	// 메인페이지
	@GetMapping()
	public String main() {
		return "/renew/index";
	}

	// 마이페이지
	@GetMapping("/mypage")
	public String mypage() {
		return "/renew/mypage";
	}

	// diy 상세 1 (체험장 저장 내역)
	@GetMapping("/mypage/diy")
	public String diy_detail() {
		return "/renew/diy_detail1";
	}

	// diy 상세 2 (체험장 저장 내역)
	@GetMapping("/mypage/diy2")
	public String diy_detail2() {
		return "/renew/diy_detail2";
	}

	// diy 상세 3 (체험장 저장 내역)
	@GetMapping("/mypage/diy3")
	public String diy_detail3() {
		return "/renew/diy_detail3";
	}

	// 예약 내역 (견적 요청 내역)
	@GetMapping("/mypage/reservation")
	public String reservation_detail() {
		return "/renew/reserve_detail";
	}

	// 예약 내역 수정 (견적 요청 내역)
	@GetMapping("/mypage/reservation/update")
	public String reservation_update() {
		return "/renew/reserve_update";
	}

	// 항공권 문의 상세 (나의 문의 내역)
	@GetMapping("/mypage/air")
	public String inquiry_air() {
		return "/renew/inquiry1_detail";
	}

	// 항공권 문의 수정 (나의 문의 내역)
	@GetMapping("/mypage/air/update")
	public String update_air() {
		return "/renew/inquiry1_update";
	}

	// 차량문의 상세 (나의 문의 내역)
	@GetMapping("/mypage/vehicle")
	public String inquiry_vehicle() {
		return "/renew/inquiry2_detail";
	}

	// 차량문의 수정 (나의 문의 내역)
	@GetMapping("/mypage/vehicle/update")
	public String update_vehicle() {
		return "/renew/inquiry2_update";
	}

	// 여행자보험 상세 (나의 문의 내역)
	@GetMapping("/mypage/insurance")
	public String inquiry_insurance() {
		return "/renew/inquiry3_detail";
	}

	// 여행자보험 수정 (나의 문의 내역)
	@GetMapping("/mypage/insurance/update")
	public String update_insurance() {
		return "/renew/inquiry3_update";
	}


	// 체험장등록 1
	@GetMapping("/diy")
	public String diy() {
		return "/renew/diy";
	}

	// 체험장등록 2
	@GetMapping("/diy2")
	public String diy2() {
		return "/renew/diy2";
	}

	// 체험장등록 3
	@GetMapping("/diy3")
	public String diy3() {
		return "/renew/diy3";
	}

	// 견적요청
	@GetMapping("/trip")
	public String trip() {
		return "/renew/trip";
	}

	// 1차코스 상세
	@GetMapping("/trip/first-course")
	public String first_course() {
		return "/renew/tripDetail";
	}

	// 1차코스 상세
	@GetMapping("/trip/second-course")
	public String second_course() {
		return "/renew/tripDetail2";
	}

	// 항공권
	@GetMapping("/air")
	public String air() {
		return "/renew/inquiry1";
	}

	// 차량문의
	@GetMapping("/vehicle")
	public String vehicle() {
		return "/renew/inquiry2";
	}

	// 여행자보험
	@GetMapping("/insurance")
	public String insurance() {
		return "/renew/inquiry3";
	}

	/***********/

	// 1차 견학시설 상세페이지
	@GetMapping("/first-course")
	public String firstCourse() {
		return "/user/course/firstCourse";
	}

	// 2차 견학시설 상세페이지
	@GetMapping("/second-course")
	public String secondCourse() {
		return "/user/course/secondCourse";
	}

	// 예약 확인 페이지
	@GetMapping("/reservation")
	public String reservation() {
		return "/user/reservation/reservation";
	}

	/** 211020 우람 신규 추가 **/
	// 회사 소개
	@GetMapping("/introduce")
	public String intro() {
		return "/user/topMenu/intro";
	}

	// VR투어 박물관
	@GetMapping("/vr/museum")
	public String vr_museum() {
		return "/user/topMenu/vr/museum";
	}

	// VR투어 호텔
	@GetMapping("/vr/hotel")
	public String vr_hotel() {
		return "/user/topMenu/vr/hotel";
	}

	// VR투어 카페
	@GetMapping("/vr/cafe")
	public String vr_cafe() {
		return "/user/topMenu/vr/cafe";
	}

	// VR투어 기타
	@GetMapping("/vr/etc")
	public String vr_etc() {
		return "/user/topMenu/vr/etc";
	}

	// VR투어 제작가격
	@GetMapping("/vr/production-price")
	public String vr_productionPrice() {
		return "/user/topMenu/vr/productionPrice";
	}

	// VR투어 제작의뢰
	@GetMapping("/vr/production-request")
	public String vr_productionRequest() {
		return "/user/topMenu/vr/productionRequest";
	}

	// 여행자 보험
//	@GetMapping("/insurance")
//	public String insurance() {
//		return "/user/topMenu/insurance";
//	}
	/** 211020 우람 신규 추가 **/


	/** 사용자 카카오 로그인 **/
	@GetMapping("/login")
	public String login_view() {
		if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			return "redirect:/";
		}

		return "/renew/login";
	}

	@GetMapping("/login/kakao")
	public void kakao_login(KakaoSignupRequest request, HttpServletResponse response) throws IOException {

		TokenResponse tokenResponse = kakaoLoginService.kakaoLogin(request);

		Optional<MemberEntity> optionalMember = memberRepository.findBySnsIdAndProvider(request.getSid(), request.getProvider());

		if(optionalMember.get().isWithdrawal()) {
			response.sendRedirect("/error/user-drop");
		}else {
			Cookie access_cookie = new Cookie("AccessToken", tokenResponse.getAccessToken());
//        access_cookie.setMaxAge(60 * 60 * 24 );
			access_cookie.setMaxAge(-1);
			access_cookie.setPath("/");
			access_cookie.setHttpOnly(true);
			response.addCookie(access_cookie);

			Cookie refresh_cookie = new Cookie("RefreshToken", tokenResponse.getRefreshToken());
			refresh_cookie.setMaxAge(-1);
			refresh_cookie.setPath("/");
			refresh_cookie.setHttpOnly(true);
			response.addCookie(refresh_cookie);

			response.sendRedirect("/");
		}
	}

	@GetMapping("/user/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			cookie.setValue(null);
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}

		new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
		response.sendRedirect("/");
	}

	@ResponseBody
	@GetMapping("/api/common/login")
	public Map<String, Object> loginChk() {
		Map<String, Object> result = new HashMap<>();
		String memberSeq = SecurityContextHolder.getContext().getAuthentication().getName();

		if (memberSeq == null || memberSeq.equals("") || memberSeq.equals("anonymousUser")) {
			result.put("login", false);
		}else {
			MemberEntity member = EntityUtils.memberThrowable(memberRepository);
			result.put("login", true);
			result.put("profilePicture", member.getMemberInfo().getProfileImage());
			result.put("name", member.getMemberInfo().getMemberName());
		}

		return  result;
	}

}
