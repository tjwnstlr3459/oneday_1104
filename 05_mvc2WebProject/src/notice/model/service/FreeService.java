package notice.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import common.JDBCTemplate;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import notice.model.vo.Notice;
import notice.model.vo.NoticePageData;
import noticemodel.dao.FreeDao;

public class FreeService {

	public NoticePageData selectPage(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		int Page = 10;
		int end = reqPage*10;
		int start = end-10+1;
		ArrayList<Notice> list = new FreeDao().selectPage(conn, start, end);
		
		int totalCount = new FreeDao().totalCount(conn);
		int totalSize=0;
		if(totalCount%10==0) {
			totalSize = totalCount/10;
		}else {
			totalSize = totalCount/10 +1;
		}
		System.out.println(totalSize);
		System.out.println(list.size());
		
		
		int naviSize=5;
		
		int naviPage=1;
		int naviNumber = ((reqPage-1)/naviSize)*naviSize +1;
		
		//이전벝트
		String pageNavi = "<ul class='pagination pagination-lg'>";
		if(naviNumber != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/freeList?reqPage="+(naviNumber-1)+"'>&lt;</a></li>";
		}
		
		//번호들 나열
		for(int i=0; i<naviSize; i++) {
			if(naviNumber==reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/freeList?reqPage="+naviNumber+"'>"+naviNumber+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/freeList?reqPage="+naviNumber+"'>"+naviNumber+"</a></li>";
			}
			naviNumber++;
			if(naviNumber > totalSize) {
				break;
			}
		}
		//다음버튼
		if(naviNumber<=totalSize) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/freeList?reqPage="+(naviNumber)+"'>&gt;</a></li>";
		}
		pageNavi += "</ul>";
		
		JDBCTemplate.close(conn);
		NoticePageData npd = new NoticePageData(list,pageNavi);
		return npd;
		
	}
}














	