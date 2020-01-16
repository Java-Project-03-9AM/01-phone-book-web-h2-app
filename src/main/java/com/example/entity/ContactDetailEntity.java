package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CONTACT_DTLS")
public class ContactDetailEntity {

	@GeneratedValue
	@Id
	@Column(name = "CONTACT_ID")
	private Integer contactId;

	@Column(length = 50, name = "CONTACT_NAME")
	private String contactName;

	@Column(length = 50, name = "CONTACT_EMAIL")
	private String contactEmail;

	@Column(length = 10, name = "CONTACT_NUM")
	private Long phNo;

	@Column(name = "ACTIVE_SW")
	private String activeSw;

	@Column(name = "ACC_STATUS")
	private String accountStatus;

	@Column(name = "USER_PWD")
	private String userPwd;
}
