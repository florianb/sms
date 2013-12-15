package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import java.util.Date;
import java.util.HashMap;

public class Message {
  
  private HashMap<Long, Message> responses;
  private Long id;
  
  public Message(Long newID, Long userID, String message, String currentTopic) {
    id = newID;
    userId = userID;
    content = message;
    topic = currentTopic;
    responses = new HashMap();
  }
  
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
    
    if (!origin && !responses.isEmpty())
      responses.clear();
	}
	
  public void addResponse(Message message) {
    responses.put(message.getID(), message);
  }
  
  public Long getID() {
    return id;
  }
  
	//creationDate
	private Date date;
	
	private String content;
	
	private String topic;
	
	private Long userId;
	
	private Boolean origin;
}
