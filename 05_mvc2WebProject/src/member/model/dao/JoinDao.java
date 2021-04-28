package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.JDBCTemplate;
import member.model.vo.Member;

public class JoinDao {

	public int JoinMember(Connection conn, String memberId, String memberPw, String memberName, String phone,
			String address) {
		
		PreparedStatement pstmt = null;
		int result=0;
		String query = "insert into member values(MEM_SEQ.NEXTVAL,?,?,?,?,?,3,to_char(SYSDATE, 'yyyy-mm-dd'))";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			pstmt.setString(3, memberName);
			pstmt.setString(4, phone);
			pstmt.setString(5, address);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
