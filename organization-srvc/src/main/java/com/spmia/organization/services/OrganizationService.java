package com.spmia.organization.services;

import com.spmia.organization.events.source.SimpleSourceBean;
import com.spmia.organization.model.Organization;
import com.spmia.organization.repository.OrganizationRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrganizationService {
	
	private static final Logger log = LoggerFactory.getLogger(OrganizationService.class);

    @Autowired
    private OrganizationRepository orgRepository;
    
    @Autowired
    private SimpleSourceBean simpleSourceBean;

    public Optional<Organization> getOrg(String organizationId) {
        return orgRepository.findById(organizationId);
    }

    public void saveOrg(Organization org){
        org.setId( UUID.randomUUID().toString());

        orgRepository.save(org);
        simpleSourceBean.publishOrgChange("SAVE", org.getId());
    }

    public void updateOrg(Organization org){
       orgRepository.save(org);
    	log.info("inside updateOrg");
        simpleSourceBean.publishOrgChange("SAVE", org.getId());
    }

    public void deleteOrg(Organization org){
        orgRepository.delete(org);
    }
}
