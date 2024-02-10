package controller.async;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.member.MemberDAO;
import model.member.MemberDTO;

@WebServlet("/naverLogin.do")
public class NaverLoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NaverLoginAction() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String clientId = "2jGYcIGym6EzQohgHOcs";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "O8RsLA0BDo";// 애플리케이션 클라이언트 시크릿값";

		String code = request.getParameter("code");
		String state = request.getParameter("state");

		String redirectURI = URLEncoder.encode("http://localhost:8088/chalKag/naverLogin.do", "UTF-8");
		String apiURL;

		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		apiURL += "client_id=" + clientId;
		apiURL += "&client_secret=" + clientSecret;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&code=" + code;
		apiURL += "&state=" + state;

		String access_token = "";
		String refresh_token = "";

		System.out.println("apiURL=" + apiURL);
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.print("responseCode=" + responseCode);
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if (responseCode == 200) {
				System.out.println(res.toString());
				JSONParser parsing = new JSONParser();
				Object obj = parsing.parse(res.toString());
				JSONObject jsonObj = (JSONObject) obj;

				access_token = (String) jsonObj.get("access_token");
				System.out.println("로그 access_token 확인용 : [" + access_token + "]");
				refresh_token = (String) jsonObj.get("refresh_token");
				System.out.println("로그 refresh_token 확인용 : [" + refresh_token + "]");

				// 사용자 프로필 API 호출
				String header = "Bearer " + access_token; // Bearer Token 형식으로 변환
				String apiUrl = "https://openapi.naver.com/v1/nid/me";
				URL profileUrl = new URL(apiUrl);
				HttpURLConnection profileCon = (HttpURLConnection) profileUrl.openConnection();
				profileCon.setRequestMethod("GET");
				profileCon.setRequestProperty("Authorization", header);
				int profileResponseCode = profileCon.getResponseCode();
				BufferedReader profileBr;
				if (profileResponseCode == 200) { // 정상 호출
					profileBr = new BufferedReader(new InputStreamReader(profileCon.getInputStream()));
				} else { // 에러 발생
					profileBr = new BufferedReader(new InputStreamReader(profileCon.getErrorStream()));
				}
				String profileInputLine;
				StringBuffer profileRes = new StringBuffer();
				while ((profileInputLine = profileBr.readLine()) != null) {
					profileRes.append(profileInputLine);
				}
				profileBr.close();
				if (profileResponseCode == 200) {
					System.out.println(profileRes.toString());
					JSONObject profileObj = (JSONObject) parsing.parse(profileRes.toString());
					JSONObject responseObj = (JSONObject) profileObj.get("response");
					String email = (String) responseObj.get("email");
					String name = (String) responseObj.get("name");
					String nickname = (String) responseObj.get("nickname");
					String birthyear = (String) responseObj.get("birthyear");
					String birthday = (String) responseObj.get("birthday");
					String mobile = (String) responseObj.get("mobile");
					
					System.out.println(email);
					System.out.println(name);
					System.out.println(nickname);
					System.out.println(birthyear);
					System.out.println(birthday);
					System.out.println(mobile);
					
					MemberDAO memberDAO = new MemberDAO();
					MemberDTO memberDTO = new MemberDTO();
					memberDTO.setId(email);
					System.out.println("[NaverLoginAction] 네이버 서버와 연결되는지 확인용 : " + memberDTO.getId());
					memberDTO.setSearchCondition("로그인");
					memberDTO.setSnsLoginCondition("SNS로그인");
					memberDTO = memberDAO.selectOne(memberDTO);
					
					System.out.println("네이버 : " + memberDTO);

					if (memberDTO != null) { // 로그인 성공시 세션 저장 후 메인으로 이동 , 이동 할 정보 없음
						if (!memberDTO.getGrade().equals("탈퇴")) {
							HttpSession session = request.getSession();
							session.setAttribute("member", memberDTO.getId());
							System.out.println("[NaverLoginAction] 로그인 성공");
							
							response.sendRedirect("/chalKag/main.do");
							
						} else {
							System.out.println("[NaverLoginAction] 로그인 실패");
						}
					} else {
						System.out.println("[NaverLoginAction] 없는 회원입니다.");
						
						request.setAttribute("naverMember", responseObj);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/naverJoin.do");
						dispatcher.forward(request, response);
						
					}
					
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
