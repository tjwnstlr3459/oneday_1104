package ajax.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxTest1Servlet
 */
@WebServlet(name = "AjaxTest1", urlPatterns = { "/ajaxTest1" })
public class AjaxTest1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AjaxTest1Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		String msg = request.getParameter("msg");
		System.out.println("클라이언트가 보낸 값 :"+msg);
		//3.비지니스로직
		//4.결과처리
		
		
	}

/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
