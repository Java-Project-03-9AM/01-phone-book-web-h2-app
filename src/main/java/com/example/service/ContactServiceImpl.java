package com.example.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Contact;
import com.example.domain.UnlockAccount;
import com.example.entity.ContactDetailEntity;
import com.example.repository.ContactDetailRepository;
import com.example.util.EmailUtils;

@Service
public class ContactServiceImpl implements ContactService {

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	@Autowired
	private ContactDetailRepository contactDtlsRepo;

	@Autowired
	private EmailUtils emailUtils;

	/**
	 * This method is used to save contact record
	 */
	@Override
	public boolean saveContact(Contact c) {
		ContactDetailEntity entity = new ContactDetailEntity();
		BeanUtils.copyProperties(c, entity);
		entity.setActiveSw("Y");

		entity.setAccountStatus("Locked");
		entity.setUserPwd(generateRandomPwd(6));

		entity = contactDtlsRepo.save(entity);
		boolean isSaved = entity.getContactId() > 0;

		if (isSaved) {
			sendRegEmail(entity);
		}

		return isSaved;
	}

	@Override
	public List<Contact> getAllContacts() {
		List<Contact> contactsList = new ArrayList();
		// db call
		List<ContactDetailEntity> contactEntities = contactDtlsRepo.findAll();

		List<ContactDetailEntity> filteredlist = contactEntities.stream()
				.filter(entity -> "Y".contentEquals(entity.getActiveSw())).collect(Collectors.toList());

		if (!filteredlist.isEmpty()) {
			filteredlist.forEach(entity -> {
				Contact contact = new Contact();
				BeanUtils.copyProperties(entity, contact);
				contactsList.add(contact);
			});
		}
		return contactsList;
	}

	@Override
	public Contact getContactById(Integer cid) {
		Contact c = null;
		Optional<ContactDetailEntity> optional = contactDtlsRepo.findById(cid);
		if (optional.isPresent()) {
			ContactDetailEntity entity = optional.get();
			c = new Contact();
			BeanUtils.copyProperties(entity, c);
		}
		return c;
	}

	@Override
	public boolean updateContact(Contact c) {
		return false;
	}

	@Override
	public boolean deleteContactById(Integer cid) {
		contactDtlsRepo.update("N", cid);
		return true;
	}

	private void sendRegEmail(ContactDetailEntity c) {
		String subject = "TollPlus Registration";
		String body = null;
		try {
			body = getMailBody(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			emailUtils.sendEmail(c.getContactEmail(), subject, body);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public String getMailBody(ContactDetailEntity c) throws Exception {
		StringBuilder body = new StringBuilder();

		String fileName = "reg-email-template.txt";
		FileReader fr = new FileReader(new File(fileName));
		BufferedReader br = new BufferedReader(fr);

		String line = br.readLine();
		while (line != null) {
			if (line.contains("${NAME}")) {
				line = line.replace("${NAME}", c.getContactName());
			}

			if (line.contains("${TEMP-PWD}")) {
				line = line.replace("${TEMP-PWD}", String.valueOf(c.getUserPwd()));
			}

			if (line.contains("${EMAIL-ID}")) {
				line = line.replace("${EMAIL-ID}", c.getContactEmail());
			}
			body.append(line);
			line = br.readLine();
		}
		br.close();

		return body.toString();
	}

	public String generateRandomPwd(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	@Override
	public boolean unlockAccount(UnlockAccount account) {
		ContactDetailEntity entity = contactDtlsRepo.findByContactEmailAndUserPwd(account.getEmail(),
				account.getTempPwd());
		if (entity != null) {
			entity.setAccountStatus("UnLocked");
			entity.setUserPwd(account.getNewPwd());
			entity = contactDtlsRepo.save(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public String findByEmail(String email) {
		ContactDetailEntity entity = contactDtlsRepo.findByContactEmail(email);
		if(null!=entity) {
			return "Duplicate";
		}
		return "Unique";
	}
}
