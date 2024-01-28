<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>에러</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/chalKag/assets/css/main.css" />

<noscript>
	<link rel="stylesheet" href="/chalKag/assets/css/noscript.css" />
</noscript>
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
</head>
<body class="is-preload">

	<!-- JSTL -->
	<stone:printNav />

	<div id="footer">
		<section>

			<div>
				<h2 style="color: #000000; top: 50px; text-align: center;">죄송합니다,오류입니다.</h2>
				<h4 style="color: #000000; top: 20px; text-align: center;">이용에
					불편을 드린 점 진심으로 사과드리며,</h4>
				<h4 style="color: #000000; text-align: center;">편리하게 서비스를 이용하실
					수 있도록 최선의 노력을 다하겠습니다.</h4>

			</div>

			<div>

				<ul class="actions"
					style="gap: 30px; justify-content: center;">
					<li><a href="/chalKag/backPage.do" class="button primary large">이전
							페이지</a></li>
					<li><a href="/chalKag/main.do" class="button primary large">메인화면</a></li>
				</ul>


			</div>


		</section>
	</div>

	<!-- Copyright -->
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