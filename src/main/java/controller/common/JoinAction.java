package controller.common;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;

import com.oreilly.servlet.MultipartRequest;

import controller.front.Action;
import controller.front.ActionForward;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class JoinAction implements Action {

	// 이미지 파일 저장되는 경로 바꿔야됨
//	private static final String SAVE_DIRECTORY =
//			"D:\\PLZJUN\\workspace_infinityStone\\chalKag\\src\\main\\webapp\\memberProfileImages";

	
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();


		// 값을 가지고 이동해야하기 때문에 redirect .노승현

		request.setCharacterEncoding("UTF-8");

		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
	
		// id는 DTO에 있는 값이라 setAttribute 사용
		// 유저한테 입력받는값(JSP) 의 값을 사용하려면 parameter
		
		// 이미지 업로드 객체 선언(값, 절대경로, 사이즈, 인코딩)
		// form 태그의 multipart/form-data 라면 multipartRequest 로 작성
		
		String uploadDir = this.getClass().getResource("").getPath();

		// .metadata 앞까지 문자열잘라서 이미지가 저장되는 폴더인 memberProfileImages까지의 절대경로 부여
		uploadDir = uploadDir.substring(1, uploadDir.indexOf(".metadata")) + "chalKag/src/main/webapp/memberProfileImages"; // 윈도우 경로 
//		uploadDir = uploadDir.substring(0, uploadDir.indexOf("/WEB-INF")) + "/memberProfileImages"; // 맥북 경로	
		
		// String 타입과 file 의 정보를 저장해야 하기 때문에 multipartRequest 사용					.노승현
		MultipartRequest multipartRequest = new MultipartRequest(request, uploadDir, 1024 * 1024 * 10, "UTF-8");
		memberDTO.setId(multipartRequest.getParameter("id"));
		memberDTO.setPw(multipartRequest.getParameter("pw"));
		memberDTO.setName(multipartRequest.getParameter("name"));
		memberDTO.setNickname(multipartRequest.getParameter("nickname"));
		// db 저장 시 생년월일을 하나의 String 타입으로 받기 때문에 하나의 String 타입으로 변환 시켜줌		.노승현
		String year = multipartRequest.getParameter("year");
		String month = multipartRequest.getParameter("month");
		String day = multipartRequest.getParameter("day");
		String memberBirth = year + "-" + month + "-" + day; // 형식에 맞게 조합
		memberDTO.setBirth(multipartRequest.getParameter(memberBirth));
		memberDTO.setPh(multipartRequest.getParameter("ph"));
		
		
		// 이미지 업로드를 처리하는 기능
		File uploadedFile = multipartRequest.getFile("file");
		System.out.println("확인: "+uploadedFile);
		// 업로드 파일이 존재할 때
		if (uploadedFile != null && uploadedFile.exists()) {
		    String originalFilename = uploadedFile.getName();						// 파일명 저장하는 변수
		    String extension = FilenameUtils.getExtension(originalFilename);		// 확장자를 저장하는 변수
		    String newFilename = UUID.randomUUID().toString() + "." + extension;	// 새로운 파일명과 확장자를 저장하는 변수
		    System.out.println("확인: "+uploadDir);
		    String filePath = uploadDir + File.separator + newFilename;				// 위 내용을 전부 통합하여 저장하는 변수
		    memberDTO.setProfile(filePath);
		    // 파일 객체 선언 후 파일 위치를 객체에 저장한다.
		    File newFile = new File(filePath);
		    // 파일을 새 위치로 이동시킵니다.
		    uploadedFile.renameTo(newFile);
		}
		else {
			// 업로드할 파일이 존재하지 않을 때
		}

		boolean flag = memberDAO.insert(memberDTO);

		if (flag) { // 성공시 메인으로 이동

			forward.setPath("error/alertPage.jsp");
			forward.setRedirect(false);
			request.setAttribute("status","success");
			request.setAttribute("msg","회원가입 성공!");

		} else { // 실패시 alert 창으로 이동

			forward.setPath("error/alert.jsp");
			forward.setRedirect(false);
			request.setAttribute("msg", "회원가입 실패! 다시 이용해 주세요");

		}

		return forward;
	}

}

