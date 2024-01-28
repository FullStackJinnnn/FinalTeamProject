<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.board.*,model.review.*,java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title> 자유 게시판 게시글 상세 보기 </title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
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
table tbody tr {
		border-color: #eeeeee;
	}

		table tbody tr:nth-child(2n + 1) {
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
	<stone:printNav member='${member}' />
	<!-- Main -->
	<div id="main">

		<!-- Post -->
		<section class="post">
			<header class="major" >		
				
				<!-- 게시판 이름 -->
				<br>
				<h3> free board</h3>
			</header>
			
			<hr style="margin-top:1px; margin-bottom:20px;">

			<!-- 게시글 제목 -->
			<div> Title : ${data.title} </div>	
			<!-- 제시글 조회수, 좋아요 -->
			<div style="text-align:right">
			Views : ${data.viewCount} &nbsp;&nbsp;&nbsp;&nbsp; Recommend : ${data.RecommendCount}		
			</div>
			<!-- 게시글 작성일, 작성자  -->
			<div> Date : ${data.boardDate} &nbsp;&nbsp;&nbsp;&nbsp; Writer : ${data.nickname} </div>	
			

			<hr style="margin-top:20px; margin-bottom:20px;  background-color:Light;">
			
			<!-- 내용 -->
			<div> Content : </div>
				<!-- 첨부 이미지 -->
				<img src="${data.image}">이미지 출력 위치 <br>
			<div style="margin-bottom:30px;"> ${data.contents} 내용 출력 위치 </div>

			
			<!-- 게시글 수정, 삭제 -->
			<div class="col-6 col-12-small" style="text-align:right;">
				<br>
				<input type="button" value="Update" onclick="/chalKag/freeBoardUpdate.do">
				<input type="button" value="Delete" onclick="/chalkag/freeBoardDelete.do">			
			</div>
			
			<!-- 게시글 좋아요 -->
			<div class="col-6 col-12-small" style="text-align:center;">
				<br>
				<input type="button" value="Recommend" onclick="/chalKag/recommendUp.do">			
			</div>
			
			<hr />

			<!-- 댓글 -->
			<stone:review />
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
	
</body>

</html>