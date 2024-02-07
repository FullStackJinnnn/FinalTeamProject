<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.board.*,java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone"%>

<!DOCTYPE html>
<html>
<head>
<!-- 문자 인코딩 및 메타 정보 설정 -->
<meta charset="UTF-8">
<title>중거거래 게시판</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />	
<!-- 외부 CSS 파일 링크 -->
<link rel="stylesheet" href="/chalKag/assets/css/main.css" />
</head>

<body class="is-preload">
<%--  <% System.out.println("[로그] 데이터 확인 : "+request.getParameter("reviewBoardDTO")); %> --%>
	<!-- 커스텀 태그를 사용하여 네비게이션 포함 -->
	<stone:printNav member='${member}' />

	<!-- 메인 콘텐츠 래퍼 -->
	<div id="main">
		<header class="major">
			<!-- 게시판 이름 -->
			<h2> used trade board</h2>
			<br>
			<p> Discover the world through lenses! 📷 Welcome to our Camera Review Board, <br>
				where shutterbugs unite to share insights on the latest cameras. <br>
				Dive into detailed reviews, expert opinions, and community discussions. </p>
		</header>

		<!-- 판매(중고거래) 게시판 데이터 선택을 위한 폼 -->
		<form id="sellBoardSelectAll" method="POST" action="/chalKag/sellBoardSelectAllPage.do">
			<!-- featured 포스트 섹션 -->
			<div class="post featured">

				<!-- 검색 폼 -->
				<div>
					<select name="serchField" style="width: 40%; display: inline-block; text-align: center;">
						<option value="title">글 제목</option>
						<option value="writer">글 작성자</option>
						<option value="contents"> 글 내용</option>
						<option value="title + contents">글 제목 + 글 내용</option>
					</select>
					<input type="text" name="search"
						style="margin-left: 10px; width: 40%; display: inline-block; text-align: center;"
						placeholder="검색어를 입력해 주세요.">
					<button type="submit" value="SEARCH" style="margin-left: 10px; width: 15%; text-align: center;">SEARCH</button>
				</div>
			</div>
		</form>

		<!-- '게시글 작성', '메인으로 돌아가기' 버튼 생성 -->
		<div class="col-6 col-12-small" style="margin-right: 25px; text-align: right">
			<button type="button" onclick="location.href='/chalKag/sellBoardWritePage.do'">Write</button>
			<button type="button" style="margin-left: 10px;" onclick="location.href='/chalKag/main.do'">MainPage</button>
		</div>

		<!-- 판매(중고거래) 게시판 데이터를 테이블로 표시하는 섹션 -->
		<div class="table-wrapper" style="margin-top: 20px; ">
			<table class="alt" style="margin-top: 30px;">
				<thead>
					<tr>
						<th width="10%">boardNum</th>
						<th width="*">title</th>
						<th width="15%">writer</th>
						<th width="15%">boardDate</th>
						<th width="10%">recommend</th>
						<th width="10%">views</th>
						<th width="10%">state</th>
					</tr>
				</thead>

				<!-- JSTL forEach를 사용하여 자유 게시판 데이터 반복 처리하여 출력-->
				<tbody>
					<!-- 출력할 게시글 정보(boardData)가 없을 경우 출력 문구 -->
					<c:if test="${fn:length(sellBoardDTO) <= 0}">
						<tr>
							<td colspan="7" align="center">등록된 글이 없습니다! 가장 먼저 새 글을 작성해 주세요!</td>
						</tr>
					</c:if>
					<!-- 출력할 게시글 정보(boardData)가 있을 경우 반복문을 사용하여 전체 목록 출력 -->
						<c:if test="${fn:length(sellBoardDTO) > 0}">
							<c:forEach var="boardData" items="${sellBoardDTO}">
									<tr>
										<td>${boardData.boardNum}</td>
										<!-- 게시글 상세 페이지로 연결되는 태그 -->
										<td><a href="/chalKag/sellBoardSelectOnePage.do?boardNum=${boardData.boardNum}">${boardData.title}</a></td>
										<td>${boardData.nickname}</td>
										<td>${boardData.boardDate}</td>
										<td>${boardData.recommendCNT}</td>
										<td>${boardData.viewCount}</td>
										<td>${boardData.state}</td>
									</tr>
							</c:forEach>
						</c:if>
				</tbody>
			</table>
		</div>

		<!-- 페이징을 포함한 푸터 섹션 -->
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
	
	<!-- 커스텀 태그를 사용하여 저작권 정보 포함 -->
	<stone:copyright />

	<!-- JavaScript 파일 링크 -->
	<script src="/chalKag/assets/js/jquery.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrollex.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrolly.min.js"></script>
	<script src="/chalKag/assets/js/browser.min.js"></script>
	<script src="/chalKag/assets/js/breakpoints.min.js"></script>
	<script src="/chalKag/assets/js/util.js"></script>
	<script src="/chalKag/assets/js/main.js"></script>

</body>

</html>