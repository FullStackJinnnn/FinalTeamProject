<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.member.*"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<!-- jQuery 추가 -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>다른 유저 정보</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet"
	href="/chalKag/assets/css/main.css" />

<noscript>
	<link rel="stylesheet" href="/chalKag/assets/css/noscript.css" />
</noscript>
<style>
input::-webkit-input
-placeholder {
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
							<img id="preview" alt="프로필 이미지" src="memberProfileImages/${memberDTO.profile}"
								onload="resizePreviewImage(this, 350, 350)">
						</div>
					</div>
				</form>

				<!-- 	</form> -->

				<!-- 아이디 -->
				<div class="field">
					<label for="myPageID"></label> <input type="text" name="myPageID"
						id="myPageID" value="${memberDTO.id}" readonly />
				</div>

				<!-- 닉네임 -->
				<div class="field">
					<label for="myPageNickname"></label> <input type="text"
						name="myPageNickname" id="myPageNickname"
						value="${memberDTO.nickname}" readonly />
				</div>

				<!-- 번호 -->
				<div class="field">
						<label for="myPagePh"></label> <input type="text" name="myPagePh"
							id="myPagePh" value="${memberDTO.ph}" readonly />
				</div>

				<!-- 유저가 작성한 글로 이동 -->
				<div class="field">
					<form id="myBoard" method="post" action="#">
						<button type="button"
							onclick="location.href='/chalKag/memberBoardSelectAllPage.do?nickname=${memberDTO.nickname}'">유저가 작성한글 보기
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
	<script>
	
		 <!-- 전화번호 암호화  -->
	    // DOMContentLoaded 이벤트가 발생했을 때 실행되는 함수 화면이 다 로드됨을 뜻함
	    document.addEventListener('DOMContentLoaded', function () {
	    	
	        // myPagePh 엘리먼트를 가져와서 phoneNumberElement 변수에 저장
	        var phoneNumberElement = document.getElementById('myPagePh');
	
	        // phoneNumberElement이 존재하면 실행
	        if (phoneNumberElement) {
	        	
	            // myPagePh의 값을 maskPhoneNumber 함수를 이용하여 "*"로 가려진 형태로 변경
	            phoneNumberElement.value = maskPhoneNumber('${memberDTO.ph}');
	        }
	    });
	
	    // 전화번호의 가운데 4자리를 "*"로 암호화 하는 함수
	    function maskPhoneNumber(phoneNumber) {
	    	
	        // 전화번호를 '-'로 분리하여 parts 배열에 저장
	        var parts = phoneNumber.split('-');
	
	        // parts 배열의 길이가 3이면 실행
	        if (parts.length === 3) {
	        	
	            // 가운데 부분을 '****'로 대체하고 다시 '-'로 조합하여 반환
	            parts[1] = '****';
	            return parts.join('-');
	        } else {
	        	
	            // 형식에 맞지 않는 경우 그대로 반환
	            return phoneNumber;
	        }
	    }
	    
	    <!-- MyPage들어올때 유저가 등록한 이미지를 프로필사이즈에 맞게 조정  -->
	    // 이미지 리사이징 여부를 나타내는 변수 
	    // 처음 MyPage에 들어왔을 때 딱 1번 리사이징 하기 위한 변수
	    var imageResized = false;
	
	    // 프로필 이미지를 원하는 크기로 조정하는 함수
	    function resizePreviewImage(img, maxWidth, maxHeight) {
	    	
	        // 이미지가 리사이징되었다면 함수 종료
	        if (imageResized) {
	            return;
	        }
	
	        // 캔버스를 생성하고 2D 컨텍스트를 얻어옴
	        var canvas = document.createElement('canvas');
	        var ctx = canvas.getContext('2d');
	
	        // 원본 이미지의 너비와 높이
	        var width = img.width;
	        var height = img.height;
	
	        // 너비와 높이 중에서 작은 쪽을 기준으로 1:1 비율로 만듦
	        var size = Math.min(width, height);
	        var xOffset = (width - size) / 2;
	        var yOffset = (height - size) / 2;
	
	        // 캔버스에 그림
	        canvas.width = size;
	        canvas.height = size;
	        ctx.drawImage(img, xOffset, yOffset, size, size, 0, 0, size, size);
	
	        // 조정된 이미지를 원하는 크기로 만들기 위한 새로운 캔버스 생성
	        var resizedCanvas = document.createElement('canvas');
	        var resizedCtx = resizedCanvas.getContext('2d');
	        resizedCanvas.width = maxWidth;
	        resizedCanvas.height = maxHeight;
	
	        // 파일 확장자에 따라 JPEG 또는 PNG로 변환하여 조정된 이미지로 설정
	 /*        if (img.src.toLowerCase().endsWith('.png')) {
	            resizedCtx.drawImage(canvas, 0, 0, size, size, 0, 0, maxWidth, maxHeight);
	            img.src = resizedCanvas.toDataURL('image/png');
	        } else { */
	            resizedCtx.drawImage(canvas, 0, 0, size, size, 0, 0, maxWidth, maxHeight);
	            img.src = resizedCanvas.toDataURL('image/jpeg');
	       // }
	
	        // 이미지가 리사이징되었음을 표시 207번라인으로 돌아가 함수종료
	        imageResized = true;
	    }
	    
	   
	</script>
</body>

</html>
