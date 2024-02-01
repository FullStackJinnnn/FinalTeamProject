<%@ tag language="java" pageEncoding="UTF-8"%>

<div class="field">
	<!-- 번호를 입력 란.안승준 -->
	<label for="ph">phone number</label> <input type="text" name="ph"
		id="ph" placeholder="-빼고 입력해주세요 입력해주세요." maxlength="11"
		oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
		required /> 
		
	<!-- 인증번호 보내기 버튼.안승준 -->	
	<input style="margin-top: 20px;" type="button" value="인증번호 보내기" id="phCheckBtn" onclick="sendAuthenticationSMS()" />
		
	<!-- 인증번호 입력 란.안승준 -->
	<input type="text" name="phCheck" id="phCheck"
		placeholder="인증번호를 입력해주세요" style="margin-top: 20px;" /> 
		
	<!-- 인증번호 확인 버튼.안승준 -->	
	<input style="margin-top: 20px;" type="button" value="확인" id="smsCheck" /> 
		
	<!-- 결과 확인 비동기.안승준 -->
	<input type="hidden" id="${result}" class="hiddenCheck"> 
		
	<span class="successPhCheck"></span>
	<!-- <input type="hidden" id="phDoubleCheck"/> -->

	<span id="phErrMsg" class="error"></span>
</div>