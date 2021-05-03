package noticemodel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import common.JDBCTemplate;
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
}












