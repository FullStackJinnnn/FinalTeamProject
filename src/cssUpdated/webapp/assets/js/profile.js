var imgFile = $('#fileInput').val();
var fileForm = /(.*?)\.(jpg|jpeg|png|gif|bmp|webp)$/;	// 이미지 업로드 제약
var maxSize = 5 * 1024 * 1024;							// 파일 사이즈 제약
var fileSize;
var fileDOM = document.getElementById('fileInput');
var previewDiv = document.getElementById('previewDiv');	// 이미지 업로드 미리보기 태그

window.onload = function() {
	target = document.getElementById('fileInput'); // file 아이디 선언
	target.addEventListener('change', function() { // change 함수
		if (target.value.length) { // 파일 첨부인 상태일경우 파일명 출력
			$('#originName').html(target.files[0].name);
		} else { //버튼 클릭후 취소(파일 첨부 없을 경우)할때 파일명값 안보이게
			$('#originName').html("");
		}
	});
}


$(document).ready(function() {
	$("#joinBtn").click(function() {
		form.addEventListener('submit', function(event) {
			if (imgFile != "" && imgFile != null) {
				fileSize = document.getElementById("fileInput").files[0].size;	// submit 전 파일이 없을 경우
				if (!imgFile.match(fileForm)) {									// submit을 막는 유효성
					event.preventDefault();
					alert("이미지 파일만 업로드 가능");
					return;
				} else if (fileSize = maxSize) {
					event.preventDefault();
					alert("파일 사이즈는 5MB까지 가능");
					return;
				}
			}
		});
	});
});
