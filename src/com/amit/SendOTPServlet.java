package com.amit;

import java.io.IOException;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amit.util.HelperClass;

/**
 * Servlet implementation class SendOTPServlet
 */
@WebServlet("/SendOTPServlet")
public class SendOTPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendOTPServlet() {
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
		HttpSession session = request.getSession(); //session create
		
		String mobileNumber = request.getParameter("mobileNumber"); //get mobilenumber for usre
		String otp = HelperClass.generateOTP();	//get otp for generate Class
		System.out.println("OTP is :" + otp);
		
		session.setAttribute("mobileNumber",mobileNumber);
		session.setAttribute("otp",otp);
		//System.out.println(session.getId());
		
		String validatePageUrl = "./validate.jsp";
    	response.sendRedirect(validatePageUrl);
		//RequestDispatcher rd=request.getRequestDispatcher("validateOTP.jsp"); 
		//rd.forward(request, response); // redirect validateOTP
	}

}
