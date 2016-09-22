package com.finalproject.myapp;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.finalproject.myapp.DogInfoController;
import com.finalproject.myapp.MyDog;
import com.finalproject.myapp.User;

@Controller
public class DogInfoController {
	private static final Logger logger = LoggerFactory.getLogger(DogInfoController.class);
	MyDog dog;
	@RequestMapping(value = "dogsearch")
	public String dogsearch(Model model, HttpServletRequest request) {
	
		logger.info("Welcome dogsearch! Inside dogcontroller");
		System.out.println("dog id is  "+ request.getParameter("id"));
		
	MyDog md= (MyDog) request.getAttribute("model");
	if(md!=null)System.out.println(" dogname " + md.getName());
		
		 try {
		        HttpClient http = HttpClientBuilder.create().build();

		        // address that we want to call
		        HttpHost host = new HttpHost("api.petfinder.com", 80, "http");
		        // http method: get
		        
		        String page="/pet.get?id="+request.getParameter("id")+"&&format=xml&key=688cf0271f4f3125175bf1d9a9f8973f";
		        System.out.println(page);
		        
		      HttpGet getPage = new HttpGet(page);

		        //getPage.setHeader("X-Mashape-Key", "put key here");
		      
		        // execute the http request and get the http response back
		        HttpResponse resp = http.execute(host, getPage);
		        String result = "";
		        String xmlString = EntityUtils.toString(resp.getEntity());
		      //builds the factory
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = factory.newDocumentBuilder();
				
				InputSource inStream = new InputSource();
				inStream.setCharacterStream(new StringReader(xmlString));

				//"parse" 
				Document doc = db.parse(inStream);
			
				String randomDog = "empty";String name="";String breed="";
				String sex=""; String age=""; String size =""; String options="";   
				String description=""; String shelterId="";
				NodeList nl = doc.getElementsByTagName("id");//<text is what we are getting, we could get images, or whatever
				NodeList n2 = doc.getElementsByTagName("name");
				NodeList n3 = doc.getElementsByTagName("breed");
				NodeList n4 = doc.getElementsByTagName("sex");
				NodeList n5 = doc.getElementsByTagName("age");
				NodeList n6 = doc.getElementsByTagName("size");
				NodeList n7 = doc.getElementsByTagName("option");
				NodeList n8 = doc.getElementsByTagName("photo");
				NodeList n9 = doc.getElementsByTagName("description");
				NodeList n10 = doc.getElementsByTagName("shelterId");
								
				String ph="";
				for (int i = 0; i < nl.getLength(); i++) {

					org.w3c.dom.Element idElement = (org.w3c.dom.Element) nl.item(i);
					randomDog = idElement.getFirstChild().getNodeValue().trim();
					
					org.w3c.dom.Element nameElement = (org.w3c.dom.Element) n2.item(i);
					 name = nameElement.getFirstChild().getNodeValue().trim();
					
					org.w3c.dom.Element breedElement = (org.w3c.dom.Element) n3.item(i);
					 breed = breedElement.getFirstChild().getNodeValue().trim();
					
					org.w3c.dom.Element sexElement = (org.w3c.dom.Element) n4.item(i);
					 sex = sexElement.getFirstChild().getNodeValue().trim();
					
					org.w3c.dom.Element ageElement = (org.w3c.dom.Element) n5.item(i);
					 age = ageElement.getFirstChild().getNodeValue().trim();
					
					org.w3c.dom.Element sizeElement = (org.w3c.dom.Element) n6.item(i);
					 size = sizeElement.getFirstChild().getNodeValue().trim();
					
					org.w3c.dom.Element optionsElement = (org.w3c.dom.Element) n7.item(i);
					 options = optionsElement.getFirstChild().getNodeValue().trim();
					
					
					 //so that photo matchesup
					org.w3c.dom.Element photoElement = (org.w3c.dom.Element) n8.item(i);
					int ctr=1;
					 ph = photoElement.getFirstChild().getNodeValue().trim();
					
					while(ph.indexOf(randomDog) ==-1)
					{
						
						 photoElement = (org.w3c.dom.Element) n8.item(i+ctr);
						 ph = photoElement.getFirstChild().getNodeValue().trim();
						ctr++;
						
					}
				//	 System.out.println(ph);

					org.w3c.dom.Element descElement = (org.w3c.dom.Element) n9.item(i);
					description = descElement.getFirstChild().getNodeValue().trim();
					
					org.w3c.dom.Element sheltElement = (org.w3c.dom.Element) n10.item(i);
					shelterId = sheltElement.getFirstChild().getNodeValue().trim();	
						 
						 String randomDogResult = ("<h6>" + randomDog+ " " + name +" " +
									breed +" " + sex +" " + age +" " + size+" " + options+" " + ph +" " +"</h6>");
								dog=	new MyDog(randomDog,name,breed,sex,age,size,options,ph);
								dog.setDesc(description);
								dog.setShelterID(shelterId);

					}
					
				
				 model.addAttribute("dog", dog);
		 }catch (Exception e) {
				System.out.println(e);
				
				return "AllAdoptedError";
			}
		 try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection cnn = DriverManager.getConnection("link");
				String selectSQL = "select CuddleFactor from CuddleFactor where name= ?";
				PreparedStatement preparedStatement = cnn.prepareStatement(selectSQL);
				preparedStatement.setString(1, request.getParameter("id"));
				//System.out.println("Name ="+dog.getName());
				
			ResultSet rs = preparedStatement.executeQuery();
			String cuddlefactor="";
			 while(rs.next())
			 {
				 cuddlefactor= rs.getString(1);
				// System.out.println(cuddlefactor);
			 }
			 
			 model.addAttribute("cuddlefactor", cuddlefactor);
		 }catch(Exception e){
				System.out.println(e);
				//e.printStackTrace();
				return "errorPage";
			}
		 
		return "DogInfoForm";
		 
	}
	
	
	@RequestMapping(value = "formPage")
	public String dogsearchbyBreed(Model model, HttpServletRequest request,HttpSession session) {
	
		logger.info("Welcome dogsearch! Inside dogcontroller's dogsearchbyBreed method");
		System.out.println("selected dog's breed is  "+ request.getParameter("breed"));
		
		try {
			System.out.println("User  is  ");
			//getting user from login controller
			if(((User)session.getAttribute("user")).getEmail()!=null)
			System.out.println("User  is  " + ((User)session.getAttribute("user")).getEmail());	
			HttpClient http = HttpClientBuilder.create().build();

			// address that we want to call
			HttpHost host = new HttpHost("api.petfinder.com", 80, "http");
			// http method: get
			HttpGet getPage = new HttpGet("/pet.find?format=xml&key=688cf0271f4f3125175bf1d9a9f8973f&location=48326&animal=dog&breed="+request.getParameter("breed")+"&output=basic");
			
	
			// execute the http request and get the http response back
			HttpResponse resp = http.execute(host, getPage);


			String randomDogResult = "";
			String xmlString = EntityUtils.toString(resp.getEntity());
			
			//builds the factory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			
			InputSource inStream = new InputSource();
			inStream.setCharacterStream(new StringReader(xmlString));

			Document doc = db.parse(inStream);
		
			String randomDog = "empty";String name="";String breed="";
			String sex=""; String age=""; String size =""; String options="";          
			NodeList nl = doc.getElementsByTagName("id");//<text is what we are getting, we could get images, or whatever
			NodeList n2 = doc.getElementsByTagName("name");
			NodeList n3 = doc.getElementsByTagName("breed");
			NodeList n4 = doc.getElementsByTagName("sex");
			NodeList n5 = doc.getElementsByTagName("age");
			NodeList n6 = doc.getElementsByTagName("size");
			NodeList n7 = doc.getElementsByTagName("option");
			NodeList n8 = doc.getElementsByTagName("photo");

			ArrayList<MyDog> dogList = new ArrayList<MyDog>();
			String ph="";
			for (int i = 0; i < nl.getLength(); i++) {

				org.w3c.dom.Element idElement = (org.w3c.dom.Element) nl.item(i);
				randomDog = idElement.getFirstChild().getNodeValue().trim();
				
				org.w3c.dom.Element nameElement = (org.w3c.dom.Element) n2.item(i);
				 name = nameElement.getFirstChild().getNodeValue().trim();
				
				org.w3c.dom.Element breedElement = (org.w3c.dom.Element) n3.item(i);
				 breed = breedElement.getFirstChild().getNodeValue().trim();
				
				org.w3c.dom.Element sexElement = (org.w3c.dom.Element) n4.item(i);
				 sex = sexElement.getFirstChild().getNodeValue().trim();
				
				org.w3c.dom.Element ageElement = (org.w3c.dom.Element) n5.item(i);
				 age = ageElement.getFirstChild().getNodeValue().trim();
				
				org.w3c.dom.Element sizeElement = (org.w3c.dom.Element) n6.item(i);
				 size = sizeElement.getFirstChild().getNodeValue().trim();
				
				org.w3c.dom.Element optionsElement = (org.w3c.dom.Element) n7.item(i);
				 options = optionsElement.getFirstChild().getNodeValue().trim();
				
				
				 //so that photo matchesup
				org.w3c.dom.Element photoElement = (org.w3c.dom.Element) n8.item(i);
				int ctr=1;
				 ph = photoElement.getFirstChild().getNodeValue().trim();
				 System.out.println(ph);
				while(ph.indexOf(randomDog) ==-1)
				{
					
					 photoElement = (org.w3c.dom.Element) n8.item(i+ctr);
					 ph = photoElement.getFirstChild().getNodeValue().trim();
					ctr++;
					
				}
				
					 
					 randomDogResult += ("<h6>" + randomDog+ " " + name +" " +
								breed +" " + sex +" " + age +" " + size+" " + options+" " + ph +" " +"</h6>");
								dogList.add(new MyDog(randomDog,name,breed,sex,age,size,options,ph));
				}
	
			

			//model.addAttribute("pagedata", randomDogResult);
			 model.addAttribute("dogs", dogList);
		} catch (Exception e) {
			System.out.println(e);
		
			return "AllAdoptedError";
		}
		return "DogSearch";
	}
	@RequestMapping(value = "favourite", method = RequestMethod.GET)
	public String inputfavorite(Model model, HttpServletRequest request,HttpSession session) {
	
		logger.info("Welcome inputfavorite! Inside dogcontroller's inputfavorite method" );
		
		ArrayList<MyDog> dogList = new ArrayList<MyDog>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cnn = DriverManager.getConnection("link");
			
			String dogid=request.getParameter("dogid");
			User u = (User)session.getAttribute("user");
			String email =u.getEmail();

			if(request.getParameter("actiontype").equalsIgnoreCase("add")){
			PreparedStatement updateemp = cnn.prepareStatement
					("insert into favorite values(?,?)");
					updateemp.setString(1,email);
					updateemp.setString(2,dogid);
					updateemp.executeUpdate();
			}else if(request.getParameter("actiontype").equalsIgnoreCase("remove")){
				PreparedStatement updateemp = cnn.prepareStatement
						("delete from favorite where email =? and dogid=?");
						updateemp.setString(1,email);
						updateemp.setString(2,dogid);
						updateemp.executeUpdate();
			}
				
				String selectSQL = "select distinct dogid from favorite where email= ?";
				PreparedStatement preparedStatement = cnn.prepareStatement(selectSQL);
				preparedStatement.setString(1, email);
				
				ResultSet rs = preparedStatement.executeQuery();
				MyDog dg=null;
				 while(rs.next())
				 {
					String idofdog= rs.getString(1);
					 dg =getdogFromId(idofdog);
					 dogList.add(dg);
				 }
				 
				 model.addAttribute("dogs", dogList);
				
				}catch(Exception e){
					System.out.println(e);
					//e.printStackTrace();
					return "errorpage";
				}
		          return "Favorite";
	}
		
	public MyDog getdogFromId(String id){
		
		 try {
		        HttpClient http = HttpClientBuilder.create().build();

		        // address that we want to call
		        HttpHost host = new HttpHost("api.petfinder.com", 80, "http");
		        // http method: get
		        
		        String page="/pet.get?id="+id+"&&format=xml&key=688cf0271f4f3125175bf1d9a9f8973f";
		        System.out.println(page);
		        
		      HttpGet getPage = new HttpGet(page);

		        //getPage.setHeader("X-Mashape-Key", "put key here");
		      
		        // execute the http request and get the http response back
		        HttpResponse resp = http.execute(host, getPage);
		        String result = "";
		        String xmlString = EntityUtils.toString(resp.getEntity());
		      //  System.out.println(xmlString);
		      //builds the factory
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = factory.newDocumentBuilder();
				
				InputSource inStream = new InputSource();
				inStream.setCharacterStream(new StringReader(xmlString));

				//"parse" 
				Document doc = db.parse(inStream);
			
				String randomDog = "empty";String name="";String breed="";
				String sex=""; String age=""; String size =""; String options="";   
				String description=""; String shelterId="";
				NodeList nl = doc.getElementsByTagName("id");//<text is what we are getting, we could get images, or whatever
				NodeList n2 = doc.getElementsByTagName("name");
				NodeList n3 = doc.getElementsByTagName("breed");
				NodeList n4 = doc.getElementsByTagName("sex");
				NodeList n5 = doc.getElementsByTagName("age");
				NodeList n6 = doc.getElementsByTagName("size");
				NodeList n7 = doc.getElementsByTagName("option");
				NodeList n8 = doc.getElementsByTagName("photo");
				NodeList n9 = doc.getElementsByTagName("description");
				NodeList n10 = doc.getElementsByTagName("shelterId");
								
				String ph="";
				for (int i = 0; i < nl.getLength(); i++) {

					org.w3c.dom.Element idElement = (org.w3c.dom.Element) nl.item(i);
					randomDog = idElement.getFirstChild().getNodeValue().trim();
					
					org.w3c.dom.Element nameElement = (org.w3c.dom.Element) n2.item(i);
					 name = nameElement.getFirstChild().getNodeValue().trim();
					
					org.w3c.dom.Element breedElement = (org.w3c.dom.Element) n3.item(i);
					 breed = breedElement.getFirstChild().getNodeValue().trim();
					
					org.w3c.dom.Element sexElement = (org.w3c.dom.Element) n4.item(i);
					 sex = sexElement.getFirstChild().getNodeValue().trim();
					
					org.w3c.dom.Element ageElement = (org.w3c.dom.Element) n5.item(i);
					 age = ageElement.getFirstChild().getNodeValue().trim();
					
					org.w3c.dom.Element sizeElement = (org.w3c.dom.Element) n6.item(i);
					 size = sizeElement.getFirstChild().getNodeValue().trim();
					
					org.w3c.dom.Element optionsElement = (org.w3c.dom.Element) n7.item(i);
					 options = optionsElement.getFirstChild().getNodeValue().trim();
					
					
					 //so that photo matchesup
					org.w3c.dom.Element photoElement = (org.w3c.dom.Element) n8.item(i);
					int ctr=1;
					 ph = photoElement.getFirstChild().getNodeValue().trim();
					
					while(ph.indexOf(randomDog) ==-1)
					{
						
						 photoElement = (org.w3c.dom.Element) n8.item(i+ctr);
						 ph = photoElement.getFirstChild().getNodeValue().trim();
						ctr++;
						
					}
					// System.out.println(ph);
					
					org.w3c.dom.Element descElement = (org.w3c.dom.Element) n9.item(i);
					description = descElement.getFirstChild().getNodeValue().trim();
					
					org.w3c.dom.Element sheltElement = (org.w3c.dom.Element) n10.item(i);
					shelterId = sheltElement.getFirstChild().getNodeValue().trim();	
						 
						 
								dog=	new MyDog(randomDog,name,breed,sex,age,size,options,ph);
								dog.setDesc(description);
								dog.setShelterID(shelterId);
						
					}
				return dog;
					
		 }catch (Exception e) {
				System.out.println(e);
				
				//return "errorPage";
			}
		 return dog;
}
}
