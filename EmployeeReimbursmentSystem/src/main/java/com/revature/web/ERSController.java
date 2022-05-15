package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;


import org.apache.log4j.Logger;

public class ERSController extends HttpServlet {

	private static final long serialVersionUID = 8468003670486353217L;

	private static Logger log = Logger.getLogger(ERSController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String URI = req.getRequestURI().replace("/EmployeeReimbursmentSystem/", "");
		log.info("URI: " + URI);

		switch (URI) {
		case "users":
			log.info("User wants a list of users from API...");
			RequestHelper.processAllUsers(req, resp);
			break;

		case "user":
			log.info("User wants to search a user from API based on the id number. URI: " + URI);
			RequestHelper.processBySearchUserParam(req, resp);
			break;
			
		case "userRoles":
			log.info("User wants a list of users from API...");
			RequestHelper.processAllUserRoles(req, resp);
			break;

		case "userRole":
			log.info("User wants to search a user from API based on the id number. URI: " + URI);
			RequestHelper.processBySearchUserRoleParam(req, resp);
			break;	

		default:
			log.info("showing error message...");
			RequestHelper.processError(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String URI = req.getRequestURI().replace("/EmployeeReimbursmentSystem/", "");
		log.info("URI: " + URI);

		switch (URI) {
		case "registerUser":
			log.info("User wants to register a new user from API...");
			RequestHelper.processUserRegistration(req, resp);
			break;

		case "registerUserRole":
			log.info("User wants to register a new user from API...");
			RequestHelper.processUserRoleRegistration(req, resp);
			break;
			
		default:
			log.info("showing error message...");
			RequestHelper.processError(req, resp);
			break;
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String URI = req.getRequestURI().replace("/EmployeeReimbursmentSystem/", "");
		log.info("URI: " + URI);

		switch (URI) {
		case "updateUser":
			log.info("updating user...");
			RequestHelper.processUserUpdate(req, resp);
			break;
		case "updateUserRole":
			log.info("updating user...");
			RequestHelper.processUserRoleUpdate(req, resp);
			break;
		default:
			log.info("showing error message...");
			RequestHelper.processError(req, resp);
			break;
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// save the URI and rewrite it to determine what functionality the user is
		// requesting based on that endpoint
		final String URI = req.getRequestURI().replace("/EmployeeReimbursmentSystem/", "");
		log.info("URI: " + URI);

		switch (URI) {
		case "deleteUser":
			log.info("removing user...");
			RequestHelper.processUserDelete(req, resp);
			break;
			
		case "deleteUserRole":
			log.info("removing user...");
			RequestHelper.processUserRoleDelete(req, resp);
			break;
			
		default:
			log.info("showing error message...");
			RequestHelper.processError(req, resp);
			break;
		}
	}
}
