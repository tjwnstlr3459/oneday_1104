package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import vo.Board;
import vo.Member;

public class Dao {

	public int insertMember( String id, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query= "select * from member where member_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int selectId(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "select * from member where member_name = ? and phone = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberName());
			pstmt.setInt(2, m.getPhone());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}		
		return result;
	}

	public Member login(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		Member login = null;;
		ResultSet rset = null;
		String query ="select * from member where member_id =? and member_pw =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				login = getMember(rset);		
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}			
		return login;
	}
	public Member getMember(ResultSet rset) {
		Member m = new Member();
		try {
			m.setMemberNo(rset.getInt("member_no"));
			m.setMemberId(rset.getString("member_id"));
			m.setMemberPw(rset.getString("member_pw"));
			m.setMemberName(rset.getString("member_name"));
			m.setPhone(rset.getInt("phone"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}

	public int insertBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query ="insert into board_2 values(board_seq_2.nextval,?,?,?,?,default)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setString(3, b.getWriter());
			pstmt.setInt(4, b.getCount());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Board> printAllBoard(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = new ArrayList<>();
		String query ="select board_no,board_title,board_content,member_name,read_count,write_date from board_2 join member on(member_id = board_writer)";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("Board_no"));
				b.setBoardTitle(rset.getString("Board_title"));
				b.setWriter(rset.getString("member_name"));
				b.setCount(rset.getInt("read_count"));
				b.setwDate(rset.getDate("write_date"));
				
				list.add(b);				
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

	public int printOneBoard(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update board_2 set read_count = read_count+1  where board_no=?";
				
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);			
		}
		return result;
	}

	public Board selectOneBoard(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board b = null;
		String query = "select board_no,board_title,board_content,member_name,read_count,write_date from board_2 join member on(member_id = board_writer) where board_no =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new Board();
				b.setBoardNo(rset.getInt("Board_no"));
				b.setBoardTitle(rset.getString("Board_title"));
				b.setContent(rset.getString("Board_content"));			
				b.setWriter(rset.getString("member_name"));
				b.setCount(rset.getInt("read_count"));
				b.setwDate(rset.getDate("write_date"));		
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);	
			JDBCTemplate.close(rset);	
		}		
		return b;
	}
	public int selectBoard(Connection conn, int no, Member loginMember) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "select * from board_2 join member on(member_id = board_writer) where board_no =? and member_id=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			pstmt.setString(2, loginMember.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);	
		}
		
		return result;
		
	}
	
	
	public int modifyBoard(Connection conn, int no, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update board_2 set board_title = ?, board_content=? where board_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setInt(3, no);
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);	
		}
		
		return result;
	}

	public int deleteBoard(Connection conn, int no) {
		PreparedStatement pstmt = null;	
		int result = 0;
		String query = "delete from board where board_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);	
		}
		return result;
	}

	public int modifyInformation(Connection conn, Member m) {
		PreparedStatement pstmt = null;	
		int result = 0;
		String query = "update member set member_pw= ?, phone = ? where member_id=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberPw());
			pstmt.setInt(2, m.getPhone());
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

	public int insertMember2(Connection conn, Member m) {
		PreparedStatement pstmt = null;	
		int result = 0;
		String query = "insert into member values(member_seq.nextval,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			pstmt.setString(3, m.getMemberName());
			pstmt.setInt(4, m.getPhone());
			
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




















