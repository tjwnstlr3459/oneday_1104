package notice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDeleteServlet
 */
@WebServlet(name = "NoticeDelete", urlPatterns = { "/noticeDelete" })
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		//3.로직처리
		NoticeService service = new NoticeService();
		Notice n = service.selectPage(noticeNo);
		int result = service.deleteNotice(noticeNo);
		//4.결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			//DB의 파일은 지웠지만, 업로드한 파일은 남아있기에
			//업로드된 파일을 지우기 위한 코드
			if(n.getFilepath() != null) {
				String root = getServletContext().getRealPath("/");
				String file = root +"upload/notice/"+n.getFilepath();
				File delFile = new File(file);
				delFile.delete();
			}
			request.setAttribute("msg", "삭제성공");
			request.setAttribute("loc", "/boardList?reqPage=1");
		}else {
			request.setAttribute("msg", "삭제실패");
			request.setAttribute("loc", "/boardList?reqPage="+noticeNo);
		}
		rd.forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
