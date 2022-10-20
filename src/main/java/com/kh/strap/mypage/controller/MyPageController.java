package com.kh.strap.mypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.strap.member.domain.Member;
import com.kh.strap.member.service.MemberService;
import com.kh.strap.mypage.service.logic.MyPageServiceImpl;


@Controller
public class MyPageController {
	@Autowired
	private MemberService mService;
	@Autowired
	private MyPageServiceImpl mpService;
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	// 마이페이지 메인
	@RequestMapping(value="/mypage/mypageView.strap", method=RequestMethod.GET)
	public String showMyPage(HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		Member member = (Member)session.getAttribute("loginUser");
//		if(member != null) {
//			String memberId = member.getMemberId();
//			Member mOne = mService.memberById(memberId);
//			session.setAttribute("memberNick", mOne.getMemberNick());
//		} else {
//			request.setAttribute("msg", "로그인후 이용 가능한 서비스입니다.");
//			request.setAttribute("url", "/member/loginView.strap");
//			return("common/alert");
//		}
		return "mypage/myPage";
	}
	
	
}
