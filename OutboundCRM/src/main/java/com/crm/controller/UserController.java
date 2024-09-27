package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.crm.model.Response;
import com.crm.model.User;
import com.crm.service.OtpService;
import com.crm.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private OtpService otpService;

	@PostMapping("/create-user")
	public String createUser(@ModelAttribute User user, Model model, HttpSession session) {
		try {
			user.setAdmin(false);
			// Fetching all user
			User dbuser = userService.createUser(user);
			List<User> allUser = userService.findAllUser();
			model.addAttribute("users", allUser);
			if (dbuser != null) {
				session.setAttribute("userSuccess", allUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "add-user";
	}

	@GetMapping("/deleteUser/{userId}")
	public String deleteUser(@PathVariable Long userId, HttpSession session) {
		userService.deleteUser(userId);
		session.setAttribute("deleteSuccess", "delete");
		return "redirect:/add-user";
	}

	@GetMapping("/verifyOtp")
	public Response verifyOtp(@RequestParam("userId") long userId, @RequestParam("otp") String otp) {
		boolean verifyOtp = otpService.verifyOtp(userId, otp);
		if (verifyOtp) {
			userService.deleteUser(userId);
			return new Response(true, "User deleted successfully..");
		} else {
			return new Response(false, "Invalid OTP");
		}
	}
	
	@PostMapping("/updateProfile/{id}")
	public String updateUserProfile(@RequestParam("image") MultipartFile file, @PathVariable("id") Long id, User user, Model model, HttpSession session) {
		try {
			System.out.println(id);
			User updateProfile = userService.updateProfile(file, user, id);
			if(updateProfile!=null) {
				session.setAttribute("success", updateProfile);
				System.out.println("Updated");
			}
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		return "redirect:/user-profile";
	}

}
