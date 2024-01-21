<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone" %>
<!DOCTYPE HTML>
<!--
	Massively by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>판매 게시글 수정</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="../assets/css/main.css" />
<noscript>
	<link rel="stylesheet" href="../assets/css/noscript.css" />
</noscript>
</head>
<body class="is-preload">

	<stone:printNav member='${member}' />


		<!-- Main -->
		<div id="main">


			<!-- Posts -->
			<form method="post" action="#">
				<div class="row gtr-uniform">
					<!-- 제목 -->
					<div class="col-12 col-12-xsmall">
						<input type="text" name="demo-name" id="demo-name" value=""
							placeholder="Title" />
					</div>

					<!-- 상품명 -->
					<div class="col-6 col-12-xsmall">
						<input type="text" name="demo-name" id="demo-name" value=""
							placeholder="Name" />
					</div>
					<!-- 가격 -->
					<div class="col-6 col-12-xsmall">
						<input type="email" name="demo-email" id="demo-email" value=""
							placeholder="Price" />
					</div>
					<!-- 종류 -->
					<div class="col-12 col-12-xsmall">
						<input type="text" name="demo-name" id="demo-name" value=""
							placeholder="Type" />
					</div>
					<!-- 제조사 -->
					<!-- Break -->
					<!-- 종류 -->
					<div class="col-12 col-12-xsmall">
						<input type="text" name="demo-name" id="demo-name" value=""
							placeholder="Company" />
					</div>

					<div class="col-12">
						<textarea name="demo-message" id="demo-message"
							placeholder="Enter your message" rows="6"></textarea>
					</div>
					<!-- Break -->
					<div class="col-12"  style="text-align:right;">
						<a href="#" class="button">UPDATE BOARD</a>
					</div>
				</div>
			</form>
		</div>


		<!-- Copyright -->
		<stone:copyright />
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