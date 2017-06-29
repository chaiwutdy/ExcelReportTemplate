package com.report.generator;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.Multipart;

public interface MailGenerator {
	public List<String> getMailToList();
	public List<String> getMailCCList();
	public String getSubject();
	public Multipart getContent();
	public void setDataOfMail(String fileDataSourceName) throws MessagingException;
}
