package com.study.servlet_study.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet_study.utils.ParamsConverter;


@WebServlet("/http")
public class HttpStudyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    // CSR 클라이언트사이드 렌더링 ex) React등 
	// SSR 서버사이드 렌더링 ex)
	
    public HttpStudyServlet() {
        super();
    }

    // HTTP 메소드
    // POST요청    -> C reate(추가)
    // GET요청     -> R ead(조회)
    // PUT요청     -> U pdate(수정)
    // DELETE요청  -> d elete(삭제)
    
    // 예외적으로 로그인 조회x 추가o post요청 써야함 ! 
    // get요청하면 주소창에 아디 비번 다보여서
    // 이 사용자가 로그인해서 웹에 추가됬다고 생각하면됨
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> paramsMap = new HashMap<>();
		
		request.getParameterMap().forEach((k,v) -> {
			StringBuilder builder = new StringBuilder();
			
			Arrays.asList(v).forEach(value -> builder.append(value));
			
			paramsMap.put(k, builder.toString());
		});
		
		System.out.println(paramsMap);
		System.out.println(request.getParameter("name"));
		
		Map<String, String> paramsMap2 = new HashMap<>();
		Iterator<String> ir = request.getParameterNames().asIterator();
		while(ir.hasNext()) {
			String key = ir.next();
			paramsMap2.put(key, request.getParameter(key));
		}
		
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> paramsMap = ParamsConverter.covertParamsMapToMap(request.getParameterMap());
		
		
		System.out.println(paramsMap);
	}

	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
