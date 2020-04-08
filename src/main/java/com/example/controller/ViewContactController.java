package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Contact;
import com.example.service.ContactService;

@Controller
public class ViewContactController {

	@Autowired
	private ContactService contactService;

	@RequestMapping("/editContact")
	public String editContact(HttpServletRequest req, Model model) {
		String contactId = req.getParameter("contactId");
		if (null != contactId && !"".equals(contactId)) {
			Integer cid = Integer.parseInt(contactId);
			Contact contact = contactService.getContactById(cid);
			model.addAttribute("contact", contact);
		}
		int j = 20;
		return "contactInfo";
	}

	@RequestMapping(value="/deleteContact")
	public String deleteContact(HttpServletRequest req) {
		String contactId = req.getParameter("contactId");
		if(null!=contactId && !"".equals(contactId)) {
			Integer cid = Integer.parseInt(contactId);
			contactService.deleteContactById(cid);
		}
		return "redirect:viewContacts";
	}

	public String addContact() {

		return null;
	}

}
