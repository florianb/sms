package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import java.util.Date;

public class Message {

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	
	public Boolean isOrigin() {
		return origin;
	}

	public void setOrigin(Boolean origin) {
		this.origin = origin;
	}
	
	//creationDate
	private Date date;
	
	private String content;
	
	private String topic;
	
	private Long userId;
	
	private Boolean origin;
}
