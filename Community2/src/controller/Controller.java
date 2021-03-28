package controller;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import dao.Dao;
import view.View;
import vo.Board;
import vo.Member;

public class Controller {

	View view;
	Member loginMember;
	Dao dao;

	public Controller() {
		super();
		view = new View();
		dao = new Dao();
	}

	public void main() {

		while (true) {
			if (loginMember == null) {
				switch (view.menu()) {
				case 1:
					login();
					break;
				case 2:
					insertMember();
					break;
				case 3:
					selectId();
					break;
				case 0:
					break;
				default:
					return;
				}
			} else {
				switch (view.loginMenu()) {
				case 1:
					printAllBoard();
					break;
				case 2:
					printOneBoard();
					break;
				case 3:
					insertBoard();
					break;
				case 4:
					modifyBoard();
					break;
				case 5:
					deleteBoard();
					break;
				case 6:
					myInformation();
					break;
				case 7:
					modifyInformation();
					break;
				case 8:
					break;
				case 0:
					logOut();
					break;
				}
			}
		}
	}


	private void insertMember() {
		view.printMsg("====회원가입====");
		Connection conn = JDBCTemplate.getConnection();
		String id = null;
		while (true) {
			id = view.getId();
			int result = dao.insertMember( id, conn);
			if (result > 0) {
				view.printMsg("중복된 아이디입니다.");
			} else {
				break;
			}
			
		}
		Member m = view.insertMember();
		m.setMemberId(id);
		int result = dao.insertMember2(conn,m);
		if(result>0) {
			view.printMsg("가입완료!");
			JDBCTemplate.commit(conn);
		}else {
			view.printMsg("가입실패");
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
	}

	private void selectId() {
		Member m = view.selectId();
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.selectId(conn, m);

		if (result > 0) {
			view.printMsg("아이디는 [" + m.getMemberId() + "] 입니다.");
		} else {
			view.printMsg("일치하는 정보가 없습니다.");
		}
		JDBCTemplate.close(conn);
	}

	private void login() {
		Member m = view.login();
		Connection conn = JDBCTemplate.getConnection();
		m = dao.login(conn, m);

		if (m != null) {
			view.printMsg("로그인 성공!");
			loginMember = m;
		} else {
			view.printMsg("아이디 또는 비밀번호를 확인하세요");
		}
		JDBCTemplate.close(conn);
	}

	private void insertBoard() {
		Board b = view.insertBoard();
		b.setWriter(loginMember.getMemberId());
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertBoard(conn,b);
		if (result > 0) {
			view.printMsg("게시물 등록 성공!");		
			JDBCTemplate.commit(conn);
		} else {
			view.printMsg("게시물 등록 실패");
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
	}

	private void printAllBoard() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Board> list = dao.printAllBoard(conn);
		
		if(list.isEmpty()) {
			view.printMsg("게시물이 없습니다~!");
		}else {
			view.printAllBoard(list);
		}
		JDBCTemplate.close(conn);
	}


	private void printOneBoard() {
		int no = view.getNo();
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.printOneBoard(conn,no);
		
		if(result > 0) {
			Board b = dao.selectOneBoard(conn,no);
			view.printOneBoard(b);
			JDBCTemplate.commit(conn);
		}else {
			view.printMsg("게시물 번호가 없습니다");
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
	}

	private void modifyBoard() {
		int no = view.getNo();
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.selectBoard(conn,no,loginMember);
		
		if(result>0) {
			Board b = view.modify();
			result = dao.modifyBoard(conn, no, b);
			if(result>0) {
				view.printMsg("수정 완료");
				JDBCTemplate.commit(conn);
			}else {
				view.printMsg("수정 실패");
				JDBCTemplate.rollback(conn);
			}
		}else{
			view.printMsg("작성자만이 수정이 가능합니다.");
		}
		JDBCTemplate.close(conn);
		
	}
	
	private void deleteBoard() {
		int no = view.getNo();
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.selectBoard(conn,no,loginMember);
		
		if(result>0) {			
			result = dao.deleteBoard(conn, no);
			if(result>0) {
				view.printMsg("삭제 완료");
				JDBCTemplate.commit(conn);
			}else {
				view.printMsg("삭제 실패");
				JDBCTemplate.rollback(conn);
			}
		}else{
			view.printMsg("작성자만이 삭제가 가능합니다.");
		}
		JDBCTemplate.close(conn);	
	}



	private void myInformation() {
		view.myInfomation(loginMember);	
	}
	private void modifyInformation() {
		Member m = view.modifyInformation();
		m.setMemberId(loginMember.getMemberId());
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.modifyInformation(conn,m);
		
		if(result > 0) {
			view.printMsg("수정완료");
			JDBCTemplate.commit(conn);
		}else {
			view.printMsg("수정실패");
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);	
	}


	private void logOut() {
		view.printMsg("로그아웃~");
		loginMember = null;
		
	}
}




















