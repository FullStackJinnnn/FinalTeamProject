<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>자유게시판게시글자세히보기</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="../assets/css/main.css" />

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

		table tbody tr:nth-child(2n + 1) {
			background-color: white;
		}
		
table.alt tbody tr td {
    border-color: white;
}

ul {
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
				<h3>화면 구성하는 거 힘들면 개추 ㅋㅋ</h3>
				<br>
			</header>
			<!-- 날짜 및 작성자 -->
			<h4>
				Date : 2024-01-24<br>Writer : 김성민입니다
			</h4>


			<hr />


			<!-- 내용 -->
			<blockquote>일단 나부터 ㅋㅋ</blockquote>
			<h6>Views : 53</h6>
			<br>
			<h6>Recommend : 7</h6>
			<br>
			<div class="col-6 col-12-small" style="text-align:right;">
				<br>
				<a href="#" class="button">UPDATE</a>
			</div>
			<div class="col-6 col-12-small" style="text-align:center;">
				<br>
				<a href="#" class="button">Recommend Board</a>
			</div>
			<hr />



			<!-- 댓글 -->
			<section>
				<form method="post" action="#">
					<div class="fields">
						<div class="field">
							<label for="message">Write Contents</label>
							<textarea name="message" id="message" rows="3"></textarea>
						</div>
					</div>
					<ul class="actions">
						<li><input type="submit" value="leaving a comment" /></li>
					</ul>
					<br>
				</form>
			</section>
			<hr />
			<div class="table-wrapper">
				<table class="alt">
					<thead>
						<tr>
							<th>Name</th>
							<th>Contents</th>
							<th>Date</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>승현짱</td>
							<td>ㄹㅇ 진짜 힘들다 힘들어</td>
							<td>2024-01-15</td>
						</tr>
						
					</tbody>

				</table>
			</div>
		</section>
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