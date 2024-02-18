package controller.sms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.sms.CreateVerificationCode;

// js의 요청을 처리해줌 
@WebServlet("/sendAuthenticationSMS")
public class SendAuthenticationSMS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SendAuthenticationSMS() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("서블릿 들어옴");
		String ph = request.getParameter("ph");
		AuthenticationSMS authenticationSMS = new AuthenticationSMS("NCS8QSCV2PUOXICE",
				"0HWUXNZ9BQV5I602DXDST5FYO6WAOZRW");

		// 인증코드 생성 로직작성
		// CreateVerificationCode의 인스턴스 생성
		// 인증 코드 생성해주는 클래
		CreateVerificationCode verificationCodeGenerator = new CreateVerificationCode();

		// 인증 코드 생성
		String verificationCode = verificationCodeGenerator.createVerificationCode();

		// 인증 SMS 전송
		boolean isSMSSent = authenticationSMS.sendMsg(ph, verificationCode);

		if (isSMSSent) {
			response.getWriter().write(verificationCode);
			request.setAttribute("result", verificationCode);
		} else {
			response.getWriter().write("fail");
		}

	}

}
