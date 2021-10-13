package com.amit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amit.config.Constants;



/**
 * Servlet implementation class AddFriendServlet
 */
@WebServlet("/AddFriendServlet")
public class AddFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFriendServlet() {
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
		String mobileNumber = request.getParameter("mobileNumber");
		int userId = (int) session.getAttribute("id");
		System.out.println("mob : " + mobileNumber);
		try {
			Class.forName(Constants.JDBC_DRIVER);
			Connection con = DriverManager.getConnection(Constants.JDBC_CONNECTION_STRING, Constants.JDBC_DATABASE_USERNAME,  Constants.JDBC_DATABASE_USERNAME);
			PreparedStatement pst = con.prepareStatement("select * from user where mobile = ? ");
			pst.setString(1, mobileNumber);
			ResultSet rs = pst.executeQuery();	
			
			if(!rs.isBeforeFirst()) {
				session.setAttribute("mobileMessage", "Not Available Friends*");
				String addfriendPageUrl = "./addfriend.jsp";
				response.sendRedirect(addfriendPageUrl);
			} else {
				rs.next();
				
				int friendId = rs.getInt("id");
				String friendName = rs.getString("name");
			
				System.out.println("Friend ID:"+friendId+"Friend name :"+friendName);
				PreparedStatement prst = con.prepareStatement("select * from friend where user_id = ? and friend_id = ? ");
				prst.setInt(1, userId);
				prst.setInt(2, friendId);
				ResultSet rst = prst.executeQuery();
				
				if(!rst.isBeforeFirst()) {
					PreparedStatement pste = con.prepareStatement("insert into friend(user_id,friend_id,friend_name) values(?,?,?)");
					pste.setInt(1, userId);
					pste.setInt(2, friendId);
					pste.setString(3, friendName);
					pste.executeUpdate();
					String homePageUrl = "./home.jsp";
					response.sendRedirect(homePageUrl);
					System.out.println("Friends ADD Success fully..");
				}else {
					session.setAttribute("mobileMessage", "Friend All ready added*");
					String addfriendPageUrl = "./addfriend.jsp";
					response.sendRedirect(addfriendPageUrl);
				}
				}
		}
		catch (Exception e) {
			System.out.println("ERROR :" +e);
		}
		
	}
}
