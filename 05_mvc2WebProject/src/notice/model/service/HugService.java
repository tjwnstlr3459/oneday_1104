package notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import notice.model.vo.Notice;
import notice.model.vo.NoticePageData;
import noticemodel.dao.HugDao;

public class HugService {

	public NoticePageData selectPage(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		
		int printPage = 10;		//보여질 게시물 갯수
		int end = reqPage * 10;	//DB에 BETWEEN ? AND ? 의 변수만들기
		int start = end-9;
		
		HugDao dao = new HugDao();
		ArrayList<Notice> list =  dao.printPage(conn,start,end);
		
		//하단 네비바만들기
		
		//총게시물 갯수
		int totalPage = dao.allPage(conn);
		
		//총게시물에 비래한 페이지넘버
		int pageNo = 0;
		if(totalPage%10==0) {
			pageNo = totalPage/10;
		}else {
			pageNo = totalPage/10 + 1;
		}
		System.out.println(totalPage);
		System.out.println(list.size());
		//하단에 숫자제한개수
		int pageNaviNo = 5;
		
		//현재 페이지넘버에 비래한 1/6/11/16/출력하게 하기
		int page = ((reqPage-1)/5)*5+1;
		
		String pageNavi = "<ul class='pagination pagination-lg'>";
		
		//이전버튼 생성
		if(page !=0) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/hugList?reqPage="+(page-1)+"'>&lt;</a></li>";
		}
		
		
		for(int i=0; i<pageNaviNo; i++) {
			if(page == reqPage) {
				pageNavi += "<li class='page-item active'>";	//눌러진페이지 엑티브 ex)3페이지 누르면 3이 엑티브되게
				pageNavi += "<a class='page-link' href='/hugList?reqPage="+page+"'>"+page+"</a></li>";
			}else {												//reqPage는 누른페이지 지정
				pageNavi += "<li class='page-item'>";//누르지 않는 페이지들
				pageNavi += "<a class='page-link' href='/hugList?reqPage="+page+"'>"+page+"</a></li>";
			}
			page++;
			if(page>totalPage) {
				break;
			}
		}
		
		//다음버튼 생성s
		if(page<=totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/hugList?reqPage="+(page)+"'>&gt;</a></li>";	
		}
		pageNavi += "</ul>";
		
		JDBCTemplate.close(conn);
		NoticePageData npd = new NoticePageData(list,pageNavi);	//보내는 순서 중요!!
		return npd;
	}
}










