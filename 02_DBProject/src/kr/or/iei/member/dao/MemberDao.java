package kr.or.iei.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.or.iei.member.vo.Member;


public class MemberDao {

	public Member selectOneMember(String id, String pw) {	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_id=? and member_pw=?";
		Member member = null;
		conn = JDBCTemplate.getConnection();	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				member = new Member();
				member.setMemberNo(rset.getInt("member_no"));
				member.setMemberId(rset.getString("member_id"));
				member.setMemberPw(rset.getString("member_pw"));
				member.setMemberName(rset.getString("member_name"));
				member.setPhone(rset.getString("phone"));
				member.setAddress(rset.getString("address"));
				member.setMemberLevel(rset.getInt("member_level"));
				member.setEnrollDate(rset.getString("enroll_date"));
			}
		}catch(SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}
		return member;
	}

	
	public ArrayList<Member> printAllMemeber() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member";
		
		ArrayList<Member> list = new ArrayList<>();
		
		conn = JDBCTemplate.getConnection();
		
		try {
			pstmt=conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Member m = new Member();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setMemberLevel(rset.getInt("member_level"));
				m.setEnrollDate(rset.getString("enroll_date"));
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
}


















