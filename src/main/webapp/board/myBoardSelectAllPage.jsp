<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.board.*,java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>내가 작성한 게시글 목록</title>
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
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>날짜</th>
						<th>추천</th>
						<th>조회수</th>
						<th></th>

					</tr>
				</thead>
				<tbody>
					<c:if test="${fn:length(boardDatas) <= 0}">
						<tr>
							<td colspan="7">내가 작성한 게시글이 없습니다.</td>
						</tr>
					</c:if>
					<c:if test="${fn:length(boardDatas) > 0}">
						<c:forEach var="data" items="${boardDatas}">
							<tr>
								<td>${data.boardNum}</td>
								<td>${data.title}</td>
								<td>${data.nickname}</td>
								<td>${data.boardDate}</td>
								<td>${data.recommendCNT}</td>
								<td>${data.viewCount}</td>
							</tr>
						</c:forEach>
					</c:if>
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
	<script src="/chalKag/assets/js/jquery.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrollex.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrolly.min.js"></script>
	<script src="/chalKag/assets/js/browser.min.js"></script>
	<script src="/chalKag/assets/js/breakpoints.min.js"></script>
	<script src="/chalKag/assets/js/util.js"></script>
	<script src="/chalKag/assets/js/main.js"></script>
</body>

</html>
