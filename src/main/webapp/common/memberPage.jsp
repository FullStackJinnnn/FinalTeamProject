<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.member.*"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<!-- jQuery 추가 -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<meta charset="UTF-8">
<title>다른 유저 정보</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet"
	href="/chalKag/assets/css/main.css" />


<style>
input::-webkit-input-placeholder {
	font-family: "Source Sans Pro", Helvetica, sans-serif;
}

a {
	font-family: "Source Sans Pro", Helvetica, sans-serif;
}

.field {
	margin-bottom: 50px;
}

/* 파일 업로드 버튼 외부 css적용 안되어서 내부 css로 적용...*/
/* Button */

/* .fileUpload {
	display: flex;
	align-items: center;
} */
.photo {
	width: 350px;
	height: 350px;
	border: 3px solid black;
	overflow: hidden;
}

.field input[type="text"] {
	display: inline-block;
	width: 60%;
	font-weight: bold;
}

#fileInput {
	display: none;
}
</style>
</head>

<body class="is-preload">

	<stone:printNav member='${member}' />

	<div id="footer">

		<section>
			<div class="fields">
				<h1 style="font-size: 150px; text-align: center;">MemberPage</h1>
				<form id="changeProfile">
					<!-- 프사 -->
					<div class="field">
						<div class="photo">
							<img id="preview" alt="프로필 이미지" src="memberProfileImages/${memberData.profile}">
						</div>
					</div>
				</form>

				<!-- 	</form> -->

				<!-- 아이디 -->
				<div class="field">
					<label for="myPageID"></label> <input type="text" name="myPageID"
						id="myPageID" value="${memberData.id}" readonly />
				</div>

				<!-- 닉네임 -->
				<div class="field">
					<label for="myPageNickname"></label> <input type="text"
						name="myPageNickname" id="myPageNickname"
						value="${memberData.nickname}" readonly />
				</div>

				<!-- 번호 -->
				<div class="field">
						<label for="myPagePh"></label> <input type="text" name="myPagePh"
							id="myPagePh" value="${memberData.ph}" readonly />
				</div>

				<!-- 유저가 작성한 글로 이동 -->
				<div class="field">
					<form id="myBoard" method="post" action="#">
						<button type="button"
							onclick="location.href='/chalKag/memberBoardSelectAllPage.do?nickname=${memberData.nickname}'">유저가 작성한글 보기
							</button>
					</form>
				</div>

				<div class="field"
					style="display: flex; justify-content: space-between; margin-top: 30px">
					<div>
						<button type="button" onclick="location.href='main.do'">돌아가기</button>
					</div>
				</div>
			</div>
		</section>
	</div>
	<div id="dataContainer" data-ph='${memberData.ph}'></div>
	<!-- 커스텀 태그를 사용하여 저작권 정보 포함 -->
	<stone:copyright />

	<!-- JavaScript 파일 링크 -->
	<script src="/chalKag/assets/js/jquery.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrollex.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrolly.min.js"></script>
	<script src="/chalKag/assets/js/browser.min.js"></script>
	<script src="/chalKag/assets/js/breakpoints.min.js"></script>
	<script src="/chalKag/assets/js/util.js"></script>
	<script src="/chalKag/assets/js/main.js"></script>
	<script src="/chalKag/assets/js/profileImage.js"></script>

</body>

</html>
