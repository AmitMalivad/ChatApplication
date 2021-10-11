package com.amit;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amit.dto.Friend;
import com.amit.util.HelperClass;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ProcessOTPServlet
 */
@WebServlet("/ValidateOTPServlet")
public class ValidateOTPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateOTPServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		String mobileNumber = (String)session.getAttribute("mobileNumber");
		String OTP = (String)session.getAttribute("otp");
		String userOTP = request.getParameter("userOTP");
		List<Friend> friendList = new ArrayList<Friend>();
		
		if(userOTP.equals(OTP)) {
			session.setAttribute("isValid", "true");
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat_application","root","root");
				PreparedStatement pst = con.prepareStatement("select * from user where mobile=?");
				pst.setString(1,mobileNumber);
				ResultSet rs = pst.executeQuery();		 
				
				if(!rs.isBeforeFirst()) {
					String profilePageUrl = "./profile.jsp";
					response.sendRedirect(profilePageUrl);
					System.out.println("InValid Mobile Number");
				}else {
					rs.next();
					if( mobileNumber.equals(rs.getString(3))) {
						
						
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat_application","root","root");
						PreparedStatement prst = conn.prepareStatement("select * from friend where user_id = ? ");
						prst.setInt(1, rs.getInt(1));
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
							friendList.add(friend);
				
						}
						
						System.out.println(rs.getString(2));
						session.setAttribute("id",rs.getInt(1));
						session.setAttribute("name",rs.getString(2));
						session.setAttribute("profilePic", HelperClass.getBaseUrl(request) +"\\"+ rs.getString(4));
						
						session.setAttribute("friendList",friendList);
						String homePageUrl = "./home.jsp";
						response.sendRedirect(homePageUrl);
						System.out.println("Valid Mobile Number");
					}
				}
			}
			catch(Exception e) {
				System.out.println("ERROR :" +e);
			}
			
		}
		else {
			session.removeAttribute("mobileNumber");
			session.removeAttribute("isValid");
			session.setAttribute("otpMessage", "incorect otp try again later *");
			String indexPage = "./index.jsp";
	    	response.sendRedirect(indexPage);
		}
	}

}
