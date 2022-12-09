package jmaster.io.notificationservice.service;

import jmaster.io.notificationservice.model.MessageDTO;

public interface EmailService {
	void sendEmail(MessageDTO messageDTO);
}
