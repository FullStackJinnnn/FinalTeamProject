function checkPh(){
	var ph=$('#ph').val();
	
	$.ajax({
		type : "POST",
		url : "checkPh.do",
		data : {'ph':ph},
		dataType: 'text',
		success : function(data){
			if(data=='1'){
				$("#phErrMsg").text("사용가능한 번호 입니다.");
			}
			else{
				$("#phErrMsg").text("중복된 번호입니다. 다시 입력해주세요");
			}
		},
		error: function(error){
			console.log('에러'+error);
		}
	});
}