package controller.front;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

@WebFilter({ "*.do", "*.jsp" })

public class EncFilter extends HttpFilter implements Filter {

	private String encoding;

	public EncFilter() {
		super();
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(encoding); // 하드 코딩 : 유지보수 용이성을 망가뜨림
			System.out.println("[EncFilter] 인코딩 UTF-8 완료");
		}

		chain.doFilter(request, response);

		// 다음 필터가 존재한다면, 그곳으로 이동해라.
		// 더이상 수행할 필터가 없다면, 원래 수행하던 요청으로 돌아가라
	}

	public void init(FilterConfig fConfig) throws ServletException {

		this.encoding = fConfig.getServletContext().getInitParameter("encoding");

	}

}