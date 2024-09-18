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

	// Admin Login
	@PostMapping("/login")
	public String login(@ModelAttribute User user, Model model, HttpSession session) {

		User admin = loginService.adminLogin(true, user.getEmail());
		User users = loginService.adminLogin(false, user.getEmail());

		if (admin != null && user.getPassword().equals(admin.getPassword())) {
			try {
				session.setAttribute("userSession", admin.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}

			String[] split = admin.getName().split(" ");
			String adminName = null;
			for (int i = 0; i <= split.length;) {
				adminName = split[i];
				break;
			}
			session.setAttribute("loginUserName", adminName);
			model.addAttribute("title", "Dashboard");
			return "admin-dashboard";
		} else if (users != null && user.getPassword().equals(users.getPassword())) {

			try {
				session.setAttribute("userSession", users.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}
			User userDB = loginService.adminLogin(false, user.getEmail());
			String[] split = userDB.getName().split(" ");
			String userName = null;
			for (int i = 0; i <= split.length;) {
				userName = split[i];
				break;
			}
			session.setAttribute("loginUserId", userDB.getId());
			session.setAttribute("loginUserName", userName);
			model.addAttribute("title", "Dashboard");
			return "user-dashboard";
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

		return "redirect:/login-page"; // Use redirect to prevent caching issues
	}

}
