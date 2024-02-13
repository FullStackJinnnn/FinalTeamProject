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
<style>
a {
	border-bottom: none;
}
th.sortable {
	cursor: pointer;
}

th.sortable.highlight {
	color: blue; /* 원하는 글자 색상으로 변경해주세요 */
	background-color: #F0F0F0; /* 원하는 배경색으로 변경해주세요 */
}

.page.active {
	background: #7abbf0;
	color: #fff;
}
</style>
<noscript>
	<link rel="stylesheet" href="/chalKag/assets/css/noscript.css" />
</noscript>
</head>

<body class="is-preload">
	<stone:printNav member='${member}' />

	<!-- Main -->
	<div id="main">
		<c:if test="${sessionScope.member == id}">
			<h2>내가 작성한 게시글</h2>
		</c:if>
		<c:if test="${sessionScope.member != id}">
			<h2>${nickname}의 작성 게시글</h2>
		</c:if>
		<!-- Featured Post -->
		<!-- 리뷰 게시판 데이터를 테이블로 표시하는 섹션 -->
		<div class="table-wrapper" style="margin-top: 20px;">
			<table class="alt" style="margin-top: 30px;">
				<thead>
					<tr>
						<th width="10%" class="sortable" data-column="boardNum">boardNum</th>
						<th width="*" class="sortable" data-column="title">title</th>
						<th width="15%" class="sortable" data-column="writer">writer</th>
						<th width="15%" class="sortable" data-column="boardDate">boardDate</th>
						<th width="10%" class="sortable" data-column="recommendCNT">recommendCNT</th>
						<th width="10%" class="sortable" data-column="views">views</th>
					</tr>
				</thead>

				<!-- JSTL forEach를 사용하여 카메라 리뷰 데이터 반복 처리하여 출력-->
				<tbody>
					<!-- pagination 으로 동적으로 채워질 테이블 공간 -->
				</tbody>
			</table>
		</div>

		<div id="dataContainer" data-jsonBoardDatas='${jsonBoardDatas}'
			data-id='${id}' ></div>


		<!-- 페이징을 포함한 푸터 섹션 -->
		<footer>
			<div id="paginationContainer" class="pagination">
				<!-- 페이징 버튼이 동적으로 생성될 부분 -->
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
	<script src="/chalKag/assets/js/pagination.js"></script>
	<script src="/chalKag/assets/js/filterSearch.js"></script>
</body>

</html>
