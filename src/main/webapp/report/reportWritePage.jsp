<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone"%>

<!DOCTYPE html>
<html>
<style>
#reportContents{
	resize:none;
}
</style>
<head>
<!-- jQuery 추가 -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>신고하기</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css" />

<noscript>
	<link rel="stylesheet" href="../assets/css/noscript.css" />
</noscript>

</head>

<body class="is-preload">
	<stone:printNav member='${member}' />
	<!-- Main -->
	<div id="main">

		<!-- Featured Post -->
		<!-- Form -->
		<h2>REPORT</h2>

		<form method="post" action="reportWrite.do">
			<div class="row gtr-uniform">
				<div class="col-6 col-12-xsmall">
				    <!-- URL은....페이지 에서 바로넘김 -->
					<input type="text" name="reportPageURL" id="reportPageURL"
						value="${param.reportPageURL}" placeholder="url" />
					<!-- 게시글 url을 자동으로 받아와야 해요 -->
				</div>
				<div class="col-6 col-12-xsmall">
					<input type="email" name="suspectMemberID" id="suspectMemberID"
						value="${boardDTO.id}" placeholder="suspect_ID" />
					<!-- 신고대상을 자동으로 받아와야 해요 -->
				</div>
				<!-- 신고 내용 입력 -->
				<div class="col-12">
					<textarea name="reportContents" id="reportContents"
						placeholder="Enter your message" rows="6" required></textarea>
				</div>
				<!-- 제출 버튼 -->
				<div class="col-12" style="text-align: right;">
					<div class="col-12" style="text-align: right;">
					<input type="submit" value="REPORT" id="btnReportSubmit">  
					<%-- <a href="reportWrite.do?nickname=${boardDTO.boardNum}" class="button">REPORT</a> --%>
					</div>
				</div>
			</div>
		</form>

		<hr />



	</div>


	<!-- Scripts -->
	<script src="../assets/js/jquery.min.js"></script>
	<script src="../assets/js/jquery.scrollex.min.js"></script>
	<script src="../assets/js/jquery.scrolly.min.js"></script>
	<script src="../assets/js/browser.min.js"></script>
	<script src="../assets/js/breakpoints.min.js"></script>
	<script src="../assets/js/util.js"></script>
	<script src="../assets/js/main.js"></script>


</body>

</html>