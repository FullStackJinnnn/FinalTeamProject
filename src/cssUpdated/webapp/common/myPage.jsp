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
	border-bottom: none;
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

.photo {
	width: 350px;
	height: 350px;
	border: 3px solid black;
	overflow: hidden;
}




/* #changeCol.field input[type="text"] {
    text-align: left;
    	font-weight: bold;
}
 */

#fileInput {
	display: none;
}

.myPageUserFont {
	font-size: 30px;
}

#userInfo {
	width: 60%;
}

#labels {
	width: 15%;
}

#idAndName {
	padding-left : 5%;
}
.field {
	margin-bottom: 50px;
	/* align-items: center; */
}

/* #label {
	padding-left : 5%;
} */

/* #changeCol {
	padding-left : 5%;
} */

.field label {
	 display: block; /* 인라인 요소를 블록 요소로 변경 */
    height: 63px;
     line-height: 63px;
    font-size: 27px; /* 글씨 크기 조절 */
    text-align: left;
    

}

  .field input {
    display: inline-block;
    width: 50%; /* Adjust the width as needed */
} 

  .field input[type="text"] {
	display: inline-block;
	width: 97%;
	font-weight: bold;
}   
 

#changeCol.field #myPageNickname, #myPagePh{
	width: 71%;
	padding-right: 10%;
}



#changeCol.field #btnNicknameSubmit, #phChBtn {
	width: 25.5%;
}

/* #memberDataID, #memberDataName {
	width: 95%;
} */

 .footerBtns {
	width: 28%;
	margin-right: 5%;
} 

</style>
</head>

<body class="is-preload">

	<stone:printNav member='${member}' />

<div id="footer">
 <section> 
 <div class="fields"> 
 <h1 style="font-size: 80px; text-align: center;">MyPage</h1> <hr>
		<div style="text-align: center; display: flex">
			<div class="field">
				<form id="changeProfile">
					<div class="fileUpload">
						<img id="preview" src="/chalKag/memberProfileImages/${memberData.profile}?v=${Math.random()}" />
					</div>
					<div class="fileUpload" style="margin-top: 17px;">
						<label for="fileInput" class="imageUpload">이미지 선택 
						<input type="file" name="file" id="fileInput" form="changeProfile"></label>
						<input type="submit" value="이미지 확정" id="btnImageSubmit">
					</div>
				</form>
			</div>

			<div id="labels">
				<div class="field" id="label">
					<div class="field">
						<label for="myPageID">&nbsp;&nbsp;&nbsp;&nbsp;ID :</label>
					</div>
					
					<div class="field">
					<label for="myPageID">&nbsp;&nbsp;&nbsp;&nbsp;NAME :</label>
					</div>
					
					<div class="field">
					<label for="myPageNickname" >&nbsp;&nbsp;&nbsp;&nbsp;NICKNAME :</label> 
					</div>
					
					<div class="field">
						<label for="myPagePh">&nbsp;&nbsp;&nbsp;&nbsp;PH :</label> 
					</div>
				</div>
			</div>
				<div id="userInfo">
				<div class="field" id="changeCol">
					<div class="field">
						<input type="text" id="memberDataID" value="${memberData.id}" readonly/>
					</div>

					<div class="field">
						<input type="text" id="memberDataName" value="${memberData.name}" readonly >
					</div>

					<div class="field">
						<form id="changeNickname" >
							<input type="text" name="myPageNickname" id="myPageNickname" value="${memberData.nickname}" required >
							<input type="submit" value="닉네임 변경" id="btnNicknameSubmit" />
						</form>
					</div>

					<div class="field">
						<form id="changePh" method="post" action="#">
							<input type="text" name="myPagePh" id="myPagePh" value="${memberData.ph}" readonly/>
							<button type="button" id="phChBtn" style="text-align: center;"onclick="location.href='/chalKag/changePh.do'">전화번호 변경</button>
						</form>
					</div>

<div class="field"  style="padding-left: 10%; display: flex; justify-content: space-between;">
    <button class="footerBtns" type="button" style="margin: 13px; padding: 0.1rem;" onclick="location.href='/chalKag/myboardSelectAllPage.do?id=${memberData.id}'">내 작성글로 가기</button>
    <button class="footerBtns" type="button" style="margin: 13px;" onclick="location.href='/chalKag/changePwPage.do'">비밀번호 변경</button>
    <button class="footerBtns" type="button" style="margin: 13px;" onclick="location.href='/chalKag/deleteAccount.do'">회원탈퇴</button>
</div>
				</div>
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
