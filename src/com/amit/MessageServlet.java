package com.amit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		String friendId = request.getParameter("friendId");
		int userId = (int) session.getAttribute("id");
		String message = request.getParameter("message"); 
		
		System.out.println("FriendID :" + friendId);
		System.out.println("userId :" + userId);
		System.out.println("message :" + message);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat_application","root","root");
			PreparedStatement pst = con.prepareStatement("insert into message(user_id,friend_id,message,date_time) values(?,?,?,?)");
			pst.setInt(1, userId);
			pst.setString(2, friendId);
			pst.setString(3, message);
			pst.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
			pst.executeUpdate();
			System.out.println("Message Send Success Fully...");
			session.setAttribute("activeChat", friendId);
			String homePageUrl = "./home.jsp";
			response.sendRedirect(homePageUrl);
		}
		catch (Exception e) {
			System.out.println("ERROR :" + e);
		}
	}

}
