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
}

.fields {
	text-align: center;
}

.field {
	margin-bottom: 10px;
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
						<label for="memberID"></label> <input type="email" name="memberID"
							id="memberID" placeholder="이메일을 입력해주세요" required />
					</div>

					<!-- 비밀번호 -->
					<div class="field">
						<label for="memberPW"></label> <input type="password"
							name="memberPW" id="memberPW" maxlength="16"
							placeholder="비밀번호를 입력해주세요" required />
					</div>
					
					<div>
					
					<ul class="icons alt">
						<li><a href="#" class="icon brands alt fa-twitter" style="margin: 0 10px;"><span class="label">Twitter</span></a></li>
						<li><a href="#" class="icon brands alt fa-facebook-f" style="margin: 0 10px;"><span class="label">Facebook</span></a></li>
						<li><a href="#" class="icon brands alt fa-instagram" style="margin: 0 10px;"><span class="label">Instagram</span></a></li>
						<li><a href="#" class="icon brands alt fa-github" style="margin: 0 10px;"><span class="label">Github</span></a></li>
						<li><a href="#" class="icon brands alt fa-dribbble" style="margin: 0 10px;"><span class="label">Dribbble</span></a></li>
					</ul>
					
					</div>

					<div class="field">
						<a href="/chalKag/findIdPage.do">아이디 찾기</a> 
						<b> / </b>  
						<a href="/chalKag/findPwPage.do">비밀번호 찾기</a> 
						<b> / </b> 
						<a href="/chalKag/joinPage.do">회원가입</a>

						<div class="actions" style="text-align: center; margin-top: 10px;">
							<input type="submit" value="로그인" style="width: 250px;" />
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
	
</body>

</html>