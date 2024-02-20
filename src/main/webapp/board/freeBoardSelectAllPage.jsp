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
<title>카메라 리뷰 게시판</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<!-- 외부 CSS 파일 링크 -->
<link rel="stylesheet" href="/chalKag/assets/css/main.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<style>
/* CSS 스타일 지정 */
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

input::-webkit-input-placeholder {
	font-family: "Source Sans Pro", Helvetica, sans-serif;
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
	justify-content: center;
	align-items: center;
}

select#searchField.searchField {
	font-family: "Source Sans Pro", Helvetica, sans-serif;
	color: #212931;
	border-color: #212931;
	width: 20%;
	display: inline-block;
	text-align: center;
}

input#searchInput.searchInput {
	font-family: "Source Sans Pro", Helvetica, sans-serif;
	color: #212931;
	border-color: #212931;
	margin: 0 0 0 10px;
	width: 60%;
	display: inline-block;
	text-align: center;
}

button#searchButton.button.primary.icon.solid.fa-search.search-button {
font-family: "Source Sans Pro", Helvetica, sans-serif;
	margin: 0 0 0 10px;
	width: 15%;
	display: inline-block;
	text-align: center;
}

table {
    table-layout: fixed;
    width: 100%;
}

table th, table td {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}

#paginationContainer {
	display: flex;
	justify-content: center; /* 수평 가운데 정렬 */
	align-items: center; /* 수직 가운데 정렬 */
	margin: 40px auto 20px auto; /* 위아래 여백은 20px이며 좌우는 자동으로 가운데 정렬 */
}

.page.active {
	background: #7abbf0;
	color: #fff;
	margin: 0 ;
	justify-content: center; 
}
</style>
</head>

<body class="is-preload">
	<script> console.log("[로그] freeBoardSelectAllPage.jsp 진입 " + ${jsonBoardDatas}); </script>	
	<stone:printNav member='${member}' />

	<div id="main"> <!-- 메인 div -->
		<header class="major" style="padding-top: 110px;"> <!-- 헤더  -->
			<!-- 게시판 이름 -->
			<h2>Free Board</h2>
			<p> Discover the world through lenses! 📷 Welcome to our Free Board, <br>
				where shutterbugs unite to share insights on the latest cameras. <br>
				Dive into detailed reviews, expert opinions, and community discussions.</p>
		</header> <!-- 헤더  -->
		<hr style="margin-top: 0px; margin-bottom: 20px; padding: 0px; background-color: #d3d3d3; height: 1px; border: 0;">

		<!-- 일반 검색 폼 ('제목', '내용', '작성자', '제목+내용'옵션 중 한 가지를 선택하여 검색) -->
		<div id="search"> <!-- 일반 검색 div -->
			<select id="searchField" name="searchField" class="searchField">
				<option value="title">제목</option>
				<option value="contents">내용</option>
				<option value="writer">작성자</option>
				<option value="titleAndContents">제목 + 내용</option>
			</select>
			<input type="text" id="searchInput" name="search" class="searchInput" placeholder="검색어를 입력해 주세요.">
			<button id="searchButton" class="button primary icon solid fa-search search-button" style="height:">검색</button>
		</div> <!-- 일반 검색 div -->
			
		<!-- '게시글 작성', '메인으로 돌아가기' 버튼 생성 -->
		<div class="col-6 col-12-small" style="padding-bottom: 0px; padding-bootom: 0px; margin-right: 25px; text-align: right"> <!-- '게시글 작성', '메인으로 돌아가기' 버튼 div -->
			<input type="button" value="Write" onclick="writeBtn()" /> 
			<!-- 버튼 클릭 시 JavaScript 함수인 writeBtn()이 실행되도록 지정 -->
			<button type="button" style="margin-left: 10px;" onclick="location.href='/chalKag/main.do'">MainPage</button>
			<!-- 버튼 클릭 시 main 페이지로 이동키는 경로 지정  -->
		</div> <!-- '게시글 작성', '메인으로 돌아가기'버튼 div -->
 
		<!-- 'pagination'(페이지 번호를 통한 데이터 쪼개기)을 통해 자유 게시판 데이터를 테이블로 표시하는 섹션 --> 
		<div class="tableWrapper" style="margin-top: 20px;"> <!-- 자유 게시판 테이블 div -->
			<table class="alt" style="margin-top: 30px;">
				<thead>
					<tr>
						<th width="12%" class="sortable" data-column="boardNum">boardNum</th>
						<th width="*" class="sortable" data-column="title">title</th>
						<th width="15%" class="sortable" data-column="writer">writer</th>
						<th width="12%" class="sortable" data-column="boardDate">boardDate</th>
						<th width="7%" class="sortable" data-column="recommendCNT">like</th>
						<th width="9%" class="sortable" data-column="views">views</th>
					</tr>
				</thead>
				<!-- 'pagination'(페이지 번호를 통한 데이터 쪼개기)을 통해 해당 카테고리의 데이터를 출력 -->
				<tbody id="dataContainer" data-jsonBoardDatas='${jsonBoardDatas}'data-category='${category}'></tbody>
			</table>
			<!-- 페이징 버튼을 동적으로 생성 -->
			<div id="paginationContainer" class="pagination"></div> <!-- 페이징 버튼 div -->
		</div> <!-- 자유 게시판 테이블 div -->
	</div> <!-- 메인 div -->

	<!-- 저작권 및 회사 정보를 담은 푸터 섹션.-->
	<stone:copyright /> <!-- 카피라이트 태그 -->

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
	<script> // writeBtn()이 눌렸을 때 실행되는 함수 ()
		function writeBtn() {
			var member = "${member}"; // 사용자 정보를 가져오기 위해 JSP에서 전달된 ${member} 값을 가져옴
			if (member == "") { // 만약 회원 정보가 비어있다면 (로그인하 않은 상태)
				Swal.fire({ // SweetAlert2를 사용하여 경고창을 표시
					 title: '게시글 작성이 불가합니다!', // Alert 제목
					  text: '로그인 후 이용해 주세요!', // Alert 내용
					  icon: 'warning' // Alert 타입
					}).then((result) => {  // 경고창이 닫힐 때 확인 버튼이 눌리지 확인
						if (result.isConfirmed) { // 확인 버튼이 눌린다면 로그인 페이지로 이동!
							location.href = '/chalKag/loginPage.do';
						}
					});
				} else { // 회원 정보가 있다면(로그인 되어있다면) 글 작성 페이지로 이동
					location.href = '/chalKag/freeBoardWritePage.do';
				}
			}
	</script>

</body>
</html>