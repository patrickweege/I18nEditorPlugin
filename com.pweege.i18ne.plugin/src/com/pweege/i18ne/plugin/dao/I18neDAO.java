package com.pweege.i18ne.plugin.dao;

import com.pweege.i18ne.plugin.model.I18nMessages;

public class I18neDAO {
	
	public I18nMessages getMessages() {
		I18nMessages messages = new I18nMessages();
		for(int i = 0; i < 10; i++) {
			messages.addMessage("com.teste.1" + i, "Message Key " + i);
		}

		return messages;
	}
		
		

}
