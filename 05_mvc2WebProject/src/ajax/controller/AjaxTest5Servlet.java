package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.Request;
import org.json.simple.JSONObject;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class AjaxTest5Selvlet
 */
@WebServlet(name = "AjaxTest5", urlPatterns = { "/ajaxTest5" })
public class AjaxTest5Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public AjaxTest5Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		String memberId = request.getParameter("memberId");
		//3.로직처리
		Member m = new MemberService().selectOneMember(memberId);
		JSONObject result = null;	//m을 알아먹지 못하기에 js객체타입을 변경 json사용
		if(m !=null) {
			result = new JSONObject();
			result.put("memberNo", m.getMemberNo());
			result.put("memberId", m.getMemberId());
			result.put("memberName1", m.getMemberName());	//한글을 인코딩 안한경우
			result.put("memberName2", URLEncoder.encode(m.getMemberName(),"UTF-8"));
			result.put("memberName3", URLEncoder.encode(m.getMemberName(),"UTF-8"));
			//한글처리를 위한 인코딩
		}
		response.setContentType("application/json");
		//되돌려주는 데이터의 타입이 json임을 명시(출력할때 풀어서써라~!)
		
		PrintWriter out = response.getWriter();
		out.print(result);	//데이터가 큰경우라면 밑에 코드작성
		out.flush();		//버퍼링 되어 기록되지 않은 데이터를 출력스트림으로 모두출력
		out.close();		//출력되지 않는 데이터가 있으면 출력하고 스트림 종료
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
