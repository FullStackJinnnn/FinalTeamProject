<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>신고하기</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="../assets/css/main.css" />

<noscript>
	<link rel="stylesheet" href="../assets/css/noscript.css" />
</noscript>

</head>

<body class="is-preload">
	<!-- Wrapper -->

	<div id="wrapper">
		<!-- Header -->
		<header id="header">
			<a href="index.jsp" class="logo">CHAL KAG</a>
		</header>



		<!-- Nav -->
		<nav id="nav">
			<ul class="links">
				<li><a href="sellBoardSelectAllPage.do">UsedTrade</a></li>
				<li><a href="cameraReviewSelectAllPage.do">CameraReview</a></li>
				<li><a href="freeBoardSelectAllPage.do">FreeBoard</a></li>
				<li><a href="logout.do">LOGOUT</a></li>
				<li><a href="myPage.do">MYPAGE</a></li>
			</ul>
		</nav>
	</div>
	<!-- Main -->
	<div id="main">

		<!-- Featured Post -->
		<!-- Form -->
		<h2>REPORT</h2>

		<form method="post" action="#">
			<div class="row gtr-uniform">
				<div class="col-6 col-12-xsmall">
					<input type="text" name="demo-name" id="demo-name" value=""
						placeholder="url" /> <!-- 게시글 url을 자동으로 받아와야 해요 -->
				</div>
				<div class="col-6 col-12-xsmall">
					<input type="email" name="demo-email" id="demo-email" value=""
						placeholder="suspect_name" /> <!-- 신고대상을 자동으로 받아와야 해요 -->
				</div>
				<!-- 신고 내용 입력 -->
				<div class="col-12">
					<textarea name="demo-message" id="demo-message"
						placeholder="Enter your message" rows="6"></textarea>
				</div>
				<!-- 제출 버튼 -->
				<div class="col-12" style="text-align : right;">
					<div class="col-12"  style="text-align:right;">
						<a href="#" class="button">REPORT</a>
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