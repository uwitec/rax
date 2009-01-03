package erp.model;

import java.util.Date;

public class Evaluation {

	private String content;
	private Date pubDate;
	private String name;
	private String explain;

	public Evaluation() {
		content = "";
		pubDate = new Date();
		name = "";
		explain = "";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}
