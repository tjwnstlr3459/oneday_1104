package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class AjaxTest6Servlet
 */
@WebServlet(name = "AjaxTest6", urlPatterns = { "/ajaxTest6" })
public class AjaxTest6Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AjaxTest6Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		//3.로직처리
		ArrayList<Member> list = new MemberService().selectAllMember();
		JSONArray memberArray = new JSONArray();	//json객체를 list타입으로 처리해줄 객체
		if(!list.isEmpty()) {
			for(Member m : list){
				JSONObject obj = new JSONObject();
				obj.put("memberNo", m.getMemberNo());
				obj.put("memberName", URLEncoder.encode(m.getMemberName(),"utf-8"));
				obj.put("phone", m.getPhone());
				memberArray.add(obj);
			}
		}
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(memberArray);
		out.flush();
		out.close();
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
