package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet(name = "Logout", urlPatterns = { "/logout" })
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		//request.getSession() : 현재 존재하는 세션을 가지고옴/존재하지 않으면 세션을 새로 생성해서 리턴
		//request.getSession(false) : 현재 존재하는 세션을 가지고옴/ 존재하지 않으면 null 리턴
		
		
		if(session !=null) {
			request.setAttribute("msg", "로그아웃하셨습니다");
			//세션을 파기
			session.invalidate();
			
		}
		//결과처리할 페이지 지정
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			//로그아웃 메세지와 보낼 페이지까시 설정하고 싶을때
		//RequestDispatcher rd = request.getRequestDispatcher("/");
			//보낼 페이지만 설정하고 싶을때
		//결과화면에서 사용할 데이터 등록
		request.setAttribute("msg", "로그아웃");
		request.setAttribute("loc", "/");
		
		//화면이동
		rd.forward(request, response);	//주소창에 서블릿주소가 남겨짐
		
		
		//결과를 처리할 화면에 전달할 객체가 없는경우
		//response.sendRedirect("/");	//href랑 동일한 효과	//주소창에 아무 변화가 없음
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
