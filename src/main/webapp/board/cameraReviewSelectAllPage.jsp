<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="model.board.*,java.util.ArrayList,com.google.gson.Gson"%>
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
<style>
#filterRemoteContainer {
	position: fixed; /* sticky 대신 fixed로 변경 */
	top: 0;
	left: 0;
	width: 200px;
	background-color: #f1f1f1;
	padding: 10px;
	margin-top: 20px; /* 네비게이션 바 아래 여백 추가 */
}
</style>
</head>

<body class="is-preload">

	<!-- 커스텀 태그를 사용하여 네비게이션 포함 -->
	<stone:printNav member='${member}' />

	<!-- 메인 콘텐츠 래퍼 -->
	<div id="main">

		<!-- 카메라 리뷰 게시판 데이터 선택을 위한 폼 -->

		<!-- featured 포스트 섹션 -->
		<div class="post featured">
			<header class="major">
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
			</header>
		</div>

		<hr>

		<div id="filterRemoteContainer">
			<h4>상품 필터링</h4>
			<!-- 가격 -->
			<label for="minPrice">최소 가격:</label> <input type="range"
				id="minPrice" name="minPrice" min="0" max="10000">
			<output for="minPrice" id="minPriceOutput">0</output>
			<br> <label for="maxPrice">최대 가격:</label> <input type="range"
				id="maxPrice" name="maxPrice" min="0" max="10000">
			<output for="maxPrice" id="maxPriceOutput">0</output>
			<br>
			<!-- 제조사 -->
			<label>제조사:</label> <input type="checkbox" id="company1"
				name="company" value="캐논"> <label for="company1">캐논</label>
			<input type="checkbox" id="company2" name="company" value="소니">
			<label for="company2">소니</label> <input type="checkbox" id="company3"
				name="company" value="니콘"> <label for="company3">니콘</label>
			<br>
			<!-- 카메라 종류 -->
			<label>카메라 종류:</label> <input type="checkbox" id="productcategory1"
				name="productcategory" value="DSLR"> <label
				for="productcategory1">DSLR</label> <input type="checkbox"
				id="productcategory2" name="productcategory" value="미러리스"> <label
				for="productcategory2">미러리스</label> <input type="checkbox"
				id="productcategory3" name="productcategory" value="컴팩트"> <label
				for="productcategory3">컴팩트</label> <br>
			<!-- 게시글 상태 -->
			<label>게시글 상태:</label> <input type="checkbox" id="selling"
				name="state" value="판매중"> <label for="selling">판매중</label> <input
				type="checkbox" id="sold" name="state" value="판매완료"> <label
				for="sold">판매완료</label>
		</div>



		<script>
	$("#minPrice").val($("#minPrice").attr("min"));
	$("#maxPrice").val($("#maxPrice").attr("min"));	
    // 최소 가격 range input 값 표시
    const minPriceInput = document.getElementById('minPrice');
    const minPriceOutput = document.getElementById('minPriceOutput');

    minPriceInput.addEventListener('input', function() {
        if (parseInt(minPriceInput.value) > parseInt(maxPriceInput.value)) {
            minPriceInput.value = maxPriceInput.value;
        }
        minPriceOutput.textContent = minPriceInput.value;
    });

    // 최대 가격 range input 값 표시
    const maxPriceInput = document.getElementById('maxPrice');
    const maxPriceOutput = document.getElementById('maxPriceOutput');

    maxPriceInput.addEventListener('input', function() {
        if (parseInt(maxPriceInput.value) < parseInt(minPriceInput.value)) {
            maxPriceInput.value = minPriceInput.value;
        }
        maxPriceOutput.textContent = maxPriceInput.value;
    });
    
    
    $(document).ready(function () {
        var minPrice = null;
        var maxPrice = null;
        var selectedCompanies, selectedProductCategories, selectedstates;

        function updateVariables() {
            minPrice = $("#minPrice").val();
            maxPrice = $("#maxPrice").val();

            selectedCompanies = $('input[type=checkbox][name=company]:checked').map(function () {
                return this.value;
            }).get();

            selectedProductCategories = $('input[type=checkbox][name=productcategory]:checked').map(function () {
                return this.value;
            }).get();

            selectedstates = $('input[type=checkbox][name=state]:checked').map(function () {
                return this.value;
            }).get();
        }

        $("#minPrice, #maxPrice").on("mouseup", function () {
            updateVariables();
            performAjaxRequest();
        });

        $('input[type=checkbox][name=company], input[type=checkbox][name=productcategory], input[type=checkbox][name=state]').change(function () {
            updateVariables();
            performAjaxRequest();
        });

        function performAjaxRequest() {
            $.ajax({
                type: "POST",
                url: "filterSearchAction2.do",
                data: {
                    'minPrice': minPrice,
                    'maxPrice': maxPrice,
                    'company': selectedCompanies,
                    'productcategory': selectedProductCategories,
                    'state': selectedstates,
                    'category': '리뷰'
                },
                traditional: true,
                dataType: 'json',
                success: function (filterDatas) {
                    var tbody = document.querySelector('.alt tbody');
                    tbody.innerHTML = '';

                    if (filterDatas.length === 0) {
                        tbody.innerHTML = '<tr><td colspan="6" align="center">찾으시는 게시글이 없습니다.</td></tr>';
                    } else {
                        filterDatas.forEach(function (data) {
                            var row = document.createElement('tr');
                            row.innerHTML =
                                '<td>' + data.boardNum + '</td>' +
                                '<td><a href="/chalKag/cameraReviewSelectOnePage.do?boardNum=' + data.boardNum + '">' + data.title + '</a></td>' +
                                '<td>' + data.id + '</td>' +
                                '<td>' + data.boardDate + '</td>' +
                                '<td>' + data.recommendCNT + '</td>' +
                                '<td>' + data.viewCount + '</td>';
                            tbody.appendChild(row);
                        });
                    }
                },
                error: function (error) {
                    console.log('에러');
                    console.log('에러종류: ' + JSON.stringify(error));
                    alert("fail");
                }
            });
        }
    });
    
</script>












































		<!-- 검색 폼 섹션 -->


		<div>
			<select name="serchField" style="width: 40%; display: inline-block;">
				<option value="title">글 제목</option>
				<option value="content">글 내용</option>
				<option value="productName">작성자</option>
				<option value="company">글 제목 + 내용</option>
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
						<th width="10%">recommendCNT</th>
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
								<td>${data.boardNum}</td>
								<!-- 게시글 상새 페이지로 연결되는 태그 -->
								<td><a
									href="/chalKag/cameraReviewSelectOnePage.do?boardNum=${data.boardNum}">${data.title}</a></td>
								<td>${data.id}</td>
								<td>${data.boardDate}</td>
								<td>${data.recommendCNT}</td>
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