package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet(name = "Login", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		//3.로직처리 -->비지니스로직 -->서비스호출
		Member m = new MemberService().selectOneMember(memberId,memberPw);
		//4.결과처리(3가지)
		//결과처리용 페이지 지정
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		//로그인 성공한 경우(memberLevel==3, !=3), 로그인 실패한경우,
		//해당하는 경우에 따라서 msg.jsp에서 사용할 변수의 값을 다르게 적용
		if(m !=null) {
			//로그인 성공
			if(m.getMemberLevel()==3) {
				//준회원인경우
				request.setAttribute("msg", "로그인 권한이 없습니다. 관리자에게 문의하세요.");
			}else {
				//정회원 또는 관리자인경우 -> 로그인 성공인경우 = 세션에 데이터 등록
				HttpSession session = request.getSession();
				session.setAttribute("m", m);
				request.setAttribute("msg", "로그인  성공!");
			}
		}else {
			//로그인 실패
			request.setAttribute("msg", "아이디 또는 비밀번호를 확인하세요.");
		}
		//alert으로 안내 후 이동할 페이지 지정
		request.setAttribute("loc", "/");
		//페이지 이동
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
