package com.finalproject.myapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.finalproject.myapp.User;

@Controller
public class LoginController {

	@RequestMapping(value = "login", method = RequestMethod.GET)
	 public String processForm()
	 {
		
		return "login";
	 }
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	 public String processLogout(HttpSession session)
	 {
		session.removeAttribute("user");
		return "login";
	 }
	
	@RequestMapping(value = "register", method = RequestMethod.GET)
	 public String redirectRegister()
	 {
		
		return "registerform";
	 }
	@RequestMapping(value = "loginuser", method = RequestMethod.POST)
	 public String processlogin(Model model, HttpServletRequest request,HttpSession session) throws ClassNotFoundException, SQLException
	 {
		// put db code to check the user
		// if the user is found, then return "home"
	    // else
		// then say return "login";
		String email =request.getParameter("usermail");
		String password =request.getParameter("password");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
		
		Connection cnn = DriverManager.getConnection("link");
		
		String selectSQL = "SELECT password, firstname FROM user where email= ?";
		ResultSet rs =null;
		PreparedStatement preparedStatement = cnn.prepareStatement(selectSQL);
		preparedStatement.setString(1, email );
		
		User user=new User(email,password);
		 rs = preparedStatement.executeQuery();
		 if(rs.next()){
			  if(password.equals(rs.getString(1))){
				  user.setFirstName(rs.getString(2));
				System.out.println(" inside next");
				//setting the user in the session
				session.setAttribute("user",user ); 
				session.setMaxInactiveInterval(15*60);
	
			     return "redirect:getRandomDog";
				}else {
					//password does not match please send message to user ,
					request.setAttribute("message","Incorrect Password: Please Try Again ");
					return "login";
				}
		 }else{ 
				//there is no user send it to register
			   request.setAttribute("message","User does not exist. Please click signup to register");
				return "login";
			}

		
		 
	 } catch (Exception e) {
		System.out.println(e.getMessage());

			e.printStackTrace();
			return "error";
	} 
		
	 }
	
	@RequestMapping(value = "registerForm", method = RequestMethod.POST)
	 public String processRegister(Model model, HttpServletRequest request)
	 {
		// put db code to insert the new user 
		String firstName =request.getParameter("first name");
		String lastName =request.getParameter("last name");
		String email =request.getParameter("usermail");
		String password =request.getParameter("password");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
		
		Connection cnn = DriverManager.getConnection("link");
		String insertCommand ="insert into user values('"+ firstName +"','"+ lastName +"','"+ email +"','"+ password +"')";
		Statement st =cnn.createStatement();
		st.executeUpdate(insertCommand);
		
		return "login";
	 } catch (Exception e) {
		 System.out.println(e);
			return "error";
	 } 
		
}
}
