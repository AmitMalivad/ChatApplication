package com.amit.util;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

public class HelperClass {
	public static String generateOTP() {
		Random r = new Random();
	    String otp = String.format("%04d", r.nextInt(1001));
	    return otp;
	}
	
	public static String getBaseUrl(HttpServletRequest request) {
		String scheme = request.getScheme() + "://";
		String serverName = request.getServerName();
		String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
		String contextPath = request.getContextPath();
		return scheme + serverName + serverPort + contextPath;
	}
}