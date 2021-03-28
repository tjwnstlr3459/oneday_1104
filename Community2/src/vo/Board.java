package vo;

import java.sql.Date;

public class Board {

	private int boardNo;
	private String boardTitle;
	private String content;
	private String writer;
	private int count;
	private Date wDate;
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Board(int boardNo, String boardTitle, String content, String writer, int count, Date wDate) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.content = content;
		this.writer = writer;
		this.count = count;
		this.wDate = wDate;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Date getwDate() {
		return wDate;
	}
	public void setwDate(Date wDate) {
		this.wDate = wDate;
	}
	
	
}
