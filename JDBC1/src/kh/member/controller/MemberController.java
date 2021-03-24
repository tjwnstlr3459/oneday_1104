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
				printMemberId();
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
		// dao를 통해서 DB에서 전체회원정보를 ArryaList로 받음
		ArrayList<Member> list = dao.selectAllMember();
		if (list.size() == 0) {
			// 회원이 한명도 없는 경우

		} else {
			view.printAllMember(list);
		}
	}

	private void printMemberId() {
		String memberId = view.getId("아이디");
		Member m = dao.selectOneMember(memberId);
		if (m == null) {
			// 조회실패
			view.printMsg("조회 실패!");
		} else {
			// 조회성공
			view.printOneMember(m);
		}
	}

	private void printMemberName() {
		String memberName = view.getId("이름");
		ArrayList<Member> list = dao.selectName(memberName);
		if (list.size() == 0) {
			// if(list.isEmpty()){ <<이방법으로도 조회가능
			// 조회실패
			view.printMsg("존재하지 않습니다");
		} else {
			// 조회성공
			view.printMemberName(list);
		}
	}

	private void insertMember() {
		view.printMsg("====회원 가입====");
		String memberId = null;
		while (true) {
			memberId = view.getId("아이디");
			Member member = dao.selectOneMember(memberId);
			if (member == null) {
				break;
			} else {
				view.printMsg("중복된 아이디입니다. 다시입력해주세요");
			}
		}
		// 1번째 아이디 입력방식(뷰에 아이디 보내서 그대로 가져오는방법)
		Member m = view.getMember(memberId);
		// 2번째 아이디 입력방식(뷰에는 아이디 제외한 나머지만 받아오고 아이디는 여기서 입력)
		// m.setMemberId(memberId);
		int result = dao.insertMember(m);
		if (result > 0) {
			view.printMsg("가입 성공!");
		} else {
			view.printMsg("실패");
		}
	}

	private void modifyMember() {
		view.printMsg("====회원 수정====");

		String memberId = view.getId("아이디");
		Member member = dao.selectOneMember(memberId);
		if (member == null) {
			view.printMsg("회원정보를 찾을수 없습니다.");
		} else {
			view.printMsg("====수정값 입력====");
		}

		Member m = view.getmodify();
		int result = dao.updateMember(m, memberId);
		if (result > 0) {
			view.printMsg("수정완료");
		} else {
			view.printMsg("수정실패");
		}
	}

	private void deleteMember() {
		view.printMsg("====회원 삭제====");
		String memberId = null;

		memberId = view.getId("아이디");
		Member member = dao.selectOneMember(memberId);
		if (member == null) {
			view.printMsg("회원정보를 찾을수 없습니다.");
		} else {
			dao.deleteMember(memberId);
			view.printMsg("삭제완료");

		}

		// int result = dao.deleteMember(memberId);
	}
}
