package com.amit.util;

import java.util.Random;

public class HelperClass {
	public static String generateOTP() {
		Random r = new Random();
	    String otp = String.format("%04d", r.nextInt(1001));
	    return otp;
	}
}