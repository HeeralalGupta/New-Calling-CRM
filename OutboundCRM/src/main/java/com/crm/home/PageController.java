package com.crm.home;

import java.time.LocalDate;
import java.util.ArrayList;
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
import com.crm.model.Feedback;
import com.crm.model.InboundReport;
import com.crm.model.Report;
import com.crm.model.User;
import com.crm.service.AssignTaskService;
import com.crm.service.FeedbackService;
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
	
	@Autowired
	private FeedbackService feedbackService;

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
		
		// Retrieve the user ID from the session and get the user details from the database
	    Long userId;
	    try {
	        userId = Long.parseLong(session.getAttribute("userSession").toString());
	    } catch (NumberFormatException e) {
	        return "redirect:/error-page";  // Redirect if session attribute is not a valid user ID
	    }

	    // Calling userProfile method form given below
	    userProfile(model, session, userId);
		
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
	    // Adding Data Report to dashboard
	    try {
	    	AssignTask taskData = assignTask.getAssignedTask(userId);
	    	
		    if(taskData != null) {
		    	int totalCallsDone = inboundService.totalCalls(userId, taskData.getTime());
		    	Long totalCallDone = inboundService.countReportById(userId);
			    Long countCallConnected = inboundService.countCallConnected(userId, "connected", LocalDate.now());
			    Long todayCalls = inboundService.countReportByIdAndDate(userId);
			    
			    model.addAttribute("totalDataAssigned", taskData.getMaxSerialNumber() - taskData.getMinSerialNumber());
			    model.addAttribute("dataType", taskData.getDataCategory());
			    model.addAttribute("callingArea", taskData.getCallingAreaName());
			    model.addAttribute("totalCallDone", totalCallDone);
			    model.addAttribute("connectedCalls", countCallConnected);
			    model.addAttribute("todayCallsDone", todayCalls);
			    model.addAttribute("outStandingData", (taskData.getMaxSerialNumber() - taskData.getMinSerialNumber())-totalCallsDone);
		    }else {
		    	model.addAttribute("totalDataAssigned", "0");
			    model.addAttribute("dataType", "Not Assigned");
			    model.addAttribute("callingArea", "Not Assigned");
			    model.addAttribute("totalCallDone", "0");
			    model.addAttribute("connectedCalls", "0");
			    model.addAttribute("todayCallsDone", "0");
			    model.addAttribute("outStandingData", "0");
		    }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	    // Calling userProfile method form given below
	    userProfile(model, session, userId);
	    
		return "user-dashboard";
	}

	// Logout Handler
	@GetMapping("/login")
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
		
		// Retrieve the user ID from the session and get the user details from the database
	    Long userId;
	    try {
	        userId = Long.parseLong(session.getAttribute("userSession").toString());
	    } catch (NumberFormatException e) {
	        return "redirect:/error-page";  // Redirect if session attribute is not a valid user ID
	    }

	    // Calling userProfile method form given below
	    userProfile(model, session, userId);
	    
		return "assign-task";
	}

	// Report page
	@RequestMapping("/add-user")
	public String addUser(Model model, HttpSession session) {
		if (session == null || session.getAttribute("userSession") == null) {
			return "redirect:/error-page";
		}
		model.addAttribute("title", "Add Users"); // adding page title to model
		
		List<User> allUsers = userService.findAllUser(); 
		List<User> list = new ArrayList<>();

		for (User u : allUsers) {
		    byte[] content = u.getData(); 
		    if (content != null) {
		        // If content is not null, encode to Base64 and set it as the file name
		        String base64Image = Base64.getEncoder().encodeToString(content);
		        u.setFileName(base64Image);
		    } else {
		        // If content is null, set fileName to null or handle it appropriately
		        u.setFileName(null); 
		    }
		    list.add(u); // Add user to the list regardless of whether content is null or not
		}
		model.addAttribute("users", list); // Add the modified user list to the model
		// Retrieve the user ID from the session and get the user details from the database
//	    Long userId;
//	    try {
//	        userId = Long.parseLong(session.getAttribute("userSession").toString());
//	    } catch (NumberFormatException e) {
//	        return "redirect:/error-page";  // Redirect if session attribute is not a valid user ID
//	    }
//	    // Calling userProfile method form given below
//	    userProfile(model, session, userId);
//	    
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

	    // Calling userProfile method form given below
	    userProfile(model, session, userId);
	    
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

	    // Calling userProfile method form given below
	    userProfile(model, session, userId);
	    
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

	    // Calling userProfile method form given below
	    userProfile(model, session, userId);
	    
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

	    // Calling userProfile method form given below
	    userProfile(model, session, userId);
	    
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

	    // Calling userProfile method form given below
	    userProfile(model, session, userId);
	    
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
	    
	    // Calling userProfile method form given below
	    userProfile(model, session, userId);
	    
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
		
		// Retrieve the user ID from the session and get the user details from the database
	    Long userId;
	    try {
	        userId = Long.parseLong(session.getAttribute("userSession").toString());
	    } catch (NumberFormatException e) {
	        return "redirect:/error-page";  // Redirect if session attribute is not a valid user ID
	    }

	    // Calling userProfile method form given below
	    userProfile(model, session, userId);
	    
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
		
		// Retrieve the user ID from the session and get the user details from the database
	    Long userId;
	    try {
	        userId = Long.parseLong(session.getAttribute("userSession").toString());
	    } catch (NumberFormatException e) {
	        return "redirect:/error-page";  // Redirect if session attribute is not a valid user ID
	    }

	    // Calling userProfile method form given below
	    userProfile(model, session, userId);
	    
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
		
		// Retrieve the user ID from the session and get the user details from the database
	    Long userId;
	    try {
	        userId = Long.parseLong(session.getAttribute("userSession").toString());
	    } catch (NumberFormatException e) {
	        return "redirect:/error-page";  // Redirect if session attribute is not a valid user ID
	    }

	    // Calling userProfile method form given below
	    userProfile(model, session, userId);
	    
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
	    userProfile(model, session, userId);
	    
		return "feedback";
	}
	
	@RequestMapping("/check-feedback")
	public String checkFeedback(Model model, HttpSession session) {
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
	    userProfile(model, session, userId);
	    
		List<Feedback> findFeedback = feedbackService.findFeedback();
		model.addAttribute("feedbacks",findFeedback);
		model.addAttribute("title","check feedbacks");
		return "check-feedback";
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
	    
	    String userRole = userdb.getRole();
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
	    if(userRole=="user") {
	    	return "profile";
	    }else {
	    	return "admin-profile";
	    }
	    
	}
	
	private void userProfile(Model model, HttpSession session, Long userId) {

	    User userdb = userService.getUserById(userId);

	    // If user data is present, encode the profile image to base64
	    if (userdb != null && userdb.getData() != null) {
	        byte[] content = userdb.getData(); 
	        String base64Image = Base64.getEncoder().encodeToString(content);
	        userdb.setFileName(base64Image);  // Set the base64 image as fileName (should be clarified if this is appropriate)
	    }

	    // Add user details and title to the model
	    model.addAttribute("userProfile", userdb);
	}
	
	
}
