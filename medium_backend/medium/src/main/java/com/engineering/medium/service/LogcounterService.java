package com.engineering.medium.service;

import java.util.List;

import com.engineering.medium.entity.Logcounter;

public interface LogcounterService {


	public List<Logcounter> getAllLogs();
	
	public List<Logcounter> getAllLogsForBlog(long blogId);
	
	public Long getNumberOfLogsForBlog(long blogId);
	
	public void saveLogCounter(Logcounter logcounter);

	
}
