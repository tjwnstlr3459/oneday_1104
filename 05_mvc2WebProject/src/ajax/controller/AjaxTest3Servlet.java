package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxTest3Servlet
 */
@WebServlet(name = "AjaxTest3", urlPatterns = { "/ajaxTest3" })
public class AjaxTest3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AjaxTest3Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		int msg1 = Integer.parseInt(request.getParameter("msg1"));
		int msg2 = Integer.parseInt(request.getParameter("msg2"));
		//3.로직처리
		//4.결과처리
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(msg1+msg2);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
