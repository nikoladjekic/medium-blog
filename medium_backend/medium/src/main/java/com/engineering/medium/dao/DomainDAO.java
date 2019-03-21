package com.engineering.medium.dao;

import java.util.List;

import com.engineering.medium.entity.Domain;

public interface DomainDAO {
	
	public void saveDomain(Domain domain);
	
	public void deleteDomain(long domainId);
	
	public List<Domain> getAllDomains();
	
	public Domain getDomainById(long domainId);
	
	public Domain getDomainByName(String domainName);
		
}
