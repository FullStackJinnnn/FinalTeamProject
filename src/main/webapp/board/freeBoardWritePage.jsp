<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<!--
	Massively by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>자유게시판 글 작성</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/chalKag/assets/css/main.css" />
<noscript>
	<link rel="stylesheet" href="/chalKag/assets/css/noscript.css" />
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
   height: 50px; border-collapse : collapse;
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
		<form method="post" action="/chalKag/freeBoardWrite.do" enctype="multipart/form-data">
			<div class="row gtr-uniform">
				<!-- 제목 -->
				<div class="col-12 col-12-xsmall">
					<input type="text" name="title" id="title" placeholder="Title"
						required />
				</div>
				<!-- 이미지 -->
				<div class="actions" id="uploadDiv">
     			<label for="fileInput" class="btn-upload" >
     				Upload
					<input type="file" name="file" id="fileInput" style="display: none;"/>
				</label>
				<p id="originName" style="display : inline-block"></p>
    			</div> 
    			<!-- 이미지 미리보기 -->
    			<div style="width: 100%; display: flex; justify-content: center; align-items: center;">
					<div class="actions" id="previewDiv" style="display: none;">
						<img id="preview" style="max-width: 100%;"/>
					</div>
				</div>
				<!-- 내용 -->
				<div class="col-12">
					<textarea name="contents" id="contents" placeholder="Enter your message" rows="6"></textarea>
				</div>
				<!-- Break -->
				<div class="col-12">
					<ul class="actions">
						<li><input type="submit" value="Post Board" class="primary" /></li>
					</ul>
				</div>
			</div>
		</form>
	</div>


	<!-- Copyright -->
	<stone:copyright />


	<!-- Scripts -->
	<script src="/chalKag/assets/js/jquery.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrollex.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrolly.min.js"></script>
	<script src="/chalKag/assets/js/browser.min.js"></script>
	<script src="/chalKag/assets/js/breakpoints.min.js"></script>
	<script src="/chalKag/assets/js/util.js"></script>
	<script src="/chalKag/assets/js/main.js"></script>
	   <script
      src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/ckeditor.js"></script>
   <script
      src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>
   <script>
   
   
   var imgFile = $('#fileInput').val();            
   var fileForm = /(.*?)\.(jpg|jpeg|png|gif|bmp|webp)$/;   // 이미지 업로드 제약
   var maxSize = 5 * 1024 * 1024;                     // 파일 사이즈 제약
   const preview = document.querySelector('#preview');      // 이미지 업로드시 미리보기 기능을 담당
   var fileDOM = document.getElementById('fileInput');
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
               previewDiv.style.display = 'block';  // 이미지가 업로드 되면 div를 보여주기
                preview.src = e.target.result;
            };
            reader.readAsDataURL(target.files[0]);
      
      });
      
   }
   
     ClassicEditor
        .create( document.querySelector("#contents"), {
        toolbar: [ 'bold', 'italic', 'bulletedList', 'numberedList', 'blockQuote', 'insertTable' ],
       language: "ko"
     })
     .then(editor => {
         const form = document.querySelector("form");

         form.addEventListener("submit", function(event) {
           const contents = editor.getData().trim();
           const image = document.querySelector("#fileInput").files[0];
           const fileSize = image ? image.size : 0; // 파일이 있을 때만 파일 사이즈를 가져옴

           requiredInputs.forEach(function(input) {
               if (input.value.trim() === '') {
                   isEmpty = true;
               }
           });
           
           if (isEmpty) {
               event.preventDefault(); // 폼 제출 막기
               alert('필수 입력 필드를 모두 작성해주세요.');
           } else if (contents === null || contents === "") {
             alert("내용을 입력해주세요.");
             event.preventDefault();
           } else  if(image && !image.name.match(fileForm)) {
               alert("이미지 파일만 업로드 가능");
        	   event.preventDefault();
        	   return;
           } else if(fileSize > maxSize) {
               alert("파일 사이즈는 5MB까지 가능");
               event.preventDefault();
               return;
             }
         });
       });
      
      
    </script>
</body>
</html>