function smsCheck() {
    // 사용자가 입력한 휴대폰 번호와 인증번호
    var userPhoneNumber = document.getElementById("ph").value;
    var userEnteredCode = document.getElementById("phCheck").value;

    // 서버로 전송할 데이터
    var data = {
        ph: userPhoneNumber,
        phCheck: userEnteredCode
    };

    // AJAX를 이용하여 서버로 데이터 전송
    $.ajax({
        type: "POST",
        url: "sendAuthenticationSMS",  // 실제 서버 엔드포인트로 변경해야 합니다.
        data: data,
        success: function(response) {
            // 서버로부터의 응답 처리
            if (response === "success") {
                // 인증 성공
                document.getElementById("smsCheck").disabled = true; // 중복 클릭 방지
                document.getElementById("smsCheck").value = "인증 완료";
                document.getElementById("phErrMsg").innerText = ""; // 오류 메시지 초기화
                document.getElementById("phDoubleCheck").value = "true"; // 중복 체크 여부 설정
            } else {
                // 인증 실패
                document.getElementById("phErrMsg").innerText = "인증에 실패했습니다. 다시 시도해주세요.";
            }
        },
        error: function() {
            // 통신 오류 처리
            alert("서버와의 통신 중 오류가 발생했습니다.");
        }
    });
}

