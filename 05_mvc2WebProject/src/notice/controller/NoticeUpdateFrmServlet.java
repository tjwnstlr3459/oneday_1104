package notice.controller;

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
 * Servlet implementation class NoticeUpdateFrmServlet
 */
@WebServlet(name = "NoticeUpdateFrm", urlPatterns = { "/noticeUpdateFrm" })
public class NoticeUpdateFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public NoticeUpdateFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		//3.로직처리
		Notice n = new NoticeService().selectPage(noticeNo);
		//4.결과처리
		RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/views/notice/noticeUpdateFrm.jsp");
		request.setAttribute("n", n);
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
