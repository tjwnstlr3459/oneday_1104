package notice.model.vo;

public class NoticeComment {
	
	private int ncNo;
	private int ncLevel;
	private String ncWriter;
	private String ncContent;
	private String ncDate;
	private int noticeRef;
	private int ncRef;
	public NoticeComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NoticeComment(int ncNo, int ncLevel, String ncWriter, String ncContent, String ncDate, int noticeRef,
			int ncRef) {
		super();
		this.ncNo = ncNo;
		this.ncLevel = ncLevel;
		this.ncWriter = ncWriter;
		this.ncContent = ncContent;
		this.ncDate = ncDate;
		this.noticeRef = noticeRef;
		this.ncRef = ncRef;
	}
	public int getNcNo() {
		return ncNo;
	}
	public void setNcNo(int ncNo) {
		this.ncNo = ncNo;
	}
	public int getNcLevel() {
		return ncLevel;
	}
	public void setNcLevel(int ncLevel) {
		this.ncLevel = ncLevel;
	}
	public String getNcWriter() {
		return ncWriter;
	}
	public void setNcWriter(String ncWriter) {
		this.ncWriter = ncWriter;
	}
	public String getNcContent() {
		return ncContent;
	}
	public String getNcContentBr() {
		return ncContent.replaceAll("\r\n","<br>");
	}
	
	public void setNcContent(String ncContent) {
		this.ncContent = ncContent;
	}
	public String getNcDate() {
		return ncDate;
	}
	public void setNcDate(String ncDate) {
		this.ncDate = ncDate;
	}
	public int getNoticeRef() {
		return noticeRef;
	}
	public void setNoticeRef(int noticeRef) {
		this.noticeRef = noticeRef;
	}
	public int getNcRef() {
		return ncRef;
	}
	public void setNcRef(int ncRef) {
		this.ncRef = ncRef;
	}
	
}
