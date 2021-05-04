package noticemodel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import common.JDBCTemplate;
import member.model.vo.NoticeComment;
import notice.model.vo.Notice;

public class NoticeDao {

	public ArrayList<Notice> selectNoticeList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ArrayList<Notice> list = new ArrayList<>();
		ResultSet rset = null;
		String query="SELECT * FROM(SELECT ROWNUM AS RNUM, N.*FROM(SELECT * FROM NOTICE ORDER BY NOTICE_NO DESC)N) WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Notice n = new Notice();
				n.setFilename(rset.getString("filename"));
				n.setFilepath(rset.getString("filepath"));
				n.setNoticContent(rset.getString("notice_content"));
				n.setNoticeDate(rset.getString("notice_date"));
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeWriter(rset.getString("notice_writer"));
				n.setRnum(rset.getInt("rnum"));
				list.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) as cnt from notice";
		int result = 0;
		
		try {
			pstmt=conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result= rset.getInt("cnt");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int insertNotice(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query="insert into notice values(notice_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'),?,?)";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeWriter());
			pstmt.setString(3, n.getNoticContent());
			pstmt.setString(4, n.getFilename());
			pstmt.setString(5, n.getFilepath());
			result = pstmt.executeUpdate();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	
	
	
	
	
	public Notice selectPage(Connection conn, int reqPage) {
		PreparedStatement pstmt = null;
		String query="select * from notice where notice_no=?";
		ResultSet rset= null;
		Notice n = null;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reqPage);
			
			rset=pstmt.executeQuery();
			if(rset.next()) {
				n = new Notice();
				n.setFilename(rset.getString("filename"));
				n.setFilepath(rset.getString("filepath"));
				n.setNoticContent(rset.getString("notice_content"));
				n.setNoticeDate(rset.getString("notice_date"));
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeWriter(rset.getString("notice_writer"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return n;
	}

	public int deleteNotice(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result=0;
		
		String query="delete from notice where notice_no=?";
		
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateNotice(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result=0;
		
		String query = "update notice set notice_title=?, notice_content=?, filename=?, filepath=? where notice_no=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticContent());
			pstmt.setString(3, n.getFilename());
			pstmt.setString(4, n.getFilepath());
			pstmt.setInt(5, n.getNoticeNo());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertComment(Connection conn, NoticeComment nc) {
		PreparedStatement pstmt = null;
		int result=0;
		
		String query="insert into notice_comment values(no_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'),?,?)";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, nc.getNcLevel());
			pstmt.setString(2, nc.getNcWriter());
			pstmt.setString(3, nc.getNcContent());
			pstmt.setInt(4, nc.getNoticeRef());
			pstmt.setString(5, (nc.getNcRef()==0)?null:String.valueOf(nc.getNcRef()));
									//NcRef의 기본 value값은 0으로 설정되어있음
									//0이면 null을 넣어주고 아니라면 다른값을 넣어준다
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<NoticeComment> selectNoticeCommentList(Connection conn, int reqPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NoticeComment> list = new ArrayList<>();
		String query ="select * from notice_comment where notice_ref=?";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, reqPage);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				NoticeComment nc = new NoticeComment();
				nc.setNcContent(rset.getString("nc_content"));
				nc.setNcDate(rset.getString("nc_date"));
				nc.setNcLevel(rset.getInt("nc_level"));
				nc.setNcNo(rset.getInt("nc_no"));
				nc.setNcRef(rset.getInt("nc_ref"));
				nc.setNcWriter(rset.getString("nc_Writer"));
				nc.setNoticeRef(rset.getInt("notice_ref"));
				list.add(nc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return list;
	}

	public int updateNoticeComment(Connection conn, int ncNo, String ncContent) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query="update notice_comment set nc_content=? where nc_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, ncContent);
			pstmt.setInt(2, ncNo);
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int deleteNoticeComment(Connection conn, int ncNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query="delete from notice_comment where nc_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ncNo);
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}












