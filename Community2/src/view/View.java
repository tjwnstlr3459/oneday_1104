package view;

import java.util.ArrayList;
import java.util.Scanner;

import vo.Board;
import vo.Member;

public class View {

	Scanner sc = new Scanner(System.in);

	public int menu() {
		System.out.println("====KH커뮤니티v2====");
		System.out.println("1. 로그인하기");
		System.out.println("2. 회원가입");
		System.out.println("3. 아이디 찾기");
		System.out.println("0. 프로그램 종료");
		System.out.print("선택>>");		
		return sc.nextInt();
	}

	public void printMsg(String str) {
		System.out.println(str);		
	}

	public String getId() {
		//Member m = new Member();
		System.out.print("ID 입력 : ");
		String id = sc.next();	
		return id;
	}
	
	public Member insertMember() {
		Member m = new Member();
		
		System.out.print("PW 입력 : ");
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
		System.out.println("====아이디 찾기====");
		Member m = new Member();
		System.out.print("이름 입력 : ");
		String name = sc.next();
		m.setMemberName(name);
		
		System.out.print("전화번호 입력 : ");
		int phone = sc.nextInt();
		m.setPhone(phone);
		return m;
		
		
		
	}

	public Member login() {
		System.out.println("====로그인====");
		Member m = new Member();
		
		System.out.print("ID 입력 : ");
		String id = sc.next();
		m.setMemberId(id);
		
		System.out.print("PW 입력 : ");
		String pw = sc.next();
		m.setMemberPw(pw);
		return m;
	}

	public int loginMenu() {
		System.out.println("====KH커뮤니티v2====");
		System.out.println("1. 게시물 목록 보기");
		System.out.println("2. 게시물 상세 보기");
		System.out.println("3. 게시물 등록");
		System.out.println("4. 게시물 수정");
		System.out.println("5. 게시물 삭제");
		System.out.println("6. 내정보 보기");
		System.out.println("7. 내 정보 변경");
		System.out.println("8. 회원 탈퇴");
		System.out.println("0. 로그아웃");
		System.out.print("선택>>");
		return sc.nextInt();
	}

	public Board insertBoard() {
		Board b = new Board();
		System.out.print("제목 입력 : ");
		String title = sc.next();
		b.setBoardTitle(title);
		
		sc.nextLine();
		System.out.print("내용 입력 : ");
		String content = sc.nextLine();
		b.setContent(content);
		
		return b;
	}

	public void printAllBoard(ArrayList<Board> list) {
		System.out.println("====게시물 목록====");
		System.out.println("번호\t제목\t이름\t조회수\t날짜");
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getBoardNo()+"\t"+
								list.get(i).getBoardTitle()+"\t"+
								list.get(i).getWriter()+"\t"+
								list.get(i).getCount()+"\t"+
								list.get(i).getwDate());
	
		}
		/*for (Board b : list) {
			System.out.println(b);
		}*/
	}

	public int getNo() {
		System.out.print("게시물 번호 입력 : ");
		return sc.nextInt();
	}

	public void printOneBoard(Board b) {
		System.out.println("====게시글 정보====");
		System.out.println("게시물 번호 : "+b.getBoardNo());
		System.out.println("게시물 제목 : "+b.getBoardTitle());
		System.out.println("게시물 내용 : "+b.getContent());
		System.out.println("게시물 작성자 : "+b.getWriter());
		System.out.println("게시물 조회수: "+b.getCount());
		System.out.println("게시물 작성일 : "+b.getwDate());		
	}

	public Board modify() {
		Board b = new Board();
		System.out.print("수정할 제목 입력 : ");
		String title = sc.next();
		b.setBoardTitle(title);
		
		sc.nextLine();
		System.out.print("수정할 내용 입력 : ");
		String content = sc.nextLine();
		b.setContent(content);
		return b;
	
		
	}

	public void myInfomation(Member loginMember) {
		System.out.println("====내 정보 보기====");
		System.out.println("회원번호 : "+ loginMember.getMemberNo());
		System.out.println("아이디 : "+ loginMember.getMemberId());
		System.out.println("비밀번호 : "+ loginMember.getMemberPw());
		System.out.println("이름 : "+ loginMember.getMemberName());
		System.out.println("전화번호 : "+ loginMember.getPhone());	
	}

	public Member modifyInformation() {
		Member m = new Member();
		System.out.println("====내 정보 수정====");
		System.out.print("수정할 PW 입력 : ");
		String pw = sc.next();
		m.setMemberPw(pw);
		
		System.out.print("수정할 전화번호 입력 (ex)01011112222): ");
		int phone = sc.nextInt();
		m.setPhone(phone);
		return m;
	}



	
	
}
























