package com.crm.home;

import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crm.model.AssignTask;
import com.crm.model.InboundReport;
import com.crm.model.Report;
import com.crm.model.User;
import com.crm.service.AssignTaskService;
import com.crm.service.InboundAddReportService;
import com.crm.service.ReportService;
import com.crm.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {

	@Autowired
	private ReportService reportService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private AssignTaskService assignTask;

	@Autowired
	private InboundAddReportService inboundService;

//	===================================== error page ========================================
	@RequestMapping("/error-page")
	public String errorPage() {
		return "error";
	}
//	===================================== error page ========================================

	// Login page
	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute("title", "Login");
		return "login";
	}

	// Login to dashboard
	@GetMapping("/admin-dashboard")
	public String adminDashboard(Model model, HttpSession session) {
		if (session == null || session.getAttribute("userSession") == null) {
			return "redirect:/error-page";
		}
		model.addAttribute("title", "Dashboard");
		return "admin-dashboard";
	}

	// Login to dashboard
	@GetMapping("/user-dashboard")
	public String userDashboard(Model model, HttpSession session) {
		model.addAttribute("title", "Dashboard");
		if (session == null || session.getAttribute("userSession") == null) {
			return "redirect:/error-page";
		}
		// Retrieve the user ID from the session and get the user details from the database
	    Long userId;
	    try {
	        userId = Long.parseLong(session.getAttribute("userSession").toString());
	    } catch (NumberFormatException e) {
	        return "redirect:/error-page";  // Redirect if session attribute is not a valid user ID
	    }

	    User userdb = userService.getUserById(userId);

	    // If user data is present, encode the profile image to base64
	    if (userdb != null && userdb.getData() != null) {
	        byte[] content = userdb.getData(); 
	        String base64Image = Base64.getEncoder().encodeToString(content);
	        userdb.setFileName(base64Image);  // Set the base64 image as fileName (should be clarified if this is appropriate)
	    }

	    // Add user details and title to the model
	    model.addAttribute("userProfile", userdb);
		return "user-dashboard";
	}

	// Logout Handler
	@GetMapping("/login-page")
	public String logoutHandler(Model model, HttpSession session) {
		model.addAttribute("title", "Login");
		return "login";
	}

	// Report page
	@RequestMapping("/add-csv")
	public String addCSVFile(Model model, HttpSession session) {
		if (session == null || session.getAttribute("userSession") == null) {
			return "redirect:/error-page";
		}
		model.addAttribute("title", "Assign Task");
		List<User> allUser = userService.findAllUser();
		model.addAttribute("users", allUser);
		return "add-csv-file";
	}

	// Report page
	@RequestMapping("/add-user")
	public String addUser(Model model, HttpSession session) {
		if (session == null || session.getAttribute("userSession") == null) {
			return "redirect:/error-page";
		}
		model.addAttribute("title", "Add Users");
		List<User> allUser = userService.findAllUser();
		model.addAttribute("users", allUser);
		return "add-user";
	}

	// Report page
	@RequestMapping("/add-report")
	public String addReport(Model model, HttpSession session) {
		if (session == null || session.getAttribute("userSession") == null) {
			return "redirect:/error-page";
		}
		model.addAttribute("title", "Add Report");
		
		// Retrieve the user ID from the session and get the user details from the database
	    Long userId;
	    try {
	        userId = Long.parseLong(session.getAttribute("userSession").toString());
	    } catch (NumberFormatException e) {
	        return "redirect:/error-page";  // Redirect if session attribute is not a valid user ID
	    }

	    User userdb = userService.getUserById(userId);

	    // If user data is present, encode the profile image to base64
	    if (userdb != null && userdb.getData() != null) {
	        byte[] content = userdb.getData(); 
	        String base64Image = Base64.getEncoder().encodeToString(content);
	        userdb.setFileName(base64Image);  // Set the base64 image as fileName (should be clarified if this is appropriate)
	    }

	    // Add user details and title to the model
	    model.addAttribute("userProfile", userdb);
		return "add-report";
	}

	// Showing report list
	@GetMapping("/view")
	public String viewReport(Model model, HttpSession session) {
		if (session == null || session.getAttribute("userSession") == null) {
			return "redirect:/error-page";
		}
		model.addAttribute("title", "View Report");

		// fetching list of report
		List<Report> ruralList = reportService.findRuralData();
		List<Report> urbanList = reportService.findUrbanData();
		model.addAttribute("ruralReports", ruralList);
		model.addAttribute("urbanReports", urbanList);
		
		// Retrieve the user ID from the session and get the user details from the database
	    Long userId;
	    try {
	        userId = Long.parseLong(session.getAttribute("userSession").toString());
	    } catch (NumberFormatException e) {
	        return "redirect:/error-page";  // Redirect if session attribute is not a valid user ID
	    }

	    User userdb = userService.getUserById(userId);

	    // If user data is present, encode the profile image to base64
	    if (userdb != null && userdb.getData() != null) {
	        byte[] content = userdb.getData(); 
	        String base64Image = Base64.getEncoder().encodeToString(content);
	        userdb.setFileName(base64Image);  // Set the base64 image as fileName (should be clarified if this is appropriate)
	    }

	    // Add user details and title to the model
	    model.addAttribute("userProfile", userdb);
		return "view-report";
	}

	// Edit rural report
	@PostMapping("/ruralEdit")
	public String editRuralReport(@RequestParam("ruralReportId") Long id, Model model, HttpSession session) {
		if (session == null || session.getAttribute("userSession") == null) {
			return "redirect:/error-page";
		}
		model.addAttribute("title", "Update Rural Data");
		// Finding report data by id
		Report report = reportService.findReport(id);
		model.addAttribute("ruralData", report);
		
		// Retrieve the user ID from the session and get the user details from the database
	    Long userId;
	    try {
	        userId = Long.parseLong(session.getAttribute("userSession").toString());
	    } catch (NumberFormatException e) {
	        return "redirect:/error-page";  // Redirect if session attribute is not a valid user ID
	    }

	    User userdb = userService.getUserById(userId);

	    // If user data is present, encode the profile image to base64
	    if (userdb != null && userdb.getData() != null) {
	        byte[] content = userdb.getData(); 
	        String base64Image = Base64.getEncoder().encodeToString(content);
	        userdb.setFileName(base64Image);  // Set the base64 image as fileName (should be clarified if this is appropriate)
	    }

	    // Add user details and title to the model
	    model.addAttribute("userProfile", userdb);
		return "update-rural-report";
	}

	// Edit urban report
	@PostMapping("/urbanEdit")
	public String editUrbanReport(@RequestParam("urbanReportId") Long id, Model model, HttpSession session) {
		if (session == null || session.getAttribute("userSession") == null) {
			return "redirect:/error-page";
		}
		model.addAttribute("title", "Update Urban Report");
		// Finding report data by id
		Report report = reportService.findReport(id);
		model.addAttribute("urbanData", report);
		// Retrieve the user ID from the session and get the user details from the database
	    Long userId;
	    try {
	        userId = Long.parseLong(session.getAttribute("userSession").toString());
	    } catch (NumberFormatException e) {
	        return "redirect:/error-page";  // Redirect if session attribute is not a valid user ID
	    }

	    User userdb = userService.getUserById(userId);

	    // If user data is present, encode the profile image to base64
	    if (userdb != null && userdb.getData() != null) {
	        byte[] content = userdb.getData(); 
	        String base64Image = Base64.getEncoder().encodeToString(content);
	        userdb.setFileName(base64Image);  // Set the base64 image as fileName (should be clarified if this is appropriate)
	    }

	    // Add user details and title to the model
	    model.addAttribute("userProfile", userdb);
		return "update-urban-report";
	}

	/*
	 * ================================ In bound report ===========================
	 */

	@RequestMapping("/add-inbound")
	public String addInboundReport(Model model, HttpSession session) {

		model.addAttribute("title", "Add Inbound Report");
		String userSession = session.getAttribute("userSession").toString();
		System.out.println(userSession);
		if (session == null || session.getAttribute("userSession") == null) {
			return "redirect:/error-page";
		}
		// Retrieve the user ID from the session and get the user details from the database
	    Long userId;
	    try {
	        userId = Long.parseLong(session.getAttribute("userSession").toString());
	    } catch (NumberFormatException e) {
	        return "redirect:/error-page";  // Redirect if session attribute is not a valid user ID
	    }

	    User userdb = userService.getUserById(userId);

	    // If user data is present, encode the profile image to base64
	    if (userdb != null && userdb.getData() != null) {
	        byte[] content = userdb.getData(); 
	        String base64Image = Base64.getEncoder().encodeToString(content);
	        userdb.setFileName(base64Image);  // Set the base64 image as fileName (should be clarified if this is appropriate)
	    }

	    // Add user details and title to the model
	    model.addAttribute("userProfile", userdb);
		return "add-inbound-report";

	}

	@RequestMapping("/view-inbound")
	public String viewInboundReport(Model model, HttpSession session) {
		if (session == null || session.getAttribute("userSession") == null) {
			return "redirect:/error-page";
		}
		model.addAttribute("title", "View Inbound Report");
		try {
			long userId = Long.parseLong(session.getAttribute("loginUserId").toString());
			List<InboundReport> callerData = inboundService.findCalledData(userId);
			model.addAttribute("callerDataList", callerData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// Retrieve the user ID from the session and get the user details from the database
	    Long userId;
	    try {
	        userId = Long.parseLong(session.getAttribute("userSession").toString());
	    } catch (NumberFormatException e) {
	        return "redirect:/error-page";  // Redirect if session attribute is not a valid user ID
	    }

	    User userdb = userService.getUserById(userId);

	    // If user data is present, encode the profile image to base64
	    if (userdb != null && userdb.getData() != null) {
	        byte[] content = userdb.getData(); 
	        String base64Image = Base64.getEncoder().encodeToString(content);
	        userdb.setFileName(base64Image);  // Set the base64 image as fileName (should be clarified if this is appropriate)
	    }

	    // Add user details and title to the model
	    model.addAttribute("userProfile", userdb);
		return "view-inbound-report";
	}

	@RequestMapping("/view-task")
	public String viewAssignedTask(Model model, HttpSession session) {
		if (session == null || session.getAttribute("userSession") == null) {
			return "redirect:/error-page";
		}
		model.addAttribute("title", "View All Assigned Task");
		List<AssignTask> allAssignedTask = assignTask.getAllAssignedTask();

		// Collect unique user IDs from tasks
		Set<Long> userIdSet = new HashSet<>();
		for (AssignTask task : allAssignedTask) {
			userIdSet.add(task.getUserId());
		}

		// Retrieve users based on user IDs
		List<User> userList = userService.getUserList(userIdSet);

		// Create a mapping from user ID to user name
		Map<Long, String> userIdToUserName = new HashMap<>();
		for (User u : userList) {
			userIdToUserName.put(u.getId(), u.getName());
		}

		// Add the mapping and tasks to the model
		model.addAttribute("userIdToUserName", userIdToUserName);
		model.addAttribute("tasks", allAssignedTask);
		return "view-assigned-task";
	}

//	======================================= view daily report ================================
	@RequestMapping("/daily-report")
	public String dailyReport(Model model, HttpSession session) {
		if (session == null || session.getAttribute("userSession") == null) {
			return "redirect:/error-page";
		}
		model.addAttribute("title", "View Daily Report");
		List<User> users = userService.findAllUser();
		model.addAttribute("users", users);
		return "view-daily-report";
	}

	@RequestMapping("/report-detials")
	public String dailyReportDetials(Model model, HttpSession session) {
		if (session == null || session.getAttribute("userSession") == null) {
			return "redirect:/error-page";
		}
		model.addAttribute("title", "Daily Report Detials");
		List<User> users = userService.findAllUser();
		model.addAttribute("users", users);
		return "daily-report-detials";
	}

//	=============================================== feedback and profile handler ===============================
	@RequestMapping("/feedback")
	public String feedback(Model model, HttpSession session) {
		if (session == null || session.getAttribute("userSession") == null) {
			return "redirect:/error-page";
		}
		model.addAttribute("title", "Feedback");
		// Retrieve the user ID from the session and get the user details from the database
	    Long userId;
	    try {
	        userId = Long.parseLong(session.getAttribute("userSession").toString());
	    } catch (NumberFormatException e) {
	        return "redirect:/error-page";  // Redirect if session attribute is not a valid user ID
	    }

	    User userdb = userService.getUserById(userId);

	    // If user data is present, encode the profile image to base64
	    if (userdb != null && userdb.getData() != null) {
	        byte[] content = userdb.getData(); 
	        String base64Image = Base64.getEncoder().encodeToString(content);
	        userdb.setFileName(base64Image);  // Set the base64 image as fileName (should be clarified if this is appropriate)
	    }

	    // Add user details and title to the model
	    model.addAttribute("userProfile", userdb);
		return "feedback";
	}
	
	@RequestMapping("/user-profile")
	public String profile(Model model, HttpSession session) {
	    // Check if the session is valid and the "userSession" attribute is present
	    if (session == null || session.getAttribute("userSession") == null) {
	        return "redirect:/error-page";  // Redirect to error page if session is invalid
	    }

	    // Retrieve the user ID from the session and get the user details from the database
	    Long userId;
	    try {
	        userId = Long.parseLong(session.getAttribute("userSession").toString());
	    } catch (NumberFormatException e) {
	        return "redirect:/error-page";  // Redirect if session attribute is not a valid user ID
	    }

	    User userdb = userService.getUserById(userId);
	    
	    boolean userRole = userdb.isAdmin();
	   System.out.println(userRole);
	    // If user data is present, encode the profile image to base64
	    if (userdb != null && userdb.getData() != null) {
	        byte[] content = userdb.getData(); 
	        String base64Image = Base64.getEncoder().encodeToString(content);
	        userdb.setFileName(base64Image);  // Set the base64 image as fileName (should be clarified if this is appropriate)
	    }

	    // Add user details and title to the model
	    model.addAttribute("userProfile", userdb);
	    model.addAttribute("title", "Profile");

	    // Return the profile view
	    if(userRole==false) {
	    	return "profile";
	    }else {
	    	return "admin-profile";
	    }
	    
	}


}
