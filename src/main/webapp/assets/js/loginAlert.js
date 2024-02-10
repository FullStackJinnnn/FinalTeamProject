function loginAlert() {
	var email = $("#id").val();
	var pass = $("#pw").val();

	$.ajax({
		url: "/chalKag/loginCheck.do",
		type: "POST",
		/*dataType: "text",*/
		data: {
				"id" : email,
				"pw" : pass
			},
		success: function(data) {
			if (data == '1') {	
				Swal.fire({
					title: '로그인 성공',
					text: '메인 페이지로 이동합니다.',
					icon: 'success',
					confirmButtonText: '확인'
				}).then((result) => {
					if (result.isConfirmed) {
						location.href = "/chalKag/main.do";
					}
				});
			}
			else {
				Swal.fire({
					title:'로그인 실패',						// alert 제목
					text:'아이디 혹은 비밀번호를 확인해주세요',		// alert 내용
					icon:'error',							// alert 타입	
				});
			}
		},
		error: function(error) {
			console.log('에러' + error);
		}
	});
}