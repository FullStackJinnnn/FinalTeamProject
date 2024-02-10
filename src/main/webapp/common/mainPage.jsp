<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>CHAL KAG</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/chalKag/assets/css/main.css" />

</head>

<body class="is-preload">
	<stone:printNav member='${member}' />
	<!-- Main -->
	<div id="main">

		<!-- Featured Post -->
		<article class="post featured">
			<header class="major">
				<h3>
					The moment the shutter sound echoes, 
					<br>
					here is where creation and emotion converge.
				</h3>
				<a href="/chalKag/main.do" class="image main"><img
					src="/chalKag/images/mainImage.png" alt="" /></a>
			</header>

		</article>
	</div>
	
	<stone:copyright />
	
	<script src="/chalKag/assets/js/jquery.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrollex.min.js"></script>
	<script src="/chalKag/assets/js/jquery.scrolly.min.js"></script>
	<script src="/chalKag/assets/js/browser.min.js"></script>
	<script src="/chalKag/assets/js/breakpoints.min.js"></script>
	<script src="/chalKag/assets/js/util.js"></script>
	<script src="/chalKag/assets/js/main.js"></script>
	
</body>

</html>