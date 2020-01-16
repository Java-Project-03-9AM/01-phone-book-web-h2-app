package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.domain.UnlockAccount;
import com.example.service.ContactService;

@Controller
public class UnlockAccountController {

	@Autowired
	private ContactService contactService;

	@RequestMapping(value = "/unlock")
	public String loadUnlockForm(HttpServletRequest req, Model model) {

		UnlockAccount unlockAccObj = new UnlockAccount();

		String email = req.getParameter("email");
		if (email != null && !"".equals(email)) {
			unlockAccObj.setEmail(email);
		}

		model.addAttribute("unlockAcc", unlockAccObj);

		return "unlock-acc-form";
	}

	@RequestMapping(value = "/unlockAccount", method = RequestMethod.POST)
	public String handleSubmitBtn(@ModelAttribute("unlockAcc") UnlockAccount acc, Model model) {
		boolean flag = contactService.unlockAccount(acc);
		if (flag) {
			model.addAttribute("succMsg", "Your account unlocked... continue with login");
		} else {
			model.addAttribute("errMsg", "Enter Correct Temporary Pwd sent in Email");
		}
		return "unlock-acc-form";
	}

}
