package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxTest2Servlet
 */
@WebServlet(name = "AjaxTest2", urlPatterns = { "/ajaxTest2" })
public class AjaxTest2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AjaxTest2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		//3.비지니스로직
		//4.결과처리
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print("서버에서 보내는 메세지!!");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
