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
<title>자유게시판 글 수정</title>
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
	<div id="wrapper" class="fade-in">

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

					<div class="col-12">
						<textarea name="demo-message" id="demo-message"
							placeholder="Enter Content" rows="6"></textarea>
					</div>
					<!-- Break -->
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