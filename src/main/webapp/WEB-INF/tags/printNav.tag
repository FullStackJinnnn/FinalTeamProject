<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="member" %>

<div id="wrapper">
		<!-- Header -->
		<header id="header">
			<a href="/main.do" class="logo">CHAL KAG</a>
		</header>



		<!-- Nav -->
		<nav id="nav">
			<ul class="links">
				<%
				if (session.getAttribute("member") != null) { // 로그인 상태 메뉴
				%>
				<li><a href="sellBoardSelectAllPage.do">UsedTrade</a></li>
				<li><a href="cameraReviewSelectAllPage.do">CameraReview</a></li>
				<li><a href="freeBoardSelectAllPage.do">FreeBoard</a></li>
				<li><a href="logout.do">LOGOUT</a></li>
				<li><a href="../myPage.do">MYPAGE</a></li>
				<%
				} else // 로그아웃 상태 메뉴
				{
				%>
				<li><a href="sellBoardSelectAllPage.do">Used Trade</a></li>
				<li><a href="cameraReviewSelectAllPage.do">Camera Review</a></li>
				<li><a href="freeBoardSelectAllPage.do">Free Board</a></li>
				<li><a href="../loginPage.do">LOGIN</a></li>
				<li><a href="joinPage.do">SIGN IN</a></li>
				<%
				}
				%>
			</ul>
		</nav>
	</div>