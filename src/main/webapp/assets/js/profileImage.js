// 프로필 이미지 업로드

// dataContainer 요소 가져오기
const dataContainer = document.getElementById('dataContainer');
// dataContainer에서 data-ph 속성 값 가져오기
const ph = dataContainer.getAttribute('data-ph');

// 프로필 이미지 업로드 버튼 클릭 이벤트 처리
$("#btnImageSubmit").on("click", function(event) {
    event.preventDefault();

    // 파일 입력 요소 가져오기
    var fileInput = document.getElementById('fileInput');
    // changeProfile 폼 가져오기
    var form = $('#changeProfile')[0];
    // FormData 객체 생성하고 폼 데이터 설정
    var formData = new FormData(form);

    // 이미지 파일이 선택되지 않았을 경우 경고 메시지 출력
    if (fileInput.files.length === 0) {
		Swal.fire({
					title: '이미지 미선택',
					text: '이미지를 선택해주세요.',
					icon: 'warning',
					confirmButtonText: '확인'
				})
        return false;
    }

    // 프로필 이미지 업로드 AJAX 호출
    $.ajax({
        type: "POST",
        // 폼 데이터가 서버로 제출될 때 해당 데이터를 인코딩하는 방법을 명시하는 속성
        // 파일 전송 같은 이진 데이터를 포함하는 경우에 이 방식을 사용
        enctype: 'multipart/form-data',
        url: "profileUpload.do",
        data: formData,
        // 이 두 속성은 파일 데이터를 전송할 때 필요한 설정
        // contentType을 false로 설정하면 데이터 타입을 자동으로 설정하지 않고,
        // processData를 false로 설정하면 객체를 쿼리 문자열로 변환하지 않음
        // 쿼리 문자열(query string)은 URL의 일부로, 웹 서버에 데이터를 전달하는 데 사용
        // URL 끝에 물음표(?) 다음에 위치하며, 일반적으로 키-값 쌍의 형태를 가짐
        contentType: false,
        processData: false,
        success: function(data) {
            console.log(data);
            if (data == '1') {
					Swal.fire({
					title: '이미지 확정',
					text: '프로필 변경이 완료 되었습니다.',
					icon: 'success',
					confirmButtonText: '확인'
				})
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

// 닉네임 변경 버튼 클릭 이벤트 처리
$("#btnNicknameSubmit").on("click", function(event) {
    event.preventDefault();
    // myPageNickname 입력값 가져오기
    var myPageNickname = $('#myPageNickname').val();
    console.log(myPageNickname);

    // 닉네임이 입력되지 않았을 경우 경고 메시지 출력
    var checkNicknameEmpty = document.getElementById("myPageNickname").value.trim();
    if (checkNicknameEmpty == "") {
        alert("변경할 닉네임을 입력해주세요!!");
        return false;
    }

    // 닉네임 변경 AJAX 호출
    $.ajax({
        type: "POST",
        url: "changeNickname.do",
        data: { 'myPageNickname': myPageNickname },
        dataType: 'text',
        success: function(data) {
            console.log(data);
            if (data == '1') {
				Swal.fire({
					title: '닉네임 확정',
					text: '닉네임 변경이 완료 되었습니다.',
					icon: 'success',
					confirmButtonText: '확인'
				})
                return true;
            } else {
                	Swal.fire({
					title: '닉네임 중복',
					text: '중복된 닉네임 입니다.',
					icon: 'warning',
					confirmButtonText: '확인'
				})
                return true;
            }
        },
        error: function(error) {
            console.log('에러');
            console.log('에러종류: ' + error);
            alert("fail");
        }
    });
});

document.addEventListener('DOMContentLoaded', function() {
    var phoneNumberElement = document.getElementById('myPagePh');
    if (phoneNumberElement) {
        var maskedNumber = maskPhoneNumber(phoneNumberElement.value);
        phoneNumberElement.value = maskedNumber;
    }
});

function maskPhoneNumber(phoneNumber) {
    var parts = phoneNumber.split('-');
    if (parts.length === 3) {
        parts[1] = '****';
        return parts.join('-');
    } else {
        return phoneNumber;
    }
}

// 이미지 리사이징 여부 변수 초기화
var imageResized = false;

// 프로필 이미지 리사이징 함수
// 이미지는 주어진 최대 너비와 높이에 맞게 조정되고, 
// 원본과 동일한 비율을 유지하면서 중앙에 위치
function resizePreviewImage(img, maxWidth, maxHeight) {
    if (imageResized) { // 이미지가 이미 조정된 경우 함수를 종료
        return;
    }

    var canvas = document.createElement('canvas'); // 캔버스 요소를 만들어 변수에 저장
    var ctx = canvas.getContext('2d'); // 2D 렌더링 컨텍스트를 가져 옴

    var width = img.width; // 이미지의 너비를 가져옵니다.
    var height = img.height; // 이미지의 높이를 가져옵니다.

    var size = Math.min(width, height); // 너비와 높이 중 작은 값을 size 변수에 저장
    var xOffset = (width - size) / 2; // 이미지를 중앙에 놓을 때 필요한 x축 오프셋을 계산
    var yOffset = (height - size) / 2; // 이미지를 중앙에 놓을 때 필요한 y축 오프셋을 계산

    canvas.width = size; 
    canvas.height = size; 
    ctx.drawImage(img, xOffset, yOffset, size, size, 0, 0, size, size); // 이미지를 캔버스에 그림

    var resizedCanvas = document.createElement('canvas'); // 새로운 캔버스 요소를 만들어 변수에 저장
    var resizedCtx = resizedCanvas.getContext('2d'); // 새로운 캔버스의 2D 렌더링 컨텍스트를 가져 옴
    resizedCanvas.width = maxWidth; // 새로운 캔버스의 너비를 최대 너비로 설정
    resizedCanvas.height = maxHeight; // 새로운 캔버스의 높이를 최대 높이로 설정

    resizedCtx.drawImage(canvas, 0, 0, size, size, 0, 0, maxWidth, maxHeight); // 원본 캔버스에서 가져온 이미지를 새로운 캔버스에 그림
    img.src = resizedCanvas.toDataURL('image/jpeg'); // img의 src를 조정된 이미지로 설정

    imageResized = true; // 이미지가 조정되었음을 표시
}

// 페이지 로드 시 미리보기 이미지 리사이징 호출
window.onload = function() {
    var img = document.getElementById('preview');
    resizePreviewImage(img, 350, 350);
};

// 파일 입력 변경 이벤트 처리
$("#fileInput").on("change", function() {
    var fileInput = document.getElementById('fileInput');
    var preview = document.getElementById('preview');

    // 파일이 선택되면 미리보기 이미지 업데이트
    if (fileInput.files && fileInput.files[0]) {
        var reader = new FileReader();

        reader.onload = function(e) {
            preview.src = e.target.result;

            // 선택된 이미지를 리사이징하여 미리보기 업데이트
            resizeImage(fileInput.files[0], 350, 350, function(resizedImage) {
                preview.src = resizedImage;
            });
        };

        reader.readAsDataURL(fileInput.files[0]);
    }
});

// 미리보기 이미지 리사이징 함수
function resizeImage(file, maxWidth, maxHeight, callback) { 
    var img = new Image(); // 새로운 이미지 객체를 생성
    var reader = new FileReader(); // 새로운 파일 읽기 객체를 생성

    reader.onload = function(e) { // 파일 읽기 객체가 파일을 읽는 것을 완료하면 실행될 함수
        img.src = e.target.result; // 읽어들인 파일의 데이터를 이미지의 소스로 설정

        img.onload = function() { // 이미지가 로드되면 실행될 함수를 정의
            var canvas = document.createElement('canvas'); // 캔버스 요소를 생성
            var ctx = canvas.getContext('2d'); // 2D 렌더링 컨텍스트를 가져 옴

            var width = img.width; // 이미지의 너비를 가져옵니다.
            var height = img.height; // 이미지의 높이를 가져옵니다.

            var size = Math.min(width, height); // 너비와 높이 중 작은 값을 size 변수에 저장
            var xOffset = (width - size) / 2; // 이미지를 중앙에 놓을 때 필요한 x축 오프셋을 계산
            var yOffset = (height - size) / 2; // 이미지를 중앙에 놓을 때 필요한 y축 오프셋을 계산

            canvas.width = size; 
            canvas.height = size; 
            ctx.drawImage(img, xOffset, yOffset, size, size, 0, 0, size, size); 

            var resizedCanvas = document.createElement('canvas'); // 새로운 캔버스 요소를 생성
            var resizedCtx = resizedCanvas.getContext('2d'); // 새로운 캔버스의 2D 렌더링 컨텍스트를 가져 옴.
            resizedCanvas.width = maxWidth; // 새로운 캔버스의 너비를 최대 너비로 설정
            resizedCanvas.height = maxHeight; // 새로운 캔버스의 높이를 최대 높이로 설정

            // 이미지의 파일 형식에 따라 적절한 포맷으로 이미지를 그립니다.
            if (file.type.toLowerCase().includes('jpeg') || file.type.toLowerCase().includes('jpg')) {
                resizedCtx.drawImage(canvas, 0, 0, size, size, 0, 0, maxWidth, maxHeight); // 원본 캔버스에서 가져온 이미지를 새로운 캔버스에 그림
                var resizedImage = resizedCanvas.toDataURL('image/jpeg'); // 조정된 이미지를 JPEG 형식의 데이터 URL로 변환
                callback(resizedImage); // 콜백 함수를 호출하고 조정된 이미지를 인자로 전달
            } else if (file.type.toLowerCase().includes('png')) {
                resizedCtx.drawImage(canvas, 0, 0, size, size, 0, 0, maxWidth, maxHeight); 
                var resizedImage = resizedCanvas.toDataURL('image/png'); 
                callback(resizedImage);
            } else if (file.type.toLowerCase().includes('webp')) {
                resizedCtx.drawImage(canvas, 0, 0, size, size, 0, 0, maxWidth, maxHeight); 
                var resizedImage = resizedCanvas.toDataURL('image/webp');
                callback(resizedImage); 
            } else if (file.type.toLowerCase().includes('gif')) {
                resizedCtx.drawImage(canvas, 0, 0, size, size, 0, 0, maxWidth, maxHeight);
                var resizedImage = resizedCanvas.toDataURL('image/gif'); 
                callback(resizedImage); 
            } else {
                callback(e.target.result); // 지원하지 않는 파일 형식인 경우, 원본 이미지를 그대로 사용합니다.
            }
        };
    };
    reader.readAsDataURL(file); // 파일 읽기 객체가 파일을 읽기 시작
}
