<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<!--
	Massively by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>판매게시판 글 작성</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="../assets/css/main.css" />
<noscript>
	<link rel="stylesheet" href="../assets/css/noscript.css" />
</noscript>
<style type="text/css">
i, em {
	font-style: italic;
}

#uploadDiv {
	display: flex;
	justify-content: center;
}

#originName {
	text-align: center;
	padding-left: 10rem;
	padding-top: 1rem;
	font: inherit;
}

.field {
	margin-bottom: 50px;
}

.field input[type="text"] {
	display: inline-block;
	width: 60%;
	font-weight: bold;
}

.btn-upload {
	width: 25rem;
	height: 50px;
	margin-top: 10px;
	background: #fff;
	border: 1px solid gray;
	border-radius: 10px;
	cursor: pointer;
	display: flex;
	align-items: center;
	justify-content: center;
}

.btn-upload:hover {
	background: black;
	color: #fff;
}

.ck-editor__editable {
	height: 400px;
}

.ck-editor__editable .ck-editor__nested-editable {
	width: 50px;
	height: 50px;
	border-collapse: collapse;
	border: 1px solid black;
	border-collapse: collapse;
}
</style>
</head>
<body class="is-preload">

	<!-- Wrapper -->
	<stone:printNav member='${member}' />


	<!-- Main -->
	<div id="main">

		<!-- Posts -->
		<form method="post" action="/chalKag/sellBoardWrite.do"
			enctype="multipart/form-data">
			<div class="row gtr-uniform">
				<!-- 제목 -->
				<div class="col-12 col-12-xsmall">
					<input type="text" name="title" id="title" placeholder="Title"
						required />
				</div>
				<!-- 상품명 -->
				<div class="col-6 col-12-xsmall">
					<input type="text" name="productName" id="productName"
						placeholder="Name" required />
				</div>
				<!-- 가격 -->
				<div class="col-6 col-12-xsmall">
					<input type="text" name="price" id="price" placeholder="Price"
						required />
				</div>
				<!-- 종류 -->
				<div class="col-12 col-12-xsmall">
					 <select name="productCategory" id="productCategory">
					    <option value="DSLR">DSLR</option>
					    <option value="미러리스">미러리스</option>
					    <option value="컴팩트">컴팩트</option>
					  </select>
				</div>
				<!-- 제조사 -->
				<!-- Break -->
				<div class="col-12 col-12-xsmall">
					 <select name="company" id="company">
					    <option value="캐논">캐논</option>
					    <option value="소니">소니</option>
					    <option value="니콘">니콘</option>
					  </select>
				</div>
				<!-- 이미지 -->
				<div class="actions" id="uploadDiv">
					<label for="fileInput" class="btn-upload"> Upload <input
						type="file" name="file" id="fileInput" style="display: none;" />
					</label>
					<p id="originName" style="display: inline-block"></p>
				</div>
				<!-- 이미지 미리보기 -->
				<div style="width: 100%; display: flex; justify-content: center; align-items: center;">
					<div class="actions" id="previewDiv" style="display: none;">
						<img id="preview" style="max-width: 100%;"/>
					</div>
				</div>
				<!-- 내용 -->
				<div class="col-12">
					<textarea name="contents" id="contents"
						placeholder="Enter your message" rows="6"></textarea>
				</div>
				<!-- Break -->
				<div class="col-12" id="dddddd">
					<ul class="actions" >
						<li><input type="submit" value="Post Board" class="primary" /></li>
					</ul>
				</div>
			</div>
			
		</form>

	</div>

	<!-- Copyright -->
	<stone:copyright />

	<!-- Scripts -->
	<script src="../assets/js/jquery.min.js"></script>
	<script src="../assets/js/jquery.scrollex.min.js"></script>
	<script src="../assets/js/jquery.scrolly.min.js"></script>
	<script src="../assets/js/browser.min.js"></script>
	<script src="../assets/js/breakpoints.min.js"></script>
	<script src="../assets/js/util.js"></script>
	<script src="../assets/js/main.js"></script>
	<script
		src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/ckeditor.js"></script>
	<script
		src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>
	<script>
   
   var imgFile = $('#fileInput').val();            
   var fileForm = /(.*?)\.(jpg|jpeg|png|gif|bmp|webp)$/;   // 이미지 업로드 제약
   var maxSize = 5 * 1024 * 1024;                     // 파일 사이즈 제약
   const preview = document.querySelector('#preview');      // 이미지 업로드시 미리보기 기능을 담당
   var previewDiv = document.getElementById('previewDiv');   // 이미지 업로드 미리보기 태그
   
   window.onload=function(){
      target=document.getElementById('fileInput'); // file 아이디 선언
      target.addEventListener('change', function(){ // change 함수
         if(target.value.length){ // 파일 첨부인 상태일경우 파일명 출력
            $('#originName').html(target.files[0].name);
         }else{ //버튼 클릭후 취소(파일 첨부 없을 경우)할때 파일명값 안보이게
            $('#originName').html("");
         }
      
         // 미리보기 기능 추가
            const reader = new FileReader();   // type이 file인 input 태그 또는 API 요청과 같은 인터페이스를 통해 File 또는 Blob 객체를 편리하게 처리할 수 있는 방법을 제공하는 객체
            reader.onload = function(e) {   
               previewDiv.style.display = 'block';  // 이미지가 업로드 되면 미리보기에 이미지 띄워준다.
                preview.src = e.target.result;
            };
            reader.readAsDataURL(target.files[0]);
      
      });
      
   }
   
   
   ClassicEditor	// 에디터 설정
      .create( document.querySelector("#contents"), { // textarea 태그에 에디터 설정
      toolbar: [ 'bold', 'italic', 'bulletedList', 'numberedList', 'blockQuote', 'insertTable' ],
      // 글 굵기, 기울기, 점 리스트, 숫자 리스트, 블록, 표 만들기
     language: "ko"			// 언어 설정 한국어
   })
   .then(editor => {		// 에디터의 값
       const form = document.querySelector("form");	// 전달할 form 태그 설정

       form.addEventListener("submit", function(event) {	// form 이벤트
         const contents = editor.getData().trim();	// 내용에 공백이 들어갈 경우 잘라준다.
         const image = document.querySelector("#fileInput").files[0];	// 이미지 파일
         const fileSize = image ? image.size : 0; // 파일이 있을 때만 파일 사이즈를 가져옴
         
         var requiredInputs = document.querySelectorAll('input[required]');	// 입력값 required 설정된 값들을 가져온다
         var isEmpty = false;							// 공백이 입력되었는지 확인 하기 위한 변수
         	
         var x = document.getElementById("price").value;	// 가격
         
         requiredInputs.forEach(function(input) {		// 입력값에 공백이 입력되면 지워줄 수 있게 설정
             if (input.value.trim() === '') {
                 isEmpty = true;
             }
         });
         
         if (isEmpty) {	// 공백 입력확인
             event.preventDefault(); // 폼 제출 막기
             alert('필수 입력 필드를 모두 작성해주세요.');
         } else if (contents === null || contents === "") { // 내용값 확인
           event.preventDefault();
           alert("내용을 입력해주세요.");
         } else if (isNaN(x) || x < 1 || x != parseInt(x, 10)) { // 가격 입력 확인
             alert("숫자만 입력해주세요.");
             event.preventDefault();
             return;
           } else if (!image) { // 이미지 파일이 없는 경우
               event.preventDefault();
               alert("이미지 파일을 첨부해주세요.");
             } else if(imgFile != "" && imgFile != null) {
           fileSize = document.getElementById("fileInput").files[0].size;
     
           if(!image.name.match(fileForm)) {      // 확장자 확인        	            
             alert("이미지 파일만 업로드 가능");
              event.preventDefault();// submit을 막는 유효성
             return;
            } else if(fileSize > maxSize) { 	// 이미지 사이즈 확인
             alert("파일 사이즈는 5MB까지 가능"); 
              event.preventDefault();
             return;
           }
         }
       });
   });
    </script>
</body>
</html>