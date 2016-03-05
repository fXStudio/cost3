package com.ruling.cost.utils;

public class SystemException extends RuntimeException {
	private static final long serialVersionUID = -335620998894315124L;

	private String messageKey;

	private String[] messageArgs;

	public SystemException() {
		super();
	}

	public SystemException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable throwable) {
		super(throwable);
	}

	public SystemException(String messageKey, String message) {
		super(message);
		this.messageKey = messageKey;
	}

	public SystemException(String messageKey, String messageArgs, String message) {
		super(message);
		this.messageKey = messageKey;
		this.messageArgs = new String[] { messageArgs };
	}

	public SystemException(String messageKey, String[] messageArgs,
			String message) {
		super(message);
		this.messageKey = messageKey;
		this.messageArgs = messageArgs;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public String[] getMessageArgs() {
		return messageArgs;
	}

}
