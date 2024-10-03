package com.crm.controller;

import java.util.Base64;

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

		User admin = loginService.adminLogin(true, user.getEmail());
		User users = loginService.adminLogin(false, user.getEmail());

		if (admin != null && user.getPassword().equals(admin.getPassword())) {
			try {
				session.setAttribute("userSession", admin.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			User userDB = loginService.adminLogin(true, user.getEmail());
			
			session.setAttribute("loginUserId", userDB.getId());
			session.setAttribute("userProflie", admin);
			model.addAttribute("title", "Dashboard");
			return "redirect:/admin-dashboard";
			
		} else if (users != null && user.getPassword().equals(users.getPassword())) {

			try {
				session.setAttribute("userSession", users.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			User userDB = loginService.adminLogin(false, user.getEmail());

			session.setAttribute("loginUserId", userDB.getId());
			session.setAttribute("userProfile", users);
			model.addAttribute("title", "Dashboard");
			return "redirect:/user-dashboard";
		} else {
			model.addAttribute("errorMsg", "Username or password is incorrect!");
			System.out.println("Failed");
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
