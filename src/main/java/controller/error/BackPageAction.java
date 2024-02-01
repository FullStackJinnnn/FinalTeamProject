package controller.error;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.front.Action;
import controller.front.ActionForward;

public class BackPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 이전 페이지의 URL을 가져옴
		String referer = request.getHeader("referer");

		// 이전 페이지의 URL이 없을 경우 기본적으로 설정할 URL
		String defaultUrl = "/chalKag/main.do"; // 예: 메인 페이지로 리디렉션
		///// 이거 지울수있음

		// referer가 null이 아니면 이전 페이지로 이동, null이면 기본 URL로 이동
		String path=defaultUrl;

		if (referer != null) {

			path = referer;

		}

		// ActionForward 설정
		ActionForward forward = new ActionForward();
		forward.setPath(path);
		forward.setRedirect(true);

		return forward;
	}

}
