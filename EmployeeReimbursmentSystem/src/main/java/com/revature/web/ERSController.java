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
		
		case "login":
			log.info("User wants a list of users from API...");
			RequestHelper.processLogin(req, resp);
			break;
		
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
			
		case "types":
			log.info("User wants a list of users from API...");
			RequestHelper.processAllTypes(req, resp);
			break;
			
		case "statuses":
			log.info("User wants a list of users from API...");
			RequestHelper.processAllStatuses(req, resp);
			break;
			
		case "reimbursement":
			log.info("User wants to search a user from API based on the id number. URI: " + URI);
			RequestHelper.processBySearchReimbursementParam(req, resp);
			break;
			
		case "reimbursements":
			log.info("User wants a list of users from API...");
			RequestHelper.processAllReimbursements(req, resp);
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
			
		case "registerType":
			log.info("User wants to register a new user from API...");
			RequestHelper.processTypeRegistration(req, resp);
			break;
			
		case "registerStatus":
			log.info("User wants to register a new user from API...");
			RequestHelper.processStatusRegistration(req, resp);
			break;	
			
		case "submitReimbursement":
			log.info("User wants to register a new user from API...");
			RequestHelper.processSubmitReimbursement(req, resp);
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

		case "updateReimbursementType":
			log.info("updateing reimbursment type");
			RequestHelper.processReimbursementTypeUpdate(req, resp);
			break;
		case "updateReimbursementStatus":
			log.info("updateing reimbursment status");
			RequestHelper.processReimbursementStatusUpdate(req, resp);
			break;

			
		case "updateReimbursement":
			log.info("updating user...");
			RequestHelper.processReimbursementUpdate(req, resp);
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
		
		case "deleteReimbursementType":
			log.info("removing reimbursement type...");
			RequestHelper.processReimbursementTypeDelete(req, resp);
			break;
			
		case "deleteReimbursementStatus":
			log.info("removing reimbursement status...");
			RequestHelper.processReimbursementStatusDelete(req, resp);
			break;
			
			
		case "deleteReimbursement":
			log.info("removing user...");
			RequestHelper.processReimbursementDelete(req, resp);
			break;
			
		default:
			log.info("showing error message...");
			RequestHelper.processError(req, resp);
			break;
		}
	}
}
