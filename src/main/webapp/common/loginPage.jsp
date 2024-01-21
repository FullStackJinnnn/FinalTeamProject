<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>로그인페이지</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="../assets/css/main.css" />
<script src="../assets/js/jquery.min.js"></script>
<script src="../assets/js/jquery.scrollex.min.js"></script>
<script src="../assets/js/jquery.scrolly.min.js"></script>
<script src="../assets/js/browser.min.js"></script>
<script src="../assets/js/breakpoints.min.js"></script>
<script src="../assets/js/util.js"></script>
<script src="../assets/js/main.js"></script>

<noscript>
	<link rel="stylesheet" href="../assets/css/noscript.css" />
</noscript>
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
						<!--맞나요? -->
	<stone:printNav member='${member}'/>

	<div id="footer">
		<section>

			<form id="login" method="post" action="../login.do">
				<div class="fields">

					<!-- 이메일 -->
					<div class="field">
						<label for="userEmail"></label> <input type="email"
							name="memberID" id="userEmail" placeholder="이메일을 입력해주세요"
							required />
					</div>

					<!-- 비밀번호 -->
					<div class="field">
						<label for="pw"></label> <input type="password" name="memberPW"
							id="userPw" maxlength="16" placeholder="비밀번호를 입력해주세요" required />
					</div>

					<div class="field">
						<a href="findIdPage.do">아이디 찾기</a> <b> / </b> <a
							href="findPwPage.do">비밀번호 찾기</a> <b> / </b> <a href="join.do">회원가입</a>

						<div class="actions" style="text-align: center; margin-top: 10px;">
							<input type="submit" value="로그인" style="width: 250px;" />
						</div>
					</div>
				</div>
			</form>
		</section>
	</div>
	<stone:copyright/>
</body>

</html>