function checkNickname(){
	var nickname=$('#nickname').val();
	
	$.ajax({
		type : "POST",
		url : "/chalKag/checkNickname.do",
		data : {'nickname':nickname},
		dataType: 'text',
		success : function(data){
			if(data=='1'){
				$("#nicknameErrMsg").text("사용가능한 닉네임 입니다.");
				$("#nicknameErrMsg").css("color","green");
			}
			else{
				$("#nicknameErrMsg").text("중복된 닉네임입니다. 다시 입력해주세요");
				$("#nicknameErrMsg").css("color","red");
			}
		},
		error: function(error){
			console.log('에러'+error);
		}
	});
}