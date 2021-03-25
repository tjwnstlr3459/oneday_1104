package kh.member.model.dao;

import kh.member.model.vo.Member;//여기 임포트가 다른패키지꺼가되어있네요
import java.sql.Connection;
import java.sql.DriverAction;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDao {
	
	
	public ArrayList<Member> getAllMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Member> list = new ArrayList<>();
		String query = "select * from member";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@172.30.1.7:1521:xe", "JDBC", "1234");
			pstmt = conn.prepareStatement(query);
			//pstmt.setString(0, null); // ?가 없어서 생략한다
			rset = pstmt.executeQuery();
			
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
			try {
				rset.close();
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;		
	}

	public Member selectOneMember(String memberId) {

		Connection conn = null;
		PreparedStatement pstmt = null; // prepare
		ResultSet rset = null;
		Member m = null;
		String query = "select * from member where member_id=?";

		try {
			// 1/ 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2.Connection객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@172.30.1.7:1521:xe", "JDBC", "1234");
			// 3.preparedStatement객체생성
			// PrepareStatement 객체를 생성하면서 query같이 전송
			pstmt = conn.prepareStatement(query);
			// 위치홀더(?)에 들어갈 값 세팅
			pstmt.setString(1, memberId);// 첫번째 ?에 memberId변수에 있는 값을 세팅
			// 4.5. 쿼리문 수행 후 결과 받아서 저장
			rset = pstmt.executeQuery();
			if (rset.next()) {
				m = new Member();
				m.setAddr(rset.getString("addr"));
				m.setAge(rset.getInt("age"));
				m.setEnDate(rset.getDate("en_date"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setPhone(rset.getString("phone"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return m;
	}

	
	public ArrayList<Member> selectMemberName(String memberName) {

		Connection conn = null;
		PreparedStatement pstmt = null; // prepare
		ResultSet rset = null;	
		ArrayList<Member>list = new ArrayList<>();
		String query = "select * from member where member_name like ?";

		try {
			// 1/ 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2.Connection객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@172.30.1.7:1521:xe", "JDBC", "1234");
			// 3.preparedStatement객체생성
			// PrepareStatement 객체를 생성하면서 query같이 전송
			pstmt = conn.prepareStatement(query);
			// 위치홀더(?)에 들어갈 값 세팅
			pstmt.setString(1, "%"+memberName+"%");// 첫번째 ?에 memberId변수에 있는 값을 세팅
			// 4.5. 쿼리문 수행 후 결과 받아서 저장
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Member m = new Member();
				m.setAddr(rset.getString("addr"));
				m.setAge(rset.getInt("age"));
				m.setEnDate(rset.getDate("en_date"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setPhone(rset.getString("phone"));
				list.add(m);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public int insertMember(Member m) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into member values(mem_seq.nextval,?,?,?,?,?,?,sysdate)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@172.30.1.7:1521:xe", "JDBC", "1234");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getAddr());
			pstmt.setInt	(5, m.getAge());
			pstmt.setString(6, m.getPhone());
			result = pstmt.executeUpdate();
			if(result >0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public void updateMember(Member m, String memberId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set member_pw = ?, addr = ?, phone = ? where member_id = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@172.30.1.7:1521:xe", "JDBC", "1234");
			pstmt = conn.prepareStatement(query);			
			pstmt.setString(1,m.getMemberPw());			
			pstmt.setString(2,m.getAddr());
			pstmt.setString(3,m.getPhone());
			pstmt.setString(4,memberId);
			result = pstmt.executeUpdate();
			if(result >0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void deleteMember(String memberId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from member where member_id = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@172.30.1.7:1521:xe", "JDBC", "1234");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}





























