package com.odc.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

// 컨트롤러에서 alert 스크립트 쓸때 사용, 해당 클래스를 사용하면 redirect가 안된다.
public class ScriptAlertUtils {
	public static void init(HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
	}
	
	// alert 스크립트만 사용할 때
	public static void alert(HttpServletResponse response, String alertText) throws IOException {
		// 사용법 : ScriptAlertUtils.alert(response, "아이디가 중복");
		init(response);
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + alertText + "');</script> ");
        out.flush();
	}
	
	// alert 스크립트 사용후 뒤로가기
	public static void alertAndBackPage(HttpServletResponse response, String alertText) throws IOException {
    	// 사용법 : ScriptAlertUtils.alertAndBackPage(response, "아이디가 중복", "/login.do");
        init(response);
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + alertText + "'); history.back();</script>");
        out.flush();
    }
 
	// alert 스크립트 사용후 다른 페이지로 이동
    public static void alertAndMovePage(HttpServletResponse response, String alertText, String nextPage) throws IOException {
    	// 사용법 : ScriptAlertUtils.alertAndMovePage("아이디가 중복","/login.do");
        init(response);
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + alertText + "'); location.href='" + nextPage + "';</script> ");
        out.flush();
    }
}
