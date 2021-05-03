package notice.model.vo;

public class Notice {
	private int rnum;
	private int noticeNo;
	private String noticeTitle;
	private String noticeWriter;
	private String noticContent;
	private String noticeDate;
	private String filename;
	private String filepath;
	
	
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Notice(int rnum, int noticeNo, String noticeTitle, String noticeWriter, String noticContent,
			String noticeDate, String filename, String filepath) {
		super();
		this.rnum = rnum;
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeWriter = noticeWriter;
		this.noticContent = noticContent;
		this.noticeDate = noticeDate;
		this.filename = filename;
		this.filepath = filepath;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeWriter() {
		return noticeWriter;
	}
	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}
	public String getNoticContent() {
		return noticContent;
	}
	public String getNoticContentBr() {
		return noticContent.replaceAll("\r\n", "<br>");
	}
	
	public void setNoticContent(String noticContent) {
		this.noticContent = noticContent;
	}
	public String getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
}
