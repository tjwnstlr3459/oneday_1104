package board.vo;

import java.sql.Date;

public class Board {

	private int 	boardNo;
	private String 	boardTitle;
	private String 	boardContent;
	private String 	boardWriter;
	private Date	enDate;
	
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

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public Date getEnDate() {
		return enDate;
	}

	public void setEnDate(Date enDate) {
		this.enDate = enDate;
	}

	public Board(int boardNo, String boardTitle, String boardContent, String boardWriter, Date enDate) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.enDate = enDate;
	}

	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return getBoardTitle()+getBoardContent()+getBoardWriter();
	}
	
}
