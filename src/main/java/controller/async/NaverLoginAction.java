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

		// 네이버 어플리케이션 클라이언트 아이디.안승준
		String clientId = "2jGYcIGym6EzQohgHOcs";
		// 네이버 어플리케이션 클라이언트 시크릿.안승준
		String clientSecret = "O8RsLA0BDo";

		// 네이버 인증 요청에서 받은 code와 state를 가져옴.안승준
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		// 네이버 인증 요청에서 받은 code와 state 확인 로그.안승준
		System.out.println("[NaverLoginAction]code 로그 = ["+code+"]");
		System.out.println("[NaverLoginAction]state 로그 = ["+state+"]");

		// 리다이랙트 URI 인코딩.안승준
		String redirectURI = URLEncoder.encode("http://localhost:8088/chalKag/naverLogin.do", "UTF-8");
		String apiURL;

		// 네이버 인증 요청 API URI 생성.안승준
		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		apiURL += "client_id=" + clientId;
		apiURL += "&client_secret=" + clientSecret;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&code=" + code;
		apiURL += "&state=" + state;

		String access_token = "";
		String refresh_token = "";

		// API URI 확인 로그.안승준
		System.out.println("[NaverLoginAction]apiURL 로그 =" + apiURL);
		
		try {
			
			// 네이버 인증 요청.안승준
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			
			// responseCode 확인 로그.안승준
			System.out.print("[NaverLoginAction]responseCode 로그= ["+responseCode+"]");
			
			if (responseCode == 200) { // 정상 호출시 수행.안승준
				
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				
			} else { // 에러 발생시 수행.안승준
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String inputLine;
			StringBuffer res = new StringBuffer();
			
			while ((inputLine = br.readLine()) != null) {
				
				res.append(inputLine);
				
			}
			
			// br = new BufferedReader(new InputStreamReader(con.getInputStream()));.안승준
			// HTTP 연결을 통해 들어오는 데이터를 읽기 위해 사용.안승준
			// con.getInputStream()은 이진 형태로 데이터 제공.안승준
			// BufferReader를 통해 이진을 텍스트 형태로 변환.안승준
			
			br.close();
			
			if (responseCode == 200) {
				
				JSONParser parsing = new JSONParser();
				Object obj = parsing.parse(res.toString());
				JSONObject jsonObj = (JSONObject) obj;

				// 네이버 인증 응답에서 access_token과 refresh_token을 가져옴.안승준
				access_token = (String) jsonObj.get("access_token");
				
				// 네이버 인증 응답 access_token 확인 로그.안승준
				System.out.println("[NaverLoginAction]access_token 로그 = [" + access_token + "]");
				
				refresh_token = (String) jsonObj.get("refresh_token");
				
				// 네이버 인증 응답 refresh_token 확인 로그.안승준
				System.out.println("[NaverLoginAction]refresh_token 로그 = [" + refresh_token + "]");

				// 사용자 프로필 API 호출.안승준
				String header = "Bearer " + access_token;
				String apiUrl = "https://openapi.naver.com/v1/nid/me";
				URL profileUrl = new URL(apiUrl);
				HttpURLConnection profileCon = (HttpURLConnection) profileUrl.openConnection();
				profileCon.setRequestMethod("GET");
				profileCon.setRequestProperty("Authorization", header);
				int profileResponseCode = profileCon.getResponseCode();
				BufferedReader profileBr;
				
				if (profileResponseCode == 200) { 
					profileBr = new BufferedReader(new InputStreamReader(profileCon.getInputStream()));
				} else { 
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
					
					// 추출 데이터 확인 로그.안승준
					System.out.println(email);
					System.out.println(name);
					System.out.println(nickname);
					System.out.println(birthyear);
					System.out.println(birthday);
					System.out.println(mobile);
					
					// 로그인 기능 수행.안승준
					MemberDAO memberDAO = new MemberDAO();
					MemberDTO memberDTO = new MemberDTO();
					memberDTO.setId(email);
					memberDTO.setSearchCondition("로그인");
					memberDTO.setSnsLoginCondition("SNS로그인");
					memberDTO = memberDAO.selectOne(memberDTO);
					
					System.out.println("[NaverLoginAction] = " + memberDTO);

					if (memberDTO != null) {
						if (!memberDTO.getGrade().equals("탈퇴")) {
							HttpSession session = request.getSession();
							session.setAttribute("member", memberDTO.getId());
							System.out.println("[NaverLoginAction]로그인 성공");
							
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
