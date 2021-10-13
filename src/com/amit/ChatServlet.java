package com.amit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amit.config.Constants;
import com.amit.dto.Friend;
import com.amit.dto.Message;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession(false);
		String currentUserId = request.getParameter("id");
		String friendId = request.getParameter("friendId");
		List<Message>  chat = new ArrayList<>();
		
		try {
			Class.forName(Constants.JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(Constants.JDBC_CONNECTION_STRING, Constants.JDBC_DATABASE_USERNAME,  Constants.JDBC_DATABASE_USERNAME);
			PreparedStatement prst = conn.prepareStatement("select * from message where user_id = ? and friend_id = ?");
			prst.setInt(1, Integer.valueOf(currentUserId));
			prst.setInt(2, Integer.valueOf(friendId));
			ResultSet rst = prst.executeQuery();
			
			
			while(rst.next()) {
				Message msg = new Message();
				msg.setMessage(rst.getString("message"));
				msg.setDate(rst.getDate("date_time"));
				
				if(rst.getInt("user_id") == Integer.valueOf(currentUserId))
					msg.setSended(true);
				else
					msg.setReceived(false);
				
				chat.add(msg);
	
			}
			
			session.setAttribute("chat",chat);
			response.getWriter().append(chat.toString());
		}catch(Exception e) {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
