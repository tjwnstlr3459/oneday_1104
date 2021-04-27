package kr.or.iei.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendData2Servlet
 */
@WebServlet(name = "SendData2", urlPatterns = { "/sendData2" })
public class SendData2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendData2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.데이터받기
		//문자열 받기
		String str = request.getParameter("str");
		//숫자받기
		int num = Integer.parseInt(request.getParameter("num"));
		//라디오받기
		String gender = request.getParameter("gender");
		//체크박스 -> 동일한 name으로 여러 데이터가 전송될 수 있으므로 String[] 처리
		String[] hobby = request.getParameterValues("hobby");
		//셀렉트박스
		String age = request.getParameter("age");
		//testStr
		String testStr = request.getParameter("testStr");
		System.out.println("str : "+str);
		System.out.println("num : "+num);
		System.out.println("gender : "+gender);
		for (int i = 0; i < hobby.length; i++) {
			System.out.print(hobby[i]+"/");
		}
		System.out.println();
		System.out.println("age"+age);
		System.out.println("testStr : "+testStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
