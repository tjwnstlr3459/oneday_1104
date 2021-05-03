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
 * Servlet implementation class BoardViewServlet
 */
@WebServlet(name = "BoardView", urlPatterns = { "/boardView" })
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		int reqPage = Integer.parseInt(request.getParameter("noticeNo"));
		System.out.println(reqPage);
		//3.로직처리
		Notice n = new NoticeService().selectPage(reqPage);
		//4.결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/notice/boardView.jsp");
		request.setAttribute("n", n);
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}









