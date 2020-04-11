package Util;

import java.io.Serializable;

public class MessageSender implements Serializable {
	private static final long serialVersionUID = -1513392875558070127L;
	private String postType;
	private Object content;

	public MessageSender(String postType, Object content) {
		this.postType = postType;
		this.content = content;
	}

	public Object getContent() {
		return this.content;
	}

	public String getPostType() {
		return this.postType;
	}
}