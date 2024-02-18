// 이 함수는 서버에서 전송한 인증코드와 사용자가 입력한 코드를 비교하고 결과를 처리합니다.

/*function smsCheck() {
    // 사용자가 입력한 인증번호 가져오기
    var serverGeneratedCode = "<%= request.getAttribute(\"result\") %>"; // JSP 파일에서 결과값을 받아와야 합니다.
    console.log("smsCheck 동작함");
    var userEnteredCode = $("#phCheck").val();
	//console.log("smsCheck "+serverGeneratedCode);
    // 서버에서 전송한 인증코드 가져오기

    // 사용자가 입력한 값과 서버에서 생성한 값 비교
    if (userEnteredCode === serverGeneratedCode) {
        $(".successPhCheck").text("인증 성공");
        $(".successPhCheck").css("color", "green");
        // 인증 성공 시 추가 동작을 수행할 수 있습니다.
    } else {
        $(".successPhCheck").text("인증 실패");
        $(".successPhCheck").css("color", "red");
        // 인증 실패 시 사용자에게 알림을 표시할 수 있습니다.
    }
};
*/

$(document).ready(function() {
    $("#smsCheck").on('click',  function() {
		console.log("smsCheck 동작함");
		console.log(serverGeneratedCode);
        if ($("#phCheck").val() ==serverGeneratedCode) {
            $(".successPhCheck").text("인증번호가 일치합니다.");
            $(".successPhCheck").css("color", "green");
        } else {
            $(".successPhCheck").text("인증번호가 일치하지 않습니다. 확인해주시기 바랍니다.");
            $(".successPhCheck").css("color", "red");
            $(this).attr("autofocus", true);
            
        }
    });
});

