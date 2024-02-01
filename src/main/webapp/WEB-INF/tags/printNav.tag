<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="member" %>

<div id="wrapper">
		<!-- Header -->
		<header id="header">
			<a href="/chalKag/main.do" class="logo">CHAL KAG</a>
		</header>

		<!-- Nav -->
		<nav id="nav">
			<ul class="links">
				<%
				if (session.getAttribute("member") != null) { // 로그인 상태 메뉴
				%>
				<li><a href="/chalKag/sellBoardSelectAllPage.do">Camera Sell</a></li>
				<li><a href="/chalKag/cameraReviewSelectAllPage.do">CameraReview</a></li>
				<li><a href="/chalKag/freeBoardSelectAllPage.do">FreeBoard</a></li>
				<li><a href="/chalKag/logout.do">LOGOUT</a></li>
				<li><a href="/chalKag/myPage.do">MYPAGE</a></li>
				<%
				} else // 로그아웃 상태 메뉴
				{
				%>
				<li><a href="/chalKag/sellBoardSelectAllPage.do">Camera Sell</a></li>
				<li><a href="/chalKag/cameraReviewSelectAllPage.do">Camera Review</a></li>
				<li><a href="/chalKag/freeBoardSelectAllPage.do">Free Board</a></li>
				<li><a href="/chalKag/loginPage.do">LOGIN</a></li>
				<li><a href="/chalKag/joinPage.do">SIGN IN</a></li>
				<%
				}
				%>
			</ul>
		</nav>
	</div>