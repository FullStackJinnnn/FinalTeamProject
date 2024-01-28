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
<title>마이페이지</title>
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
								src="memberProfileImages/${memberDTO.profile}?v=${Math.random()}"
								onload="resizePreviewImage(this, 350, 350)">
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
						id="myPageID" value="${memberDTO.id}" readonly />
				</div>

				<!-- 이름 -->
				<div class="field">
					<label for="myPageName"></label> <input type="text"
						name="myPageName" id="myPageName" value="${memberDTO.name}"
						readonly />
				</div>

				<!-- 닉네임 -->
				<div class="field">
					<form id="changeNickname">
						<label for="myPageNickname"></label> <input type="text"
							name="myPageNickname" id="myPageNickname"
							value="${memberDTO.nickname}" required /> <input type="submit"
							value="닉네임 변경" id="btnNicknameSubmit" style="margin-left: 30px;" />
					</form>
				</div>

				<!-- 번호 -->
				<div class="field">
					<form id="changePh" method="post" action="#">
						<label for="myPagePh"></label> <input type="text" name="myPagePh"
							id="myPagePh" value="${memberDTO.ph}" readonly />
						<button type="button" style="margin-left: 30px;"
							onclick="location.href='/chalKag/changePh.do'">전화번호 변경</button>
					</form>
				</div>

				<!-- 자신이 작성한 글로 이동 -->
				<div class="field">
					<form id="myBoard" method="post" action="#">
						<button type="button"
							onclick="location.href='/chalKag/myBoardSelectAllPage.do?nickname=${memberDTO.nickname}'">내
							작성글로 가기</button>
					</form>
				</div>

				<div class="field"
					style="display: flex; justify-content: space-between; margin-top: 30px">
					<div>
						<button type="button" onclick="location.href='/chalKag/main.do'">변경
							완료</button>
					</div>
					<div style="text-align: right;">
						<button type="button"
							onclick="location.href='/chalKag/deleteAccount.do'">회원
							탈퇴</button>
					</div>
				</div>
			</div>
		</section>
	</div>
	<script>
	
	$(document).ready(function(){
	      $("#btnImageSubmit").on("click",function(event){
	    	//preventDefault 는 기본으로 정의된 이벤트를 작동하지 못하게 하는 메서드이다. submit을 막음 
	    	//아마 submit type 말고 button을 쓰는게 나은듯..
	    	  event.preventDefault();
	    	  
	    	   var fileInput = document.getElementById('fileInput');
			   var form= $('#changeProfile')[0];
			   var formData = new FormData(form);
	   	    // 선택한 파일이 없으면 알림창 띄우기
	   	   	 if (fileInput.files.length === 0) {
	   	        alert("이미지를 선택해주세요.");
	   	        return false; // 폼 제출을 막기 위해 false 반환
	   	    }
	   	    // 선택한 파일이 있으면 폼 제출을 허용
	      
		   	  $.ajax({
		   	    type: "POST",
		   	    enctype: 'multipart/form-data', 
		   	    url: "profileUpload.do",
		   	    data: formData,
		   	    contentType: false,
		   	    processData: false,
		   	    success: function(data) {
		   	        console.log(data);
		   	        if (data == '1') {
		   	            alert("프로필 이미지 변경완료!");
		   	            return true;
		   	        }
		   	    },
		   	    error: function(error) {
		   	        console.log('에러');
		   	        console.log('에러종류: ' + JSON.stringify(error));
		   	        alert("fail");
		   	    }
		   	});
	          
	      });
	   });
	
	
 $(document).ready(function(){
	      $("#btnNicknameSubmit").on("click",function(event){
	    	  event.preventDefault();
	    	   var myPageNickname = $('#myPageNickname').val();
	    	   console.log(myPageNickname);
	    	   var checkNicknameEmpty = document.getElementById("myPageNickname").value.trim(); //아무것도 입력 안했을때 체크
	   	    // 선택한 파일이 없으면 알림창 띄우기
	   	   	 if (checkNicknameEmpty=="") {
	   	        alert("변경할 닉네임을 입력해주세요!!");
	   	        return false; // 폼 제출을 막기 위해 false 반환
	   	    }
	   	    // 선택한 파일이 있으면 폼 제출을 허용
	      
	    	  $.ajax({
	              type : "POST",
	              url : "changeNickname.do",
	              data : {'myPageNickname' : myPageNickname},
	              dataType : 'text',
				  success : function(data){
					  console.log(data);
					  if(data=='1'){
						  alert("닉네임 변경완료!");  
						  return true; 
					  } else {
						  alert("닉네임 중복!");
						  return true;
					  }
					  
				  },
				  error :function(error) {
					  console.log('에러');
					  console.log('에러종류: '+error);
					  alert("fail"); 
				  }
	    	    });
	      });
	   });
	

	    
	
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
	    
	    <!-- 유저가 새로운프로필을 등록할때 선택한 사진을 프로필 사이즈에 맞게 조정하며 미리 보여줌  -->
	    <!-- 이후 "변경완료" 버튼을 눌러야 DB 및 mimg폴더에 저장  -->
	    // 화면이 모두 로드되면 실행되는 함수
	    $(document).ready(function () {
	    	
	        // 유저가 파일을 선택했을때 실행되는 이벤트 핸들러 등록
	        $("#fileInput").on("change", function () {
	        	
	            // 파일 입력과 이미지 미리보기 엘리먼트를 가져옴
	            var fileInput = document.getElementById('fileInput');
	            var preview = document.getElementById('preview');
	
	            // 파일 입력이 존재하고 첫 번째 파일이 존재하면 실행
	            if (fileInput.files && fileInput.files[0]) {
	            	
	                // FileReader 객체를 생성
	                var reader = new FileReader();
	
	                // 파일 읽기가 완료되면 실행되는 이벤트 핸들러 등록
	                reader.onload = function (e) {
	                	
	                    // 미리보기 엘리먼트에 이미지 소스 설정
	                    // 120번라인 id="preview" 를 가진 img태그에 파일을 읽어온 결과를 src속성에 할당
	                    preview.src = e.target.result;
	
	                    // resizeImage 함수를 사용하여 이미지 크기를 조정하고 새로운 미리보기 설정
	                    resizeImage(fileInput.files[0], 350, 350, function (resizedImage) {
	                        preview.src = resizedImage;
	                    });
	                };
	
	                // 파일을 Data URL로 읽기 시작
	                // 파일의 내용을 읽어와서 Base64 인코딩된 데이터 URL로 제공
	                reader.readAsDataURL(fileInput.files[0]);
	            }
	        });
	
	        // 이미지 크기를 조정하고 새로운 미리보기를 설정하는 함수
	        function resizeImage(file, maxWidth, maxHeight, callback) {
	        	
	            // 이미지 객체와 FileReader 객체 생성
	            var img = new Image();
	            var reader = new FileReader();
	
	            // 파일 읽기가 완료되면 실행되는 이벤트 핸들러 등록
	            reader.onload = function (e) {
	            	
	                // 이미지 소스 설정
	                img.src = e.target.result;
	
	                // 이미지 로딩이 완료되면 실행되는 이벤트 핸들러 등록
	                img.onload = function () {
	                	
	                    // 캔버스 생성
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
	                    if (file.type.toLowerCase().includes('jpeg') || file.type.toLowerCase().includes('jpg')) {
	                        resizedCtx.drawImage(canvas, 0, 0, size, size, 0, 0, maxWidth, maxHeight);
	                        
	                        // 조정된 이미지를 Data URL로 변환
	                        var resizedImage = resizedCanvas.toDataURL('image/jpeg');
	                        
	                        // 콜백 함수 호출
	                        // 이 코드는 콜백 함수를 호출하면서 resizedImage를 인자로 전달 
	                        // 이 콜백 함수는 전달된 이미지를 받아 특정한 작업을 수행
	                        // 이미지를 비동기적으로 조정하고 나서 그 결과를 전달할 필요가 있을 때, 
	                        // 외부에서 지정한 콜백 함수를 호출하여 조정된 이미지를 전달
	                        // 이렇게 하면 이미지가 비동기적으로 처리되어도 다음 단계에서 적절한 작업 가능
	                        callback(resizedImage);
	                    } else if (file.type.toLowerCase().includes('png')) {
	                        resizedCtx.drawImage(canvas, 0, 0, size, size, 0, 0, maxWidth, maxHeight);
	                        
	                        // 조정된 이미지를 Data URL로 변환
	                        var resizedImage = resizedCanvas.toDataURL('image/png');
	                        
	                        // 콜백 함수 호출
	                        callback(resizedImage);
	                    } else if (file.type.toLowerCase().includes('webp')) {
	                        // Support WebP format
	                        resizedCtx.drawImage(canvas, 0, 0, size, size, 0, 0, maxWidth, maxHeight);
	                        var resizedImage = resizedCanvas.toDataURL('image/webp');
	                        callback(resizedImage);
	                    } else if (file.type.toLowerCase().includes('gif')) {
	                        // Support WebP format
	                        resizedCtx.drawImage(canvas, 0, 0, size, size, 0, 0, maxWidth, maxHeight);
	                        var resizedImage = resizedCanvas.toDataURL('image/gif');
	                        callback(resizedImage);
	                    } else {
	                     	
	                        // 다른 형식의 파일은 그대로 Data URL을 콜백 함수에 전달
	                        callback(e.target.result);
	                    }
	                };
	            };
	            // 파일을 Data URL로 읽기 시작
	            reader.readAsDataURL(file);
	        }
	    });
	</script>
</body>

</html>
