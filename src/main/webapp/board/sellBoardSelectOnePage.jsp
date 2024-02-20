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
<title>판매 게시판 게시글 상세 보기</title>
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

p {
	font-family: "Source Sans Pro", Helvetica, sans-serif;
	font-size: 25px;
	margin-top: 2px;
	margin-bottom: 2px;

}

pre {
	font-family: "Source Sans Pro", Helvetica, sans-serif;
	font-size: 25px;
	margin-top: 2px;
	margin-bottom: 2px;
}

pre. {
	margin-top: 2px;
	margin-bottom: 2px;
	font-family: "Source Sans Pro", Helvetica, sans-serif;
	font-weight: bolder;
	font-size: 25px;
}


/* 이미지를 담는 컨테이너 요소에 대한 스타일 지정 (클래스 'imageContainer') */
.imageContainer {
    max-width: 1400px; /* 이미지 컨테이너의 최대 너비를 1400px로 설정 */
    width: 100%; /* 너비를 부모 요소의 100%로 설정하여 반응형으로 만듦 */
    height: auto; /* 높이를 자동으로 조정하여 이미지 비율을 유지함 */
    overflow: hidden; /* 이미지가 컨테이너를 벗어나지 않도록 설정 */
}

/* 반응형 디자인을 위한 미디어 쿼리: 화면 크기가 1400px 이하일 때 이미지 컨테이너의 너비 조정 */
@media (max-width: 1400px) {
    .imageContainer {
        max-width: 100%; /* 이미지 컨테이너의 최대 너비를 부모 요소의 100%로 설정하여 화면 크기에 따라 조정 */
    }
}

/* 이미지에 대한 스타일 지정 (클래스 'boardImage') */
.boardImage {
    max-width: 100%; /* 이미지의 최대 너비를 부모 요소의 100%로 설정하여 반응형으로 만듦 */
    height: auto; /* 높이를 자동으로 조정하여 이미지 비율을 유지함 */
    display: block; /* 블록 요소로 설정하여 가로 여백을 자동으로 추가 */
    margin: 0 auto 0 0; /* 상하 여백 0, 좌우 여백을 자동으로 조정하여 가운데 정렬 */
}

/* 'boardContents' 클래스를 가진 요소에 대한 스타일 지정 */
.boardContents {
    /* white-space 속성: pre-wrap으로 설정하여 공백 유지 및 줄 바꿈 유지 */
    white-space: pre-wrap;
    /* word-wrap 속성: break-word로 설정하여 긴 단어나 문자열이 컨테이너를 넘어갈 때 줄 바꿈 처리 */
    word-wrap: break-word;
}

/* 반응형 디자인을 위한 미디어 쿼리: 화면 크기가 1400px 이하일 때 특정 클래스 'boardContents'의 white-space 조정 */
@media (max-width: 1400px) {
    .boardContents {
        white-space: normal; /* 화면이 작을 때 줄 바꿈을 자동으로 처리하여 텍스트가 잘리지 않도록 함 */
        word-wrap: break-word; /* 긴 단어가 넘칠 경우 줄 바꿈 처리 */
    }
}


.actions {
	float: right;
}
</style>
</head>


<body class="is-preload">
	<script> console.log("[로그] sellBoardSelectOnePage.jsp 진입 " + ${jsonBoardDatas}); </script>	
	<!-- 커스텀 태그를 사용하여 네비게이션 포함 -->
	<stone:printNav member='${member}' />
	
	<div id="main"> <!-- 메인 div -->
		 <section class="post">
			<header class="major"> <!-- 헤더  -->
				<!-- 게시판 이름 -->
				<br><h3>sell Board board</h3>
			</header>
	
			<hr style="margin-top: 1px; margin-bottom: 10px; background: grey; height: 4px; border: 0;">

			<!-- 게시글 정보 (제목, 조회수, 좋아요수, 작성일, 작성자) -->
			<div id="boardData" style="font-weight: bold;"> <!-- 게시글 정보 div -->
				<!-- 게시글 제목 -->
				<pre> Title  ${boardData.title} </pre>
				<!-- 제시글 조회수, 좋아요 -->
				<pre style="text-align: right"> Views   ${boardData.viewCount}   |   Recommend  ${boardData.recommendCNT} </pre>
				<!-- 게시글 작성일, 작성자  -->
				<pre> Date  ${boardData.boardDate}   |   Writer  ${boardData.nickname} </pre>
			</div> <!-- 게시글 정보 div -->

			<hr style="margin-top: 10px; margin-bottom: 10px; background: Lightgrey; height: 3px; border: 0;">

			<!-- 판매할 카메라 상품 정보 -->
			<div id="cameraReviewData" style="font-weight: bold;"> <!-- 카메라 상품 정보 div -->
				<!-- 상품 가격 -->
				<pre> Price  :  ${boardData.price}원</pre>
				<!-- 상품 종류 -->
				<pre> ProductCategory  :  ${boardData.productCategory} </pre>
				<!-- 상품 이름 -->
				<pre> ProductName  :  ${boardData.productName} </pre>
				<!-- 상품 제조사 -->
				<pre> Company  :  ${boardData.company} </pre>
				<!-- 판매 상 -->
				<pre> State  :  ${boardData.state} </pre>
			</div> <!-- 카메라 상품 정보 div -->

			
			<!-- 게시글 내용 -->
			<div> <!-- 게시글 내용 div -->
				<pre style="font-weight: bold;"> Content  :</pre>
				<!-- 첨부 이미지 -->
			<div id="imageContainer">
			    <img id="boardImage" class="boardImage" src="bimg/${boardData.image}"> <br>
			</div>
				<pre class="boardContents"> ${boardData.contents} </pre>
			</div> <!-- 게시글 내용 div -->


			<!-- '좋아요' 버튼 생성 -->
            <div class="col-6 col-12-small" style="text-align: center; margin-bottom: 60px;"> <br> <!-- '좋아요' 버튼 div -->
                <c:if test="${member != null}"> <!-- 만약 로그인이 되어있다면 -->
                  <c:if test="${member == recommendData.id}"> <!-- 만약 member가 recommendData.id와 같다면 -->
                     <input class="button primary" type="button" id="recommendBtn" value="Like" /> <!-- '좋아요' 버튼 생성 -->
                  </c:if>
                  <c:if test="${member != recommendData.id}"> <!-- 만약 member가 recommendData.id와 같지 않다면 -->
                     <input type="button" id="recommendBtn" value="Like" />  <!-- '좋아요' 버튼 생성 -->
                  </c:if>
                 </c:if>
                   <c:if test="${member == null}"> <!-- 만약 로그인이 되어있지 않다면-->
                     <input type="button" id="recommendBtn" value="Like" disabled /> <!-- '좋아요' 버튼 비활성화 -->
                 </c:if>
            </div> <!-- '좋아요' 버튼 div -->

			<hr style="margin-top: 10px; margin-bottom: 10px; background: Lightgrey; height: 3px; border: 0;">

			
			<!-- '글 수정', '글 삭제', '글 신고하기' 버튼 생성 -->
			<div class="col-6 col-12-small" style="margin-top: 45px; margin-bottom: 45px; text-align: right;"> <!-- 버튼 생성 div -->
				<!-- 로그인 상태인 경우 '글 수정', '글 삭제' 버튼 활성화 -->
				<c:if test="${member == boardData.id}">
					<button type="button" style="margin-left: 10px;" onclick="location.href='/chalKag/sellBoardUpdatePage.do?boardNum=${boardData.boardNum}'">Update</button>
					<button type="button" style="margin-left: 10px;" onclick="location.href='/chalKag/boardDelete.do?boardNum=${boardData.boardNum}&category=${boardData.category}'">Delete</button>
				</c:if>
				<!-- 로그인 상태이고 글 작성자가 작성자가 아닐 경우 '글 신고하기' 버튼 활성화 -->
                <c:if test="${sessionScope.member != null && sessionSzope.member != boardData.id}">
                  	 <button id="report"  style="margin-left: 10px;"
                      onClick="location.href='/chalKag/reportWritePage.do?boardNum=${boardData.boardNum}&reportPageURL='+window.location.href">Report</button>
                 </c:if>
			</div> <!-- 버튼 생성 div -->

			
			<!-- 댓글 -->
			<stone:review />
			<!-- '현재 카테고리 목록으로', '메인으로 돌아가기' 버튼 생성 -->
			<div class="col-6 col-12-small" style="margin-top: 45px; text-align: right;"> <!-- 버튼 생성 div -->
				<!-- '글 작성', '현재 카테고리 목록으로', '메인으로 돌아가기' 버튼 생성 -->
				<input type="button" value="Write" onclick="writeBtn()" /> 
				<!-- 버튼 클릭 시 JavaScript 함수인 writeBtn()이 실행되도록 지정 -->
				<button type="button" style="margin-left: 10px;" onclick="location.href='/chalKag/sellBoardSelectAllPage.do'">List</button>
				<!-- 버튼 클릭 시 '현재 카테고리 목록'으로 이동시키는 경로 지정 -->
				<button type="button" style="margin-left: 10px;" onclick="location.href='/chalKag/main.do'">MainPage</button>
				<!-- 버튼 클릭 시 '메인 페이지'로 이동시키는 경로 지정 -->
			</div> <!-- 버튼 생성 div -->
		</section>
	</div> <!-- 메인 div -->
	
	<!-- 저작권 및 회사 정보를 담은 푸터 섹션.-->
	<stone:copyright /> <!-- 카피라이트 태그 -->
	

	<!-- JavaScript 파일 링크 -->
	<script src="/chalKag/assets/js/jquery.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrollex.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrolly.min.js"></script>
	<script src="/chalKag/assets/js/browser.min.js"></script>
	<script src="/chalKag/assets/js/breakpoints.min.js"></script>
	<script src="/chalKag/assets/js/util.js"></script>
	<script src="/chalKag/assets/js/main.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script> // writeBtn()이 눌렸을 때 실행되는 함수 ()
		function writeBtn() {
			var member = "${member}"; // 사용자 정보를 가져오기 위해 JSP에서 전달된 ${member} 값을 가져옴
			if (member == "") { // 만약 회원 정보가 비어있다면 (로그인하지 않은 상태)
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
					location.href = '/chalKag/sellBoardWritePage.do';
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
