package kr.or.iei.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.dao.JoinDao;
import kr.or.iei.member.vo.Member;

/**
 * Servlet implementation class JoinServelet
 */
@WebServlet(name = "Join", urlPatterns = { "/join" })
public class JoinServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 넘어온값 받기
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String pwRe = request.getParameter("pwRe");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String addr = request.getParameter("addr");
		//3. 로직처리
		JoinDao dao = new JoinDao();
		int result = dao.insertMember(id,pw,name,phone,addr);
		
//		Member m = new Member();
//		m.setMemberId(id);
//		m.setMemberPw(pw);
//		m.setMemberName(name);
//		m.setPhone(phone);
//		m.setAddress(addr);
		
		
		//4. 결과처리
		response.setContentType("text/html;charset=utf8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html><head><title>로그인결과</title></head><body>");
		out.println("<h1>로그인결과</h1><hr>");
		if(result>0) {
			//회원가입 성공한 경우
			out.println("<h2>회원가입 성공</h2>");
			out.println("<script>alert('가입 성공!!');</script>");
		}else {
			//회원가입 실패한 경우
			out.println("<h2>회원가입 실패</h2>");
			out.println("<script>alert('가입실패');</script>");
		}
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













