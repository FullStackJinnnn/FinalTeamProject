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

th.sortable {
	cursor: pointer;
}

.page.active {
    background: #7abbf0;
    color: #fff;
}

</style>
</head>

<body class="is-preload">
	<%--  <% System.out.println("[로그] 데이터 확인 : "+request.getParameter("reviewBoardDTO")); %> --%>
	<!-- 커스텀 태그를 사용하여 네비게이션 포함 -->
	<stone:printNav member='${member}' />

	<!-- 메인 콘텐츠 래퍼 -->
	<div id="main">
		<header class="major">
			<!-- 게시판 이름 -->
			<h2>camera review board</h2>
			<br>
			<p>
				Discover the world through lenses! 📷 Welcome to our Camera Review
				Board, <br> where shutterbugs unite to share insights on the
				latest cameras. <br> Dive into detailed reviews, expert
				opinions, and community discussions.
			</p>
		</header>

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


		<!-- 카메라 리뷰 게시판 데이터 선택을 위한 폼 -->
		<form id="cameraReviewSelectAll" method="POST"
			action="/chalKag/cameraReviewSelectAllPage.do">
			<!-- featured 포스트 섹션 -->
			<div class="post featured">

				<!-- 검색 폼 -->
				<div>
					<select id="searchField" name="searchField"
						style="width: 40%; display: inline-block;">
						<option value="title">글 제목</option>
						<option value="contents">글 내용</option>
						<option value="writer">작성자</option>
						<option value="titleAndContents">글 제목 + 내용</option>
					</select> <input type="text" id="searchInput" name="search"
						style="margin-left: 10px; width: 40%; display: inline-block;"
						placeholder="검색어를 입력해 주세요."> <input type="button"
						value="검색" id="searchButton"
						style="margin-left: 10px; width: 15%;">
				</div>
			</div>
		</form>

		<!-- '게시글 작성', '메인으로 돌아가기' 버튼 생성 -->
		<div class="col-6 col-12-small"
			style="margin-right: 25px; text-align: right">
			<button type="button"
				onclick="location.href='/chalKag/cameraReviewWritePage.do'">Write</button>
			<button type="button" style="margin-left: 10px;"
				onclick="location.href='/chalKag/main.do'">MainPage</button>
		</div>

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
					<!-- 출력할 게시글 정보(boardData)가 없을 경우 출력 문구 -->
					<c:if test="${fn:length(reviewBoardDTO) <= 0}">
						<tr>
							<td colspan="6" align="center">등록된 글이 없습니다! 가장 먼저 새 글을 작성해
								주세요!</td>
						</tr>
					</c:if>
					<!-- 출력할 게시글 정보(boardData)가 있을 경우 반복문을 사용하여 전체 목록 출력 -->
					<c:if test="${fn:length(reviewBoardDTO) > 0}">
						<c:forEach var="boardData" items="${reviewBoardDTO}">
							<tr>
								<td>${boardData.boardNum}</td>
								<!-- 게시글 상세 페이지로 연결되는 태그 -->
								<td><a
									href="/chalKag/cameraReviewSelectOnePage.do?boardNum=${boardData.boardNum}">${boardData.title}</a></td>
								<td>${boardData.nickname}</td>
								<td>${boardData.boardDate}</td>
								<td>${boardData.recommendCNT}</td>
								<td>${boardData.viewCount}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>


		<script>
// 최소 가격과 최대 가격 초기화 및 이벤트 핸들러 등록
$("#minPrice").val($("#minPrice").attr("min"));
$("#maxPrice").val($("#maxPrice").attr("min"));

// 최소 가격 range input 값 표시를 담당하는 변수 및 이벤트 핸들러 등록
const minPriceInput = document.getElementById('minPrice');
const minPriceOutput = document.getElementById('minPriceOutput');
minPriceInput.addEventListener('input', function() {
    // 최소 가격이 최대 가격을 초과하면 최대 가격으로 설정
    if (parseInt(minPriceInput.value) > parseInt(maxPriceInput.value)) {
        minPriceInput.value = maxPriceInput.value;
    }
    minPriceOutput.textContent = minPriceInput.value;
});

// 최대 가격 range input 값 표시를 담당하는 변수 및 이벤트 핸들러 등록
const maxPriceInput = document.getElementById('maxPrice');
const maxPriceOutput = document.getElementById('maxPriceOutput');
maxPriceInput.addEventListener('input', function() {
    // 최대 가격이 최소 가격보다 작으면 최소 가격으로 설정
    if (parseInt(maxPriceInput.value) < parseInt(minPriceInput.value)) {
        maxPriceInput.value = minPriceInput.value;
    }
    maxPriceOutput.textContent = maxPriceInput.value;
});

$(document).ready(function () {
	var jsonFilterDatas;
	var isFiltered = false;
	 
	
    // 필터 및 정렬에 사용되는 변수들 초기화
    var minPrice = null;
    var maxPrice = null;
    var selectedCompanies, selectedProductCategories, selectedstates;
    var searchField;
    var searchInput;
    var column;
    // 초기 상태 정렬 방향
    var defaultOrderColumnDirection = {
        boardNum: 'ASC',
        title: 'ASC',
        writer: 'ASC',
        boardDate: 'ASC',
        recommendCNT: 'DESC',
        views: 'DESC'
    };
    // 현재 정렬 방향
    var orderColumnDirection = $.extend(true, {}, defaultOrderColumnDirection);
    // 선택된 헤더1개와 방향을 저장하는 객체
    var selectedOrderDirection;
    
    // 필터링 및 정렬에 사용되는 변수들 업데이트
    function updateVariables() {
        minPrice = $("#minPrice").val();
        maxPrice = $("#maxPrice").val();
        searchField = $("#searchField").val(); 
        searchInput = $("#searchInput").val(); 

        // 선택된 체크박스들의 값을 배열로 저장
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

    // 검색 버튼 클릭 이벤트
    $("#searchButton").on("click", function () {
        updateVariables();
        performAjaxRequest();
    });

    // 가격 입력값 변경 이벤트
    $("#minPrice, #maxPrice").on("mouseup", function () {
        updateVariables();
        performAjaxRequest();
    });

    // 체크박스 변경 이벤트
    $('input[type=checkbox]').change(function () {
        updateVariables();
        performAjaxRequest();
    });

    // 테이블 헤더 정렬 클릭 이벤트
    $('th.sortable').on('click', function() {
        column = $(this).data('column');

        // 동일 헤더를 클릭하면 정렬 방향을 토글
        if (column === orderColumnDirection.column) {
            orderColumnDirection[column] = orderColumnDirection[column] === 'ASC' ? 'DESC' : 'ASC';
        } else { // 다른 헤더를 클릭하면 초기 상태의 정렬 방향을 사용
            orderColumnDirection = $.extend(true, {}, defaultOrderColumnDirection);
            orderColumnDirection[column] = orderColumnDirection[column];
        }
        orderColumnDirection.column = column;
        // 선택된 컬럼과 그 방향만 보내는 객체 생성
        selectedOrderDirection = {};
        selectedOrderDirection[column] = orderColumnDirection[column];

        // 클릭된 컬럼을 활성 상태로 표시
        $('th.sortable').removeClass('active');
        $(this).addClass('active');

        updateVariables();
        performAjaxRequest();
    });

    // Ajax 요청을 수행하는 함수
    function performAjaxRequest() {
        $.ajax({
            type: "POST",
            url: "filterSearchAction.do",
            data: {
                'minPrice': minPrice,
                'maxPrice': maxPrice,
                'company': selectedCompanies,
                'productcategory': selectedProductCategories,
                'state': selectedstates,
                'category': '리뷰게시판', //게시판에 맞는 게시글 출력을 위한 변수
                'searchField': searchField, 
                'searchInput': searchInput,
                'orderColumnDirection' : JSON.stringify(selectedOrderDirection)
            },
            traditional: true,
            dataType: 'json',
            success: function (filterDatas) {
     				  if (filterDatas != null) { // filterDatas가 존재하는 경우
                     jsonFilterDatas = filterDatas; // 서버에서 받은 데이터를 변수에 할당
                     console.log(jsonFilterDatas); // 데이터 확인
                     isFiltered = true; // 데이터가 존재하므로 isFiltered를 true로 설정
                     loadReviewData(1);
                 } 
            	 
            },
            error: function (error) {
                console.log('에러');
                console.log('에러종류: ' + JSON.stringify(error));
                alert("fail");
            }
        });
    }
    
    
    
    
   
    
    
    var jsonBoardDatas = ${jsonReviewBoardDTO};
	
    loadReviewData(1);
 // 페이징 버튼 클릭 이벤트
    $(document).on("click", "#paginationContainer .page", function (event) {
        event.preventDefault();
        var page = $(this).data("page");
        loadReviewData(page);
        console.log(page);
    });

    function loadReviewData(page) {
        var dataToSend = {
            'jsonBoardDatas': JSON.stringify(jsonBoardDatas),
            'page': page,
        };

        if (isFiltered) {
        	console.log(jsonFilterDatas);
            dataToSend.jsonFilterDatas = JSON.stringify(jsonFilterDatas);
        }

        console.log(dataToSend);

        $.ajax({
            url: "pagination.do",
            method: "GET",
            data: dataToSend,
            dataType: 'json',
            success: function (pagingDatas) {
                // 성공 시 처리
                displayReviewData(pagingDatas);
                displayPagination(pagingDatas);
                alert("데이터가 성공적으로 로드되었습니다!"); // 성공 시 알림
            },
            error: function () {
                // 에러 처리
                console.error("데이터 로딩 중 에러 발생");
                alert("데이터 로딩 중 에러 발생!"); // 에러 시 알림
            }
        });
    }

    function displayReviewData(pagingDatas) {
        var tbody = document.querySelector('.alt tbody');
        tbody.innerHTML = '';

        // 결과가 없을 경우 메시지 출력
        if (pagingDatas.data.length === 0) {
            tbody.innerHTML = '<tr><td colspan="6" align="center">찾으시는 게시글이 없습니다.</td></tr>';
        } else {
            // 결과가 있을 경우 테이블에 데이터 추가
            pagingDatas.data.forEach(function (boardData) {
                var row = document.createElement('tr');
                row.innerHTML =
                    '<td>' + boardData.boardNum + '</td>' +
                    '<td><a href="/chalKag/cameraReviewSelectOnePage.do?boardNum=' + boardData.boardNum + '">' + boardData.title + '</a></td>' +
                    '<td>' + boardData.nickname + '</td>' +
                    '<td>' + boardData.boardDate + '</td>' +
                    '<td>' + boardData.recommendCNT + '</td>' +
                    '<td>' + boardData.viewCount + '</td>';
                tbody.appendChild(row);
            });
        }
    }

    function displayPagination(pagingDatas) {
        // 페이징 버튼 등을 표시하는 로직
        var paginationContainer = $("#paginationContainer");
        paginationContainer.empty();

        // 한 페이지 그룹에 표시할 페이지 수
        var pageSize = 10;

        // 현재 페이지 그룹
        var currentGroup = Math.floor((pagingDatas.currentPage - 1) / pageSize);

        // 시작 페이지와 끝 페이지 설정
        var startPage = currentGroup * pageSize + 1;
        var endPage = Math.min((currentGroup + 1) * pageSize, pagingDatas.totalPages);

        // 이전 페이지 그룹으로 이동하는 버튼 추가
        if (startPage > 1) {
            var prevGroupPage = startPage - 1;
            var prevGroupLink = "<a href='#' class='page' data-page='" + prevGroupPage + "'>&laquo;</a>";
            paginationContainer.append(prevGroupLink);
        }

     // 페이지 버튼 추가
        for (var i = startPage; i <= endPage; i++) {
            var pageLinkClass = (i === pagingDatas.currentPage) ? "page active" : "page";
            var pageLink = "<a href='#' class='" + pageLinkClass + "' data-page='" + i + "'>" + i + "</a>";
            paginationContainer.append(pageLink);
        }

        // 다음 페이지 그룹으로 이동하는 버튼 추가
        if (endPage < pagingDatas.totalPages) {
            var nextGroupPage = endPage + 1;
            var nextGroupLink = "<a href='#' class='page' data-page='" + nextGroupPage + "'>&raquo;</a>";
            paginationContainer.append(nextGroupLink);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
});
</script>



















		<!-- 페이징을 포함한 푸터 섹션 -->
		<footer>
			<div id="paginationContainer" class="pagination">
				<!-- 페이징 버튼이 동적으로 생성될 부분 -->
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