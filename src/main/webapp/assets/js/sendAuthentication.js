function sendAuthenticationSMS() {
	alert("인증번호 발송이 완료되었습니다.");
    // 사용자가 입력한 전화번호 가져오기
    var ph = $("#ph").val();

    // AJAX를 사용하여 서버에 전화번호 전송
    $.ajax({
        url: "sendAuthenticationSMS",
        type: "POST",
        dataType:"text",
        data: { ph: ph },
        success: function(data) {
            // 서버 응답에 따른 처리
            if (data === "success") {
				console("data"+data)
                alert("인증 보내기 성공");
                // 성공적으로 SMS를 보낸 경우 추가 동작을 수행할 수 있습니다.
            } else {
                alert("인증 보내기 실패");
                // SMS 전송 실패 시 사용자에게 알림을 표시할 수 있습니다.
            }
        },
        error: function(error) {
			console.log(error);
            alert("ajax 요청오류");
            // AJAX 요청 중 오류가 발생한 경우에 대한 처리
        }
    });
}