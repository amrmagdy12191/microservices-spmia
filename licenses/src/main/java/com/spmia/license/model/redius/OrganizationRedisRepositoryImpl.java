package com.spmia.license.model.redius;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.spmia.license.model.Organization;

@Repository
public class OrganizationRedisRepositoryImpl implements OrganizationRedisRepository {
	private static final String HASH_NAME="organization";
	
	private RedisTemplate<String, Organization> rediusTemplate;
	
	private HashOperations hashOperations;
	
	public OrganizationRedisRepositoryImpl() {
		super();
	}
	
	@Autowired
	public OrganizationRedisRepositoryImpl(RedisTemplate redisTemplate) {
		this.rediusTemplate = redisTemplate;
	}
	
	@PostConstruct
	private void init() {
		hashOperations = rediusTemplate.opsForHash();
	}
	
	
	
	@Override
	public void saveOrganization(Organization org) {
		hashOperations.put(HASH_NAME, org.getId(), org);
	}

	@Override
	public void updateOrganization(Organization org) {
		hashOperations.put(HASH_NAME, org.getId(), org);
	}

	@Override
	public void deleteOrganization(String organizationId) {
		hashOperations.delete(HASH_NAME, organizationId);

	}

	@Override
	public Organization findOrganization(String organizationId) {
		return (Organization)hashOperations.get(HASH_NAME, organizationId);
	}

}
