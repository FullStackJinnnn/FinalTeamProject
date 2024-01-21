<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>찾은 아이디 확인 페이지</title>
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

	<!-- Wrapper -->

	<stone:printNav/>

	<div id="footer">
		<section>
										<!-- 액션 꼭 수정해!!!!!!!!!!!!!!!!!!!! -->
			<form id="findId" method="post" action="#">
				<div class="fields" style="border:1px solid black; height:400px;">

					<!-- 찾은 아이디 -->
					<div class="field">
						<label for="name"></label> <input type="text" name="findName"
							id="findName" placeholder="이름을 입력해주세요" required />
					</div>


					<!--  확인버튼 -->
					<div class="field">

						<div class="actions" style="text-align: center; margin-top: 10px;">
							<input type="submit" value="로그인페이지" style="width: 250px;" />
						</div>
					</div>
					
					<!-- 비밀번호 찾기 -->
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
</body>

</html>