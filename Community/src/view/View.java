package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.vo.Board;
import model.vo.Member;


public class View {
	Scanner sc = new Scanner(System.in);

	
	public void printMsg(String str) {
		System.out.println(str);		
	}
	public int getNo() {
		System.out.print("게시물 번호 입력 : ");
		return sc.nextInt();
	}
	

	public int mainMenu() {
		System.out.println("====KH커뮤니티====");
		System.out.println("1. 로그인 하기");
		System.out.println("2. 회원가입");
		System.out.println("3. 아이디 찾기");
		System.out.println("0. 프로그램 종료");
		System.out.print("선택>>");
		return sc.nextInt();		
	}


	public Member insertMember() {
		Member m = new Member();
		System.out.println("====회원가입====");
		
		System.out.print("ID 입력 : ");
		String id = sc.next();
		m.setMemberId(id);
		
		System.out.print("PD 입력 : ");
		String pw = sc.next();
		m.setMemberPw(pw);
		
		System.out.print("이름 입력 : ");
		String name = sc.next();
		m.setMemberName(name);
		
		System.out.print("전화번호 입력 : ");
		int phone = sc.nextInt();
		m.setPhone(phone);
	
		return m;
	}


	public Member selectId() {
		System.out.println("====아이디찾기====");
		Member m = new Member();
		System.out.print("이름 입력  : ");
		String name = sc.next();
		m.setMemberName(name);
		
		System.out.print("전화번호 입력  : ");
		int phone = sc.nextInt();
		m.setPhone(phone);

		
		return m;
	}


	public Member login() {
		Member m = new Member();
		System.out.println("====로그인====");
		System.out.print("ID 입력 : ");
		String id = sc.next();
		m.setMemberId(id);
		
		System.out.print("PW 입력 : ");
		String pw = sc.next();
		m.setMemberPw(pw);
		
		return m;
	}


	public int loginMenu() {
		System.out.println("====KH커뮤니티====");
		System.out.println("1. 게시물 목록 보기");
		System.out.println("2. 게시물 상세 보기");
		System.out.println("3. 게시물 등록");
		System.out.println("4. 게시물 수정");
		System.out.println("5. 게시물 삭제");
		System.out.println("6. 내정보 보기");
		System.out.println("7. 내정보 변경");
		System.out.println("8. 회원탈퇴");
		System.out.println("0. 로그아웃");
		System.out.print("선택>>");
		return sc.nextInt();		
	}
////////////////////////////////////////////
//로그인 메뉴
	public Board insertPosted() {
		Board b = new Board();
		System.out.print("제목입력 : ");
		String title = sc.next();
		b.setBoardTitle(title);
		
		System.out.print("내용 입력 : ");
		String content = sc.next();
		b.setBoardContent(content);
			
		return b;		
	}


	public void PrintAllPost(ArrayList<Board> list) {
		System.out.println("번호\t제목\t작성자\t조회수\t작성날짜");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getBoardNo()+"\t"+
								list.get(i).getBoardTitle()+"\t"+								
								list.get(i).getBoardWriter()+"\t"+
								list.get(i).getCount()+"\t"+
								list.get(i).getWDate());						
		}
		
	}
	public void PrintPost(Board b) {
		int count = 0;
		System.out.println("====게시글 정보====");
		System.out.println("게시물 번호 : " + b.getBoardNo());
		System.out.println("게시물 제목 : " + b.getBoardTitle());
		System.out.println("게시물 내용 : " + b.getBoardContent());
		System.out.println("게시물 작성자 : " + b.getBoardWriter());
		System.out.println("게시물 조회수 : " + b.getCount());
		System.out.println("게시물 작성일 : " + b.getWDate());	
	}
	public Board modifyPost() {
		Board b = new Board();
		System.out.print("수정할 제목 입력 : ");
		String title = sc.next();
		b.setBoardTitle(title);
		
		System.out.print("수정할 내용 입력 : ");
		String content = sc.next();
		b.setBoardContent(content);
		
		return b;
		
		
		
	}


	
}






















