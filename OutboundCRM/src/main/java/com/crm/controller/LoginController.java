package com.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crm.model.User;
import com.crm.service.LoginService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	// Admin and User Login
	@PostMapping("/login")
	public String login(@ModelAttribute User user, Model model, HttpSession session) {

		User admin = loginService.adminLogin("admin", user.getEmail());
		User users = loginService.adminLogin("user", user.getEmail());

		if (admin != null && user.getPassword().equals(admin.getPassword())) {
			try {
				session.setAttribute("userSession", admin.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			User userDB = loginService.adminLogin("admin", user.getEmail());
			
			session.setAttribute("loginUserId", userDB.getId());
			session.setAttribute("userProflie", admin);
			model.addAttribute("title", "Dashboard");
			return "redirect:/admin-dashboard";
			
		} else if (users != null && user.getPassword().equals(users.getPassword()) && users.getStatus().equals("Active")) {

			try {
				session.setAttribute("userSession", users.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			User userDB = loginService.adminLogin("user", user.getEmail());

			session.setAttribute("loginUserId", userDB.getId());
			session.setAttribute("userProfile", users);
			model.addAttribute("title", "Dashboard");
			return "redirect:/user-dashboard";
		} else if(users.getStatus().equals("Inactive")) {
			model.addAttribute("errorMsg", "You are inactive !");
			return "login";
		}else {
			model.addAttribute("errorMsg", "Username or password is incorrect !");
			return "login";
		}

	}

	@RequestMapping("/logout")
	public String logout(HttpSession session, HttpServletResponse response) {
		// Check if "userSession" attribute is not null
		Object userSession = session.getAttribute("userSession");
		if (userSession != null) {
			session.removeAttribute("userSession"); // Remove using the attribute name
		}
		session.invalidate(); // Invalidate the session to log out the user

		return "redirect:/login"; // Use redirect to prevent caching issues
	}

}
