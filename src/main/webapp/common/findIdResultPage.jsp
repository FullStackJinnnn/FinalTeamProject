<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>찾은 아이디 확인 페이지</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/chalKag/assets/css/main.css" />

<noscript>
	<link rel="stylesheet" href="/chalKag/assets/css/noscript.css" />
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

	<stone:printNav member='${member}'/>

	<div id="footer">
		<section class="post">

			<div class="box">
										
				<p style="text-align: center;">노승현 바보~~~~~~~</p>
		
			</div>


			<div>

				<ul class="actions" style="justify-content: center;">
				
					<li><a href="/chalKag/loginPage.do" class="button primary large">로그인</a></li>
					<li><a href="/chalKag/findPwPage.do" class="button primary large">비밀번호 찾기</a></li>
			
				</ul>


			</div>

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