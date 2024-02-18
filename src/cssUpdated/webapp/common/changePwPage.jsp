<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/chalKag/assets/css/main.css" />
<style>
a {
	border-bottom: none;
}
</style>
</head>

<body>

	<stone:printNav member='${member}' />
	
	<footer id="footer">
	
		<section>
		
			<form id="changePwForm" method="post" action="/chalKag/changePw.do" onsubmit = "return validateForm()">
			
				<div class="fields">
				
					<div class="field">
						<label for="pw">password</label> 
						<input type="password" name="pw" id="pw" minlength="8" maxlength="16" required />
					</div>
					<div class="field">
						<label for="pwCheck">password check</label> 
						<input type="password" id="pwCheck" required />
						<p id="pwError" class="error"></p>
					</div>
					
					<div class="field" style="text-align:center;">
						<input type="submit" value="changePw" />
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
	<script src="/chalKag/assets/js/checkID.js"></script>
	<script src="/chalKag/assets/js/checkNickname.js"></script>
	<script src="/chalKag/assets/js/smsCheck.js"></script>
	<script src="/chalKag/assets/js/sendAuthentication.js"></script>

</body>

</html>