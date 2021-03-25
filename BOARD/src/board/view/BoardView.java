package board.view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import board.vo.Board;

public class BoardView {
	Scanner sc = new Scanner(System.in);
	

	public void printMsg(String str) {
		System.out.println(str);
	}

	public int showMenu() {
		System.out.println("====게시판====");
		System.out.println();
		System.out.println("1. 게시물 목록 보기");
		System.out.println("2. 게시물 등록");
		System.out.println("0. 프로그램 종료");
		System.out.print("선택>>>");
		return sc.nextInt();
	}
	public void printAllBoard(ArrayList<Board> b) {
		System.out.println("========게시물 목록========");
		System.out.println("글번호\t제목\t작성자\t작성일");
		
		for (int i = 0; i < b.size(); i++) {
			System.out.println(b.get(i).getBoardNo()+"\t"+
								b.get(i).getBoardTitle()+"\t"+
								b.get(i).getBoardWriter()+"\t"+
								b.get(i).getEnDate());		
		}
		System.out.println("=========================");
	}
	public Board boardInsert() {
		Board b = new Board();
		
		System.out.println("========게시물 등록========");
		System.out.println();
		System.out.print("제목 입력 : ");
		String title = sc.next();
		b.setBoardTitle(title);
		
		
		sc.nextLine();
		System.out.print("내용 입력 : ");
		String content = sc.nextLine();
		b.setBoardContent(content);
		
		System.out.print("작성자 입력 : ");
		String writer = sc.next();
		b.setBoardWriter(writer);	
		return b;
		
		
	}
	public int board() {
		System.out.println("====게시글 메뉴====");
		System.out.println();
		System.out.println("1. 게시물 상세보기");
		System.out.println("2. 게시물 수정");
		System.out.println("3. 게시물 삭제");
		System.out.println("0. 메인으로 돌아가기");
		System.out.print("선택>>>");
		return sc.nextInt();
	}

	public int getNum() {
		System.out.print("글번호 : ");
		return sc.nextInt();
	}

	public void printNumBoard(Board boardNum) {
		System.out.println("====게시글 상세보기====");
		System.out.println();
		System.out.println("글번호 : "+boardNum.getBoardNo());
		System.out.println("제목 : "+boardNum.getBoardTitle());
		System.out.println("내용 : "+boardNum.getBoardContent());
		System.out.println("작성자 : "+boardNum.getBoardWriter());
		System.out.println("작성일 : "+boardNum.getEnDate());
		
	}

	public Board modifyBoard() {
		Board b = new Board();
		System.out.println("====게시글 수정====");
		System.out.print("수정 할 제목 입력 : ");
		String title = sc.next();
		b.setBoardTitle(title);
		
		sc.nextLine();
		System.out.print("수정 할 내용 입력 : ");
		String content = sc.nextLine();
		b.setBoardContent(content);
		
		return b;
		
	}

	public String choice() {
		System.out.print("정말 삭제하시겠습니까?[yes/no] : ");	
		return sc.next();
	}

	




}






















