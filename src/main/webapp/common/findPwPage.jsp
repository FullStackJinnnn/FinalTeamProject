<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
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
		<section>
								
			<form id="findId" method="post" action="/chalKag/findPw.do">
				<div class="fields">
					
					<!-- 아이디 -->
					<div class="field">
						<label for="findId"></label> <input type="text" name="findId"
							id="findId" placeholder="아이디를 입력해주세요" required />
					</div>

					<div class="field">
						<label for="ph"></label> <input type="text" name="phCheck"
							id="phCheck" placeholder="전화번호를 입력해주세요" required />
					</div>

					<!-- 번호 인증 -->
					<div class="field">
						<div class="actions" style="text-align: center; margin-top: 10px;">
							<input type="submit" value="번호 인증" style="width: 250px;" />
						</div>
					</div>

					<!--  확인버튼 -->
					<div class="field">

						<div class="actions" style="text-align: center; margin-top: 10px;">
							<input type="submit" value="비밀번호 찾기" style="width: 250px;" />
						</div>
					</div>
				</div>
			</form>
		</section>
	</div>
	
	<stone:copyright/>
	
	<script src="/chalKag/assets/js/jquery.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrollex.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrolly.min.js"></script>
	<script src="/chalKag/assets/js/browser.min.js"></script>
	<script src="/chalKag/assets/js/breakpoints.min.js"></script>
	<script src="/chalKag/assets/js/util.js"></script>
	<script src="/chalKag/assets/js/main.js"></script>
	
</body>

</html>