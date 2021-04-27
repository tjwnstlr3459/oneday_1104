

package controller;



import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import model.dao.ExamDao;
import model.vo.Board;
import model.vo.Member;
import view.ExamView;

 

 
public class ExamController {	

	ExamView view;

	ExamDao dao;

	Member loginMember;

	

	public ExamController() {

		super();

		view = new ExamView();

		dao = new ExamDao();				

	}

 

	public void main() {

		while(true) {

			view.printMsg("\n---------- KH커뮤니티 ----------");

			

			if(loginMember == null) {

				int sel = view.logoutMenu();

				switch(sel) {

				case 1: login();break;

				case 2: insertMember();break;

				case 3: idSearch();break;

				case 0: return;

				}

			}else {

				int sel = view.loginMenu();

				switch(sel) {

				case 1: selectBoardList();break;

				case 2: selectBoard();break;

				case 3: insertBoard();break;

				case 4: modifyBoard();break;

				case 5: deleteBoard();break;

				case 6: selectMember();break;

				case 7: modifyMember();break;

				case 8: deleteMember();break;

				case 0: logout();break;

				}

			}

		}

	}

 

	private void insertBoard() {

		Board b = view.insertBoard();

		b.setBoardWriter(loginMember.getMemberId());

		Connection conn = JDBCTemplate.getConnection();

		int result = dao.insertBoard(conn,b);

		if(result>0) {

			JDBCTemplate.commit(conn);

			view.printMsg("게시글 등록 성공!!");

		}else {

			JDBCTemplate.rollback(conn);

			view.printMsg("게시글 등록 실패!!");

		}

		JDBCTemplate.close(conn);

	}

	private void deleteBoard() {

		int boardNo = view.getBoardNo();

		Connection conn = JDBCTemplate.getConnection();		

		String boardWriter = dao.checkWriter(conn,boardNo);

		if(boardWriter == null){

			view.printMsg("게시글 번호를 확인하세요.");

		}else if (boardWriter.equals(loginMember.getMemberId())){

			int result = dao.deleteBoard(conn,boardNo);

			if(result>0) {

				JDBCTemplate.commit(conn);

				view.printMsg("게시글 삭제 성공!!");

			}else {

				JDBCTemplate.rollback(conn);

				view.printMsg("게시글 삭제 실패!!");

			}

		}else {

			view.printMsg("작성자만 삭제가 가능합니다.");

		}

		JDBCTemplate.close(conn);

		

	}

 

	private void modifyBoard() {

		int boardNo = view.getBoardNo();

		Connection conn = JDBCTemplate.getConnection();		

		String boardWriter = dao.checkWriter(conn,boardNo);

		if(boardWriter == null){

			view.printMsg("게시글 번호를 확인하세요.");

		}else if (boardWriter.equals(loginMember.getMemberId())){

			Board b = view.insertBoard();

			b.setBoardNo(boardNo);

			int result = dao.modifyBoard(conn,b);

			if(result>0) {

				JDBCTemplate.commit(conn);

				view.printMsg("게시글 수정 성공!!");

			}else {

				JDBCTemplate.rollback(conn);

				view.printMsg("게시글 수정 실패!!");

			}

		}else {

			view.printMsg("작성자만 수정이 가능합니다.");

		}

		JDBCTemplate.close(conn);

		

	}

 

	private void selectMember() {

		view.printMember(loginMember);

		

	}

 

	private void logout() {

		view.printMsg("Bye~Bye~");

		loginMember = null;		

	}

 

	private void deleteMember() {

		Connection conn = JDBCTemplate.getConnection();

		int sel = view.deleteMember();

		if(sel==1) {

			int result  = dao.deleteMember(conn,loginMember.getMemberNo());

			if(result>0) {

				JDBCTemplate.commit(conn);			

				logout();

			}else {

				JDBCTemplate.rollback(conn);

				view.printMsg("회원 탈퇴 실패!");

			}

		}		

		JDBCTemplate.close(conn);		

	}

 

	private void modifyMember() {

		Member m = view.modifyMember();

		m.setMemberId(loginMember.getMemberId());

		Connection conn = JDBCTemplate.getConnection();

		int result  = dao.modifyMember(conn,m);

		if(result>0) {

			JDBCTemplate.commit(conn);

			loginMember = dao.login(conn, m);

			view.printMsg("정보 수정 성공");

		}else {

			JDBCTemplate.rollback(conn);

			view.printMsg("정보 수정 실패");

		}

		JDBCTemplate.close(conn);	

	}

 

 

 

	private void selectBoard() {

		int boardNo = view.getBoardNo();

		Connection conn = JDBCTemplate.getConnection();

		int result = dao.readCount(conn,boardNo);

		if(result>0) {

			Board b = dao.selectBoardOne(conn,boardNo);

			view.printBoard(b);

			JDBCTemplate.commit(conn);

		}else {

			view.printMsg("게시물 번호를 확인하세요.");

			JDBCTemplate.rollback(conn);

		}		

		JDBCTemplate.close(conn);

	}

 

	private void selectBoardList() {

		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Board> boards = dao.selectBoardList(conn);

		view.printBoarList(boards);

		JDBCTemplate.close(conn);		

	}

 

	private void idSearch() {

		Member m = view.idSearch();

		Connection conn = JDBCTemplate.getConnection();

		m = dao.idSearch(conn,m);

		if(m != null) {

			view.printMsg("아이디는 ["+m.getMemberId()+"] 입니다.");			

		}else {

			view.printMsg("일치하는 정보가 없습니다.");

		}

		JDBCTemplate.close(conn);

	}

 

	private void insertMember() {

		view.printMsg("\n---------- 회원가입 ----------");

		Connection conn = JDBCTemplate.getConnection();

		String memberId = null;

		while(true) {			

			memberId = view.getId();

			if(dao.checkId(conn, memberId)) {

				view.printMsg("이미 존재하는 아이디 입니다.");

			}else {

				break;

			}

		}

		Member m = view.insertMember();

		m.setMemberId(memberId);

		int result  = dao.insertMember(conn,m);

		if(result>0) {

			JDBCTemplate.commit(conn);

			view.printMsg("회원가입 성공");

		}else {

			JDBCTemplate.rollback(conn);

			view.printMsg("회원가입 실패");

		}

		JDBCTemplate.close(conn);

	}

 

	private void login() {

		Member m = view.login();

		Connection conn = JDBCTemplate.getConnection();

		m = dao.login(conn,m);

		if(m != null) {

			view.printMsg("로그인 성공!!");

			loginMember = m;

		}else {

			view.printMsg("아이디 또는 비밀번호를 확인하세요.");

		}

		JDBCTemplate.close(conn);

		

	}

}