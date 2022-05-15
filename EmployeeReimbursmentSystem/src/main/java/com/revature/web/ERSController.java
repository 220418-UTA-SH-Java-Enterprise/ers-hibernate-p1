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
		final String URI = req.getRequestURI().replace("/ERSController/", "");
		log.info("URI: " + URI);

		switch (URI) {
		case "users":
			log.info("User wants a list of users from API...");
			RequestHelper.processAllUsers(req, resp);
			break;

		case "user":
			log.info("User wants to search a user from API based on the id number. URI: " + URI);
			RequestHelper.processBySearchParam(req, resp);
			break;

		default:
			log.info("showing error message...");
			RequestHelper.processError(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String URI = req.getRequestURI().replace("/ERSController/", "");
		log.info("URI: " + URI);

		switch (URI) {
		case "register":
			log.info("User wants to register a new user from API...");
			RequestHelper.processRegistration(req, resp);
			break;

		default:
			log.info("showing error message...");
			RequestHelper.processError(req, resp);
			break;
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String URI = req.getRequestURI().replace("/ERSController/", "");
		log.info("URI: " + URI);

		switch (URI) {
		case "update":
			log.info("updating user...");
			RequestHelper.processUserUpdate(req, resp);
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
		final String URI = req.getRequestURI().replace("/ERSController/", "");
		log.info("URI: " + URI);

		switch (URI) {
		case "delete":
			log.info("removing user...");
			RequestHelper.processUserDelete(req, resp);
			break;
		default:
			log.info("showing error message...");
			RequestHelper.processError(req, resp);
			break;
		}
	}
}
