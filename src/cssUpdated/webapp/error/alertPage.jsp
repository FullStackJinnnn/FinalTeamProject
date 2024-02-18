<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>알림</title>
</head>
<body>
	<script>
	
    /* alert("${msg}"); */
    console.log(1);
    // 로그인 성공인 경우에 대한 처리
    // 페이지 이동 시 유효성 검사를 여기서 처리 하기로 함			.노승현
    /* if ('${msg}' === "로그인 성공") {
    	alert('${msg}');
        location.href = "/chalKag/main.do";
    } else if('${msg}' ==="이미 탈퇴한 회원의 아이디입니다.") {
    	alert('${msg}');
        history.go(-1);
    }
    else if('${msg}'==="로그인 실패"){
    	alert('${msg}');
        history.go(-1);
    }
    else if('${msg}'==="회원가입 성공!"){
    	alert('${msg}');
    	location.href="/chalKag/main.do";
    }
    else if('${msg}'==="회원가입 실패! 다시 이용해 주세요"){
    	alert('${msg}');
    	history.go(-1);
    } */
    
    if("${status}" == "success"){	// 회원가입 성공 했을 때
    	alert("${msg}")
    	location.href = 'common/mainPage.jsp';
    }else {							// 회원가입에 실패하면 원래 있던 화면으로
    	alert("${msg}")
    	history.go(-1);
    }
	</script>
</body>
</html>