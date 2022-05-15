package com.revature.web;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.repository.UserDAOImpl;
import com.revature.repository.UserRoleDAOImpl;
import com.revature.services.UserRoleService;
import com.revature.services.UserRoleServiceImpl;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

public class RequestHelper {

	private static UserService userService = new UserServiceImpl(new UserDAOImpl());
	private static UserRoleService userRoleService = new UserRoleServiceImpl(new UserRoleDAOImpl());
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

		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();

		log.info("Leaving RequestHelper");
		String line = reader.readLine();
		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = sb.toString();

		log.info("User searching with this info: " + body);

		String[] searchParams = body.split("&");
		List<String> values = new ArrayList<String>();

		for (String pair : searchParams) {
			values.add(pair.substring(pair.indexOf("=") + 1));
		}

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
		BufferedReader reader = request.getReader();
		StringBuilder s = new StringBuilder();

		// we are just transferring our Reader data to our StringBuilder, line by line
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString();
		String[] sepByAmp = body.split("&"); // separate username=bob&password=pass -> [username=bob, password=pass]

		List<String> values = new ArrayList<String>();

		for (String pair : sepByAmp) { // each element in array looks like this
			values.add(pair.substring(pair.indexOf("=") + 1)); // trim each String element in the array to just value ->
																// [bob, pass]
		}
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

	public static void processError(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// if something goes wrong, redirect the user to a custom 404 error page
		req.getRequestDispatcher("error.html").forward(req, resp);
		/*
		 * Remember that the forward() method does NOT produce a new request, it just
		 * forwards it to a new resource, and we also maintain the URL
		 */

	}

	public static void processUserUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("inside of request helper...processUserUpdate...");
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();

		// we are just transferring our Reader data to our StringBuilder, line by line
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString();
		String[] sepByAmp = body.split("&"); // separate username=bob&password=pass -> [username=bob, password=pass]

		List<String> values = new ArrayList<String>();

		for (String pair : sepByAmp) { // each element in array looks like this
			values.add(pair.substring(pair.indexOf("=") + 1)); // trim each String element in the array to just value ->
																// [bob, pass]
		}
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
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();

		// we are just transferring our Reader data to our StringBuilder, line by line
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString();
		String[] sepByAmp = body.split("&"); // separate username=bob&password=pass -> [username=bob, password=pass]

		List<String> values = new ArrayList<String>();

		for (String pair : sepByAmp) { // each element in array looks like this
			values.add(pair.substring(pair.indexOf("=") + 1)); // trim each String element in the array to just value ->
																// [bob, pass]
		}
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

		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();

		log.info("Leaving RequestHelper");
		String line = reader.readLine();
		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = sb.toString();

		log.info("User searching with this info: " + body);

		String[] searchParams = body.split("&");
		List<String> values = new ArrayList<String>();

		for (String pair : searchParams) {
			values.add(pair.substring(pair.indexOf("=") + 1));
		}

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
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();

		// we are just transferring our Reader data to our StringBuilder, line by line
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString();
		String[] sepByAmp = body.split("&"); // separate username=bob&password=pass -> [username=bob, password=pass]

		List<String> values = new ArrayList<String>();

		for (String pair : sepByAmp) { // each element in array looks like this
			values.add(pair.substring(pair.indexOf("=") + 1)); // trim each String element in the array to just value ->
																// [bob, pass]
		}
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
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();

		// we are just transferring our Reader data to our StringBuilder, line by line
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString();
		String[] sepByAmp = body.split("&"); // separate username=bob&password=pass -> [username=bob, password=pass]

		List<String> values = new ArrayList<String>();

		for (String pair : sepByAmp) { // each element in array looks like this
			values.add(pair.substring(pair.indexOf("=") + 1)); // trim each String element in the array to just value ->
																// [bob, pass]
		}
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
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();

		// we are just transferring our Reader data to our StringBuilder, line by line
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString();
		String[] sepByAmp = body.split("&"); // separate username=bob&password=pass -> [username=bob, password=pass]

		List<String> values = new ArrayList<String>();

		for (String pair : sepByAmp) { // each element in array looks like this
			values.add(pair.substring(pair.indexOf("=") + 1)); // trim each String element in the array to just value ->
																// [bob, pass]
		}
		log.info("User attempted to delete with information:\n " + body);
		// capture the actual username and password values
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

}
