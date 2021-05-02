package noticemodel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import notice.model.vo.Notice;

public class HugDao {

	public ArrayList<Notice> printPage(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ArrayList<Notice> list = new ArrayList<>();
		ResultSet rset = null;
		
		String query="select * from (select rownum as rnum, n. *from(select * from hug order by hug_no desc)n)where rnum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Notice n = new Notice();
				n.setFilename(rset.getString("filename"));
				n.setFilepath(rset.getString("filepath"));
				n.setNoticContent(rset.getString("hug_content"));
				n.setNoticeDate(rset.getString("hug_date"));
				n.setNoticeNo(rset.getInt("hug_no"));
				n.setNoticeTitle(rset.getString("hug_title"));
				n.setNoticeWriter(rset.getString("hug_writer"));
				n.setRnum(rset.getInt("rnum"));
				list.add(n);
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

	public int allPage(Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rset = null;
		String query = "select count(*) as cnt from hug";
		
		try {
			pstmt=conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return result;
	}
}















