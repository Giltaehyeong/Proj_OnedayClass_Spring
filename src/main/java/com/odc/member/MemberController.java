package com.odc.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.odc.utils.ScriptAlertUtils;
import com.odc.utils.SessionMgr;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;

	// 회원가입 시작
	@GetMapping(value = "/member")
	public ModelAndView signUp(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> sessMap = SessionMgr.outputData(request);
		
		if (sessMap != null && !sessMap.isEmpty()) {	
			String alertMsg = "이미 로그인 상태입니다.";
			ScriptAlertUtils.alertAndMovePage(response, alertMsg, "/");
		}
		
		return new ModelAndView("sign/Member");
	}

	@PostMapping(value="/member")
	public ModelAndView memberPost(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
	
		int affectRowCnt = this.memberService.signUp(map);
		
		if (affectRowCnt == 0) {
			String alertMsg = "회원가입 중 오류가 생겼습니다. 다시 시도해 주십시오";
			ScriptAlertUtils.alertAndMovePage(response, alertMsg, "/member");
		} else {
			String alertMsg = "회원가입을 완료하였습니다.";
			ScriptAlertUtils.alertAndMovePage(response, alertMsg, "/");
		}
		
		return mav;
	}
	// 회원가입 끝
	
	// 중복확인 시작(/idCheck)
	@GetMapping(value = "/idCheck")
	public ModelAndView checkId(@RequestParam Map<String, Object> map) {
		
		ModelAndView mav = new ModelAndView();	
		
		Map<String, Object> checkIdMap = this.memberService.checkId(map);
		mav.addObject("checkIdData", checkIdMap);
		mav.setViewName("/sign/IdCheck");
		return mav;
	}
	// 중복확인 끝

	// 로그인 시작(/login)
	@GetMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request) {		
		return new ModelAndView("sign/Login");
	}
	
	@PostMapping(value = "/login")
	public ModelAndView loginPost (@RequestParam Map<String, Object> map, HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		Map<String, Object> loginMap = this.memberService.login(map);
		
		if (loginMap == null || loginMap.isEmpty()) {
			String alertMsg = "아이디와 비밀번호를 확인해주세요.";
			ScriptAlertUtils.alertAndMovePage(response, alertMsg, "/login");
			
		} else {
			SessionMgr.inputData(request, loginMap);
			mav.setViewName("redirect:/");
		}
		
		return mav;
	}
	// 로그인 끝
	
	// 로그아웃 시작(/logout)
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		// 세션 받기
		HttpSession session = request.getSession();
		// 세션 종료
		session.invalidate();
		
		mav.setViewName("redirect:/");

		return mav;
	}
	// 로그아웃 끝
	
	// 회원 정보 수정 시작(/memberMod)
	
	// 회원 정보 확인 시작
	@GetMapping(value = "/memberMod")
	public ModelAndView memberMod(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> sessMap = SessionMgr.outputData(request);

		if (sessMap == null || sessMap.isEmpty()) {
			String alertMsg = "비정상적인 접속입니다.\\n";
			alertMsg += "메인페이지로 이동합니다.";
			ScriptAlertUtils.alertAndMovePage(response, alertMsg, "/");
		}
		
		Map<String, Object> memMap = this.memberService.memberInfo(sessMap);
		mav.addObject("memData", memMap);
		
		mav.setViewName("sign/MemberMod");
		
		return mav;
	}
	// 회원 정보 확인 끝
	
	// 회원 정보 수정 시작
	@PostMapping(value = "/memberMod")
	public ModelAndView memberModPost(@RequestParam Map<String, Object> map, HttpServletResponse response, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();

		Map<String, Object> sessMap = SessionMgr.outputData(request);
		Map<String, Object> memMap = this.memberService.memberInfo(sessMap);
		mav.addObject("memData", memMap);
		
		mav.setViewName("sign/MemberMod");

		int affectRowCnt = this.memberService.memberMod(map);

		if (affectRowCnt == 0) {
			String alertMsg = "회원정보 수정중에 오류가 발생하였습니다. 다시 시도해주십시오.\\n"
									+ "만일 오류가 계속된다면 관리자에게 연락부탁드립니다."; 
			ScriptAlertUtils.alertAndBackPage(response, alertMsg);
		} else {
			String alertMsg = "회원정보 수정완료!";
			ScriptAlertUtils.alertAndMovePage(response, alertMsg, "/");
		}
		
		return mav;
	}
	// 회원 정보 수정 끝
	
	// 회원 탈퇴 시작
	@RequestMapping(value = "/memberDel")
	public ModelAndView memberDel(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> sessMap = SessionMgr.outputData(request);

		int affectRowCnt = this.memberService.deleteMember(sessMap);
		
		if (affectRowCnt == 1) {
			HttpSession session = request.getSession();
			session.invalidate();
		} 
		
		mav.setViewName("redirect:/");
		
		return mav;
	}
}
