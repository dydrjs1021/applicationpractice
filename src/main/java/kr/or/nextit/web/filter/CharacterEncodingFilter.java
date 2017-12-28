package kr.or.nextit.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{

	private String enCoding = "";
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		// 필터 초기화 작업.
		enCoding = config.getInitParameter("enCoding");
		
		if(enCoding == null) {
			enCoding = "utf-8";
		}
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 필터 작업.
		
		request.setCharacterEncoding(enCoding);
		
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		// 필터에서 사용한 자원 해제.
		
	}
}
