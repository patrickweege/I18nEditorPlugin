package com.pweege.i18ne.plugin.model;

import java.util.ArrayList;
import java.util.List;

public class I18nMessages {

	private final List<I18nMessage> messages;
	
	public I18nMessages() {
		this.messages = new ArrayList<I18nMessage>();
	}
	
	public void addMessage(String key, String message) {
		I18nMessage i18Message = new I18nMessage(key);
		messages.add(i18Message);	
	}
	
	
	public List<I18nMessage> getMessages() {
		return messages;
	}

}
