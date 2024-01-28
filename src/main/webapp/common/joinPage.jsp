<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<link rel="stylesheet" href="/chalKag/assets/css/main.css" />
</head>

<body>
	<stone:printNav member='${member}' />
	<footer id="footer">
		<section>

			<form id="yourFormId" method="post" action="/chalKag/join.do"
				onsubmit="return validateForm()">
				<div class="fields">
					<div class="field">
						<label for="email">email</label> <input type="email"
							name="memberID" id="email" required /> <input
							style="margin-top: 10px;" type="button" value="이메일 중복확인"
							onclick="checkID()" /> <span id="IDErrMsg"></span>
					</div>
					<div class="field">
						<label for="pw">password</label> <input type="password"
							name="memberPW" id="pw" minlength="8" maxlength="16" required />
					</div>
					<div class="field">
						<label for="pwCheck">password check</label> <input type="password"
							id="pwCheck" required />
						<p id="pwError" class="error"></p>
					</div>

					<div class="field">
						<label for="memberName">name</label> <input type="text"
							name="name" id="memberName" maxlength="50" required />
					</div>

					<div class="field">
						<label for="nickname">nick name</label> <input type="text"
							id="nickname" name="nickname" minlength="2" maxlength="50"
							required /> <input style="margin-top: 10px;" type="button"
							value="닉네임 중복확인" onclick="checkNickname()" /> <span
							id="nicknameErrMsg" class="error"></span>
					</div>

					<div class="field">
						<label for="img">프로필</label> <input type="file" name="profile"
							id="profile" />
						<!-- <input style="margin-top: 10px;" type="button" value="파일선택"> -->
					</div>

					<div class="field">
						<label for="ph">phone number</label> <input type="text" name="ph"
							id="ph" placeholder="-빼고 입력해주세요 입력해주세요." maxlength="11" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" required />
							<input style="margin-top: 20px;" type="button" value="인증번호 보내기" id="phCheckBtn" onclick="sendAuthenticationSMS()"/> 
							<input type="text" name="phCheck" id="phCheck" placeholder="인증번호를 입력해주세요" style="margin-top:20px;"/>
							 <input style="margin-top: 20px;" type="button" value="확인" id="smsCheck" onclick="smsCheck()" />  
							<span class="successPhCheck"></span>
							<input type="hidden" id="phDoubleCheck"/>
							
							<span id="phErrMsg" class="error"></span>
					</div>

					<div class="field">
						<label for="birth">birth</label> <select id="year" name="year"></select>
						<label for="year">year</label> <select id="month" name="month"></select>
						<label for="month">month</label> <select id="day" name="day"></select>
						<label for="day">day</label>
					</div>

					 
					<div class="field">
						<div class="terms">
							<div class="field">
								<input type="checkbox" id="agreeTerms" name="agreeTerms1">
								<label for="agreeTerms">이용약관에 동의합니다.</label>
								<p style="overflow:scroll; width:500px; height:150px;">
									**1. 약관의 목적**

									1.1 본 약관은 [서비스 제공자] (이하 "회사")가 제공하는 모든 서비스(이하 "서비스")의 이용에 관한 조건 및 절차를 정의합니다.

									**2. 약관의 효력과 변경**

									2.1 본 약관은 서비스를 이용하는 모든 이용자에게 적용되며, 회사는 필요한 경우 약관을 변경할 권리를 보유합니다.

									2.2 약관이 변경된 경우, 변경된 내용은 서비스 내 공지사항을 통해 공지됩니다. 변경된 약관은 공지 즉시 효력을 발생하며, 이용자는 변경된 약관에
									동의하는 것으로 간주됩니다.

									**3. 서비스 이용**

									3.1 서비스 이용을 위해서는 회사의 회원 가입 절차를 완료하여야 합니다. 이용자는 본인의 실명 및 정확한 정보를 제공하여야 하며, 제공한 정보의
									정확성을 유지하여야 합니다.

									3.2 이용자는 서비스를 이용함에 있어서 다음 각 호의 행위를 해서는 안됩니다.
									- 타인의 개인정보를 수집, 저장, 공개, 유포하는 행위
									- 서비스의 운영을 방해하거나 고의로 시스템에 부하를 주는 행위
									- 불법적이거나 부적절한 목적으로 서비스를 이용하는 행위

									**4. 서비스 제공 및 중단**

									4.1 회사는 서비스를 지속적으로 제공하기 위해 최선을 다하겠으나, 다음과 같은 상황에 대해서는 서비스 제공을 중단할 수 있습니다.
									- 시스템 점검 또는 업그레이드 시
									- 천재지변, 전쟁, 기간통신사업자의 서비스 중단 등 불가항력적인 사유로 인한 경우

									4.2 서비스 제공 중단의 경우, 회사는 사전에 공지할 수 있으며, 이에 대해 이용자에게 손해가 발생한 경우 회사는 책임을 지지 않습니다.

									**5. 개인정보 보호**

									5.1 이용자의 개인정보는 회사의 개인정보 처리 방침에 따라 보호됩니다.

									**6. 소유권 및 권리의 귀속**

									6.1 서비스에 대한 저작권 및 지적 재산권은 회사에게 소유권이 있습니다.

									6.2 이용자는 회사가 제공하는 서비스를 이용함으로써 얻은 정보를 회사의 사전 동의 없이 복제, 수정, 배포, 출판 등 상업적인 목적으로 사용할 수
									없습니다.

									**7. 책임 제한**

									7.1 회사는 서비스의 특성과 기술적인 한계를 고려하여 제공되는 서비스에 대한 일부 기능의 중단, 오류, 누락 등으로 인해 발생하는 손해에 대해 책임을
									지지 않습니다.

									7.2 이용자의 귀책사유로 인한 서비스 이용의 장애 또는 손해에 대해서 회사는 책임을 지지 않습니다.

									**8. 약관 해석 및 재판관할**


									8.1 본 약관에 관한 해석 및 분쟁의 해결은 대한민국 법률을 적용하며, 관할 법원은 서울중앙지방법원으로 합니다.

									**9. 기타**

									9.1 본 약관에 명시되지 않은 사항은 관련 법령 및 회사의 정책에 따릅니다.


								</p>
							</div>

							<div class="field">
								<input type="checkbox" id="personalInformation" name="agreeTerms2">
								<label for="personalInformation">개인정보 수집 및 이용 동의합니다.</label>
								<p style="overflow:scroll; width:500px; height:150px;">
									**1. 개인정보의 수집 및 이용 목적**

									회사는 다음과 같은 목적으로 개인정보를 수집 및 이용합니다.

									- 회원 가입 및 관리
									- 서비스 제공 및 계약 이행
									- 서비스 개선 및 신규 서비스 개발
									- 고객 문의 및 불만 처리
									- 마케팅 및 광고 활용

									**2. 수집하는 개인정보 항목**

									회사는 다음과 같은 개인정보를 수집할 수 있습니다.

									- 필수 항목: 이름, 이메일 주소, 연락처 등
									- 선택 항목: 주소, 성별, 생년월일 등

									**3. 개인정보의 보유 및 이용 기간**

									회사는 개인정보를 수집한 목적이 달성된 후에는 지체 없이 파기하거나 별도의 보관함에 안전하게 보관하며, 법령에서 정한 기간 동안 보유합니다.

									**4. 개인정보의 제3자 제공**

									회사는 원칙적으로 개인정보를 제3자에게 제공하지 않습니다. 다만, 아래의 경우에는 제3자 제공이 가능합니다.

									- 동의를 받은 경우
									- 법령에 따라 제공이 요구되는 경우

									**5. 개인정보의 안전성 확보 조치**

									회사는 개인정보의 안전성 확보를 위해 다음과 같은 조치를 취하고 있습니다.

									- 개인정보 암호화 전송
									- 보안 프로그램 설치 및 주기적인 갱신
									- 접근 제한 및 모니터링

									**6. 이용자의 권리**

									이용자는 언제든지 자신의 개인정보에 대한 접근, 정정, 삭제, 처리정지 등의 권리를 행사할 수 있습니다. 자세한 내용은 개인정보 처리 방침을 참고하시기
									바랍니다.

									**7. 개인정보 보호책임자**

									회사는 이용자의 개인정보를 보호하고 개인정보와 관련된 불만 처리 및 피해 구제 등을 위하여 아래와 같이 개인정보 보호책임자를 지정하고 있습니다.

									- 성명: [보호책임자의 성명]
									- 이메일: [보호책임자의 이메일]
									- 전화번호: [보호책임자의 전화번호]

									**8. 개정 및 공지**

									이 개인정보 처리방침은 정부의 법령, 정책 또는 회사의 내부 운영정책 등의 변경에 따라 변경될 수 있습니다. 변경 사항에 대해서는 홈페이지를 통해
									사전에 공지할 것입니다.

								</p>
							</div>

							<div class="field">
								<input type="checkbox" id="locationBasedService" name="agreeTerms3">
								<label for="locationBasedService">위치기반 서비스 및 이용약관 동의합니다.</label>
								<p style="overflow:scroll; width:500px; height:150px;">
									**1. 위치 정보의 수집 및 이용 목적**

									1.1 회사는 위치 기반 서비스를 제공하기 위해 위치 정보를 수집 및 이용합니다.
									1.2 위치 정보는 다음과 같은 목적으로 수집 및 이용됩니다.
									- 서비스 제공을 위한 위치 확인
									- 사용자 위치를 활용한 맞춤형 서비스 제공
									- 서비스 이용 통계 및 분석 자료 생성
									- 위치 정보를 활용한 광고 서비스 제공

									**2. 수집하는 위치 정보 항목**

									2.1 회사가 수집하는 위치 정보의 항목은 다음과 같습니다.
									- 현재 위치 또는 특정 지역의 위도, 경도 정보
									- 기기 정보: 디바이스 ID, 디바이스 모델, 운영 체제 버전 등

									**3. 위치 정보 제3자 제공 및 파기**

									3.1 회사는 원칙적으로 위치 정보를 제3자에게 제공하지 않습니다. 다만, 다음의 경우에는 제3자 제공이 가능합니다.
									- 이용자 동의를 받은 경우
									- 법령에 따라 제공이 요구되는 경우

									3.2 위치 정보는 이용자가 해당 서비스를 종료하거나 로그아웃한 시점부터 지체 없이 파기됩니다.

									**4. 위치 정보의 보유 및 이용 기간**

									4.1 위치 정보는 위치 기반 서비스 제공 목적을 달성한 후에는 지체 없이 파기됩니다. 단, 관련 법규에 따라 일정 기간 동안 보관할 필요가 있는
									경우에는 해당 기간 동안 보관됩니다.

									**5. 이용자의 권리**

									5.1 이용자는 언제든지 위치 정보의 수집 및 이용에 대한 동의 철회 및 위치 정보의 삭제를 요청할 수 있습니다.

									**6. 개인정보 보호책임자**

									6.1 회사는 위치 정보를 보호하고 개인정보와 관련된 불만 처리 및 피해 구제 등을 위하여 아래와 같이 개인정보 보호책임자를 지정하고 있습니다.

									- 성명: [보호책임자의 성명]
									- 이메일: [보호책임자의 이메일]
									- 전화번호: [보호책임자의 전화번호]

									**7. 개정 및 공지**

									7.1 이 위치 기반 서비스 이용약관은 정부의 법령, 정책 또는 회사의 내부 운영정책 등의 변경에 따라 변경될 수 있습니다. 변경 사항에 대해서는
									홈페이지를 통해 사전에 공지할 것입니다.

								</p>
							</div>
						</div>
						<div class="field" style="text-align:center;">
							<input type="submit" value="signIn" />
						</div>
					</div>
					 
			</form>
		</section>
	</footer>
	<stone:copyright />
	
	<script src="/chalKag/assets/js/jquery.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrollex.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrolly.min.js"></script>
	<script src="/chalKag/assets/js/browser.min.js"></script>
	<script src="/chalKag/assets/js/breakpoints.min.js"></script>
	<script src="/chalKag/assets/js/util.js"></script>
	<script src="/chalKag/assets/js/main.js"></script>
	<script src="/chalKag/assets/js/signIn.js"></script>
	<script src="/chalKag/assets/js/checkNickname.js"></script>
	<script src="/chalKag/assets/js/checkPh.js"></script>
	<script src="/chalKag/assets/js/checkID.js"></script>
	<script src="/chalKag/assets/js/smsCheck.js"></script>
	<script src="/chalKag/assets/js/sendAuthentication.js"></script>
</body>

</html>