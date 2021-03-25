package board.controller;

import java.sql.Date;
import java.util.ArrayList;

import board.dao.BoardDao;
import board.view.BoardView;
import board.vo.Board;

public class BoardController {

	Board board;
	BoardDao dao;
	BoardView view;

	public BoardController() {
		super();
		board = new Board();
		view = new BoardView();
		dao = new BoardDao();
	}

	public void main() {

		while (true) {
			switch (view.showMenu()) {
			case 1:
				ArrayList<Board> b = dao.boardAllPrint();
				view.printAllBoard(b);	//게시판 출력
							
				int sel = view.board();
				switch (sel) {
				case 1:	//게시물 상세보기
					int check = view.getNum();
					Board checkNum = dao.selectBoardNum(check);
					view.printNumBoard(checkNum);
					break;
					
				case 2:	//게시물 수정하기
					int modifyNum = view.getNum();
					if(modifyNum == 0) {
						view.printMsg("글번호를 확인해주세요");
					}else {
						Board mo = view.modifyBoard();
						int modify = dao.modifyBoardNum(modifyNum,mo);
						if(modify > 0) {
							view.printMsg("수정완료");
							view.printAllBoard(b);
						}else {
							view.printMsg("수정실패");
						}		
					}
					break;
					
				case 3:	//게시물 삭제
					int delete = view.getNum();
					if(delete == 0) {
						view.printMsg("글번호를 확인해주세요");
					}else {
						String ch = view.choice();
						if(ch.equals("yes")) {
							int deleteNum = dao.deleteBoardNum(delete);
							if(deleteNum > 0) {
								view.printMsg("삭제완료");
								view.printAllBoard(b);
							}else {
								view.printMsg("삭제실패");
							}									
						}else if(ch.equals("no")) {
							view.printMsg("삭제가 취소되었습니다.");
							break;
						}else {
							view.printMsg("잘못 입력하셨습니다.");
						}
					}
					break;
					
				case 0:	//메인으로 돌아가기
					view.printMsg("메인으로");
					break;
				}			
				break;
				
			case 2:
				Board writ = view.boardInsert();
				int result = dao.boardInsert(writ);
				if (result > 0) {
					view.printMsg("등록완료");
				} else {
					view.printMsg("등록실패");
				}
				break;
			case 0:
				view.printMsg("프로그램종료");
				return;
			}

		}
	}

}
