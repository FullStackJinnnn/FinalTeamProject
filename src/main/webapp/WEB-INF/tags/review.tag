<%@ tag language="java" pageEncoding="UTF-8" import="model.review.*, java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="stone"%>

<% // session.setAttribute("member", "kim");%>
<section>
			<form method="post" action="/chalKag/reviewWrite.do">
				<div class="fields">
					<div class="field">
						<label for="message">Write Contents</label>
						<textarea name="reviewContents" id="reviewContents" rows="3"></textarea>
					</div>
				</div>
				<ul class="actions">
					<li><input type="submit" value="leaving a comment" /></li>
				</ul>
				<br>
			</form>
		</section>

		<hr />

		<c:if test="${fn:length(reviews) <= 0}">
			<h4>댓글이 없습니다. 가장 먼저 댓글을 남겨보세요!</h4>
		</c:if>

		<c:if test="${fn:length(reviews) > 0 }">
			<div class="table-wrapper">
				<table class="alt">
					<thead>
						<tr>
							<th>reviews : ${fn:length(reviews)}</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="review" items="${reviews}">
						

							<tr>
								<!-- 본인이 쓴 댓글이면 수정/삭제 표시 -->

								<c:if test="${review.id == member}">
									<td>
										<div>${review.writer}</div>
										<div id="reviewContents_${review.reviewNum}">
											${review.reviewContents}</div>
										<div style="text-align: right;">
											<a class="updateContents" href="javascript:void(0);"
												onclick="toggleEdit(${review.reviewNum}, '${review.reviewContents}', event)">수정</a>
												
											<!-- GET 으로 댓글 삭제 -->
											<a href="/chalKag/reviewDelete.do?reviewNum=${review.reviewNum}">삭제</a>
											<div>${review.reviewDate}</div>
										</div>
									</td>
								</c:if>

								<!-- 본인이 쓴 댓글이 아니라면 내용만 표시 -->
								<c:if test="${review.id!=member}">

									<td><div>${review.writer}</div>
										<div>${review.reviewContents}</div>
										<div style="text-align: right;">${review.reviewDate}</div></td>
								</c:if>
							</tr>

						</c:forEach>

					</tbody>

				</table>
			</div>
		</c:if>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>

	
	// 댓글 수정 비동기처리 작성자 | 김성민
    function toggleEdit(reviewNum, reviewContents, event) {
    	
        // console.log('toggleEdit 함수 호출됨');
        // console.log('reviewNum:', reviewNum);
        // console.log('reviewContents:', reviewContents);
        // console.log('event.target:', event.target);

        // 클릭된 요소
        var clickedElement = $(event.target);

        // 수정을 클릭한 경우
        if (clickedElement.hasClass('updateContents')) {
            // '수정'인 경우
            if (clickedElement.text() === '수정') {
                // '수정'을 '저장'으로 변경
                clickedElement.text('저장');
                
                // 해당 댓글Div에 있는 내용을 divContent에 저장
                var divContent = $('#reviewContents_' + reviewNum).text();
                
                // 텍스트 영역 엘리먼트 생성
                var textarea = $('<textarea>').val(divContent.trim()).attr('rows', 5);

                // div 엘리먼트의 내용을 텍스트 영역으로 완전히 대체
                var divElement = $('#reviewContents_' + reviewNum);

                if (divElement.length) {
                    // 선택된 div 요소를 비우고, 그 안에 새로운 textarea 요소를 추가
                    divElement.empty().append(textarea);
                }
            }
            // '수정'이 아닌 경우
            else {
            	
                // 텍스트 영역에서 업데이트된 내용 가져오기
                var updatedContents = $('#reviewContents_' + reviewNum + ' textarea').val();
                
                // 유효성 검사: 공백이나 내용이 없는 경우 저장하지 않음
                if (updatedContents.trim() === '') {
                    alert('내용을 입력하세요.'); // 또는 다른 사용자 피드백 방식으로 변경
                    return; // 저장 중단
                }

                // AJAX 요청 수행
                $.ajax({
                    url: "reviewUpdateAction.do", // 실제 서버 엔드포인트로 변경
                    type: "POST", // 요청 방식 (GET, POST 등)
                    data: {
                    	// update를 하기 위한 요소들을 보냄
                        'reviewNum': reviewNum,
                        'updatedContents': updatedContents
                    },
                    dataType:'text',
                    success: function(response) {
                    	// response 확인
                        // console.log('서버 응답:' + response);

                        // 성공적으로 업데이트된 경우
                        // div 엘리먼트의 내용을 업데이트된 내용으로 교체
                        var reviewContentsDiv = $('#reviewContents_' + reviewNum);
                        if (reviewContentsDiv.length) {
                            reviewContentsDiv.text(response);
                            // 현재 db에 저장된 데이터
                            // 서버에서 응답을 줄 때 인자인 Response에 태워서 보내줘야함
                        }
                        // '저장'을 '수정'으로 변경
                       
                        clickedElement.text('수정');
                        
                       
                        
                   
                    },
                    error: function(error) {
                        console.error('에러 발생:', error);
                        // 에러 처리 로직 추가
                    }
                });

                // 이벤트 전파 막기
                event.stopPropagation();
            }
        }
    }
</script>