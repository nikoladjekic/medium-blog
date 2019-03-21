package com.engineering.medium.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.engineering.medium.dao.LogcounterDAO;
import com.engineering.medium.entity.Logcounter;

@Service
public class LogcounterServiceImpl implements LogcounterService {

	@Autowired
	LogcounterDAO logcounterDAO;
	
	@Override
	@Transactional
	public List<Logcounter> getAllLogs() {
		return logcounterDAO.getAllLogs();
	}

	@Override
	@Transactional
	public List<Logcounter> getAllLogsForBlog(long blogId) {
		return logcounterDAO.getAllLogsForBlog(blogId);
	}

	@Override
	@Transactional
	public Long getNumberOfLogsForBlog(long blogId) {
		return logcounterDAO.getNumberOfLogsForBlog(blogId);
	}

	@Override
	@Transactional
	public void saveLogCounter(Logcounter logcounter) {
		logcounterDAO.saveLogCounter(logcounter);		
	}

}
