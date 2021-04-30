package member.model.service;

import java.sql.Connection;

import common.JDBCTemplate;
import member.model.dao.JoinDao;
import member.model.vo.Member;

public class JoinService {

	public int JoinMember(String memberId, String memberPw, String memberName, String phone, String address) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = new JoinDao().JoinMember(conn,memberId,memberPw,memberName,phone,address);

		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		return result;
	}

	
}
