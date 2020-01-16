package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Contact;
import com.example.service.ContactService;

@Controller
public class ContactInfoController {

	@Autowired
	private ContactService contactService;

	@RequestMapping("/")
	public String displayContactForm(Model model) {
		Contact c = new Contact();// form binding obj
		model.addAttribute("contact", c);// adding to model scope
		return "contactInfo";// logical view name
	}

	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	public String handleSubmitBtn(@ModelAttribute("contact") Contact c, Model model) {
		boolean flag = contactService.saveContact(c);
		if (flag) {
			model.addAttribute("succMsg", "Registration almost done, check your mail to unlock account.");
		} else {
			model.addAttribute("errMsg", "Failed to save contact");
		}
		return "contactInfo";
	}

	@RequestMapping("/viewContacts")
	public String viewContacts(Model model) {
		List<Contact> contactsList = contactService.getAllContacts();
		model.addAttribute("contacts", contactsList);// sending data to UI
		return "viewContacts";// logical view name
	}

	@GetMapping("/validateEmail")
	public @ResponseBody String validateEmail(HttpServletRequest req) {
		String email = req.getParameter("email");
		return contactService.findByEmail(email);
	}
}
