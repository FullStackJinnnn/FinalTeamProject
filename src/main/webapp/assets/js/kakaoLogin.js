Kakao.init('6424bde253e983763ed4a337d2dbce14'); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단
//카카오 로그인
function kakaoLogin() {
	Kakao.Auth.login({
		success: function(authResponse) {
			Kakao.API.request({
				url: '/v2/user/me',         // 로그인하는 유저의 카카오 계정 정보를 가져올 수 있는 URL
				success: function(userResponse) {
					$.ajax({
						url: '/chalKag/kaKaoLogin.do',
						type: 'post',
						data: {
							memberID: userResponse.kakao_account.email  // 카카오 사용자 ID
						},
						success: function(loginResponse) {

							var responseObj = JSON.parse(loginResponse);
							console.log(responseObj);

							if (responseObj.isNewUser) {

								// 카카오 API에서 받아온 출생년도와 생일 정보
								var birthyear = userResponse.kakao_account.birthyear;  // 예: '1990'
								var birthday = userResponse.kakao_account.birthday;  // 예: '0525'

								// 월과 일을 분리
								var month = birthday.substring(0, 2);
								var day = birthday.substring(2);

								// 년도, 월, 일을 결합
								var fullBirthday = birthyear + '-' + month + '-' + day;

								$.ajax({
									url: '/chalKag/kakaoJoin.do',
									type: 'post',
									data: {
										memberID: userResponse.kakao_account.email, // 카카오 사용자 ID
										name: userResponse.properties.nickname, // 카카오 사용자 닉네임
										nickname: userResponse.properties.nickname, // 카카오 사용자 닉네임
										memberBirth: fullBirthday, // 카카오 사용자 생일
										ph: userResponse.kakao_account.phone_number // 카카오 사용자 전화번호
									},
									success: function(joinResponse) {
										alert('회원가입 성공');
										window.location.href = "/chalKag/loginPage.do";
									},
									error: function(xhr, status, error) {
										alert('회원가입 실패');
										history.go(-1);
									},
								});
							} else if (!responseObj.isNewUser) {
								alert('로그인 성공');
								window.location.href = "/chalKag/main.do";
							}
						},
						error: function(xhr, status, error) {
							alert('로그인 실패');
							history.go(-1);
						},
					});
				},
				fail: function(error) {
					console.log(error)
					history.go(-1);
				},
			})
		},
		fail: function(error) {
			console.log(error)
			history.go(-1);
		},
	})
}