<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.board.*,java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone"%>

<!DOCTYPE html>
<html>
<head>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<!-- 문자 인코딩 및 메타 정보 설정 -->
<meta charset="UTF-8">
<title>카메라 리뷰 게시판</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />

<!-- 외부 CSS 파일 링크 -->
<link rel="stylesheet" href="/chalKag/assets/css/main.css" />
</head>

<body class="is-preload">


<script>
    // Java에서 전송한 데이터를 JavaScript에 할당
    var boardDatas = ${boardDatas};

    function onClickFilter() {
        var lowerPrice = parseInt(document.getElementById('minAmount').value, 10);
        var upperPrice = parseInt(document.getElementById('maxAmount').value, 10);

        // 가격 범위에 따라 boardDatas 필터링
        var filteredData = boardDatas.filter(function (data) {
            var dataPrice = parseInt(data.price, 10); // data.price를 parseInt로 변환
            return !isNaN(dataPrice) && lowerPrice <= dataPrice && dataPrice <= upperPrice;
        });

        // 기존 테이블 본문 내용 지우기
        var tableBody = document.querySelector('.table-wrapper table tbody');
        tableBody.innerHTML = '';

        // 필터링된 데이터로 테이블 채우기
        filteredData.forEach(function (data) {
            var row = tableBody.insertRow();
            row.innerHTML = `
                <td>${data.boardNum}</td>
                <td><a href="/chalKag/cameraReviewSelectOnePage.do?boardNum=${data.boardNum}">${data.title}</a></td>
                <td>${data.id}</td>
                <td>${data.boardDate}</td>
                <td>${data.recommendNum}</td>
                <td>${data.viewCount}</td>
            `;
        });
    }

    document.addEventListener('DOMContentLoaded', function () {
        // 버튼 엘리먼트 찾기
        var filterButton = document.querySelector('button[name="확정"]');
        
        // 버튼에 클릭 이벤트 리스너 추가
        filterButton.addEventListener('click', onClickFilter);
    });
</script>

	<!-- 커스텀 태그를 사용하여 네비게이션 포함 -->
	<stone:printNav member='${member}' />

	<!-- 메인 콘텐츠 래퍼 -->
	<div id="main">

		<!-- 카메라 리뷰 게시판 데이터 선택을 위한 폼 -->


		<!-- featured 포스트 섹션 -->
		<div class="post featured"> <header class="major">
			<h2>
				camera review board
				<!-- <a href="#">camera review board</a> -->
			</h2>
			<br>

			<p>
				Discover the world through lenses! 📷 Welcome to our Camera Review
				Board, <br>where shutterbugs unite to share insights on the
				latest cameras. <br>Dive into detailed reviews, expert
				opinions, and community discussions.
			</p>
		</header> </div>

		<hr>
		<!-- 검색 폼 섹션 -->
		<div class="pricefilter">
			<label for="minAmount">최소금액:</label> <input type="text"
				id="minAmount" name="minAmount" placeholder="최소금액을 입력하세요" required>
			<br> <label for="maxAmount">최대금액:</label> <input type="text"
				id="maxAmount" name="maxAmount" placeholder="최대금액을 입력하세요" required>
			<button value="확정" name="확정" onClick="onClickFilter()"></button>
		</div>
		
		
		<div>

			<select name="serchField" style="width: 40%; display: inline-block;">
				<option value="title">제목</option>
				<option value="content">작성자</option>
				<option value="productName">상품명</option>
				<option value="company">제조사</option>
			</select> <input type="text" name="search"
				style="margin-left: 10px; width: 40%; display: inline-block;"
				placeholder="검색어를 입력해 주세요."> <input type="button"
				value="SERCH" style="margin-left: 10px; width: 15%;"
				onclick="Update.do">
			<!-- <input type="submit" style="margin-left: 10px; width: 15%;" value="검색하기"> -->
		</div>

		<!-- 리뷰 게시판 데이터를 테이블로 표시하는 섹션 -->
		<div class="table-wrapper" style="margin-top: 20px;">
			<table class="alt" style="margin-top: 30px;">
				<thead>
					<tr>
						<th width="10%">boardNum</th>
						<th width="*">title</th>
						<th width="15%">writer</th>
						<th width="15%">boardDate</th>
						<th width="10%">recommend</th>
						<th width="10%">views</th>
					</tr>
				</thead>

				<!-- JSTL forEach를 사용하여 카메라 리뷰 데이터 반복 처리하여 출력-->
				<tbody>
					<!-- 출력할 게시글 정보(boardDatas)가 없을 경우 출력 문구 -->
					<c:if test="${fn:length(boardDatas) <= 0}">
						<tr>
							<td colspan="10" align="center">등록된 글이 없습니다!</td>

						</tr>
					</c:if>
					<!-- 출력할 게시글 정보(boardDatas)가 없을 경우 있을 경우 반복문을 사용하여 전체 목록 출력 -->
					<c:if test="${fn:length(boardDatas) > 0}">
						<c:forEach var="data" items="${boardDatas}">
							<tr>
								<td name="boardNum">${data.boardNum}</td>
								<!-- 게시글 상새 페이지로 연결되는 태그 -->
								<td><a
									href="/chalKag/cameraReviewSelectOnePage.do?boardNum=${data.boardNum}">${data.title}</a></td>
								<td>${data.id}</td>
								<td>${data.boardDate}</td>
								<td>${data.recommendNum}</td>
								<td>${data.viewCount}</td>
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