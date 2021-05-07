package photo.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JdbcTemplate;
import photo.model.dao.PhotoDao;
import photo.model.vo.Photo;

public class PhotoService {
	// 사진 게시물 총 갯수 조회
	public int totalCount() {
		Connection conn = JdbcTemplate.getConnection();
		int totalCount = new PhotoDao().totalCount(conn);

		JdbcTemplate.close(conn);

		return totalCount;
	}

	// 사진 게시물 작성
	public int insertPhoto(Photo p) {
		Connection conn = JdbcTemplate.getConnection();
		int result = new PhotoDao().insertPhoto(conn, p);

		if (result > 0) {
			JdbcTemplate.commit(conn);
		} else {
			JdbcTemplate.rollback(conn);
		}
		JdbcTemplate.close(conn);

		return result;
	}

	// 사진 더보기
	public ArrayList<Photo> morePhoto(int start) {
		Connection conn = JdbcTemplate.getConnection();
		int length = 5;
		int end = start + length - 1;
		
		ArrayList<Photo> list = new PhotoDao().morePhoto(conn, start, end);
		
		JdbcTemplate.close(conn);
		
		return list;
	}
}