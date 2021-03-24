package kh.member.controller;

import java.util.ArrayList;

import kh.member.model.dao.MemberDao;
import kh.member.model.vo.Member;
import kh.member.view.MemberView;

public class MemberController {

	MemberView view;
	MemberDao dao;

	public MemberController() {
		super();
		view = new MemberView();
		dao = new MemberDao();
	}

	public void main() {
		while (true) {
			int sel = view.showMenu();

			switch (sel) {
			case 1:
				printAllMember();
				break;
			case 2:
				printOneMemberId();
				break;
			case 3:
				printMemberName();
				break;
			case 4:
				insertMember();
				break;
			case 5:
				modifyMember();
				break;
			case 6:
				deleteMember();
				break;
			case 0:
				return;
			}
		}
	}

	

	

	private void printAllMember() {
		ArrayList<Member> list = dao.getAllMember();
		if (list.size() == 0) {
			view.printMsg("회원이 없습니다");
		} else {
			view.printAllMember(list);
		}
	}

	private void printOneMemberId() {

		String memberId = view.getId("아이디");
		Member m = dao.selectOneMember(memberId);
		if (m != null) {
			view.printOneMember(m);
		} else {
			view.printMsg("조회실패");
		}
	}

	private void printMemberName() {
		String name = view.getId("이름");
		ArrayList<Member> list = dao.selectMemberName(name);
		if (list != null) {
			view.printMemberName(list);
		} else {
			view.printMsg("조회실패");
		}		
	}
	private void insertMember() {
		view.printMsg("====회원가입====");
		String memberId = null;			
		while(true) {
			memberId = view.getId("아이디");
			Member m = dao.selectOneMember(memberId);
			if(m == null) {
				break;
			}else {
				view.printMsg("이미 사용중인 아이디입니다.");
			}
		}
		Member m = view.getMember();
		m.setMemberId(memberId);
		int result = dao.insertMember(m);
		if(result > 0) {
			view.printMsg("회원 가입 성공");
		}else {
			view.printMsg("회원 가입 실패");
		}		
	}
	
	private void modifyMember() {
		view.printMsg("====회원 정보 수정====");
		String memberId = view.getId("아이디");
		
		Member m = dao.selectOneMember(memberId);
		
		if(m == null) {
			view.printMsg("존재하지 않는 회원입니다.");
		}else {
			m = view.getmodify();
			dao.updateMember(m,memberId);
			view.printMsg("수정완료");
		}	
	}

	private void deleteMember() {
		view.printMsg("====회원 정보 삭제====");
		String memberId = view.getId("아이디");
		Member m = dao.selectOneMember(memberId);
		if(m == null) {
			view.printMsg("존재하지 않는 회원입니다");
		}else {
			dao.deleteMember(memberId);
			view.printMsg("삭제완료");
		}		
	}

	
}































