package com.spmia.authentication.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spmia.authentication.model.UserOrganization;

@Repository
public interface OrgUserRepository extends CrudRepository<UserOrganization, String> {
	 public UserOrganization findByUserName(String userName);
}
