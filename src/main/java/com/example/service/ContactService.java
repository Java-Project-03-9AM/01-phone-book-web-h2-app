package com.example.service;

import java.util.List;

import com.example.domain.Contact;
import com.example.domain.UnlockAccount;

public interface ContactService {

	public boolean saveContact(Contact c);

	public List<Contact> getAllContacts();

	public Contact getContactById(Integer cid);

	public boolean updateContact(Contact c);

	public boolean deleteContactById(Integer cid);

	public boolean unlockAccount(UnlockAccount account);
	
	public String findByEmail(String email);

}
