package com.easylearnjava.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
This is my first login application using servlets
@Author Teja
@Return param1 param 2 param3
@Result interger

*/
public class LoginApp extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("uname");
		String password = req.getParameter("upassword");
		
		LoginApp app=new LoginApp();
		boolean isValidData = app.validateUserData(username, password);
		if(!isValidData){
			System.out.println("please enter username and password with atleast 5 characters each:");
			return;
		}
		
		boolean isValid = app.validateCredentials(username, password);
		
		if(isValid){
			System.out.println("welcome to the app...");
			/*resp.getWriter().print("<html><head><title>Yahooo!</title></head>");
			resp.getWriter().print("<body>yahooo! logged in to the application</body>");
			resp.getWriter().println("</html>");*/
			//resp.sendRedirect("success.html");
			req.getRequestDispatcher("/success.html").forward(req, resp);
		}else{
			System.out.println("Invalid credentials...");
			//resp.sendRedirect("login.html");
			req.getRequestDispatcher("/login.html").forward(req, resp);
		}
		
	}

	public boolean validateUserData(String uName, String uPswd) {
		// TODO Auto-generated method stub
		if (uName == null || uPswd == null){
		return false;
		}
		if (uName.length() < 5 || uPswd.length() < 5){
			return false;
		}
		
		return true;

		
		
	}

	public boolean validateCredentials(String username, String password) {
		// TODO Auto-generated method stub
		LoginApp app=new LoginApp();
		String pwdFromDB=app.getPassword(username);
		
		if(pwdFromDB != null && pwdFromDB.equals(password)){
			return true;
		}
		
		return false;
	}
	

	public String getPassword(String username) {
	
		Map<String, String> map= new HashMap<String, String>();
		map.put("raghu", "secret");
		map.put("naveen", "topsecret");
		
		return map.get(username);
	}

}
