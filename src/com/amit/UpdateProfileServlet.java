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
		boolean isUpdatePic = true;
		String fileName;
		if(profilePic.getSize() == 0) {
			isUpdatePic = false;
			fileName ="images\\default.jpg";
		}else {
			fileName = "images\\" + profilePic.getSubmittedFileName();
			String folderPath= request.getServletContext().getRealPath("/");
			profilePic.write(folderPath + "\\" +  fileName);
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat_application","root","root");
			PreparedStatement pst = con.prepareStatement("select * from user where mobile=?");
			pst.setString(1,mobileNumber);
			ResultSet rs = pst.executeQuery();		 
			
			if(!rs.isBeforeFirst()) {
				PreparedStatement pste = con.prepareStatement("insert into user(name, mobile, profile_pic) values(?,?,?)");
				pste.setString(1, userName);
				pste.setString(2, mobileNumber);
				pste.setString(3, fileName);
				pste.executeUpdate();		 
				System.out.println("userName ADD Success fully..");
			}else {
				rs.next();
				PreparedStatement pste = con.prepareStatement("update user set name=?, mobile=?, profile_pic=? where mobile=?");
				pste.setString(1, userName);
				pste.setString(2, mobileNumber);
				if(!isUpdatePic) {
					pste.setString(3, rs.getString(4));
					fileName = rs.getString(4);
				}
				pste.setString(3, fileName);
				pste.setString(4, mobileNumber);
				pste.executeUpdate();		 
				System.out.println("user update ADD Success fully..");
			}
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


