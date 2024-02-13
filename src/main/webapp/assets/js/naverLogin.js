var clientId = "2jGYcIGym6EzQohgHOcs"; // 애플리케이션 클라이언트 아이디값.안승준
var redirectURI = encodeURIComponent("http://localhost:8088/chalKag/naverLogin.do"); // 네이버 로그인 후 리디렉션될 URI.안승준
var state = Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15); // CSRF 공격 방지를 위한 상태 토큰 생성.안승준
var apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code" + "&client_id=" + clientId
	+ "&redirect_uri=" + redirectURI + "&state=" + state; // 네이버 OAuth 인증 요청을 위한 URL 생성.안승준
document.write(apiURL); // 생성된 URL을 출력하여 확인 (실제 사용시에는 제거).안승준
sessionStorage.setItem("state", state); // 상태 토큰을 세션 스토리지에 저장.안승준

// 네이버 로그인 링크를 클릭했을 때 실행될 함수 등록.안승준
document.getElementById("naverLoginLink").addEventListener("click", function() {
	window.location.href = apiURL; // 네이버 OAuth 인증 페이지로 리디렉션.안승준
});