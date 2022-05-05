package com.odc.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// 세션의 키와 값을 받거나 내보낼때 사용하기 위한 클래스
public class SessionMgr {
	
	// 세션의 맵의 키와 값을 저장
	public static void inputData(HttpServletRequest request, Map<String, Object> map) {
		
		HttpSession session = request.getSession();
		
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			String val = (String)entry.getValue();
			session.setAttribute(key, val);
		}
	}
	
	// 세션의 맵의 키와 값을 리턴
	public static Map<String, Object> outputData(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		Enumeration<?> attr = session.getAttributeNames();

		Map<String, Object> map = new HashMap<String, Object>();
		
		while (attr.hasMoreElements()) {
			String attrName = (String)attr.nextElement();
			String attrValue = (String)session.getAttribute(attrName);
			
			map.put(attrName, attrValue);
		}
		
		return map;
	}
}
