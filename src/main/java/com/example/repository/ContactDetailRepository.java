package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.ContactDetailEntity;

@Repository
public interface ContactDetailRepository extends JpaRepository<ContactDetailEntity, Integer> {

	@Transactional
	@Modifying
	@Query("update ContactDetailEntity set activeSw=:sw where contactId=:cid")
	public void update(String sw, Integer cid);

	@Query("from ContactDetailEntity where contactEmail=:email and userPwd=:pwd")
	public ContactDetailEntity findByContactEmailAndUserPwd(String email, String pwd);

	public ContactDetailEntity findByContactEmail(String email);
}
