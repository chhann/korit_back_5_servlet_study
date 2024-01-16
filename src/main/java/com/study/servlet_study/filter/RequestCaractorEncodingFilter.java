package com.study.servlet_study.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/*")
public class RequestCaractorEncodingFilter extends HttpFilter implements Filter {
       
    	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 전처리 시작
		HttpServletRequest httpRequest = (HttpServletRequest) request;
			
		String[] method = new String [] {"POST", "PUT"};
		
		System.out.println(httpRequest.getMethod());
		
		if(Arrays.asList(method).contains(httpRequest.getMethod().toUpperCase())) {
			httpRequest.setCharacterEncoding("utf-8");
		}
		// 전처리 끝
		
		chain.doFilter(request, response);
		
		// 후처리 시작
		// 후처리 끝
		
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
