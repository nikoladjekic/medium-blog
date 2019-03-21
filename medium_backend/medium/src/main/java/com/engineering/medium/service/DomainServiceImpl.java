/**
 * 
 */
package com.engineering.medium.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engineering.medium.dao.DomainDAO;
import com.engineering.medium.entity.Domain;

/**
 * @author zeljko.jelicic
 *
 */
@Service
public class DomainServiceImpl implements DomainService {
	
	@Autowired
	DomainDAO domainDAO;

	/* (non-Javadoc)
	 * @see com.engineering.medium.service.DomainService#saveDomain(com.engineering.medium.entity.Domain)
	 */
	@Override
	@Transactional
	public void saveDomain(Domain domain) {
		domainDAO.saveDomain(domain);
	}

	/* (non-Javadoc)
	 * @see com.engineering.medium.service.DomainService#deleteDomain(long)
	 */
	@Override
	@Transactional
	public void deleteDomain(long domainId) {
		domainDAO.deleteDomain(domainId);
	}

	/* (non-Javadoc)
	 * @see com.engineering.medium.service.DomainService#getAllDomains()
	 */
	@Override
	@Transactional
	public List<Domain> getAllDomains() {
		return domainDAO.getAllDomains();
	}

	@Override
	@Transactional
	public Domain getDomainById(long domainId) {
		return domainDAO.getDomainById(domainId);
	}

	@Override
	@Transactional
	public Domain getDomainByName(String domainName) {
		return domainDAO.getDomainByName(domainName);
	}

}
