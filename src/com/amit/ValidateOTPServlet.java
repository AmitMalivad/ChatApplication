package com.amit;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

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
		
		if(userOTP.equals(OTP)) {
			session.setAttribute("isValid", "true");
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapplication","root","1234");
				PreparedStatement pst = con.prepareStatement("select * from user where mobilenumber=?");
				pst.setString(1,mobileNumber);
				ResultSet rs = pst.executeQuery();		 
				
				if(!rs.isBeforeFirst()) {
					String profilePageUrl = "./profile.jsp";
					response.sendRedirect(profilePageUrl);
					System.out.println("InValid Mobile Number");
				}
				
				rs.next();
				if( mobileNumber.equals(rs.getString(2))) {
					System.out.println(rs.getString(1));
					session.setAttribute("name",rs.getString(1));
					String homePageUrl = "./home.jsp";
					response.sendRedirect(homePageUrl);
					System.out.println("Valid Mobile Number");
				}

			}
			catch(Exception e) {
				System.out.println("ERROR :" +e);
			}
			
		}
		else {
			session.removeAttribute("mobileNumber");
			session.removeAttribute("isValid");
			session.setAttribute("message", "incorect otp try again later *");
			String indexPage = "./index.jsp";
	    	response.sendRedirect(indexPage);
		}
	}

}
