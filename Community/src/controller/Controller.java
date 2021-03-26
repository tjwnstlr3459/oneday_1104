package controller;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import model.dao.Dao;
import model.vo.Board;
import model.vo.Member;
import view.View;

public class Controller {
	View view;
	Board board;
	Member member;
	Member loginMember;
	Dao dao;

	public Controller() {
		super();
		view = new View();
		board = new Board();
		member = new Member();
		loginMember = new Member();
		dao = new Dao();
	}

	public void main() {

		while (true) {
			switch (view.mainMenu()) {
			case 1: // 로그인하기
				Member m = view.login();

				Connection conn = JDBCTemplate.getConnection();
				int result = dao.loginMember(conn, m);
				if (result > 0) {
					view.printMsg("로그인 성공!");
					loginMember = m;
					loginMenu(m);
				} else {
					view.printMsg("아이디 또는 비밀번호를 확인하세요");
					break;
				}
				JDBCTemplate.close(conn);
				break;

			case 2: // 회원가입
				insertMember();
				break;
			case 3: // 아이디찾기
				selectId();
				break;

			case 0:
				view.printMsg("프로그램종료~");
				break;
			}
		}
	}

	private void loginMenu(Member m) {
		while (true) {
			switch (view.loginMenu()) {
			case 1: // 게시물 목록보기
				printAllPost(m);
				break;
			case 2: // 게시물 상세 보기
				printPost();
				break;
			case 3: // 게시물 등록
				insertPost();
				break;
			case 4: // 게시물 수정			
				modifyPost(m);
				break;
			case 5: // 게시물 삭제
				break;
			case 6: // 내정보 보기
				break;
			case 7: // 내정보 변경
				break;
			case 8: // 회원탈퇴
				break;
			case 0: // 로그아웃
				view.printMsg("로그아웃~");
				break;
			}
		}

	}


	private void insertMember() { // 회원가입
		Member m = view.insertMember();
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertMember(conn, m);

		if (result > 0) {
			view.printMsg("가입성공!");
			JDBCTemplate.commit(conn);
		} else {
			view.printMsg("가입실패!");
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

	}

	private void selectId() { // 아이디찾기
		Member id = view.selectId();
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.selectMember(conn, id);
		if (result > 0) {
			view.printMsg("아이디는[" + id.getMemberId() + "]");
			JDBCTemplate.commit(conn);
		} else {
			view.printMsg("일치하는정보가 없습니다");
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
	}

	////////////////////////////////////////////////////////////////////
	// 로그인 메뉴

	private void printAllPost(Member m) { // 게시글 전체 출력
		view.printMsg("====게시물 목록====");
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Board> list = dao.boardAllPrint(conn,m);
		if (list.isEmpty()) {
			view.printMsg("게시물이 없습니다.");
		} else {
			
			view.PrintAllPost(list);
		}
	}

	private void insertPost() { // 게시물 등록
		Board b = view.insertPosted();

		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertBoard(b, conn);

		if (result > 0) {
			view.printMsg("게시글 등록 성공!");
			JDBCTemplate.commit(conn);
		} else {
			view.printMsg("등록 실패!");
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
	}
	private void printPost() {
		int no = view.getNo();
		
		Connection conn = JDBCTemplate.getConnection();
		Board b = dao.selectBoard(conn,no);
		
		if(b==null) {
			view.printMsg("번호가 잘못되었습니다.");
		}else {
			view.PrintPost(b);
		}	
		JDBCTemplate.close(conn);
	}
	private void modifyPost(Member m) {
		int no = view.getNo();
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.modifyLoginBoard(conn,no,m);
		
		if(result==0) {
			view.printMsg("작성자만 수정이 가능합니다.");
		}else {
			Board b = view.modifyPost();
			result = dao.modifyPost(b,conn,m);
			view.printMsg("게시글 수정 완료");
		}
		JDBCTemplate.close(conn);	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
