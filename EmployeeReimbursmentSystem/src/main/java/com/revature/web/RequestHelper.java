package com.revature.web;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.*;
import com.revature.repository.*;
import com.revature.services.*;

public class RequestHelper {

	private static UserService userService = new UserServiceImpl(new UserDAOImpl());
	private static UserRoleService userRoleService = new UserRoleServiceImpl(new UserRoleDAOImpl());
	private static ReimbursementTypeService typeService = new ReimbursementTypeServiceImpl(new ReimbursementTypeDAOImpl());
	private static ReimbursementStatusService statusService = new ReimbursementStatusServiceImpl(new ReimbursementStatusDAOImpl());
	private static ReimbursementService reimbursementService = new ReimbursementServiceImpl(new ReimbursementDAOImpl(), new ReimbursementStatusDAOImpl(), new UserDAOImpl());
	
	
	private static Logger log = Logger.getLogger(RequestHelper.class);
	private static ObjectMapper om = new ObjectMapper();

	public static void processAllUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		log.info("in RequestHelper -> getting all users");
		resp.setContentType("application/json");
		List<User> users = userService.findAllUsers();

		String json = om.writeValueAsString(users);

		PrintWriter out = resp.getWriter();
		out.println(json);

		log.info("Leaving RequestHelper");
	}

	public static void processBySearchUserParam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("in RequestHelper -> searching a user by param");

		String body = getStringRequest(req);

		log.info("User searching with this info: " + body);

		List<String> values = getSearchParamsList(body);

		if (body.startsWith("id")) {
			log.info("in RequestHelper -> get user by id");
			resp.setContentType("application/json");
			int id = Integer.parseInt(values.get(0));

			User user = userService.findUserById(id);

			String json = om.writeValueAsString(user);

			PrintWriter out = resp.getWriter();
			out.println(json);

			log.info("Leaving RequestHelper");
		}
	}

	public static void processUserRegistration(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		log.info("inside of request helper...processRegistration...");
		String body = getStringRequest(request);
		
		List<String> values = getSearchParamsList(body);
		
		log.info("User attempted to register with information:\n " + body);
		// capture the actual username and password values
		String username = values.get(0);
		String password = values.get(1);
		String firstname = values.get(2);
		String lastname = values.get(3);
		String email = values.get(4);
		int userRoleId = Integer.parseInt(values.get(5));
		UserRole userRole = userRoleService.findUserRoleById(userRoleId);

		User user = new User(username, password, firstname, lastname, email, userRole);
		int targetId = userService.register(user);

		if (targetId != 0) {
			PrintWriter pw = response.getWriter();
			user.setId(targetId);
			log.info("New user: " + user);
			String json = om.writeValueAsString(user);
			pw.println(json);
			System.out.println("JSON:\n" + json);

			response.setContentType("application/json");
			response.setStatus(200); // SUCCESSFUL!
			log.info("User has been created successfully.");
		} else {
			response.setContentType("application/json");
			response.setStatus(204); // this means that the connection was successful but no user created!
		}
		log.info("leaving request helper now...");

	}

	public static void processUserUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("inside of request helper...processUserUpdate...");
		String body = getStringRequest(req);
		
		List<String> values = getSearchParamsList(body);
		
		log.info("User attempted to update with information:\n " + body);
		// capture the actual username and password values
		int id = Integer.parseInt(values.get(0)); // id numbers cannot be modified!
		String username = values.get(1); // bob
		String password = values.get(2); // pass
		String firstname = values.get(3);
		String lastname = values.get(4);
		String email = values.get(5);

		int userRoleId = Integer.parseInt(values.get(6));
		UserRole userRole = userRoleService.findUserRoleById(userRoleId);

		User tempUser = new User(id, username, password, firstname, lastname, email, userRole);
		boolean isUpdated = userService.editUser(tempUser);

		if (isUpdated) {
			PrintWriter pw = resp.getWriter();
			log.info("Edit successful! New user info: " + tempUser);
			String json = om.writeValueAsString(tempUser);
			pw.println(json);
			System.out.println("JSON:\n" + json);

			resp.setContentType("application/json");
			resp.setStatus(200); // SUCCESSFUL!
			log.info("User has successfully been edited.");
		} else {
			resp.setContentType("application/json");
			resp.setStatus(400); // this means that the connection was successful but no user was updated!
		}
		log.info("leaving request helper now...");
	}

	public static void processUserDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("inside of request helper...processUserUpdate...");
		String body = getStringRequest(req);
		
		List<String> values = getSearchParamsList(body);
		
		log.info("User attempted to delete with information:\n " + body);
		// capture the actual username and password values
		int id = Integer.parseInt(values.get(0));
		User tempUser = userService.findUserById(id);

		if (tempUser != null) {
			boolean isDeleted = userService.deleteUser(id);

			if (isDeleted) {
				PrintWriter pw = resp.getWriter();
				log.info("Delete successful! Removed user by id: " + id);
				String json = om.writeValueAsString(tempUser);
				pw.println(json);
				System.out.println("JSON:\n" + json);

				resp.setContentType("application/json");
				resp.setStatus(200); // SUCCESSFUL!
				log.info("User has successfully been deleted.");
			} else {
				resp.setContentType("application/json");
				resp.setStatus(400); // this means that the connection was successful but no user was updated!
			}
		} else {
			resp.setContentType("application/json");
			resp.setStatus(400);
			log.info("Delete unsuccessful! Unable to find the user with the id: " + id);
		}

		log.info("leaving request helper now...");

	}

	public static void processAllUserRoles(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("in RequestHelper -> getting all user roles");
		resp.setContentType("application/json");
		List<UserRole> userRoles = userRoleService.findAllUserRoles();

		String json = om.writeValueAsString(userRoles);

		PrintWriter out = resp.getWriter();
		out.println(json);

		log.info("Leaving RequestHelper");
	}

	public static void processBySearchUserRoleParam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("in RequestHelper -> searching a user role by param");

		String body = getStringRequest(req);

		log.info("User searching with this info: " + body);

		List<String> values = getSearchParamsList(body);

		if (body.startsWith("id")) {
			log.info("in RequestHelper -> get user role by id");
			resp.setContentType("application/json");
			int id = Integer.parseInt(values.get(0));

			UserRole userRole = userRoleService.findUserRoleById(id);

			String json = om.writeValueAsString(userRole);

			PrintWriter out = resp.getWriter();
			out.println(json);

			log.info("Leaving RequestHelper");
		}
	}

	public static void processUserRoleRegistration(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		log.info("inside of request helper...processRegistration...");
		String body = getStringRequest(req);
		
		List<String> values = getSearchParamsList(body);
		
		log.info("User attempted to register with information:\n " + body);
		// capture the actual username and password values
		String role = values.get(0);
		UserRole userRole = new UserRole(role);

		int targetId = userRoleService.register(userRole);

		if (targetId != 0) {
			PrintWriter pw = resp.getWriter();
			userRole.setId(targetId);
			log.info("New user role: " + userRole);
			String json = om.writeValueAsString(userRole);
			pw.println(json);
			System.out.println("JSON:\n" + json);

			resp.setContentType("application/json");
			resp.setStatus(200); // SUCCESSFUL!
			log.info("User has been created successfully.");
		} else {
			resp.setContentType("application/json");
			resp.setStatus(204); // this means that the connection was successful but no user created!
		}
		log.info("leaving request helper now...");

	}

	public static void processUserRoleUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("inside of request helper...processUserUpdate...");
		String body = getStringRequest(req);
		
		List<String> values = getSearchParamsList(body);
		
		log.info("User attempted to update with information:\n " + body);
		// capture the actual username and password values
		int id = Integer.parseInt(values.get(0)); // id numbers cannot be modified!
		String role = values.get(1); 

		UserRole userRole = new UserRole(id, role);
		boolean isUpdated = userRoleService.editUserRole(userRole);

		if (isUpdated) {
			PrintWriter pw = resp.getWriter();
			log.info("Edit successful! New user info: " + userRole);
			String json = om.writeValueAsString(userRole);
			pw.println(json);
			System.out.println("JSON:\n" + json);

			resp.setContentType("application/json");
			resp.setStatus(200); // SUCCESSFUL!
			log.info("User has successfully been edited.");
		} else {
			resp.setContentType("application/json");
			resp.setStatus(400); // this means that the connection was successful but no user was updated!
		}
		log.info("leaving request helper now...");
		
	}

	public static void processUserRoleDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("inside of request helper...processUserUpdate...");
		
		String body = getStringRequest(req);
		
		List<String> values = getSearchParamsList(body);
		
		log.info("User attempted to delete with information:\n " + body);
		
		int id = Integer.parseInt(values.get(0));
		UserRole tempUserRole = userRoleService.findUserRoleById(id);

		if (tempUserRole != null) {
			boolean isDeleted = userRoleService.deleteUserRole(id);

			if (isDeleted) {
				PrintWriter pw = resp.getWriter();
				log.info("Delete successful! Removed user by id: " + id);
				String json = om.writeValueAsString(tempUserRole);
				pw.println(json);
				System.out.println("JSON:\n" + json);

				resp.setContentType("application/json");
				resp.setStatus(200); // SUCCESSFUL!
				log.info("User role has successfully been deleted.");
			} else {
				resp.setContentType("application/json");
				resp.setStatus(400); // this means that the connection was successful but no user was updated!
			}
		} else {
			resp.setContentType("application/json");
			resp.setStatus(400);
			log.info("Delete unsuccessful! Unable to find the user role with the id: " + id);
		}

		log.info("leaving request helper now...");
		
	}

	public static void processError(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// if something goes wrong, redirect the user to a custom 404 error page
		req.getRequestDispatcher("error.html").forward(req, resp);
		/*
		 * Remember that the forward() method does NOT produce a new request, it just
		 * forwards it to a new resource, and we also maintain the URL
		 */

	}

	private static List<String> getSearchParamsList(String body) {
		String[] searchParams = body.split("&");
		List<String> values = new ArrayList<String>();

		for (String pair : searchParams) {
			values.add(pair.substring(pair.indexOf("=") + 1));
		}
		return values;
	}

	private static String getStringRequest(HttpServletRequest req) throws IOException {
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();

		log.info("Leaving RequestHelper");
		String line = reader.readLine();
		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = sb.toString();
		return body;
	}

	public static void processLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("in RequestHelper -> searching a user by param");

		String body = getStringRequest(req);

		log.info("User searching with this info: " + body);

		List<String> values = getSearchParamsList(body);

		if (body.startsWith("username")) {
			log.info("in RequestHelper -> get user by id");
			resp.setContentType("application/json");
			String userName = values.get(0);
			String password = values.get(1);

			User user = userService.login(userName, password);

			String json = om.writeValueAsString(user);

			PrintWriter out = resp.getWriter();
			out.println(json);

			log.info("Leaving RequestHelper");
		}
	}

	public static void processAllTypes(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("in RequestHelper -> getting all reimbursement types");
		resp.setContentType("application/json");
		List<ReimbursementType> reimbursementTypes = typeService.findAllReimbursementTypes();

		String json = om.writeValueAsString(reimbursementTypes);

		PrintWriter out = resp.getWriter();
		out.println(json);

		log.info("Leaving RequestHelper");
	}

	public static void processAllStatuses(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("in RequestHelper -> getting all reimbursement statuses");
		resp.setContentType("application/json");
		List<ReimbursementStatus> reimbursementStatuses = statusService.findAllReimbursementStatus();

		String json = om.writeValueAsString(reimbursementStatuses);

		PrintWriter out = resp.getWriter();
		out.println(json);

		log.info("Leaving RequestHelper");
	}

	public static void processTypeRegistration(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("inside of request helper...processRegistration...");
		String body = getStringRequest(req);
		
		List<String> values = getSearchParamsList(body);
		
		log.info("User attempted to register with information:\n " + body);

		String type = values.get(0);
		ReimbursementType reimbursementType = new ReimbursementType(type);

		int targetId = typeService.register(reimbursementType);

		if (targetId != 0) {
			PrintWriter pw = resp.getWriter();
			reimbursementType.setId(targetId);
			log.info("New user role: " + reimbursementType);
			String json = om.writeValueAsString(reimbursementType);
			pw.println(json);
			System.out.println("JSON:\n" + json);

			resp.setContentType("application/json");
			resp.setStatus(200); // SUCCESSFUL!
			log.info("User has been created successfully.");
		} else {
			resp.setContentType("application/json");
			resp.setStatus(204); // this means that the connection was successful but no user created!
		}
		log.info("leaving request helper now...");
	}

	public static void processStatusRegistration(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("inside of request helper...processRegistration...");
		String body = getStringRequest(req);
		
		List<String> values = getSearchParamsList(body);
		
		log.info("User attempted to register with information:\n " + body);

		String status = values.get(0);
		ReimbursementStatus reimbursementStatus = new ReimbursementStatus(status);

		int targetId = statusService.register(reimbursementStatus);

		if (targetId != 0) {
			PrintWriter pw = resp.getWriter();
			reimbursementStatus.setId(targetId);
			log.info("New user role: " + reimbursementStatus);
			String json = om.writeValueAsString(reimbursementStatus);
			pw.println(json);
			System.out.println("JSON:\n" + json);

			resp.setContentType("application/json");
			resp.setStatus(200); // SUCCESSFUL!
			log.info("User has been created successfully.");
		} else {
			resp.setContentType("application/json");
			resp.setStatus(204); // this means that the connection was successful but no user created!
		}
		log.info("leaving request helper now...");
	}

	public static void processSubmitReimbursement(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("inside of request helper...processSubmitReimbursement...");
		String body = getStringRequest(req);
		
		List<String> values = getSearchParamsList(body);
		
		log.info("User attempted to submit a new reimbursement with information:\n " + body);
		
		Double amount = Double.parseDouble(values.get(0)) ;
		LocalDate submittedDate = LocalDate.now();
		String description = values.get(1);
		
		int authorId = Integer.parseInt(values.get(2));
		User author = userService.findUserById(authorId);
		 
		int typeId = Integer.parseInt(values.get(3));
		ReimbursementType type =  typeService.findReimbursementTypeById(typeId);
		ReimbursementStatus status = statusService.findReimbursementStatusById(1);
		
		
		
		Reimbursement reimbursement = new Reimbursement(amount, submittedDate, description, author, status, type);
		
		log.info("Reimbursement:\n " + reimbursement);
		
		int targetId = reimbursementService.submit(reimbursement);

		if (targetId != 0) {
			PrintWriter pw = resp.getWriter();
			reimbursement.setId(targetId);
			log.info("New Reimbursement: " + reimbursement);
			String json = om.writeValueAsString(reimbursement);
			pw.println(json);
			System.out.println("JSON:\n" + json);

			resp.setContentType("application/json");
			resp.setStatus(200); // SUCCESSFUL!
			log.info("User has been created successfully.");
		} else {
			resp.setContentType("application/json");
			resp.setStatus(204); // this means that the connection was successful but no user created!
		}
		log.info("leaving request helper now...");
		
	}

	public static void processBySearchReimbursementParam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("in RequestHelper -> searching a Reimbursement by param");

		String body = getStringRequest(req);

		log.info("User searching Reimbursement(s) with this info: " + body);

		List<String> values = getSearchParamsList(body);

		if (body.startsWith("status")) {
			log.info("in RequestHelper -> get Reimbursement by status");
			resp.setContentType("application/json");
			int statusId = Integer.parseInt(values.get(0));

			List<Reimbursement> reimbursements = reimbursementService.findByStatus(statusId);

			String json = om.writeValueAsString(reimbursements);

			PrintWriter out = resp.getWriter();
			out.println(json);

			log.info("Leaving RequestHelper");
		} else if (body.startsWith("userId")) {
			log.info("in RequestHelper -> get Reimbursement by status for an employee");
			resp.setContentType("application/json");
			
			int userId = Integer.parseInt(values.get(0));
			int statusId = Integer.parseInt(values.get(1));

			List<Reimbursement> reimbursements = reimbursementService.findByStatusAndUser(statusId, userId);

			String json = om.writeValueAsString(reimbursements);

			PrintWriter out = resp.getWriter();
			out.println(json);

			log.info("Leaving RequestHelper");
		}
		
	}

	public static void processAllReimbursements(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("in RequestHelper -> getting all reimbursements");
		resp.setContentType("application/json");
		List<Reimbursement> reimbursements = reimbursementService.findAll();

		String json = om.writeValueAsString(reimbursements);

		PrintWriter out = resp.getWriter();
		out.println(json);

		log.info("Leaving RequestHelper");
		
	}
}
