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
<title>마이페이지</title>
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

.field {
	margin-bottom: 50px;
}

/* 파일 업로드 버튼 외부 css적용 안되어서 내부 css로 적용...*/
/* Button */
.fileUpload label.imageUpload {
	-moz-appearance: none;
	-webkit-appearance: none;
	-ms-appearance: none;
	appearance: none;
	-moz-transition: background-color 0.2s ease-in-out, box-shadow 0.2s
		ease-in-out, color 0.2s ease-in-out;
	-webkit-transition: background-color 0.2s ease-in-out, box-shadow 0.2s
		ease-in-out, color 0.2s ease-in-out;
	-ms-transition: background-color 0.2s ease-in-out, box-shadow 0.2s
		ease-in-out, color 0.2s ease-in-out;
	transition: background-color 0.2s ease-in-out, box-shadow 0.2s
		ease-in-out, color 0.2s ease-in-out;
	border: 0;
	border-radius: 0;
	cursor: pointer;
	display: inline-block;
	font-family: "Source Sans Pro", Helvetica, sans-serif;
	font-size: 0.8rem;
	font-weight: 900;
	letter-spacing: 0.075em;
	height: 3rem;
	line-height: 3rem;
	padding: 0 2rem;
	text-align: center;
	text-decoration: none;
	text-transform: uppercase;
	white-space: nowrap;
	background-color: transparent;
	box-shadow: inset 0 0 0 2px #717981;
	color: #717981 !important;
	margin-top: 0.325rem;
}

.fileUpload label.imageUpload:hover {
	box-shadow: inset 0 0 0 2px #18bfef;
	color: #18bfef !important;
}

.fileUpload label.imageUpload.primary {
	background-color: #212931;
	box-shadow: none;
	color: #ffffff !important;
}

.fileUpload label.imageUpload.primary:hover {
	background-color: #18bfef;
}

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
				<h1 style="font-size: 150px; text-align: center;">MyPage</h1>
				<form id="changeProfile">
					<!-- 프사 -->
					<div class="field">
						<div class="photo">
							<!-- 브라우저에서 이미지가 캐시에 저장되는 것을 방지하려면 일반적으로 이미지의 URL에 무작위 쿼리 매개변수를 추가하거나, 
    파일 이름을 변경하여 이미지 URL을 고유하게 만들 수 있다. 이렇게 하면 브라우저가 이미지를 새로운 것으로 간주하고 캐시에 저장하지 않는다.
    ?v=${Math.random()} 부분은 무작위 숫자를 포함하는 쿼리 매개변수를 추가하는 것-->
							<img id="preview" alt="프로필 이미지"
								src="/chalKag/memberProfileImages/${memberData.profile}?v=${Math.random()}">
						</div>

						<div class="fileUpload">
							<label for="fileInput" class="imageUpload">이미지 선택 <input
								type="file" name="file" id="fileInput" form="changeProfile"></label>

							<input type="submit" value="이미지 확정" id="btnImageSubmit"><br>
						</div>
					</div>
				</form>

				<!-- 	</form> -->

				<!-- 아이디 -->
				<div class="field">
					<label for="myPageID"></label> <input type="text" name="myPageID"
						id="myPageID" value="${memberData.id}" readonly />
				</div>

				<!-- 이름 -->
				<div class="field">
					<label for="myPageName"></label> <input type="text"
						name="myPageName" id="myPageName" value="${memberData.name}"
						readonly />
				</div>

				<!-- 닉네임 -->
				<div class="field">
					<form id="changeNickname">
						<label for="myPageNickname"></label> <input type="text"
							name="myPageNickname" id="myPageNickname"
							value="${memberData.nickname}" required /> <input type="submit"
							value="닉네임 변경" id="btnNicknameSubmit" style="margin-left: 30px;" />
					</form>
				</div>

				<!-- 번호 -->
				<div class="field">
					<form id="changePh" method="post" action="#">
						<label for="myPagePh"></label> <input type="text" name="myPagePh"
							id="myPagePh" value="${memberData.ph}" readonly />
						<button type="button" style="margin-left: 30px;"
							onclick="location.href='/chalKag/changePh.do'">전화번호 변경</button>
					</form>
				</div>

				<!-- 자신이 작성한 글로 이동 -->
				<div class="field">
					<button type="button"
						onclick="location.href='/chalKag/myboardSelectAllPage.do?id=${memberData.id}'">내
						작성글로 가기</button>
				</div>

				<div class="field"
					style="display: flex; justify-content: space-between; margin-top: 30px">
					<div>
						<button type="button" onclick="location.href='/chalKag/main.do'">변경완료</button>
					</div>
					<div style="text-align: right;">
						<button type="button"
							onclick="location.href='/chalKag/deleteAccount.do'">회원탈퇴</button>
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
