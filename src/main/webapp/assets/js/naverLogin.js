var clientId = "2jGYcIGym6EzQohgHOcs"; // 애플리케이션 클라이언트 아이디값
var redirectURI = encodeURIComponent("http://localhost:8088/chalKag/naverLogin.do");
var state = Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15); // SecureRandom 대신 사용
var apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code" + "&client_id=" + clientId
	+ "&redirect_uri=" + redirectURI + "&state=" + state;
document.write(apiURL);
sessionStorage.setItem("state", state);

document.getElementById("naverLoginLink").addEventListener("click", function() {
	window.location.href = apiURL;
});