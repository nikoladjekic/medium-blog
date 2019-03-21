package com.engineering.medium.dao;

import java.util.List;

import com.engineering.medium.entity.Logcounter;

public interface LogcounterDAO {

	public List<Logcounter> getAllLogs();
	
	public List<Logcounter> getAllLogsForBlog(long blogId);
	
	public Long getNumberOfLogsForBlog(long blogId);
	
	public void saveLogCounter(Logcounter logcounter);
	
}
