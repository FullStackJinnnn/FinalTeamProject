<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="model.board.*,model.review.*,java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone"%>
<!DOCTYPE html>
<html>
<head>
<!-- 문자 인코딩 및 메타 정보 설정 -->
<meta charset="UTF-8">
<title>카메라 리뷰 게시판 게시글 상세 보기</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<!-- 외부 CSS 파일 링크 -->
<link rel="stylesheet" href="/chalKag/assets/css/main.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

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

div {
	font-family: "Source Sans Pro", Helvetica, sans-serif;
}

pre {
	font-family: "Source Sans Pro", Helvetica, sans-serif;
	font-size: 20;
	margin-top: 2px;
	margin-bottom: 2px;
}

pre. {
	margin-top: 2px;
	margin-bottom: 2px;
	font-family: "Source Sans Pro", Helvetica, sans-serif;
	font-weight: bolder;
}

table tbody tr {
	border-color: #eeeeee;
}

table tbody tr:nth-child(2n+1) {
	background-color: white;
}

table.alt tbody tr td {
	border-color: white;
}

.actions {
	float: right;
}

</style>
</head>


<body class="is-preload">
	<!-- 커스텀 태그를 사용하여 네비게이션 포함 -->
	<stone:printNav member='${member}' />
	
	<!-- 메인 콘텐츠 래퍼 -->
	<div id="main">
		<section class="post">
			<header class="major">
				<!-- 게시판 이름 -->
				<br><h3>camera review board</h3>
			</header>
	
			<hr style="margin-top: 1px; margin-bottom: 10px; background: grey; height: 4px; border: 0;">

			<!-- 게시글 정보 (제목, 조회수, 좋아요수, 작성일, 작성자) -->
			<div id="boardData" style="font-weight: bold;">
				<!-- 게시글 제목 -->
				<pre> Title  ${boardData.title} </pre>
				<!-- 제시글 조회수, 좋아요 -->
				<pre style="text-align: right"> Views   ${boardData.viewCount}   |   Recommend  ${boardData.recommendCNT} </pre>
				<!-- 게시글 작성일, 작성자  -->
				<pre> Date  ${boardData.boardDate}   |   Writer  ${boardData.nickname} </pre>
			</div>

			<hr style="margin-top: 10px; margin-bottom: 10px; background: Lightgrey; height: 3px; border: 0;">

			<!-- 리뷰할 카메라 상품 정보 -->
			<div id="cameraReviewData" style="font-weight: bold;">
				<!-- 상품 가격 -->
				<pre> Price  :  ${boardData.price}원</pre>
				<!-- 상품 종류 -->
				<pre> ProductCategory  :  ${boardData.productCategory} </pre>
				<!-- 상품 이름 -->
				<pre> ProductName  :  ${boardData.productName} </pre>
				<!-- 상품 제조사 -->
				<pre> Company  :  ${boardData.company} </pre>
			</div>

			
			<!-- 게시글 내용 -->
			<div>
				<pre style="font-weight: bold;"> Content : </pre>
				<!-- 첨부 이미지 -->
				<img src="bimg/${boardData.image}" style="width: 30%; height: 30%;"> <br>
				<pre> ${boardData.contents} </pre>
			</div>


			<!-- 게시글 좋아요 -->
            <div class="col-6 col-12-small" style="text-align: center; margin-bottom: 40px;">
                <br>
                <c:if test="${member != null}">
                  <c:if test="${member == recommendData.id}">
                     <input class="button primary" type="button" id="recommendBtn" value="Recommend" />
                  </c:if>
                  <c:if test="${member != recommendData.id}">
                     <input type="button" id="recommendBtn" value="Recommend" />
                  </c:if>
                 </c:if>
                   <c:if test="${member == null}">
                     <input type="button" id="recommendBtn" value="Recommend" disabled />
                 </c:if>
            </div>

			<hr style="margin-top: 10px; margin-bottom: 10px; background: Lightgrey; height: 2px; border: 0;">

			<!-- '글 수정', '글 삭제', '글 작성', '현재 카테고리 목록으로' 버튼 생성 -->
			<div class="col-6 col-12-small" style="margin-top: 45px; text-align: right;">
				<!-- 로그인 상태인 경우 '글 수정', '글 삭제' 버튼 활성화 -->
				<c:if test="${member == boardData.id}">
					<button type="button" style="margin-left: 10px;" onclick="location.href='/chalKag/cameraReviewUpdatePage.do?boardNum=${boardData.boardNum}'">Update</button>
					<button type="button" style="margin-left: 10px;" onclick="location.href='/chalKag/boardDelete.do?boardNum=${boardData.boardNum}&category=${boardData.category}'">Delete</button>
				</c:if>	
							
				<!-- '글 작성', '현재 카테고리 목록으로' 버튼 생성 -->
				<button type="button" style="margin-left: 10px;" onclick="location.href='/chalKag/cameraReviewWritePage.do'">Write</button>
				<button type="button" style="margin-left: 10px;" onclick="location.href='/chalKag/cameraReviewSelectAllPage.do'">List</button>
			</div>
		
             <!-- '글 신고하기' 버튼 생성 -->
			 <div class="col-6 col-12-small" style="margin-top: 45px; margin-bottom: 40px; text-align: right;">
                 <c:if test="${sessionScope.member != null && sessionSzope.member != boardData.id}">
                     <button id="report"
                         onClick="location.href='/chalKag/reportWritePage.do?boardNum=${boardData.boardNum}&reportPageURL='+window.location.href"
                         style="border: 0; color: white;">Report</button>
                 </c:if>
             </div>
             
			<hr style="margin-top: 10px; margin-bottom: 10px; background: Lightgrey; height: 2px; border: 0;">
			
			<!-- 댓글 -->
			<stone:review />
		</section>
	</div>

    <stone:copyright />
	

	<!-- JavaScript 파일 링크 -->
	<script src="/chalKag/assets/js/jquery.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrollex.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrolly.min.js"></script>
	<script src="/chalKag/assets/js/browser.min.js"></script>
	<script src="/chalKag/assets/js/breakpoints.min.js"></script>
	<script src="/chalKag/assets/js/util.js"></script>
	<script src="/chalKag/assets/js/main.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script>
	/* 비 로그인 시 글 작성 불가. Write 버튼 클릭 시 로그인 페이지로 이동 */
		function writeBtn() {
			// 서버에서 받은 회원 정보를 확인하여 로그인 상태를 판별
			var member = "${member}";
	
			// 만약 회원 정보가 비어있다면, 즉 로그인이 되어있지 않다면
			if (member == "") {
				// SweetAlert를 사용하여 로그인 안내 메시지를 표시
				Swal.fire({
					title: '게시글 작성이 불가합니다!',     // Alert 제목
					text: '로그인 후 이용해 주세요!',     // Alert 내용
					icon: 'warning'                    // Alert 타입
				}).then((result) => {
					// 사용자가 확인을 클릭하면 로그인 페이지로 이동
					if (result.isConfirmed) {
						location.href = '/chalKag/loginPage.do';
					}
				});
			} else {
				// 회원 정보가 있으면, 즉 로그인이 되어있다면 글 작성 페이지로 이동
				location.href = '/chalKag/cameraReviewWritePage.do';
			}
		}
	</script>
	
			
	<script type="text/javascript"> /* 좋아요 버튼 클릭 시 액션 */
    $(function() {
        $("#recommendBtn").on("click", function() {
             $.ajax({
                    type : "POST",            // 서버 요청 방식
                    url : "/chalKag/recommendUpAndDown.do",    // 서버 url
                    data : {'boardNum' : ${boardData.boardNum}},
                      success : function(data){
                           console.log("추천");
                           history.go(0);
                  },
                  error : function(){
                     console.log("에러발생!");
                  }
               });
        });
    });
    </script>

</body>

</html>