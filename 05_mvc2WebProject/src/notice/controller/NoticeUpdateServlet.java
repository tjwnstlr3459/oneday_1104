package notice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet(name = "NoticeUpdate", urlPatterns = { "/noticeUpdate" })
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public NoticeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "공지사항 수정 오류[enctype]");
			request.setAttribute("loc", "/");
			rd.forward(request, response);
			return;
		}
		
		//1)업로드 경로 지정
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload/notice";
		//2)업로드 파일 최대 크기 지정
		int maxSize = 1024 *1024*10;
		//3)request -> MultipartRequest로 변환
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize,"utf-8",new DefaultFileRenamePolicy());
		//수정된 정보
		int noticeNo = Integer.parseInt(mRequest.getParameter("noticeNo"));
		String noticeTitle = mRequest.getParameter("noticeTitle");
		String filename = mRequest.getOriginalFileName("filename");
		String filepath = mRequest.getFilesystemName("filename");
		
		String noticeContent = mRequest.getParameter("noticeContent");
		//기존파일 이름 및 경로
		String oldFilename = mRequest.getParameter("oldFilename");
		String oldFilepath = mRequest.getParameter("oldFilepath");
		
		//기존파일 삭제 확인용
		String status = mRequest.getParameter("status");
		if(status.equals("delete")) {	//기존 첨부파일을 삭제했을때
			File delFile = new File(saveDirectory+"/"+oldFilepath);
			delFile.delete();
		}else if(oldFilename != null) {
			filename = oldFilename;
			filepath = oldFilepath;
		}
		Notice n = new Notice(0, noticeNo, noticeTitle, null, noticeContent, null, filename, filepath);
		//update notice set notice_title=?, notice_content=?, filename=? filepaht=? where notice_no=?
//		Notice n = new Notice();
//		n.setNoticeNo(noticeNo);
//		n.setNoticeTitle(noticeTitle);
//		n.setNoticContent(noticeContent);
//		n.setFilename(filename);
//		n.setFilepath(filepath);
		//3.로직처리
		int result = new NoticeService().updateNotice(n);
		System.out.println(result);
		//4.결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "변경 성공");
		}else {
			request.setAttribute("msg", "변경 실패");
		}
		request.setAttribute("loc", "/noticeView?noticeNo="+noticeNo);
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

