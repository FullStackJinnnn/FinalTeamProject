<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>알림</title>
</head>
<body>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script>	   
    // 게시글 성공 시 모달창을 통해 성공 멘트 출력 및 경로 설정. 전미지
    console.log("[로그] alertPage.jsp 1 진입" );
    
    if("${status}" == "sellBoardDeleteSuccess"){ // 판매 게시판의 게시글 삭제 성공 시. 전미지
        Swal.fire({
            title: '게시글 삭제',
            text: "${msg}",
            icon: 'success',
            confirmButtonText: '확인'
        }).then((result) => {
            if (result.isConfirmed) {
                location.href = "/chalKag/sellBoardSelectAllPage.do";
            }
        });
    }
    else if ("${status}" == "cameraReviewDeleteSuccess"){ // 카메라 리뷰 게시판의 게시글 삭제 성공 시. 전미지
    	Swal.fire({
            title: '게시글 삭제',
            text: '${msg}',
            icon: 'success',
            confirmButtonText: '확인'
        }).then((result) => {
            if (result.isConfirmed) {
                location.href = "/chalKag/cameraReviewSelectAllPage.do";
            }
        });
    }
    else if ("${status}" == "freeBoardDeleteSuccess"){ // 자유 게시판의 게시글 삭제 성공 시. 전미지
    	Swal.fire({
            title: '게시글 삭제',
            text: '${msg}',
            icon: 'success',
            confirmButtonText: '확인'
        }).then((result) => {
            if (result.isConfirmed) {
                location.href = "/chalKag/freeBoardSelectAllPage.do";
            }
        });	
    }  
    else if ("${status}" == "DeleteFail"){ // 게시글 삭제 실패 시. 전미지
    	Swal.fire({
            title: '게시글 삭제',
            text: '${msg}',
            icon: 'warning',
            confirmButtonText: '확인'
        }).then((result) => {
            if (result.isConfirmed) {
            	window.history.back();
            }
        });	
	}
    else if ("${status}" == "reportSuccess"){ // 회원 신고 성공 시. 정석진
    	Swal.fire({
            title: '회원 신고',
            text: '신고가 접수 되었습니다.',
            icon: 'success',
            confirmButtonText: '확인'
        }).then((result) => {
            if (result.isConfirmed) {
                location.href = "${path}";
            }
        });	
	}
    else if ("${status}" == "deleteAccountSuccess"){ // 회원 탈퇴 성공 시. 정석진
    	Swal.fire({
            title: '회원 탈퇴',
            text: '탈퇴 되었습니다.',
            icon: 'success',
            confirmButtonText: '확인'
        }).then((result) => {
            if (result.isConfirmed) {
                location.href = "/chalKag/main.do";
            }
        });	
	} 
    else if("${status}" == "naverLoginSuccess"){	// 네이버 로그인 성공 시. 김도연 
    	Swal.fire({
            title: '${msg}',
            text: '로그인에 성공했습니다.',
            icon: 'success',
            confirmButtonText: '확인'
        }).then((result) => {
            if (result.isConfirmed) {
                location.href = "/chalKag/main.do";
            }
        });	
    }  else if("${status}" == "naverLoginFail"){	// 네이버 로그인 실패 시. 김도연 
    	Swal.fire({
            title: '${msg}',
            text: '탈퇴한 회원입니다.',
            icon: 'warning',
            confirmButtonText: '확인'
        }).then((result) => {
            if (result.isConfirmed) {
            	history.go(-1);
            }
        });	
    } 
    else if("${status}" == "naverJoinSuccess"){	// 네이버 회원가입 성공 시. 김도연 
    	Swal.fire({
            title: '${msg}',
            text: '회원가입에 성공했습니다.',
            icon: 'success',
            confirmButtonText: '확인'
        }).then((result) => {
            if (result.isConfirmed) {
                location.href = "/chalKag/main.do";
            }
        });	
    }  else if("${status}" == "naverJoinFail"){	// 네이버 회원가입 실패 시. 김도연 
    	Swal.fire({
            title: '${msg}',
            text: '회원가입에 실패했습니다. 다시 해주세요.',
            icon: 'warning',
            confirmButtonText: '확인'
        }).then((result) => {
            if (result.isConfirmed) {
            	history.go(-1);
            }
        });	
    } 
  
	</script>
</body>
</html>