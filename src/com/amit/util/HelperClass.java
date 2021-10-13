package com.amit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.amit.config.Constants;
import com.amit.dto.Friend;
import com.amit.dto.Message;

public class HelperClass {
	public static String generateOTP() {
		Random r = new Random();
//	    String otp = String.format("%04d", r.nextInt(1001));
		String otp = "2134";
	    return otp;
	}
	
	public static String getBaseUrl(HttpServletRequest request) {
		String scheme = request.getScheme() + "://";
		String serverName = request.getServerName();
		String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
		String contextPath = request.getContextPath();
		return scheme + serverName + serverPort + contextPath;
	}
	public static List<Friend> getFriends(int userId){
		List<Friend> friendList = new ArrayList<Friend>();
		try {
			Class.forName(Constants.JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(Constants.JDBC_CONNECTION_STRING, Constants.JDBC_DATABASE_USERNAME,  Constants.JDBC_DATABASE_USERNAME);
			PreparedStatement prst = conn.prepareStatement("select * from friend where user_id = ? ");
			prst.setInt(1, userId);
			ResultSet rst = prst.executeQuery();
			
			while(rst.next()) {
				
				PreparedStatement prstatement = conn.prepareStatement("select * from user where id = ? ");
				prstatement.setInt(1, rst.getInt("friend_id"));
				ResultSet rstatement = prstatement.executeQuery();
				rstatement.next();
				
				
				Friend friend = new Friend();
				friend.setId(rst.getInt("friend_id"));
				friend.setName(rstatement.getString("name"));
				friend.setImg(rstatement.getString("profile_pic"));
				friend.setChat(HelperClass.getChat(userId, rst.getInt("friend_id")));
				friendList.add(friend);
	
			}
		}catch(Exception e) {
			
		}
		
		return friendList;
	}

	public static List<Message> getChat(int userId, int friendId) {
		List<Message>  chat = new ArrayList<>();
		
		try {
			Class.forName(Constants.JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(Constants.JDBC_CONNECTION_STRING, Constants.JDBC_DATABASE_USERNAME,  Constants.JDBC_DATABASE_USERNAME);
			PreparedStatement prst = conn.prepareStatement("select * from message where (user_id = ? and friend_id = ?) or (user_id = ? and friend_id = ?) ");
			prst.setInt(1, Integer.valueOf(userId));
			prst.setInt(2, Integer.valueOf(friendId));
			prst.setInt(3, Integer.valueOf(friendId));
			prst.setInt(4, Integer.valueOf(userId));
			ResultSet rst = prst.executeQuery();
			
			
			while(rst.next()) {
				Message msg = new Message();
				msg.setMessage(rst.getString("message"));
				msg.setDate(rst.getDate("date_time"));
				
				if(rst.getInt("user_id") == Integer.valueOf(userId))
					msg.setSended(true);
				else
					msg.setReceived(false);
				
				chat.add(msg);
	
			}		
		}catch(Exception e) {
			
		}
		return chat;
	}
}