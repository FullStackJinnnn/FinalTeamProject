<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.board.*"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>내가 쓴 게시글 자세히보기</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/main.css" />

<noscript>
	<link rel="stylesheet" href="../assets/css/noscript.css" />
</noscript>

<style>
dt {
	float: left;
}

hr {
	border-bottom-color: grey;
}

h3 {
	float: left;
}

h6 {
	float: right;
}

table tbody tr {
	border-color: #eeeeee;
}

table tbody tr:nth-child(2n+1) {
	background-color: white;
}

table.alt tbody tr td {
	border-color: white;
}

.actions {
	float: right;
}
</style>

</head>

<body class="is-preload">
	<stone:printNav member='${member}' />
	<!-- Main -->
	<div id="main">
		<!-- Post -->
		<section class="post">
			<header class="major">
				<h3>${boardDTO.title}</h3>
				<br>
			</header>
			<!-- 날짜 및 작성자 -->
			<h4>
				Date :${boardDTO.boardDate}<br>Writer : ${boardDTO.nickname}<br>
				<c:if test="${boardDTO.memberID ne sessionScope.member}">
					<script>
						var reportPageURL = document.location.href;
						console.log("현재 페이지 URL: " + reportPageURL);
					</script>

					<button type="button"
						onclick="location.href='reportWritePage.do?boardNum=${boardDTO.boardNum}&reportPageURL=' + encodeURIComponent(reportPageURL)">신고하기
					</button>
				</c:if>


				<br>
			</h4>
			<hr />
			<!-- 내용 -->
			<blockquote>${boardDTO.contents}</blockquote>
			<h6>Views : ${boardDTO.viewCount}</h6>
			<br>
			<h6>Recommend : ${boardDTO.recommandCount}</h6>
			<br>
			<div class="col-6 col-12-small" style="text-align: right;">
				<br> <a href="#" class="button">UPDATE</a>
			</div>
			<div class="col-6 col-12-small" style="text-align: center;">
				<br> <a href="#" class="button">Recommend Board</a>
			</div>
			<hr />



			<!-- 댓글 -->
			<stone:review />
		</section>
	</div>

	<stone:copyright />




	<!-- Scripts -->
	<script src="../assets/js/jquery.min.js"></script>
	<script src="../assets/js/jquery.scrollex.min.js"></script>
	<script src="../assets/js/jquery.scrolly.min.js"></script>
	<script src="../assets/js/browser.min.js"></script>
	<script src="../assets/js/breakpoints.min.js"></script>
	<script src="../asse
	ts/js/util.js"></script>
	<script src="../assets/js/main.js"></script>
</body>

</html>