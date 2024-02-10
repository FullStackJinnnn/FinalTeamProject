package controller.async;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.member.MemberDAO;
import model.member.MemberDTO;

/**
 * Servlet implementation class ProfileUploadasync
 */
@WebServlet("/profileUpload.do")
public class ProfileUploadAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileUploadAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();

		// UploadAction클래스 위치의 경로를 찾아서 uploadDir에 대입
		// 확인된 위치 :
		// /C:/eclipse/FinalTeamProject/workspace_infinityStone/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/chalKag/WEB-INF/classes/controller/common/
		String uploadDir = this.getClass().getResource("").getPath();

		// .metadata 앞까지 문자열잘라서 이미지가 저장되는 폴더인 memberProfileImages까지의 절대경로 부여
		uploadDir = uploadDir.substring(1, uploadDir.indexOf(".metadata"))
				+ "chalKag/src/main/webapp/memberProfileImages";

		// 총 100M 까지 저장 가능하게 함
		int maxSize = 1024 * 1024 * 100;
		String encoding = "UTF-8";

		// memberNum을 가져와서 사용자가 저장한 프로필 이미지 명으로 사용하려고 selectOne 사용
		HttpSession session = request.getSession();
		memberDTO.setId((String) session.getAttribute("member"));
		memberDTO.setSearchCondition("내정보출력");
		memberDTO = memberDAO.selectOne(memberDTO);

		if (memberDTO != null) {
			// 사용자가 전송한 파일 정보를 토대로 업로드 장소에 파일 업로드 수행할 수 있게 함
			// 사용자가 저장한 파일 명을 원하는대로 바꿔서 원하는 폴더에 저장

			// MultipartRequest 객체를 생성하여 파일 업로드 처리를 위한 정보를 설정
			// request: 현재의 HttpServletRequest 객체
			// uploadDir: 파일을 업로드할 디렉토리 경로
			// maxSize: 업로드할 파일의 최대 크기
			// encoding: 인코딩 방식
			// new CustomFileRenamePolicy(Integer.toString(memberDTO.getMemberNum())):
			// 파일 이름 중복 시 사용할 사용자 정의 파일 리네임 정책 객체를 생성하고 전달 103번라인 참조
			// 현재 이름을 무조건 현재로그인된 id로 변경하고 저장
			String id = memberDTO.getId();
			int dotIndex = id.indexOf(".");
			String resultId = id.substring(0, dotIndex) + id.substring(dotIndex + 1);

			MultipartRequest multipartRequest = new MultipartRequest(request, uploadDir, maxSize, encoding,
					new CustomFileRenamePolicy(resultId));

			// memberNum으로 재설정한 이름 newFileName에 대입
			String newFileName = multipartRequest.getFilesystemName("file");

			// 유저가 프로필을 등록하지 않고 회원가입을 했을 때 아래 코드를 실행 안해도 되므로 예외처리
			if (memberDTO.getProfile() != null) {
				// 유저가 "티모.JPG"를 저장하고 "티모.PNG"를 저장할때 rename에 의해서
				// "1.JPG"저장 후 "1.PNG"를 저장하는 과정에서 확장자를 제외한 "1"만 비교해서
				// 같은게 있다면 기존 파일 삭제 후 새로운 파일 폴더에 저장하는 과정

				// 기존 파일이 저장되어있는 절대경로 확인
				String existingFilePath = uploadDir + File.separator + memberDTO.getProfile();

				// 기존 파일이 저장되어있는 절대 경로를 가지고있는 exsistingFile 객체 생성
				File existingFile = new File(existingFilePath);

				// 새로운 파일이 저장된 절대경로 확인
				String newFilePath = uploadDir + File.separator + newFileName;
				// 새로운 파일이 저장되어있는 절대 경로를 가지고있는 newFile 객체생성
				File newFile = new File(newFilePath);

				String existingFileName = memberDTO.getProfile();
				// 기존 파일이 존재하고 기존파일의 확장자를 포함한 이름이 새로운파일의 확장자를 포함한 이름과 같으면
				if (existingFile.exists() && existingFileName.equals(newFileName)) {
					try {
						// newfile을 existingFile에 덮어씌움
						Files.copy(newFile.toPath(), existingFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
						e.printStackTrace();// 덮어씌움 실패하면 에러처리 해야함
					}
					// 확장자를 포함한 기존파일과 새로운파일의 이름이 다르고 "default_image.jpg" 가 아니라면 파일삭제
				} else if (!existingFileName.equals(newFileName)) {
					if (!existingFileName.equals("default.jpg")) {
						existingFile.delete();
					}
				}

			}
			int outFlag = 0;
			// DB에 변경한 프로필 사진명 저장
			memberDTO.setProfile(newFileName);
			memberDTO.setSearchCondition("프로필변경");
			boolean flag = memberDAO.update(memberDTO);
			if (flag) {
				outFlag = 1;
			}
			PrintWriter out = response.getWriter();
			out.print(outFlag);

		}

	}

}

//rename Override를 위한 클래스
class CustomFileRenamePolicy extends DefaultFileRenamePolicy {

	private String newFileName;

	public CustomFileRenamePolicy(String newFileName) {
		this.newFileName = newFileName;
	}

	// 저장하는 파일명 재정의 하는 메서드
	@Override
	public File rename(File file) {

		// 업로드한파일 확장자를 extension에 대입
		String extension = (extractExtension(file.getName()));

		// newName에 newFileName(memberNum값) + ".확장자" 대입
		String newName = newFileName + extension;

		// 새로운 파일객체 생성 후 리턴
		File newFile = new File(file.getParent(), newName);

		return newFile;
	}

	// 파일 이름에서 확장자를 추출하는 메서드
	private String extractExtension(String fileName) {
		// "."이 있는 인덱스 확인
		int dotIndex = fileName.lastIndexOf('.');

		// 파일 이름에 점이 존재하고, 파일 이름의 마지막 문자가 점이 아닌 경우 확인
		if (dotIndex > 0 && dotIndex < fileName.length() - 1) {

			// "."을포함한 문자열 추출
			return fileName.substring(dotIndex);

		} else {
			return ""; // 확장자가 없는 경우 빈 문자열 반환
		}
	}
}
