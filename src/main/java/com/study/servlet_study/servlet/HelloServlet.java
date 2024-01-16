package com.study.servlet_study.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthOptionPaneUI;


@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 주소창에서 요청을 날리면 Request Method 가 무조건 get 이다
	// Status Code:200 OK  (정상적인 요청이다)
	// 요청이 있으면 항상 응답이 있다
	// 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청을 날리는 대상 : 클라이언트
		// 응답 : 서버가 함
		System.out.println(request.getMethod());
		System.out.println(request.getRequestURL()); // 전체 주소 경로를 다 들고옴
		System.out.println(request.getRequestURI()); // 프로젝트 이름만 들고옴
		System.out.println(response.getStatus());
		
		response.setContentType("text/plain");
		
		System.out.println(response.getContentType());
		
		response.getWriter().println("헬로");
		
		System.out.println("요청이 들어옴!!");
		
	}

}
