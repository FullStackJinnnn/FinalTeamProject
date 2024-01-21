<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>(마이페이지)비밀번호 변경</title>
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
</style>
</head>

<body class="is-preload">

	<!-- Wrapper -->

	<stone:printNav/>

	<div id="footer">
		<section>
										<!-- 액션 꼭 수정해!!!!!!!!!!!!!!!!!!!! -->
			<form id="findId" method="post" action="#"
				onsubmit="return validateForm()">
				<div class="fields">

					<!-- 비밀번호 입력 -->
					<div class="field">
						<label for="checkPw"></label> <input type="text" name="checkPw"
							id="checkPw" placeholder="비밀번호를 입력해주세요" required />
					</div>

					<!-- 변경할 비밀번호 확인 -->
					<div class="field">
						<label for="checkPwCheck"></label> <input type="text" name="checkPwCheck"
							id="checkPwCheck" placeholder="비밀번호를 다시 한 번 확인해주세요" required />
					</div>

					<!--  확인버튼 -->
					<div class="field">

						<div class="actions" style="text-align: center; margin-top: 10px;">
							<input type="submit" value="비밀번호 변경" style="width: 250px;" />
						</div>
					</div>
				</div>
			</form>
		</section>
	</div>
	<stone:copyright/>
</body>

</html>