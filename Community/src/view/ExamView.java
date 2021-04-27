
package view;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import common.JDBCTemplate;
import model.vo.Board;
import model.vo.Member;

 

 

public class ExamView {

	Scanner sc;

	

	public ExamView() {

		super();

		sc = new Scanner(System.in);

	}

	public void printMsg(String msg) {

		System.out.println(msg);

	}

	public int logoutMenu() {

		System.out.println("1. 로그인하기");

		System.out.println("2. 회원가입");

		System.out.println("3. 아이디 찾기");

		System.out.println("0. 프로그램 종료");

		System.out.print("선택 > ");

		return sc.nextInt();

	}

	public int loginMenu() {

		System.out.println("1. 게시물 목록 보기");

		System.out.println("2. 게시물 상세 보기");

		System.out.println("3. 게시물 등록");

		System.out.println("4. 게시물 수정");

		System.out.println("5. 게시물 삭제");

		System.out.println("6. 내정보 보기");

		System.out.println("7. 내 정보 변경");

		System.out.println("8. 회원 탈퇴");

		System.out.println("0. 로그아웃");

		System.out.print("선택 > ");

		return sc.nextInt();

	}

	public Member login() {

		System.out.println("\n---------- 로그인 ----------");

		Member m = new Member();

		System.out.print("ID 입력 : ");

		m.setMemberId(sc.next());

		System.out.print("PW 입력 : ");

		m.setMemberPw(sc.next());

		return m;

	}

	public Member idSearch() {

		System.out.println("\n---------- 아이디 찾기 ----------");

		Member m = new Member();

		System.out.print("이름 입력 : ");

		m.setMemberName(sc.next());

		System.out.print("전화번호 입력 : ");

		m.setPhone(sc.next());

		return m;

	}

	public String getId() {

		System.out.print("ID 입력 : ");

		return sc.next();

	}

	public Member insertMember() {		

		Member m = new Member();		

		System.out.print("PW 입력 : ");

		m.setMemberPw(sc.next());

		System.out.print("이름 입력 : ");

		m.setMemberName(sc.next());

		System.out.print("전화번호 입력(ex.01011112222) : ");

		m.setPhone(sc.next());

		return m;

	}

	public Member modifyMember() {

		System.out.println("\n---------- 내 정보 수정 ----------");

		Member m = new Member();		

		System.out.print("PW 입력 : ");

		m.setMemberPw(sc.next());		

		System.out.print("전화번호 입력(ex.01011112222) : ");

		m.setPhone(sc.next());

		return m;

	}

	public int deleteMember() {

		System.out.println("\n---------- 회원 탈퇴 ----------");

		System.out.print("정말 탈퇴 하시겠습니까(1.YES / 2.NO)? ");

		return sc.nextInt();

	}

	public void printMember(Member loginMember) {

		System.out.println("\n---------- 내 정보 보기 ----------");

		System.out.println("회원번호 : "+loginMember.getMemberNo());

		System.out.println("아이디 : "+loginMember.getMemberId());

		System.out.println("비밀번호 : "+loginMember.getMemberPw());

		System.out.println("이름 : "+loginMember.getMemberName());

		System.out.println("전화번호 : "+loginMember.getPhone());

		

	}

	public void printBoarList(ArrayList<Board> boards) {

		System.out.println("\n---------- 게시물 목록 ----------");

		for(Board b : boards) {

			System.out.println(b);

		}

		

	}

	public int getBoardNo() {

		System.out.print("게시물 번호 입력 : ");

		return sc.nextInt();

	}

	public void printBoard(Board b) {

		System.out.println("\n---------- 게시글 정보 ----------");

		System.out.println("게시물 번호 : "+b.getBoardNo());

		System.out.println("게시물 제목 : "+b.getBoardTitle());

		System.out.println("게시물 내용 : "+b.getBoardContent());

		System.out.println("게시물 작성자 : "+b.getBoardWriter());

		System.out.println("게시물 조회수 : "+b.getReadCount());

		System.out.println("게시물 작성일 : "+b.getWriteDate());

		

	}

	public Board insertBoard() {

		Board b = new Board();

		System.out.print("제목 입력 : ");

		b.setBoardTitle(sc.next());

		System.out.print("내용 입력 : ");

		b.setBoardContent(sc.next());

		return b;

	}

	

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