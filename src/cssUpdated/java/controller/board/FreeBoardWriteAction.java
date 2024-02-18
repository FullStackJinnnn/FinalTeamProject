package controller.board;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;

import com.oreilly.servlet.MultipartRequest;

import controller.front.Action;
import controller.front.ActionForward;
import model.board.BoardDAO;
import model.board.BoardDTO;

public class FreeBoardWriteAction implements Action {

	// 무조건 절대 경로 사용
	/*
	 * private static final String SAVE_DIRECTORY =
	 * "D:\\fintec_kyd\\infinityStone\\chalKag\\chalKag\\src\\main\\webapp\\bimg";
	 */

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// String uploadDir =
		// "D:\\PLZJUN\\workspace_infinityStone\\chalKag\\src\\main\\webapp\\bimg";
		String uploadDir = this.getClass().getResource("").getPath();

		// .metadata 앞까지 문자열잘라서 이미지가 저장되는 폴더인 memberProfileImages까지의 절대경로 부여
		uploadDir = uploadDir.substring(1, uploadDir.indexOf(".metadata")) + "chalKag/src/main/webapp/bimg";
		/*
		 * uploadDir = uploadDir.substring(1, uploadDir.indexOf(".metadata")) +
		 * "/chalKag/src/main/webapp/bimg";
		 */

		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();

		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();

		boardDTO.setId((String) session.getAttribute("member"));
		boardDTO.setCategory("자유게시판");

		// 이미지 업로드 객체 선언(값, 절대경로, 사이즈, 인코딩)
		MultipartRequest multipartRequest = new MultipartRequest(request, uploadDir, 1024 * 1024 * 10, "UTF-8");

		boardDTO.setTitle(multipartRequest.getParameter("title"));
		boardDTO.setContents(multipartRequest.getParameter("contents"));

		// 이미지 업로드를 처리하는 기능
		File uploadedFile = multipartRequest.getFile("file");
		System.out.println("확인: " + uploadedFile);
		if (uploadedFile != null && uploadedFile.exists()) {
			String originalFilename = uploadedFile.getName(); // 파일명 저장하는 변수
			String extension = FilenameUtils.getExtension(originalFilename); // 확장자를 저장하는 변수
			String newFilename = UUID.randomUUID().toString() + "." + extension; // 새로운 파일명과 확장자를 저장하는 변수
			/// String uploadDir = request.getServletContext().getRealPath(SAVE_DIRECTORY);
			/// // 업로드 되는 폴더 주소를 저장하는 변수
			// getServletContext()은 서버 모듈의 서버패스를 불러온다.
			/*
			 * String uploadDir=SAVE_DIRECTORY; System.out.println("확인: "+SAVE_DIRECTORY);
			 */
			String filePath = uploadDir + File.separator + newFilename; // 위 내용을 전부 통합하여 저장하는 변수
			boardDTO.setImage(newFilename);
			// 파일 객체 선언 후 파일 위치를 객체에 저장한다.
			File newFile = new File(filePath);
			// 파일을 새 위치로 이동시킵니다.
			uploadedFile.renameTo(newFile);
		}

		boolean flag = boardDAO.insert(boardDTO);

		if (flag) { // 성공시 메인으로 이동

			forward.setPath("/chalKag/freeBoardSelectAllPage.do");
			forward.setRedirect(true);

		} else { // 실패시 alert 창으로 이동

			forward.setPath("error/alert.jsp");
			forward.setRedirect(false);
			request.setAttribute("msg", "게시글 등록 실패! 다시 이용해 주세요");

		}

		return forward;

	}

}
