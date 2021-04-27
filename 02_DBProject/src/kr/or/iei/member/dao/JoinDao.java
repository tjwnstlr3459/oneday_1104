package kr.or.iei.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import kr.or.iei.member.vo.Join;
import kr.or.iei.member.vo.Member;

public class JoinDao {

	public int insertMember(String id, String pw, String name, String phone, String addr) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rset = null;
		int result = 0;;
		//String query = "insert into MEMBER values(MEM_SEQ.NEXTVAL,ID,PW,NAME,PHONE,ADDR,3,(SYSDATE,'yyyy-mm-dd'))";
		String query = "insert into MEMBER values(MEM_SEQ.NEXTVAL,?,?,?,?,?,3,to_char(SYSDATE,'yyyy-mm-dd'))";
		
		
		conn = JDBCTemplate.getConnection();
		
		try {
			pstmt = conn.prepareStatement(query);
//			강사님코드
//			pstmt.setString(1, m.getMemberId());
//			pstmt.setString(2, m.getMemberPw());
//			pstmt.setString(3, m.getMemberName());
//			pstmt.setString(4, m.getPhone());
//			pstmt.setString(5, m.getAddress());
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, phone);
			pstmt.setString(5, addr);
			
			result = pstmt.executeUpdate();
			
			if(result>0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (SQLException e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	
}
