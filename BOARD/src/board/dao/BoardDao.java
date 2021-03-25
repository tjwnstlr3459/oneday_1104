package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.vo.Board;
import common.JDBCTemplate;

public class BoardDao {

	public ArrayList<Board> boardAllPrint() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = new ArrayList<>();
		String query = "select * from board";
		
		conn = JDBCTemplate.getConnection();
		try {
			pstmt =conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardWriter(rset.getString("board_writer"));
				b.setEnDate(rset.getDate("board_Date"));
				list.add(b);
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

	public int boardInsert(Board writ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into board values(board_seq.nextval,?,?,?,sysdate)";
		
		conn = JDBCTemplate.getConnection();
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setString(1, writ.getBoardTitle());
			pstmt.setString(2, writ.getBoardContent());
			pstmt.setString(3, writ.getBoardWriter());
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
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

	public Board selectBoardNum(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board b = new Board();
		
		String query = "select * from board where board_no = ?";
		
		conn = JDBCTemplate.getConnection();
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setInt(1, num);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				b = new Board();
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardWriter(rset.getString("board_title"));
				b.setEnDate(rset.getDate("board_Date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);			
		}	
		return b;
	}

	public int modifyBoardNum(int modifyNum, Board mo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		Board b = new Board();
		
		String query = "update board set board_title =?, board_content=? where board_no = ?";
		
		conn = JDBCTemplate.getConnection();
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setString(1, mo.getBoardTitle());
			pstmt.setString(2, mo.getBoardContent());
			pstmt.setInt(3, modifyNum);
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
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

	public int deleteBoardNum(int delete) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		Board b = new Board();
		
		String query = "delete from board where board_no = ?";
		
		conn = JDBCTemplate.getConnection();
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setInt(1, delete);
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
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












