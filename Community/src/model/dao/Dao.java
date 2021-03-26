package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import model.vo.Board;
import model.vo.Member;

public class Dao {

	public int insertMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into member values(member_seq.nextval,?,?,?,?)";		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			pstmt.setString(3, m.getMemberName());
			pstmt.setInt	(4, m.getPhone());
			
			result = pstmt.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return result;
	}

	public int selectMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "select * from member where member_name like ? and phone = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberName());
			pstmt.setInt(2, m.getPhone());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return result;
	}
	public int loginMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "select * from member where member_id = ? and member_pw = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return result;
	}

	public int insertBoard(Board b, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into board_2 values(board_seq_2.nextval,?,?,?,?,sysdate)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setString(3, b.getBoardWriter());
			pstmt.setInt(4, b.getCount());
			
			result = pstmt.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public ArrayList<Board> boardAllPrint(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = new ArrayList<>();		
		String query = "select * from board_2";
		String query2 = "select * from member";
		//테이블명이 board로 되어있으면 어제 실습한  board테이블로 조회를할꺼에요
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardTitle(rset.getString("board_Title"));
				b.setBoardWriter(m.getMemberName());	
				b.setCount(rset.getInt("read_count"));
				b.setWDate(rset.getDate("write_date"));
				list.add(b);
			}				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return list;
	}

	public Board selectBoard(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board b = null;
		
		String qeury = "select * from board_2 where board_no = ?";
		
		try {
			pstmt = conn.prepareStatement(qeury);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new Board();
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardWriter(rset.getString("board_writer"));
				b.setCount(rset.getInt("read_count"));
				b.setWDate(rset.getDate("write_date"));
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}	
		return b;		
	}

	public int modifyLoginBoard(Connection conn, int no, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "select * from board_2 where board_no = ? and board_writer=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			pstmt.setString(2, m.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);				
		}
		return result;
	}

	public int modifyPost(Board b, Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update board set board_title = ? ,board_content = ? where board_writer = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setString(3, m.getMemberId());
			
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
