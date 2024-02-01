<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/chalKag/assets/css/main.css" />

</head>

<body class="is-preload">

	<stone:printNav member='${member}'/>

	<div id="footer">
		<section>
			
			<form id="findId" method="post" action="/chalKag/findIdResultPage.do">
				<div class="fields">

					<!-- 이름입력.안승준 -->
					<div class="field">
						<label for="name"></label> <input type="text" name="name"
							id="name" placeholder="이름을 입력해주세요" required />
					</div>

					<stone:phCheck />

					<!-- 아이디 찾기 버튼.안승준 -->
					<div class="field">

						<div class="actions" style="text-align: center; margin-top: 10px;">
							<input type="submit" value="아이디 찾기" style="width: 250px;" />
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
	<script src="/chalKag/assets/js/signIn.js"></script>
	<script src="/chalKag/assets/js/checkID.js"></script>
	<script src="/chalKag/assets/js/checkNickname.js"></script>
	<script src="/chalKag/assets/js/smsCheck.js"></script>
	<script src="/chalKag/assets/js/sendAuthentication.js"></script>
	
</body>

</html>