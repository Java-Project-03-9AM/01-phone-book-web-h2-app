package com.example.domain;

import lombok.Data;

@Data
public class Contact {

	private Integer contactId;
	private String contactName;
	private String contactEmail;
	private Long phNo;
}
