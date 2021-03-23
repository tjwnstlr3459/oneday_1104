package kh.member.model.dao;

import kh.member.model.vo.Member;//여기 임포트가 다른패키지꺼가되어있네요
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDao {//Data Access Object

	public ArrayList<Member> selectAllMember(){
		Connection conn = null;	//DBMS 연결용 객체
		Statement stmt = null;	//SQL구문을 실행하고 결과를 받아오는 객체
		ResultSet rset = null;	//SELECT실행 결과를 저장하는 객체
		ArrayList<Member> list = new ArrayList<Member>();//전체회원정보를 저장할 객체
		String query = "select * from member";	//쿼리문 작성시 ; 포함x
		
		
		
		try {
			//1. 사용할 DB에 대한 드라이버 등록(사용할 클래스 등록)
			Class.forName("oracle.jdbc.driver.OracleDriver");//라이브러리를 넣어줘야 사용가능
			//2. 등록된 클래스를 이용해서 DB연결(소켓연결같은느낌)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@172.30.1.7:1521:xe","JDBC","1234");
			//3. 쿼리문을 실행할 statement 객체 생성
			stmt = conn.createStatement();
			//4. 쿼리문 전송
			//	SELECT를 수행하기때문에 executeQuery()메소드를 호출
			//5. 쿼리문 수행결과 저장
			//	SELECT 수행결과는 ResultSet 객체로 리턴
			rset = stmt.executeQuery(query);
			while(rset.next()) {
				Member m = new Member();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setAddr(rset.getString("addr"));
				m.setAge(rset.getInt("age"));
				m.setPhone(rset.getString("phone"));
				m.setEnDate(rset.getDate("en_date"));
				list.add(m);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//6. 자원반환
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public Member selectOneMember(String memberId) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Member m = null;//어짜피 한명이라 Member로 
		String query = "select * from member where member_id='"+memberId+"'";
		
		try {
			//1. 사용할 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. 등록된 클래스로 DB 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@172.30.1.7:1521:xe","JDBC","1234");
			//3. 쿼리문을 실행할 Statement객체 생성
			stmt = conn.createStatement();
			//4. 쿼리문을 실행하고, 결과를 받아옴, 5.받은 결과를 저장
			rset = stmt.executeQuery(query); //select쿼리 할때만 execute씀
			
			if(rset.next()) {	//어짜피 결과는 한명만이라 if를 쓰는게 더 자연스럽긴하다
				m = new Member();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setAddr(rset.getString("addr"));
				m.setAge(rset.getInt("age"));
				m.setPhone(rset.getString("phone"));
				m.setEnDate(rset.getDate("en_date"));	
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return m;//객체가 없다면 null값 리턴
	}

	public ArrayList<Member> selectName(String memberName) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<>();
		
		String query = "select * from member where member_name like'%"+memberName+"%'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@172.30.1.7:1521:xe","JDBC","1234");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Member m = new Member();
				m.setMemberNo(rset.getInt(1)); 		//컬럼명이 아닌 숫자로 해도된다
				m.setMemberId(rset.getString(2));	//컬럼명이 아닌 숫자로 해도된다
				m.setMemberPw(rset.getString(3));	//컬럼명이 아닌 숫자로 해도된다
				m.setMemberName(rset.getString(4));	//컬럼명이 아닌 숫자로 해도된다
				m.setAddr(rset.getString(5));		//컬럼명이 아닌 숫자로 해도된다
				m.setAge(rset.getInt(6));			//컬럼명이 아닌 숫자로 해도된다
				m.setPhone(rset.getString(7));		//컬럼명이 아닌 숫자로 해도된다
				m.setEnDate(rset.getDate(8));		//컬럼명이 아닌 숫자로 해도된다
				list.add(m);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return list;
	}
}











