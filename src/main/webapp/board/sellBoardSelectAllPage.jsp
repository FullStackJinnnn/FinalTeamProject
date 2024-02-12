<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.board.*,java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone"%>

<!DOCTYPE html>
<html>
<head>
    <!-- 문자 인코딩 및 메타 정보 설정 -->
    <meta charset="UTF-8">
    <title>중거거래 게시판</title>
    <meta charset="utf-8" />
    <meta name="viewport"
        content="width=device-width, initial-scale=1, user-scalable=no" />
    <!-- 외부 CSS 파일 링크 -->
    <link rel="stylesheet" href="/chalKag/assets/css/main.css" />

    <style>
        #filterRemoteContainer {
            position: fixed;
            top: 0;
            left: 0;
            width: 200px;
            background-color: #f1f1f1;
            padding: 10px;
            margin-top: 20px;
        }

        th.sortable {
            cursor: pointer;
        }

        th.sortable.highlight {
            color: blue;
            background-color: #F0F0F0;
        }

        #priceAscButton.highlight, 
        #priceDescButton.highlight {
            background-color: #F0F0F0;
            color: blue;
            border: 1px solid blue;
        }

        .page.active {
            background: #7abbf0;
            color: #fff;
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
            <br>
            <p>
                Discover the world through lenses! 📷 Welcome to our Camera Review
                Board, <br> where shutterbugs unite to share insights on the
                latest cameras. <br> Dive into detailed reviews, expert
                opinions, and community discussions.
            </p>
        </header>

        <div id="filterRemoteContainer">
            <h4>상품 필터링</h4>
            <!-- 가격 -->
            <label for="minPrice">최소 가격:</label> 
            <input type="range" id="minPrice" name="minPrice" min="0" max="10000">
            <output for="minPrice" id="minPriceOutput">0</output>
            <br> 
            <label for="maxPrice">최대 가격:</label> 
            <input type="range" id="maxPrice" name="maxPrice" min="0" max="10000">
            <output for="maxPrice" id="maxPriceOutput">0</output>
            <br>
            <!-- 제조사 -->
            <label>제조사:</label> 
            <input type="checkbox" id="company1" name="company" value="캐논"> 
            <label for="company1">캐논</label>
            <input type="checkbox" id="company2" name="company" value="소니">
            <label for="company2">소니</label> 
            <input type="checkbox" id="company3" name="company" value="니콘"> 
            <label for="company3">니콘</label>
            <br>
            <!-- 카메라 종류 -->
            <label>카메라 종류:</label> 
            <input type="checkbox" id="productcategory1" name="productcategory" value="DSLR"> 
            <label for="productcategory1">DSLR</label> 
            <input type="checkbox" id="productcategory2" name="productcategory" value="미러리스"> 
            <label for="productcategory2">미러리스</label> 
            <input type="checkbox" id="productcategory3" name="productcategory" value="컴팩트"> 
            <label for="productcategory3">컴팩트</label> 
            <br>
            <!-- 게시글 상태 -->
            <label>게시글 상태:</label> 
            <input type="checkbox" id="selling" name="state" value="판매중"> 
            <label for="selling">판매중</label> 
            <input type="checkbox" id="sold" name="state" value="판매완료"> 
            <label for="sold">판매완료</label>
        </div>

        <!-- 검색 폼 -->
        <div>
            <select id="searchField" name="searchField" style="width: 40%; display: inline-block;">
                <option value="title">글 제목</option>
                <option value="contents">글 내용</option>
                <option value="writer">작성자</option>
                <option value="titleAndContents">글 제목 + 내용</option>
            </select> 
            <input type="text" id="searchInput" name="search" style="margin-left: 10px; width: 40%; display: inline-block;" placeholder="검색어를 입력해 주세요"> 
            <input type="button" value="검색" id="searchButton" style="margin-left: 10px; width: 15%;">
        </div>

        <!-- '게시글 작성', '메인으로 돌아가기' 버튼 생성 -->
        <div class="col-6 col-12-small" style="margin-right: 25px; text-align: right">
            <button type="button" onclick="location.href='/chalKag/freeBoardWritePage.do'">Write</button>
            <button type="button" style="margin-left: 10px;" onclick="location.href='/chalKag/main.do'">MainPage</button>
        </div>

        <div id="sortingButtons" style="margin-bottom: 10px;">
            <input type="button" value="Price ↑" id="priceAscButton" style="font-size: 16px; padding: 5px 10px;"> 
            <input type="button" value="Price ↓" id="priceDescButton" style="font-size: 16px; padding: 5px 10px;">
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
                        <th width="10%" class="sortable" data-column="recommendCNT">recommendCNT</th>
                        <th width="10%" class="sortable" data-column="views">views</th>
                        <th width="10%" class="notSortable" data-column="state">state</th>
                    </tr>
                </thead>
                <!-- JSTL forEach를 사용하여 카메라 리뷰 데이터 반복 처리하여 출력-->
                <tbody>
                    <!-- pagination 으로 동적으로 채워질 테이블 공간 -->
                </tbody>
            </table>
        </div>

        <div id="dataContainer" data-jsonBoardDatas='${jsonBoardDatas}' data-category='${category}'></div>

        <!-- 페이징을 포함한 푸터 섹션 -->
        <footer>
            <div id="paginationContainer" class="pagination">
                <!-- 페이징 버튼이 동적으로 생성될 부분 -->
            </div>
        </footer>
    </div>

    <!-- 커스텀 태그를 사용하여 저작권 정보 포함 -->
    <stone:copyright />

    <!-- JavaScript 파일 링크 -->
    <script src="/chalKag/assets/js/jquery.min.js"></script>
    <script src="/chalKag/assets/js/jquery.scrollex.min.js"></script>
    <script src="/chalKag/assets/js/jquery.scrolly.min.js"></script>
    <script src="/chalKag/assets/js/browser.min.js"></script>
    <script src="/chalKag/assets/js/breakpoints.min.js"></script>
    <script src="/chalKag/assets/js/util.js"></script>
    <script src="/chalKag/assets/js/main.js"></script>
    <script src="/chalKag/assets/js/pagination.js"></script>
    <script src="/chalKag/assets/js/filterSearch.js"></script>
</body>
</html>
