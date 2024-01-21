<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>다른유저 페이지</title>
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
	<link rel="stylesheet" href="assets/css/noscript.css" />
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

.action>button {
	text-align: right;
}

.photo {
	width: 50px;
	height: 50px;
	border: 3px solid black;
	overflow: hidden;
}
</style>
</head>

<body class="is-preload">

	<stone:printNav member='${member}'/>
	

	<div id="footer">
		<section>
			<!-- 회원탈퇴로 -->
			<form id="myPage" method="post" action="#">
				<div class="fields">

					<!-- 프사 -->
					<div class="field">
						<div class="photo">
							<img alt="이미지 01" src="images/1.jpg">
						</div>
						<div>
							<button type="button" onclick="location.href='#'" style="display : flex; margin-top:10px;">이미지 선택</button>
						</div>
					</div>




					<!-- 이름 -->
					<div class="field">
						<label for="myPageName"></label> <input type="text"
							name="myPageName" id="myPageName" placeholder="이름" readonly />
					</div>

					<!-- 닉네임 -->
					<div class="field">
						<label for="userPageNickname"></label> <input type="text"
							name="userPageNickname" id="userPageNickname" placeholder="닉네임"
							readonly />
					</div>

					<!-- 아이디 -->
					<div class="field">
						<label for="myPageID"></label> <input type="text" name="userPageID"
							id="userPageID" placeholder="자기 아이디 표시" readonly />
					</div>


					<!-- 자신이 작성한 글로 이동 -->
					<div class="field" style="text-align: center; margin-top: 10px;">
						<div class="actions">
							<button type="button" onclick="location.href="#">유저가 작성한글로
								가기</button>
						</div>
					</div>
					
					<!-- 자신이 작성한 글로 이동 -->
					<div class="field" style="text-align: center; margin-top: 10px;">
						<div class="actions">
							<button type="button" onclick="location.href="#">유저가 작성한 댓글로
								가기</button>
						</div>
					</div>
	
	
					<!-- 관리자라면 이 버튼이 보이게 -->
					<!--  회원탈퇴 -->
					<div class="field" style="text-align: center; margin-top: 10px;">
						<div class="actions">
							<input type="submit" value="회원 탈퇴" style="width: 160px;" />
						</div>
					</div>

					<!--  확인버튼 -->
					<div class="field">
						<div class="actions" style="text-align: right; margin-top: 10px;">
							<!-- 메인 페이지나 마이페이지로 -->
							<button type="button" onclick="location.href="#">변경 완료</button>

						</div>
					</div>

				</div>
			</form>
		</section>
	</div>
	<stone:copyright/>
</body>

</html>