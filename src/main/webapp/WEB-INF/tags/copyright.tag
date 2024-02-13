<%@ tag language="java" pageEncoding="UTF-8"%>

<!-- 저작권 및 회사 정보를 담은 태그 -->

<style>
a {
	border-bottom: none;
}

.info_policy {
	font-family: "Source Sans Pro", Helvetica, sans-serif;
	font-weight: bold;
	font-size: 17px;
	text-align: center;
	margin: 60px auto 0;
}

.info_policy > a {
	color: #ffffff40;
	width: 1px;
	height: 11px;
	margin: 0px;
}

.info_policy > a:first-child:before {
	display: none;
}

.info_policy > a:before {
	content: "";
	display: inline-block;
	width: 1px;
	height: 11px;
	margin: 0 10px;
	background: #ffffff40;
}

.companyAddress {
	font-family: "Source Sans Pro", Helvetica, sans-serif;
	font-weight: normal;
	font-size: 17px;
	text-align: center;
	margin: 15px auto 0 auto;
	padding-top: 1px;
}

.companyAddress>span {
	color: #ffffff40;
	width: 1px;
	height: 11px;
	margin: 0px;
}

.companyAddress>span:first-child:before {
	display: none;
}

.companyAddress>span:before {
	content: "";
	display: inline-block;
	width: 1px;
	height: 11px;
	margin: 0 10px;
	background: #ffffff40;
}

#copyright {
	font-family: "Source Sans Pro ", Helvetica, sans-serif;
	font-weight: normal;
	font-size: 17px;
	border-left: none;
	text-align: center;
	color: #ffffff40;
	margin: 0px auto 0;
	padding-top: 15px;
}

#copyright ul li {
    border-left: none;
    display: inline;
    line-height: 1.2;
    margin: 0 0 0 10px;
    padding: 0 0 0 0px;
}

.icon {
	text-decoration: none;
	border-bottom: none;
	position: relative;
	margin: 0 0px;
	padding: 0 0px;
	color: #ffffff40;
}

ul.icons.alt li .icon:before {
    box-shadow: inset 0 0 0 1.5px #ffffff40;
}

.icon>.label {
	margin: 0 0px;
	padding: 0 0px;
	display: none;
	color: #ffffff40;
}

ul.icons {
	margin: 0 0px;
	padding: 0 0px;
	cursor: default;
	list-style: none;
	color: #ffffff40;
}

ul.icons>li {
	font-size: 1.25rem;
	display: none;
	margin: 0 0px;
	padding: 0 0px;
	vertical-align: middle;
	color: #ffffff40;	
}
</style>

	<!-- 저작권 및 회사 정보를 담은 푸터 섹션 -->
	<footer id="copyright" style="padding-top: 0;">
	 	 <!-- 회사 정보 링크들이 담긴 div -->
		<div class="info_policy">
			<a href="/chalKag/main.do">회사소개</a> <a href="/chalKag/main.do">광고안내</a>
			<a href="/chalKag/main.do">이용약관</a> <a href="/chalKag/main.do">개인정보처리방침</a>
			<!-- 회사 주소 및 연락처 정보가 담긴 address 태그 -->
			<address class="companyAddress" style="font-weight: normal;">
				<span class="address">Address : 146, Teheran-ro, Gangnam-gu, Seoul, Republic of Korea</span>
				<span class="address">Tel : 010-3975-7635</span> <span class="address">Mail : plzjun0731@naver.com</span>
			</address>
			<!-- 저작권 정보가 담긴 문구 -->
			<p id="copyright">Copyright ⓒ 2023 - 2024 INFINITY STONE . All rights reserved.<p>
		</div>
	</footer>