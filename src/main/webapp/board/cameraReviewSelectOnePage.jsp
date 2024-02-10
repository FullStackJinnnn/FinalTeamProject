<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
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
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />

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
                <h3>camera review board</h3>

                <div class="col-6 col-12-small" style="margin-top: 45px; text-align: right;">
                    <c:if test="${sessionScope.member != null && sessionScope.member != boardData.id}">
                        <button id="report"
                            onClick="location.href='/chalKag/reportWritePage.do?boardNum=${boardData.boardNum}&reportPageURL='+window.location.href"
                            style="border: 1px solid red; color: white; background-color: red;">Report</button>
                    </c:if>
                </div>

            </header>

            <hr style="margin-top: 1px; margin-bottom: 10px;">

            <!-- 게시글 정보 (제목, 조회수, 좋아요수, 작성일, 작성자 -->
            <div>
                <!-- 게시글 제목 -->
                <pre> Title ${boardData.title} </pre>
                <!-- 제시글 조회수, 좋아요 -->
                <pre style="text-align: right"> Views ${boardData.viewCount} | Recommend ${boardData.recommendCNT}
                </pre>
                <!-- 게시글 작성일, 작성자 -->
                <pre> Date ${boardData.boardDate} | Writer ${boardData.nickname} </pre>
            </div>

            <hr style="margin-top: 10px; margin-bottom: 10px; background: Lightgrey; height: 2px; border: 0;">

            <!-- 리뷰할 카메라 상품 정보 -->
            <div>
                <!-- 상품 가격 -->
                <pre> Price : ${boardData.price} </pre>
                <!-- 상품 종류 -->
                <pre> ProductCategory : ${boardData.productCategory} </pre>
                <!-- 상품 이름 -->
                <pre> ProductName : ${boardData.productName} </pre>
                <!-- 상품 제조사 -->
                <pre> Company : ${boardData.company} </pre>
            </div>

            <!-- 게시글 내용 -->
            <div>
                <pre> Content : </pre>
                <!-- 첨부 이미지 -->
                <img src="bimg/${boardData.image}">이미지 출력 위치 <br>
                <pre>${boardData.contents}</pre>
            </div>

            <!-- '글 수정', '글 삭제', '글 작성', '현재 카테고리 목록으로' 버튼 생성 -->
            <div class="col-6 col-12-small" style="margin-top: 45px; text-align: right;">
                <!-- 로그인 상태인 경우 '글 수정', '글 삭제' 버튼 활성화 -->
                <c:if test="${sessionScope.memberid!=null}">
                    <button type="button"
                        onclick="location.href='/chalKag/cameraReviewUpdatePage.do'">Update</button>
                    <button type="button"
                        onclick="location.href='/chalKag/cameraReviewDeletePage.do'">Delete</button>
                </c:if>
                <!-- '글 작성', '현재 카테고리 목록으로' 버튼 생성 -->
                <button type="button"
                    onclick="location.href='/chalKag/cameraReviewWritePage.do'">Write</button>
                <button type="button"
                    onclick="location.href='/chalKag/cameraReviewSelectAllPage.do'">Tolist</button>
            </div>

            <!-- 게시글 좋아요 -->
            <div class="col-6 col-12-small" style="text-align: center;">
                <br>
                <c:if test="${member != null}">
                    <c:if test="${member == recommendData.id}">
                        <input class="button primary" type="button" id="recommendBtn" value="좋아요" />
                    </c:if>
                    <c:if test="${member != recommendData.id}">
                        <input type="button" id="recommendBtn" value="좋아요" />
                    </c:if>
                </c:if>
                <c:if test="${member == null}">
                    <input type="button" id="recommendBtn" value="좋아요" disabled />
                </c:if>
            </div>

            <hr>

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
    <script type="text/javascript">
        $(function () {
            $("#recommendBtn").on("click", function () {
                $.ajax({
                    type: "POST", // 서버 요청 방식
                    url: "/chalKag/recommendUpAndDown.do", // 서버 url
                    data: {
                        'boardNum': ${boardData.boardNum}
                    },
                    success: function (data) {
                        console.log("추천");
                        history.go(0);
                    },
                    error: function () {
                        console.log("에러발생!");
                    }
                });
            });
        });
    </script>
</body>

</html>
