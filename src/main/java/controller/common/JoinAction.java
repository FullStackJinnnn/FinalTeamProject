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
	private static final String SAVE_DIRECTORY =
			"D:\\NSH\\workspace\\chalKag\\src\\main\\webapp\\memberProfileImages";
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
		MultipartRequest multipartRequest = new MultipartRequest(request, SAVE_DIRECTORY, 1024 * 1024 * 10, "UTF-8");
		memberDTO.setId(multipartRequest.getParameter("id"));
		memberDTO.setPw(multipartRequest.getParameter("pw"));
		memberDTO.setName(multipartRequest.getParameter("name"));
		memberDTO.setNickname(multipartRequest.getParameter("nickname"));
		String year = multipartRequest.getParameter("year");
		String month = multipartRequest.getParameter("month");
		String day = multipartRequest.getParameter("day");
		String memberBirth = year + "-" + month + "-" + day; // 형식에 맞게 조합
		memberDTO.setBirth(multipartRequest.getParameter(memberBirth));
		memberDTO.setPh(multipartRequest.getParameter("ph"));
		
		
		// 이미지 업로드를 처리하는 기능
		File uploadedFile = multipartRequest.getFile("file");
		System.out.println("확인: "+uploadedFile);
		if (uploadedFile != null && uploadedFile.exists()) {
		    String originalFilename = uploadedFile.getName();		// 파일명 저장하는 변수
		    String extension = FilenameUtils.getExtension(originalFilename);	// 확장자를 저장하는 변수
		    String newFilename = UUID.randomUUID().toString() + "." + extension;	// 새로운 파일명과 확장자를 저장하는 변수
		    System.out.println("확인: "+SAVE_DIRECTORY);
		    String filePath = SAVE_DIRECTORY + File.separator + newFilename;		// 위 내용을 전부 통합하여 저장하는 변수
		    memberDTO.setProfile(filePath);
		    // 파일 객체 선언 후 파일 위치를 객체에 저장한다.
		    File newFile = new File(filePath);
		    // 파일을 새 위치로 이동시킵니다.
		    uploadedFile.renameTo(newFile);
		}

		boolean flag = memberDAO.insert(memberDTO);

		if (flag) { // 성공시 메인으로 이동

			forward.setPath("/chalKag/main.do");
			forward.setRedirect(true);
			request.setAttribute("msg","회원가입 성공!");

		} else { // 실패시 alert 창으로 이동

			forward.setPath("error/alert.jsp");
			forward.setRedirect(false);
			request.setAttribute("msg", "회원가입 실패! 다시 이용해 주세요");

		}

		return forward;
	}

}

