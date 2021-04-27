package kr.or.iei.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalcServlet
 */
@WebServlet(name = "Calc", urlPatterns = { "/calc" })
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalcServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.숫자받기
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));
		String oper = request.getParameter("oper");
		int sum = 0;
		
		if(oper.equals("+")) {
			sum = num1+num2;
		}else if(oper.equals("-")) {
			sum = num1-num2;
		}else if(oper.equals("*")) {
			sum = num1*num2;
		}else if(oper.equals("/")) {
			sum = num1/num2;
		}
		response.setContentType("text/html;charset=UTF-8"); //응답을 HTML파일로 하고, 문자셋은 UTF-8로 
		PrintWriter out = response.getWriter();				//사용자가 볼 HTML을 작성하는 객체생성
		//HTML작성
		out.println("<!DOCTYPE html>");
		out.println("<html><head><title>계산기</title></head><body>");
		out.println("<h1>결과</h1>");
		out.println("<hr>");
		out.println("<h2>"+num1+oper+num2+" = "+sum+"</h2>");
		out.println("</body></html>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
