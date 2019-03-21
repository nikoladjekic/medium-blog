package com.engineering.medium.rest.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.engineering.medium.entity.Logcounter;
import com.engineering.medium.service.BlogService;
import com.engineering.medium.service.LogcounterService;

@RestController
@RequestMapping("/logcounter")
public class LogcounterController {

	@Autowired
	LogcounterService logcounterService;

	@Autowired
	BlogService blogService;

	// ------------------- List all logcounters ---------------------------

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Logcounter>> getAllLogcounters() {
		List<Logcounter> logcounter = logcounterService.getAllLogs();
		return new ResponseEntity<List<Logcounter>>(logcounter, HttpStatus.OK);
	}

	// ------------------- Create new logcounter ---------------------------

	@PostMapping(value = "/")
	public ResponseEntity<Void> createLogCounter(@RequestBody Logcounter logcounter, HttpServletRequest request) {
		logcounter.setDate(new Date());
		logcounter.setIpadress(request.getRemoteAddr());
		logcounterService.saveLogCounter(logcounter);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// ------------------- Get logs for blog ---------------------------

	@GetMapping(value = "/blog/{blogId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Logcounter>> getAllLogcountersForBlog(@PathVariable("blogId") long blogId) {
		List<Logcounter> logcounter = logcounterService.getAllLogsForBlog(blogId);
		if(logcounter.isEmpty()) {
			return new ResponseEntity<List<Logcounter>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Logcounter>>(logcounter, HttpStatus.OK);
	}
	
	// ------------------- Get count of logs for blog ---------------------------

	@GetMapping(value = "/blog/count/{blogId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> getCountOfLogcountersForBlog(@PathVariable("blogId") long blogId) {
		Long count = logcounterService.getNumberOfLogsForBlog(blogId);
		return new ResponseEntity<Long>(count, HttpStatus.OK);
	}	

}
