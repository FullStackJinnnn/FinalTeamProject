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
<title>자유 게시판 게시글 상세 보기</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<!-- 외부 CSS 파일 링크 -->
<link rel="stylesheet" href="/chalKag/assets/css/main.css" />
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
	margin-top: 2px;
	margin-bottom: 2px;
	font-family: "Source Sans Pro", Helvetica, sans-serif;
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
		<!-- Post -->
		<section class="post">
			<header class="major">
				<!-- 게시판 이름 -->
				<br>
				<h3>free board</h3>
			</header>

			<hr style="margin-top: 1px; margin-bottom: 10px;">


			<!-- 게시글 정보 (제목, 조회수, 좋아요수, 작성일, 작성자 -->
			<div>
				<!-- 게시글 제목 -->
				<pre> Title ${board.title} </pre>
				<!-- 제시글 조회수, 좋아요 -->
				<pre style="text-align: right"> Views ${board.viewCount}  |  Recommend ${board.recommendCNT} </pre>
				<!-- 게시글 작성일, 작성자  -->
				<pre> Date ${board.boardDate}  |  Writer ${board.nickname} </pre>
			</div>

			<hr style="margin-top: 10px; margin-bottom: 10px; background: Lightgrey; height: 2px; border: 0;">

			
			<!-- 게시글 내용 -->
			<div>
				<pre> Content : </pre>
				<!-- 첨부 이미지 -->
				<img src="bimg/${board.image}"> <br>
				<pre>${board.contents} 내용 출력 위치 </pre>
			</div>


			<!-- '글 수정', '글 삭제', '글 작성', '현재 카테고리 목록으로' 버튼 생성 -->
			<div class="col-6 col-12-small" style="margin-top: 45px; text-align: right;">
				<!-- 로그인 상태인 경우 '글 수정', '글 삭제' 버튼 활성화 -->
				<c:if test="${sessionScope.memberid!=null}">
					<button type="button" onclick="location.href='/chalKag/freeBoardUpdatePage.do'">Update</button>
					<button type="button" onclick="location.href='/chalKag/freeBoardDeletePage.do'">Delete</button>
				</c:if>		
				<button type="button" onclick="location.href='/chalKag/freeBoardWritePage.do'">Write</button>
				<button type="button" onclick="location.href='/chalKag/freeBoardSelectAllPage.do'">Tolist</button>	
			</div>

			<!-- 게시글 좋아요 -->
            <div class="col-6 col-12-small" style="text-align:center;">
                <br>
                <input type="button" id="recommendBtn" value="좋아요" />
            </div>

			<hr>

			<!-- 댓글 -->
			<%-- <stone:review /> --%>
		</section>
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
	<script type="text/javascript">

    $(function() {
        $("#recommendBtn").on("click", function() {
             $.ajax({
                    type : "POST",            // 서버 요청 방식
                    url : "/chalKag/recommendUpAndDown.do",    // 서버 url
                    data : {'boardNum' : ${board.boardNum}},
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