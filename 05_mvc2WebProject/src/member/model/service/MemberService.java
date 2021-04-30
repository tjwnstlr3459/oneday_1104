package member.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.sun.org.apache.xml.internal.serializer.utils.StringToIntTable;

import common.JDBCTemplate;
import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {

	public Member selectOneMember(String memberId, String memberPw) {
		//커넥션 생성
		Connection conn = JDBCTemplate.getConnection();
		Member m = new MemberDao().selectOneMember(conn,memberId,memberPw);
		JDBCTemplate.close(conn);
		
		return m;
	}

	public Member selectOneMember(String memberId) {
		//아이디 중복값 확인
		//커넥션 생성
		
		Connection conn = JDBCTemplate.getConnection();
		Member m = new MemberDao().selectOneMember(conn,memberId);
		JDBCTemplate.close(conn);
		
		return m;
	}

	public int updateMember(Member m) {
		//마이페이지 정보수정(업데이트)
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().selectOneMember(conn,m);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int deleteMember(int memberNo) {
		//회원 탈퇴(delete)
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().deleteMember(conn,memberNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Member> selectAllMember() {
		//관리자모드에서 전체회원 출력(select)
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Member> list = new MemberDao().selectAllMember(conn);
		
		JDBCTemplate.close(conn);
		return list;
	}

	public int changeLevle(int memberlevel, int memberNo) {
		//관리자모드에서 회원등급 변경(업데이트)
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().changeLevel(conn,memberlevel,memberNo);
		System.out.println(result);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public boolean checkedChangeLevel(String num, String level) {
		//관리자모드에서 회원등급 다중선택 변경
		Connection conn = JDBCTemplate.getConnection();
		// num = 10/15/17
		// level = 3/1/2
		StringTokenizer sT1 = new StringTokenizer(num,"/");
		StringTokenizer sT2 = new StringTokenizer(level,"/");

		MemberDao dao = new MemberDao();
		boolean result = true;
		
		while(sT1.hasMoreTokens()) {
			int memberNo = Integer.parseInt(sT1.nextToken());
			int memberLevel = Integer.parseInt(sT2.nextToken());
			//조건이 안맞으면 0업데이트라 0이 리턴
			int result1 = dao.changeLevel(conn,memberLevel,memberNo);
			//하나라도 실패하면 false
			if(result1 == 0) {
				result = false;
				break;
			}
		}
		if(result) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
}






















