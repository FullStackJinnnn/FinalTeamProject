<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>중고거래게시판</title>
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

		<!-- Featured Post -->
		<article class="post featured">
			<header class="major">
				<h2>
					<a href="#">Used Trade</a>
				</h2>
				<p>
					Unlock new perspectives with our Camera Marketplace! 📸 
					<br>Buy, sell, and trade pre-loved cameras and gear in our community hub.
					<br>Whether you're upgrading your equipment or seeking a new companion for your photography journey, 
					<br>this is the place to connect with fellow enthusiasts.
				</p>
			</header>
		</article>

		<div class="table-wrapper">
			<table class="alt">
				<thead>
					<tr>
						<th>num</th>
						<th>title</th>
						<th>writer</th>
						<th>date</th>
						<th>recommend</th>
						<th>views</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>6</td>
						<td><a href="#">화면 구성하는 거 힘들면 개추 ㅋㅋ</a></td>
						<td>김성민입니다</td>
						<td>date</td>
						<td>1</td>
						<td>1</td>
					</tr>
					<tr>
						<td>5</td>
						<td><a href="#">title 5</a></td>
						<td>writer 5</td>
						<td>date 5</td>
						<td>recommend 5</td>
						<td>views 5</td>
					</tr>
					<tr>
						<td>4</td>
						<td><a href="#">title 4</a></td>
						<td>writer 4</td>
						<td>date 4</td>
						<td>recommend 4</td>
						<td>views 4</td>
					</tr>
					<tr>
						<td>3</td>
						<td><a href="#">title 3</a></td>
						<td>writer 3</td>
						<td>date 3</td>
						<td>recommend 3</td>
						<td>views 3</td>
					</tr>
					<tr>
						<td>2</td>
						<td><a href="#">title 2</a></td>
						<td>writer 2</td>
						<td>date 2</td>
						<td>recommend 2</td>
						<td>views 2</td>
					</tr>
					<tr>
						<td>1</td>
						<td><a href="#">title 1</a></td>
						<td>writer 1</td>
						<td>date 1</td>
						<td>recommend 1</td>
						<td>views 1</td>
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