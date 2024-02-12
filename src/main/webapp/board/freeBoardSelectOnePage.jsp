<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.board.*,model.review.*,java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone"%>

<!DOCTYPE html>
<html>

<head>
    <!-- Meta information and character encoding -->
    <meta charset="UTF-8">
    <title>자유 게시판 게시글 상세 보기</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />

    <!-- External CSS and JavaScript files -->
    <link rel="stylesheet" href="/chalKag/assets/css/main.css" />
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

    <!-- Internal CSS styles -->
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
    <!-- Include navigation using custom tags -->
    <stone:printNav member='${member}' />

    <!-- Main content wrapper -->
    <div id="main">
        <!-- Post section -->
        <section class="post">
            <header class="major">
                <!-- Board name -->
                <br>
                <h3>free board</h3>
                <div class="col-6 col-12-small" style="margin-top: 45px; text-align: right;">
                    <!-- Report button if logged in and not the author -->
                    <c:if test="${sessionScope.member != null && sessionScope.member != boardData.id}">
                        <button id="report"
                            onClick="location.href='/chalKag/reportWritePage.do?boardNum=${boardData.boardNum}&reportPageURL='+window.location.href"
                            style="border: 1px solid red; color: white; background-color: red;">Report</button>
                    </c:if>
                </div>
            </header>

            <hr style="margin-top: 1px; margin-bottom: 10px;">

            <!-- Post information (title, views, likes, date, writer) -->
            <div>
                <!-- Post title -->
                <pre> Title ${boardData.title} </pre>
                <!-- Post views, likes -->
                <pre style="text-align: right"> Views ${boardData.viewCount} | Recommend ${boardData.recommendCNT} </pre>
                <!-- Post date, writer -->
                <pre> Date ${boardData.boardDate} | Writer ${boardData.nickname} </pre>
            </div>

            <hr style="margin-top: 10px; margin-bottom: 10px; background: Lightgrey; height: 2px; border: 0;">

            <!-- Post content -->
            <div>
                <pre> Content : </pre>
                <!-- Attached image -->
                <img src="bimg/${boardData.image}">이미지 출력 위치 <br>
                
                <pre>${boardData.contents} 내용 출력 위치 </pre>
            </div>

            <!-- Buttons for 'Edit Post', 'Delete Post', 'Write Post', 'Go to Current Category List' -->
            <div class="col-6 col-12-small" style="margin-top: 45px; text-align: right;">
                <!-- Enable 'Edit Post' and 'Delete Post' buttons if logged in -->
                <c:if test="${sessionScope.memberid!=null}">
                    <button type="button" onclick="location.href='/chalKag/freeBoardUpdatePage.do'">Update</button>
                    <button type="button" onclick="location.href='/chalKag/freeBoardDeletePage.do'">Delete</button>
                </c:if>
                <button type="button" onclick="location.href='/chalKag/freeBoardWritePage.do'">Write</button>
                <button type="button" onclick="location.href='/chalKag/freeBoardSelectAllPage.do'">Tolist</button>
            </div>

            <!-- Like Post button -->
            <div class="col-6 col-12-small" style="text-align:center;">
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

            <!-- Comments -->
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
        $(function() {
            $("#recommendBtn").on("click", function() {
                $.ajax({
                    type: "POST",
                    url: "/chalKag/recommendUpAndDown.do",
                    data: {'boardNum': ${boardData.boardNum}},
                    success: function(data) {
                        console.log("추천");
                        history.go(0);
                    },
                    error: function() {
                        console.log("에러발생!");
                    }
                });
            });
        });
    </script>
</body>

</html>
