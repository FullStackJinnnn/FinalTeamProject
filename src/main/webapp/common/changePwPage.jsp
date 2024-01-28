<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/chalKag/assets/css/main.css" />

<noscript>
	<link rel="stylesheet" href="/chalKag/assets/css/noscript.css" />
</noscript>
<style>
<style>
input::-webkit-input-placeholder {
	font-family: "Source Sans Pro", Helvetica, sans-serif;
}

a {
	font-family: "Source Sans Pro", Helvetica, sans-serif;
}

.fields {
	text-align: center;
}

.field {
	margin-bottom: 10px;
}
</style>
</style>
</head>

<body class="is-preload">

	<stone:printNav member='${member}'/>

	<div id="footer">
		<section>
				
				
	<script>
    // 비밀번호 일치 확인 VIEW에서 진행
    window.onload = function() {
        document.changePw.onsubmit = function(event) {
            if (document.changePw.newPw.value !== document.changePw.newPwCheck.value) {
                alert("비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
                return false; // 폼 제출의 기본 동작 중지
            }
        }
    };
</script>

			<form id="changePw" name="changePw" method="post" action="/chalKag/changePw.do"
				onsubmit="return validateForm()">
				<div class="fields">
					<h1 style="font-size: 20px; margin-left : 20px;">비밀번호 변경</h1>
					<!-- 변경할 비밀번호 -->
					<div class="field">
						<label for="newPw"></label> <input type="text" name="newPw"
							id="newPw" placeholder="변경할 비밀번호를 입력해주세요" required />
					</div>

					<!-- 변경할 비밀번호 확인 -->
					<div class="field">
						<label for="newPwCheck"></label> <input type="text" name="newPwCheck"
							id="newPwCheck" placeholder="비밀번호를 다시 한 번 확인해주세요" required />
					</div>

					<!--  확인버튼 -->
					<div class="field">

						<div class="actions" style="text-align: center; margin-top: 10px;">
							<input type="submit" value="비밀번호 변경" style="width: 250px;" />
						</div>
					</div>
				</div>
			</form>
		</section>
	</div>
	
	<stone:copyright />
	
	<script src="/chalKag/assets/js/jquery.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrollex.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrolly.min.js"></script>
	<script src="/chalKag/assets/js/browser.min.js"></script>
	<script src="/chalKag/assets/js/breakpoints.min.js"></script>
	<script src="/chalKag/assets/js/util.js"></script>
	<script src="/chalKag/assets/js/main.js"></script>
	
</body>

</html>