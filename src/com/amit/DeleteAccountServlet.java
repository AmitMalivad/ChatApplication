package com.amit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amit.config.Constants;

/**
 * Servlet implementation class DeleteAccountServlet
 */
@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAccountServlet() {
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
		int userId = (int) session.getAttribute("id");
		try {
			Class.forName(Constants.JDBC_DRIVER);
			Connection con = DriverManager.getConnection(Constants.JDBC_CONNECTION_STRING, Constants.JDBC_DATABASE_USERNAME,  Constants.JDBC_DATABASE_USERNAME);
			PreparedStatement st = con.prepareStatement("DELETE FROM user WHERE id = ?");
	        st.setInt(1,userId);
	        st.executeUpdate(); 
			session.setAttribute("otpMessage", "Your Account Delete*");
			String indexPageUrl = "./index.jsp";
			response.sendRedirect(indexPageUrl);
		}
		catch(Exception e) {
			System.out.println("ERROR :" + e);		
		}
 	}

}
