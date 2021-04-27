
package model.dao;

 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import model.vo.Board;
import model.vo.Member;

 



public class ExamDao {

		public boolean checkId(Connection conn, String memberId) {

			PreparedStatement pstmt = null;

			String query = "select * from exam_member where member_id=?";

			ResultSet rset = null;

			boolean result = false;

			try {

				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, memberId);			

				rset = pstmt.executeQuery();

				result = rset.next();

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}finally {

				JDBCTemplate.close(rset);

				JDBCTemplate.close(pstmt);

			}

			return result;

		}

		public Member login(Connection conn, Member m) {

			PreparedStatement pstmt = null;

			String query = "select * from exam_member where member_id=? and member_pw=?";

			ResultSet rset = null;

			Member member = null;

			try {

				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, m.getMemberId());

				pstmt.setString(2, m.getMemberPw());

				rset = pstmt.executeQuery();

				if(rset.next()) {

					member = getMember(rset);

				}

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}finally {

				JDBCTemplate.close(rset);

				JDBCTemplate.close(pstmt);

			}

			return member;

		}

		public Board getBoard(ResultSet rset) {

			Board b = new Board();

			try {

				b.setBoardNo(rset.getInt("board_no"));

				b.setBoardTitle(rset.getString("board_title"));

				b.setBoardContent(rset.getString("board_content"));

				b.setBoardWriter(rset.getString("member_name"));

				b.setReadCount(rset.getInt("read_count"));

				b.setWriteDate(rset.getDate("write_date"));

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}

			return b;

		}

		public Member getMember(ResultSet rset) {

			Member m = new Member();

			try {

				m.setMemberNo(rset.getInt("member_no"));

				m.setMemberId(rset.getString("member_id"));

				m.setMemberPw(rset.getString("member_pw"));

				m.setMemberName(rset.getString("member_name"));

				m.setPhone(rset.getString("phone"));

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}

			return m;

		}

	 

		public Member idSearch(Connection conn, Member m) {

			PreparedStatement pstmt = null;

			String query = "select * from exam_member where member_name=? and phone=?";

			ResultSet rset = null;

			Member member = null;

			try {

				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, m.getMemberName());

				pstmt.setString(2, m.getPhone());

				rset = pstmt.executeQuery();

				if(rset.next()) {

					member = getMember(rset);

				}

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}finally {

				JDBCTemplate.close(rset);

				JDBCTemplate.close(pstmt);

			}

			return member;

		}

	 

		public int insertMember(Connection conn, Member m) {

			PreparedStatement pstmt = null;

			String query = "insert into exam_member values(member_seq.nextval,?,?,?,?)";

			int result = 0;

			try {

				pstmt = conn.prepareStatement(query);

				int i = 1;

				pstmt.setString(i++, m.getMemberId());

				pstmt.setString(i++, m.getMemberPw());

				pstmt.setString(i++, m.getMemberName());

				pstmt.setString(i++, m.getPhone());

				result = pstmt.executeUpdate();

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}finally {

				JDBCTemplate.close(pstmt);			

			}

			return result;

		}

	 

		public int modifyMember(Connection conn, Member m) {

			PreparedStatement pstmt = null;

			String query = "update exam_member set member_pw=?,phone=? where member_id=?";

			int result = 0;

			try {

				pstmt = conn.prepareStatement(query);

				int i = 1;

				pstmt.setString(i++, m.getMemberPw());			

				pstmt.setString(i++, m.getPhone());

				pstmt.setString(i++, m.getMemberId());

				result = pstmt.executeUpdate();

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}finally {

				JDBCTemplate.close(pstmt);			

			}

			return result;

		}

	 

		public int deleteMember(Connection conn, int memberNo) {

			PreparedStatement pstmt = null;

			String query = "delete from exam_member where member_no=?";

			int result = 0;

			try {

				pstmt = conn.prepareStatement(query);

				pstmt.setInt(1, memberNo);

				result = pstmt.executeUpdate();

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}finally {

				JDBCTemplate.close(pstmt);			

			}

			return result;

		}

	 

		public ArrayList<Board> selectBoardList(Connection conn) {

			PreparedStatement pstmt = null;

			String query = "select board_no,board_title,board_content,member_name,read_count,write_date from exam_board left join exam_member on (board_writer = member_id)";

			ResultSet rset = null;

			ArrayList<Board> boards = new ArrayList<Board>();

			try {

				pstmt = conn.prepareStatement(query);

				rset = pstmt.executeQuery();

				while(rset.next()) {

					boards.add(getBoard(rset));

				}

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}finally {

				JDBCTemplate.close(rset);

				JDBCTemplate.close(pstmt);

			}

			return boards;

		}

		public Board selectBoardOne(Connection conn, int boardNo) {

			PreparedStatement pstmt = null;

			String query = "select board_no,board_title,board_content,member_name,read_count,write_date from exam_board join exam_member on (board_writer = member_id) where board_no = ?";

			ResultSet rset = null;

			Board b = null;

			try {

				pstmt = conn.prepareStatement(query);

				pstmt.setInt(1, boardNo);

				rset = pstmt.executeQuery();

				if(rset.next()) {

					b = getBoard(rset);

				}

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}finally {

				JDBCTemplate.close(rset);

				JDBCTemplate.close(pstmt);

			}

			return b;

		}

		public int readCount(Connection conn, int boardNo) {

			PreparedStatement pstmt = null;

			String query = "update exam_board set read_count = read_count+1 where board_no=?";

			int result = 0;

			try {

				pstmt = conn.prepareStatement(query);

				pstmt.setInt(1, boardNo);

				result = pstmt.executeUpdate();

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}finally {

				JDBCTemplate.close(pstmt);

			}

			return result;

		}

		public int insertBoard(Connection conn, Board b) {

			PreparedStatement pstmt = null;

			String query = "insert into exam_board values(board_seq.nextval,?,?,?,default,default)";

			int result = 0;

			try {

				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, b.getBoardTitle());

				pstmt.setString(2, b.getBoardContent());

				pstmt.setString(3, b.getBoardWriter());

				result = pstmt.executeUpdate();

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}finally {

				JDBCTemplate.close(pstmt);

			}

			return result;

		}

		public String checkWriter(Connection conn, int boardNo) {

			PreparedStatement pstmt = null;

			String query = "select board_writer from exam_board where board_no=?";

			ResultSet rset = null;

			String boardWriter = null;

			try {

				pstmt = conn.prepareStatement(query);

				pstmt.setInt(1, boardNo);

				rset = pstmt.executeQuery();

				if(rset.next()) {

					boardWriter = rset.getString("board_writer");

				}

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}finally {

				JDBCTemplate.close(rset);

				JDBCTemplate.close(pstmt);

			}

			return boardWriter;

		}

		public int modifyBoard(Connection conn, Board b) {

			PreparedStatement pstmt = null;

			String query = "update exam_board set board_title=?,board_content=? where board_no=?";

			int result = 0;

			try {

				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, b.getBoardTitle());

				pstmt.setString(2, b.getBoardContent());

				pstmt.setInt(3, b.getBoardNo());

				result = pstmt.executeUpdate();

			} catch (SQLException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}finally {

				JDBCTemplate.close(pstmt);

			}

			return result;

		}

		public int deleteBoard(Connection conn, int boardNo) {

			PreparedStatement pstmt = null;

			String query = "delete from exam_board where board_no=?";

			int result = 0;

			try {

				pstmt = conn.prepareStatement(query);

				pstmt.setInt(1,boardNo);

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