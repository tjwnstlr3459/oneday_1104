package kr.or.iei.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.dao.MemberDao;
import kr.or.iei.member.vo.Member;

/**
 * Servlet implementation class AllMemeberServelet
 */
@WebServlet(name = "AllMemeber", urlPatterns = { "/allMember" })
public class AllMemberServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllMemberServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값 받기
		//받을게 없음
		
		//3.로직처리
		MemberDao dao = new MemberDao();
		ArrayList<Member> list = dao.printAllMemeber();
		
		//4.결과처리
		response.setContentType("text/html;charset=utf8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html><head><title>전체회원조회</title></head><body>");
		
		out.println("<h1>전체회원 조회 결과</h1><hr>");
		if(list.size()==0) {
			out.println("<script>alert('회원이 존재하지 않습니다.');</script>");
		}else {
			out.println("<table border='1px'>");
			out.println("<tr><td>번호</td><td>ID</td><td>이름</td><td>전화번호</td><td>주소</td></tr>");
			for (int i = 0; i < list.size(); i++) {
				out.println("<tr><td>"+list.get(i).getMemberNo()+"</td><td>"+list.get(i).getMemberId()+"</td><td>"+
							list.get(i).getMemberName()+"</td><td>"+list.get(i).getPhone()+"</td><td>"+list.get(i).getAddress()+"</td></tr>");
			}
		}
		out.println("</table>");
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




















