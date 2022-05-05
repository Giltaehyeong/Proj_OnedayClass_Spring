package com.odc.member;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {	// sql문의 결과를 반환하는 클래스
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public int insertMember(Map<String, Object> map) {
		return this.sqlSessionTemplate.insert("sign.insert_member", map);
	}
	
	public Map<String, Object> selectCheckId(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("sign.select_checkId", map);
	}
	
	public Map<String,Object> selectLogin(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("sign.select_login", map);
	}
	
	public Map<String, Object> selectMemberInfo(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("sign.select_memberMod", map);
	}
	
	public int updateMember(Map<String, Object> map) {
		return this.sqlSessionTemplate.update("sign.update_memberMod", map);
	}
	
	public int deleteMember(Map<String, Object> map) {
		return this.sqlSessionTemplate.delete("sign.delete_member", map);
	}
}
