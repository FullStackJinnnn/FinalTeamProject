<%@page import="java.math.BigInteger"%>
<%@page import="java.util.Random"%>
<%@page import="java.security.SecureRandom"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>로그인페이지</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/chalKag/assets/css/main.css" />

<style>
input::-webkit-input-placeholder {
	font-family: "Source Sans Pro", Helvetica, sans-serif;
}

a {
	font-family: "Source Sans Pro", Helvetica, sans-serif;
	border-bottom: none;
}

.fields {
	text-align: center;
}

.field {
	margin-bottom: 10px;
}

#footer {
  background-color : #ffffff;
}

</style>
</head>

<body class="is-preload">
	<stone:printNav member='${member}' />

	<div id="footer">
		<section>

			<form id="login" method="post" action="/chalKag/login.do">
				<div class="fields">
					<!-- 이메일 -->
					<div class="field">
						<label for="id"></label> <input type="email" name="id"
							id="id" placeholder="이메일을 입력해주세요" required />
					</div>

					<!-- 비밀번호 -->
					<div class="field">
						<label for="pw"></label> <input type="password"
							name="pw" id="pw" maxlength="16"
							placeholder="비밀번호를 입력해주세요" required />
					</div>

					<div id="social_login">
						<a id="naverLoginLink" href="#"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG" /></a>
						<a href="javascript:kakaoLogin();"><img height="50"
							src="/chalKag/images/kakao_login_small.png" alt="카카오계정 로그인" /></a>
					</div>

					<div class="field">
						<a href="/chalKag/findIdPage.do">아이디 찾기</a> <b> / </b> <a
							href="/chalKag/findPwPage.do">비밀번호 찾기</a> <b> / </b> <a
							href="/chalKag/joinPage.do">회원가입</a>

						<div class="actions" style="text-align: center; margin-top: 10px;">
							<input type="button" value="로그인" style="width: 250px;" onclick="loginAlert()"/>
						</div>
					</div>
				</div>
			</form>
		</section>
	</div>

	<stone:copyright />



	<script src="/chalKag/assets/js/jquery.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrollex.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrolly.min.js"></script>
	<script src="/chalKag/assets/js/browser.min.js"></script>
	<script src="/chalKag/assets/js/breakpoints.min.js"></script>
	<script src="/chalKag/assets/js/util.js"></script>
	<script src="/chalKag/assets/js/main.js"></script>
	<script src="/chalKag/assets/js/loginAlert.js"></script>
	<script src="/chalKag/assets/js/naverLogin.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script type="text/javascript"
		src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script type="text/javascript" src="/chalKag/assets/js/kakaoLogin.js"></script>

</body>

</html>