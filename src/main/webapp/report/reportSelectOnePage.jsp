<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>신고관리페이지</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/chalKag/assets/css/main.css" />

<noscript>
	<link rel="stylesheet" href="/chalKag/assets/css/noscript.css" />
</noscript>

</head>

<body class="is-preload">
	<stone:printNav member='${member}' />
	<!-- Main -->
	<div id="main">

		<!-- Featured Post -->
	

		<div class="table-wrapper">
			<table class="alt">
				<thead>
					<tr>
						<th>num</th>
						<th>url</th>
						<th>contents</th>
						<th>suspect</th>
						<th>reporter</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>6</td>
						<td><a href="#">www.naver.com</a></td>
						<td>너무 못생겼어요</td>
						<td>야채안먹는편</td>
						<td>김성민입니다</td>
					</tr>
				</tbody>
			</table>
		</div>

		<!-- Footer -->
		<footer>
			<div class="pagination">
				<!--<a href="#" class="previous">Prev</a>-->
				<a href="#" class="page active">1</a> <a href="#" class="page">2</a>
				<a href="#" class="page">3</a> <span class="extra">&hellip;</span> <a
					href="#" class="page">8</a> <a href="#" class="page">9</a> <a
					href="#" class="page">10</a> <a href="#" class="next">Next</a>
			</div>
		</footer>

	</div>


	<!-- Scripts -->
	<script src="/chalKag/assets/js/jquery.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrollex.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrolly.min.js"></script>
	<script src="/chalKag/assets/js/browser.min.js"></script>
	<script src="/chalKag/assets/js/breakpoints.min.js"></script>
	<script src="/chalKag/assets/js/util.js"></script>
	<script src="/chalKag/assets/js/main.js"></script>


</body>

</html>