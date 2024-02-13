<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.board.*,java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone"%>

<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <!-- 문자 인코딩 및 메타 정보 설정 -->
    <meta charset="UTF-8">
    <title>중거거래 게시판</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<!-- 외부 CSS 파일 링크 -->
<link rel="stylesheet" href="/chalKag/assets/css/main.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<style>
#main>* {
	padding: 2rem 6rem 2rem 6rem;
	border: none;
}

a {
	border-bottom: none;
}

pre {
	font-family: "Merriweather", Georgia, serif;
	font-weight: bolder;
}

a>#write {
	font-family: "Source Sans Pro", Helvetica, sans-serif;
}

th.sortable {
	cursor: pointer;
}

th.sortable.highlight {
	color: blue;
	background-color: #F0F0F0;
}

#filterRemoteContainer {
	margin: 0 40px 0 40px;
	flex-wrap: wrap;
	justify-content: space-evenly;
	align-items: center;
}

input[type=range] {
	cursor: pointer;
	width: 90%;
	background: linear-gradient(to right, #ececec 0%, #ececec 100%);
	border-radius: 8px;
	outline: none;
	transition: background 450ms ease-in;
	-webkit-appearance: none;
	accent-color: #717981;
}

.price {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	width: 100%;
	flex: 0 0 auto;
	margin-right: 0 0;
}

.company>Ul {
	list-style: none;
	margin: 0px;
	padding: 0px;
}

.companyUl>li {
	margin: 0 30px 0 0;
	padding: 0px;
}

.company {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-evenly;
	width: 100%;
	flex: 0 0 auto;
	margin-right: 40px;
}

.company>input[type="checkbox"] {
	margin-right: 80px;
}

.category {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-evenly;
	width: 100%;
	flex: 0 0 auto;
	margin-right: 40px;
}

.category>input[type="checkbox"] {
	margin-right: 80px;
}
.state {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-evenly;
	width: 100%;
	flex: 0 0 auto;
	margin-right: 40px;
}

.state>input[type="checkbox"] {
	margin-right: 80px;
}

#priceAscButton.highlight, #priceDescButton.highlight {
	background-color: #F0F0F0;
	color: blue;
	border: 1px solid blue;
}

.page.active {
	background: #7abbf0;
	color: #fff;
	margin: 0 auto;
	justify-content: space-evenly;
}

input::-webkit-input-placeholder {
	font-family: "Merriweather", Georgia, serif;
	font-weight: lighter;
	color: #212931;
}

input {
	font-family: "Source Sans Pro", Helvetica, sans-serif;
	border-color: #212931;
	outline: #212931;
}

#search {
	font-family: "Source Sans Pro", Helvetica, sans-serif;
	margin: 0 0px 0 0px;
	display: flex;
	justify-content: space-evenly;
	align-items: center;
}

select#searchField.searchField {
	color: #212931;
	border-color: #212931;
	width: 30%;
	display: inline-block;
	text-align: center;
}

input#searchInput.searchInput {
	color: #212931;
	border-color: #212931;
	margin: 0 0 0 10px;
	width: 53%;
	display: inline-block;
	text-align: center;
}

button#searchButton.button.primary.icon.solid.fa-search.search-button {
	margin: 0 0 0 10px;
	width: 17%;
	display: inline-block;
	text-align: center;
}

tableWrapper {
	text-align: center;
}

#paginationContainer {
	display: flex;
	justify-content: center; /* 수평 가운데 정렬 */
	align-items: center; /* 수직 가운데 정렬 */
	margin: 40px auto 20px auto; /* 위아래 여백은 20px이며 좌우는 자동으로 가운데 정렬 */
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
            <h2>used trade board</h2>
			<p> Discover the world through lenses! 📷 Welcome to our Camera Review Board, <br>
				where shutterbugs unite to share insights on the latest cameras. <br>
				Dive into detailed reviews, expert opinions, and community discussions.</p>
			<hr style="margin-top: 10px; margin-bottom: 10px; padding: 0px; background-color: #d3d3d3; height: 1px; border: 0;">
		</header>

		<div id="filterRemoteContainer">
			<h4 style="text-align: center;">상품 필터링</h4>
			<!-- 금액대별 검색 -->
			<div class="price">
				<div style="width: 50%; padding-right: 10px;">
					<label style="display: inline-block; padding-right: 20px;"for="minPrice">최저 금액</label>
					<output for="minPrice" id="minPriceOutput">0</output>원 
					<input type="range" id="minPrice" name="minPrice" min="0" max="1000000" value="1">
				</div>
				<div style="width: 50%; padding-right: 10px;">
					<label style="display: inline-block; padding-right: 20px" for="maxPrice">최고 금액</label>
					<output for="maxPrice" id="maxPriceOutput">0</output>원
					<input type="range" id="maxPrice" name="maxPrice" min="0" max="1000000" value="1">
				</div>
			</div>

			<!-- 제조사 -->
			<div class="company">
				<label style="margin-right: 40px;">제조사</label>
				<input type="checkbox" id="company1" name="company" value="캐논">
				<label for="company1">캐논</label> <input type="checkbox" id="company2"
					name="company" value="소니"> <label for="company2">소니</label>
				<input type="checkbox" id="company3" name="company" value="니콘">
				<label for="company3">니콘</label>
			</div>
			<hr style="margin: 10px 0; background: #717981; height: 1px; border: 0;">

			<!-- 카메라 종류 -->
			<div class="category">
				<label>카메라 종류</label>
				 <input type="checkbox" id="productcategory1"
					name="productcategory" value="DSLR"> <label
					for="productcategory1">DSLR</label> <input type="checkbox"
					id="productcategory2" name="productcategory" value="미러리스">
				<label for="productcategory2">미러리스</label> <input type="checkbox"
					id="productcategory3" name="productcategory" value="컴팩트"> <label
					for="productcategory3">컴팩트</label>
			</div>
			<hr style="margin: 10px 0; background: #717981; height: 1px; border: 0;">

            <!-- 게시글 상태 -->
            <div class="state">
	            <label>게시글 상태:</label> 
	            <input type="checkbox" id="selling" name="state" value="판매중"> 
	            <label for="selling">판매중</label> 
	            <input type="checkbox" id="sold" name="state" value="판매완료"> 
	            <label for="sold">판매완료</label>
	        </div>

	
		<!-- 검색 폼 -->
			<div id="search">
				<select id="searchField" name="searchField" class="searchField">
					<option value="title">제목</option>
					<option value="contents">내용</option>
					<option value="writer">작성자</option>
					<option value="titleAndContents">제목 + 내용</option>
				</select> <input type="text" id="searchInput" name="search"
					class="searchInput" placeholder="검색어를 입력해 주세요.">
				<button id="searchButton"
					class="button primary icon solid fa-search search-button"
					style="height:">검색</button>
			</div>
			<hr style="margin: 10px 0; background: #717981; height: 1px; border: 0;">
		</div>

		<!-- '게시글 작성', '메인으로 돌아가기' 버튼 생성 -->
		<div class="col-6 col-12-small"
			style="margin-right: 25px; text-align: right">
			<input type="button" value="Write" onclick="writeBtn()" />
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
                        <th width="10%" class="sortable" data-column="price">price</th>
                        <th width="10%" class="sortable" data-column="views">views</th>
                        <th width="10%" class="notSortable" data-column="state">state</th>
                    </tr>
                </thead>
                <!-- JSTL forEach를 사용하여 카메라 리뷰 데이터 반복 처리하여 출력-->
                <tbody id="dataContainer" data-jsonBoardDatas='${jsonBoardDatas}' data-category='${category}'></tbody>
                    <!-- pagination 으로 동적으로 채워질 테이블 공간 -->
            				<!-- pagination 으로 동적으로 채워질 테이블 공간 (카메라 리뷰 게시판 데이터를 저장)-->
				<tbody id="dataContainer" data-jsonBoardDatas='${jsonBoardDatas}'
					data-category='${category}'></tbody>
			</table>


            <div id="paginationContainer" class="pagination">
                <!-- 페이징 버튼이 동적으로 생성될 부분 -->
            </div>
      
    </div>
 </div><!-- div 메인  -->
    <!-- 커스텀 태그를 사용하여 저작권 정보 포함 -->
    <stone:copyright />
    
    <!-- JavaScript 파일 링크 -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script src="/chalKag/assets/js/jquery.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrollex.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrolly.min.js"></script>
	<script src="/chalKag/assets/js/browser.min.js"></script>
	<script src="/chalKag/assets/js/breakpoints.min.js"></script>
	<script src="/chalKag/assets/js/util.js"></script>
	<script src="/chalKag/assets/js/main.js"></script>
	<script src="/chalKag/assets/js/pagination.js"></script>
	<script src="/chalKag/assets/js/filterSearch.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script>
		/* 비 로그인 시 글 작성 불가. Write 버튼 클릭 시 로그인 페이지로 이동 */
		function writeBtn() {
			var member = "${member}";
			if (member == "") {
				Swal.fire({
					 title: '게시글 작성이 불가합니다!',     // Alert 제목
					  text: '로그인 후 이용해 주세요!',  // Alert 내용
					  icon: 'warning'                     // Alert 타입
					}).then((result) => {
						// 사용자가 확인을 클릭하면 로그인 페이지로 이동
						if (result.isConfirmed) {
							location.href = '/chalKag/loginPage.do';
						}
					});
				} else {
					// 회원 정보가 있으면, 즉 로그인이 되어있다면 글 작성 페이지로 이동
					location.href = '/chalKag/sellBoardWritePage.do';
				}
			}
	</script>
	<script>
		/* 금액대별 검색 시 최저 금액 슬라이드바 클릭 시 발생되는 이벤트 */
		document
				.querySelector('#minPrice')
				.addEventListener(
						'input',
						function(event) {
							var gradient_value = 100 / event.target.attributes.max.value;
							event.target.style.background = 'linear-gradient(to right, 	#1E3269 0%, #1E3269 '
									+ gradient_value
									* event.target.value
									+ '%, rgb(236, 236, 236) '
									+ gradient_value
									* event.target.value
									+ '%, rgb(236, 236, 236) 100%)';
						});
	</script>
	<script>
		/* 금액대별 검색 시 최고 금액 슬라이드바 클릭 시 발생되는 이벤트 */
		document
				.querySelector('#maxPrice')
				.addEventListener(
						'input',
						function(event) {
							var gradient_value = 100 / event.target.attributes.max.value;
							event.target.style.background = 'linear-gradient(to right, 	#1E3269 0%, #1E3269 '
									+ gradient_value
									* event.target.value
									+ '%, rgb(236, 236, 236) '
									+ gradient_value
									* event.target.value
									+ '%, rgb(236, 236, 236) 100%)';
						});
	</script>

</body>
</html>

   