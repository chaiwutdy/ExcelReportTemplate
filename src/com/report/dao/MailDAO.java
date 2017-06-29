package com.report.dao;

import java.util.List;

public interface MailDAO {
	public List<String> getToList();
	public List<String> getCCList();
}
