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
		while(true) {
		int sel = view.showMenu();
		
		switch (sel) {
		case 1:
			printAllMember();
			break;
		case 2:
			printMemberId();
			break;
		case 3:
			printMemberName();
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 0:
			return;
			

		}
		
		
		}
	}
	

	private void printAllMember() {
		//dao를 통해서 DB에서 전체회원정보를 ArryaList로 받음
		ArrayList<Member> list = dao.selectAllMember();
		if(list.size()==0) {
			//회원이 한명도 없는 경우
			
		}else {
			view.printAllMember(list);
		}
	}
	
	private void printMemberId() {
		String memberId = view.getId("아이디");
		Member m = dao.selectOneMember(memberId);
		if(m == null) {
			//조회실패
			view.printMsg("조회 실패!");
		}else {
			//조회성공
			view.printOneMember(m);
		}
	}
	private void printMemberName() {
		String memberName = view.getId("이름");
		ArrayList<Member> list = dao.selectName(memberName);
		if(list.size()==0) {
	  //if(list.isEmpty()){		<<이방법으로도 조회가능
			//조회실패
			view.printMsg("존재하지 않습니다");
		}else {
			//조회성공
			view.printMemberName(list);
		}
	}
	
}





























