package com.odc.member;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImp implements MemberService {	// DAO에서 받은 데이터를 조작하여 Controller에 보낸다

	@Autowired
	MemberDAO memberDAO;
	
	// 회원가입
	@Override
	public int signUp(Map<String, Object> map) {
		
		int affectRowCnt = 0;
		affectRowCnt = this.memberDAO.insertMember(map);

		return affectRowCnt;
	}
	
	// 중복아이디 체크
	@Override
	public Map<String, Object> checkId(Map<String, Object> map) {
		
		return this.memberDAO.selectCheckId(map);
	}
	
	// 로그인
	@Override
	public Map<String, Object> login(Map<String, Object> map) {
		
		return this.memberDAO.selectLogin(map);
	}
	
	// 로그아웃
	@Override
	public String logout() {
		return null;
	}
	
	// 회원정보 확인
	@Override
	public Map<String, Object> memberInfo(Map<String, Object> map) {
		return this.memberDAO.selectMemberInfo(map);
	}
	
	// 회원정보 수정
	@Override
	public int memberMod(Map<String, Object> map) {
		
		int affectRowCnt = 0;
		
		affectRowCnt = this.memberDAO.updateMember(map);
		
		return affectRowCnt;
	}
	
	// 회원 탈퇴
	@Override
	public int deleteMember(Map<String, Object> map) {

		int affectRowCnt = 0;
		
		affectRowCnt = this.memberDAO.deleteMember(map);
		
		return affectRowCnt;
	}
}
