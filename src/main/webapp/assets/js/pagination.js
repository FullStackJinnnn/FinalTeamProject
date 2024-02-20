/**
 * 일반검색 & 필터검색 JS파일 
 */
var jsonFilteredBoardDatas;
var loadReviewData;
const dataContainer = document.getElementById('dataContainer');
const jsonBoardDatas = JSON.parse(dataContainer.getAttribute('data-jsonBoardDatas'));
const id = dataContainer.getAttribute("data-id");
var index;

console.log('[로그]id= ' + id);
$(document).ready(function() {

   // 페이징 버튼 클릭 이벤트
   $(document).on("click", "#paginationContainer .page", function(event) {
      event.preventDefault();
      var page = $(this).data("page");
      loadReviewData(page);
      //console.log("[로그] page :" page);
   });

   loadReviewData = function(page) {
      var dataToSend = {
         'jsonBoardDatas': JSON.stringify(jsonBoardDatas),
         'page': page,
      };

      if (isFiltered) {
         console.log(jsonFilteredBoardDatas);
         dataToSend.jsonFilteredBoardDatas = JSON.stringify(jsonFilteredBoardDatas);
      }

      //console.log("[로그] 총 데이터 :"dataToSend);

      $.ajax({
         url: "pagination.do",
         method: "GET",
         data: dataToSend,
         dataType: 'json',
         success: function(jsonPaginationDatas) {
            // 성공 시 처리
            
            displayReviewData(jsonPaginationDatas);
            displayPagination(jsonPaginationDatas);
            //alert("데이터가 성공적으로 로드되었습니다!"); // 성공 시 알림
         },
         error: function() {
            // 에러 처리
            console.error("데이터 로딩 중 에러 발생");
            //alert("데이터 로딩 중 에러 발생!"); // 에러 시 알림
         }
      });
   }

   function displayReviewData(jsonPaginationDatas) {
      console.log("[로그]" + jsonPaginationDatas);
      var tbody = document.querySelector('.alt tbody');
      var thead = document.querySelector('.alt thead');
      var thCnt = thead.getElementsByTagName('th');
      var maxColspan = thCnt.length;
      tbody.innerHTML = '';
      // 결과가 없을 경우 메시지 출력
      if (jsonPaginationDatas.data.length === 0) {
         tbody.innerHTML = '<tr><td colspan="' + maxColspan + '" align="center">등록된 글이 없습니다. 새 글을 작성해주세요.</td></tr>';
      } else {
        // 결과가 있을 경우 테이블에 데이터 추가
        jsonPaginationDatas.data.forEach(function(boardData) {
            var row = document.createElement('tr');
            // boardData.category에 따라 다른 출력을 하도록 조건문 추가
            if (id === boardData.id) {
               console.log('[로그] myBoard & memberBoard');
               row.innerHTML =
                  '<td>' + boardData.rownum + '</td>' +
                  '<td>' + boardData.title + '</td>' +
                  '<td>' + boardData.nickname + '</td>' +
                  '<td>' + boardData.boardDate + '</td>' +
                  '<td>' + boardData.recommendCNT + '</td>' +
                  '<td>' + boardData.viewCount + '</td>';
            } else if (boardData.category === '판매게시판') {
               row.innerHTML =
                  '<td>' + boardData.rownum + '</td>' +
                  '<td><a href="/chalKag/sellBoardSelectOnePage.do?boardNum=' + boardData.boardNum + '">' + boardData.title + '</a></td>' +
                  '<td>' + numberWithCommas(boardData.price) + '원</td>' +
                  '<td>' + boardData.state + '</td>'+
                  '<td><a href="/chalKag/memberPage.do?nickname=' + boardData.nickname + '">' + boardData.nickname + '</a></td>' +
                  '<td>' + boardData.boardDate + '</td>' +
                  '<td>' + boardData.viewCount + '</td>';
                  console.log('[로그]' + boardData.state);
            } else if (boardData.category === '리뷰게시판') {
               row.innerHTML =
                  '<td>' + boardData.rownum + '</td>' +
                  '<td><a href="/chalKag/cameraReviewSelectOnePage.do?boardNum=' + boardData.boardNum + '">' + boardData.title + '</a></td>' +
                  '<td>' + numberWithCommas(boardData.price) + '원</td>' +
                  '<td><a href="/chalKag/memberPage.do?nickname=' + boardData.nickname + '">' + boardData.nickname + '</a></td>' +
                  '<td>' + boardData.boardDate + '</td>' +
                  '<td>' + boardData.recommendCNT + '</td>' +
                  '<td>' + boardData.viewCount + '</td>';
            } else {
               row.innerHTML =
                  '<td>' + boardData.rownum + '</td>' +
                  '<td><a href="/chalKag/freeBoardSelectOnePage.do?boardNum=' + boardData.boardNum + '">' + boardData.title + '</a></td>' +
                  '<td><a href="/chalKag/memberPage.do?nickname=' + boardData.nickname + '">' + boardData.nickname + '</a></td>' +
                  '<td>' + boardData.boardDate + '</td>' +
                  '<td>' + boardData.recommendCNT + '</td>' +
                  '<td>' + boardData.viewCount + '</td>';
            }
            tbody.appendChild(row);
         });
      }
   }
   // 가격을 3자리마다 컴마로 구분하는 함수
function numberWithCommas(price) {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

   function displayPagination(jsonPaginationDatas) {
      // 페이징 버튼 등을 표시하는 로직
      var paginationContainer = $("#paginationContainer");
      paginationContainer.empty();

      // 한 페이지 그룹에 표시할 페이지 수
      var pageSize = 10;

      // 현재 페이지 그룹
      var currentGroup = Math.floor((jsonPaginationDatas.currentPage - 1) / pageSize);

      // 시작 페이지와 끝 페이지 설정
      var startPage = currentGroup * pageSize + 1;
      var endPage = Math.min((currentGroup + 1) * pageSize, jsonPaginationDatas.totalPages);

      // 이전 페이지 그룹으로 이동하는 버튼 추가
      if (startPage > 1) {
          var prevGroupPage = startPage - 1;
          var prevGroupLink = "<a href='#' class='page' data-page='" + prevGroupPage + "'>&laquo; PREV</a>";
          paginationContainer.append(prevGroupLink);
      }

      // 페이지 버튼 추가
      for (var i = startPage; i <= endPage; i++) {
         var pageLinkClass = (i === jsonPaginationDatas.currentPage) ? "page active" : "page";
         var pageLink = "<a href='#' class='" + pageLinkClass + "' data-page='" + i + "'>" + i + "</a>";
         paginationContainer.append(pageLink);
      }

      // 다음 페이지 그룹으로 이동하는 버튼 추가
      if (endPage < jsonPaginationDatas.totalPages) {
          var nextGroupPage = endPage + 1;
          var nextGroupLink = "<a href='#' class='page' data-page='" + nextGroupPage + "'>NEXT &raquo;</a>";
          paginationContainer.append(nextGroupLink);
      }
   }
   loadReviewData(1);
});