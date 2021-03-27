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
			pstmt.setInt(4, m.getPhone());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
		} finally {
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
		} finally {
			JDBCTemplate.close(pstmt);
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
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public ArrayList<Board> boardAllPrint(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = new ArrayList<>();
		String query = "select board_no, board_title, board_content, member_name, read_count, write_date from board_2 join member on(member_id=board_writer)";

		try {
			pstmt= conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardWriter(rset.getString("member_name"));
				b.setCount(rset.getInt("read_count"));
				b.setWDate(rset.getDate("write_date"));
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

	public Board selectBoard(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board b = null;

		String qeury = "select * from board_2 join member on(board_writer = member_id) where board_no = ?";

		try {
			pstmt = conn.prepareStatement(qeury);
			pstmt.setInt(1, no);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				b = new Board();
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardWriter(rset.getString("member_id"));
				b.setCount(rset.getInt("read_count"));
				b.setWDate(rset.getDate("write_date"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return b;
	}

	public int modifyLoginBoard(Connection conn, int no, Member loginMember) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "select * from board_2 where board_no = ? and board_writer=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			pstmt.setString(2, loginMember.getMemberId());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int modifyPost(Board b, Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update board_2 set board_title = ? ,board_content = ? where board_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setInt(3, no);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deletePost(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from board_2 where board_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Member myInformation(Connection conn, Member loginMember) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		String query = "select * from member where member_id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginMember.getMemberId());

			rset = pstmt.executeQuery();

			if (rset.next()) {
				m = new Member();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberId(rset.getString("member_Id"));
				m.setMemberPw(rset.getString("member_Pw"));
				m.setMemberName(rset.getString("member_Name"));
				m.setPhone(rset.getInt("Phone"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return m;
	}

	public int modifyInformation(Connection conn, Member m, Member loginMember) {
		PreparedStatement pstmt = null;
		int result= 0;
		String query = "update member set member_pw = ?, phone = ? where member_id=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberPw());
			pstmt.setInt(2, m.getPhone());
			pstmt.setString(3, loginMember.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteMember(Connection conn, Member loginMember) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from member where member_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginMember.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}		
		return result;
	}

	public void nullBoard(Connection conn) {
		PreparedStatement pstmt = null;
		
		String query = "update board_2 set board_wirter = ? where board_writer = ?";
		String out = "탈퇴회원";
		String ddd = (null);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, out);	
			pstmt.setString(2, ddd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}		
	}

	public int readCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query ="update board_2 set read_count = read_count+1 where board_no = ?";
		
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

}







































