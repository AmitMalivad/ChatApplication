package com.amit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.amit.util.HelperClass;

/**
 * Servlet implementation class UpdateProfileServlet
 */
@WebServlet("/UpdateProfileServlet")
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
  maxFileSize = 1024 * 1024 * 10,      // 10 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfileServlet() {
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
		String mobileNumber = (String)session.getAttribute("mobileNumber");
		String userName = request.getParameter("userName");
		Part profilePic = request.getPart("profilePic");
		
		String fileName = profilePic.getSubmittedFileName();
		String folderPath= request.getServletContext().getRealPath("/");
		profilePic.write(folderPath + "\\" +  fileName);	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat_application","root","root");
			PreparedStatement pst = con.prepareStatement("insert into user values(?,?, ?)");
			pst.setString(1, userName);
			pst.setString(2, mobileNumber);
			pst.setString(3, fileName);
			pst.executeUpdate();		 
			System.out.println("userName ADD Success fully..");
			
			session.setAttribute("name",userName);
			session.setAttribute("profilePic", HelperClass.getBaseUrl(request)+"\\"+fileName);
			String homePageUrl = "./home.jsp";
			response.sendRedirect(homePageUrl);
		}
		catch (Exception e) {
			System.out.println("ERROR :" + e);
		}
	}

}
