package com.odc.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	// 회원가입 시작(/member)
	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public ModelAndView signUp(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		// 만들어둔 SessionMgr 클래스를 통해 세션안의 값이 있는지 확인
		Map<String, Object> sessMap = SessionMgr.outputData(request);
		
		// sessMap안의 내용이 null이거나 초기화만 되어있는 경우가 아닐때
		if (sessMap != null && !sessMap.isEmpty()) {	
			String alertMsg = "이미 로그인 상태입니다.";
			
			// 여기서 만들어둔 ScriptAlertUtils(Controller에서 JS(alert("");)를 실행하기 위한 클래스)가 실행되면 
			// mav 객체에서 mav.setViewName("redirect:/")가 실행이 안되므로 스크립트에서 location.href ("/"); 를 실행한다
			ScriptAlertUtils.alertAndMovePage(response, alertMsg, "/");
		}
		
		return new ModelAndView("sign/Member");
	}

	@RequestMapping(value="/member", method = RequestMethod.POST)
	public ModelAndView memberPost(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		// insert sql문이 실행되면 MemberServiceImp 클래스에서 affectRowCnt가 0에서 1로 바뀐다.
		int affectRowCnt = this.memberService.signUp(map);
		
		// insert sql문이 실행되지 않았을때
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
	@RequestMapping(value = "/idCheck", method = RequestMethod.GET)
	public ModelAndView checkId(@RequestParam Map<String, Object> map) {
		
		ModelAndView mav = new ModelAndView();	
		
		// sql select_checkId 실행후 Map자료형으로 반환
		// Map 자료형은 key와 value로 짝 지어진 것이다. (ex. uId(key), test1(value))
		// 여기서 key는 String 자료형, value는 Object 자료형(String으로 형변환 가능)이다.
		// 여기서는 입력한 uId가 데이터베이스ㅡ에 있을 경우 해당 uId를 다시 return한다.
		Map<String, Object> checkIdMap = this.memberService.checkId(map);
		
		// view에서 jstl로 부를 수 있게 만들어주는 것 jsp파일 안에서 ${checkIdData.uId} 이렇게 사용한다.
		mav.addObject("checkIdData", checkIdMap);

		// view설정 => 주소창에 /idCheck 치면 "views/sign/IdCheck.jsp"를 보여주는것
		mav.setViewName("/sign/IdCheck");
		return mav;
	}
	// 중복확인 끝

	// 로그인 시작(/login)
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {		
		return new ModelAndView("sign/Login");
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginPost (@RequestParam Map<String, Object> map, HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		// Map자료형에서 uId, uName, uLevel을 key로, sql문의 조회값을 해당 key들의 value로 저장한다.
		Map<String, Object> loginMap = this.memberService.login(map);
		
		if (loginMap == null || loginMap.isEmpty()) {
			
			String alertMsg = "아이디와 비밀번호를 확인해주세요.";
			ScriptAlertUtils.alertAndMovePage(response, alertMsg, "/login");
			
		} else {
			
			// 세션에 loginMap에 저장되어 있는 key와 value 전부(uId, uName, uLevel)를 저장하는 메서드
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
	@RequestMapping(value = "/memberMod", method = RequestMethod.GET)
	public ModelAndView memberMod(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		// 세션의 저장되어 있는 모든 값들을 불러와 Map자료형으로 불러오는 메서드
		Map<String, Object> sessMap = SessionMgr.outputData(request);
		
		// 세션이 null이거나 비었을 경우 (=로그인을 안했을 경우)
		if (sessMap == null || sessMap.isEmpty()) {
			String alertMsg = "비정상적인 접속입니다.\\n";
			alertMsg += "메인페이지로 이동합니다.";
			ScriptAlertUtils.alertAndMovePage(response, alertMsg, "/");
		}
		
		// 해당 릴레이션(memberList)에 모든 애트리뷰트안의 값들을 불러와 Map자료형으로 불러온다.
		Map<String, Object> memMap = this.memberService.memberInfo(sessMap);
		mav.addObject("memData", memMap);
		
		mav.setViewName("sign/MemberMod");
		
		return mav;
	}
	// 회원 정보 확인 끝
	
	// 회원 정보 수정 시작
	@RequestMapping(value = "/memberMod", method = RequestMethod.POST)
	public ModelAndView memberModPost(@RequestParam Map<String, Object> map, HttpServletResponse response, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		// post가 된 후이므로 위의 회원 정보 확인 메서드가 실행이 안되므로 중복되게 작성
		Map<String, Object> sessMap = SessionMgr.outputData(request);
		Map<String, Object> memMap = this.memberService.memberInfo(sessMap);
		mav.addObject("memData", memMap);
		
		mav.setViewName("sign/MemberMod");
		
		// update sql문이 실행되었는지 확인
		int affectRowCnt = this.memberService.memberMod(map);
		
		// sql문 실행이 안되었을 때
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
		
		// delete sql문이 실행되었는지 확인
		int affectRowCnt = this.memberService.deleteMember(sessMap);
		
		// delete sql문이 실행되었을 때
		if (affectRowCnt == 1) {
			HttpSession session = request.getSession();
			session.invalidate();
		} 
		
		mav.setViewName("redirect:/");
		
		return mav;
	}
}
