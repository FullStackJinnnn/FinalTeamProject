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
<title>멤버페이지</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/chalKag/assets/css/main.css" />


<style>


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
	width: 97%;
	padding-right: 10%;
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
 <h1 style="font-size: 80px; text-align: center;">MemberPage</h1> <hr>
		<div style="text-align: center; display: flex">
			<div class="field">
						<img id="preview" src="/chalKag/memberProfileImages/${memberData.profile}?v=${Math.random()}" />
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
						</form>
					</div>

					<div class="field">
						<form id="changePh" method="post" action="#">
							<input type="text" name="myPagePh" id="myPagePh" value="${memberData.ph}" readonly/>
						</form>
					</div>

<div class="field" style="padding-left: 10%; display: flex; justify-content: flex-end;">
    <button class="footerBtns" type="button" style="margin: 13px; padding: 0.1rem;" onclick="location.href='/chalKag/memberBoardSelectAllPage.do?nickname=${memberData.nickname}'">회원 작성글로 가기</button>
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
