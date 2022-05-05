package com.odc.member;

import java.util.Map;

public interface MemberService {
	
	// 회원가입
	int signUp(Map<String, Object> map);
	
	// 중복아이디 체크
	Map<String, Object> checkId(Map<String, Object> map);
	
	// 로그인
	Map<String, Object> login(Map<String, Object> map);
	
	// 로그아웃
	String logout();
	
	// 회원정보 수정 (회원 정보 확인)
	Map<String, Object> memberInfo(Map<String, Object> map);
	
	// 회원정보 수정
	int memberMod(Map<String, Object> map);
	
	// 회원 탈퇴
	int deleteMember(Map<String, Object> map);
}
