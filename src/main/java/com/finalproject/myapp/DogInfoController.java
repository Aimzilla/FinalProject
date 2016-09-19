package com.finalproject.myapp;

import java.io.StringReader;
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
					 System.out.println(ph);
					//NodeList childNode = photoElement.getChildNodes();
					//for (int j = 0; j < childNode.getLength(); j++) {
						//org.w3c.dom.Element optionsEle = (org.w3c.dom.Element) childNode.item(j);
					org.w3c.dom.Element descElement = (org.w3c.dom.Element) n9.item(i);
					description = descElement.getFirstChild().getNodeValue().trim();
					
					org.w3c.dom.Element sheltElement = (org.w3c.dom.Element) n10.item(i);
					shelterId = sheltElement.getFirstChild().getNodeValue().trim();	
						 
						 String randomDogResult = ("<h6>" + randomDog+ " " + name +" " +
									breed +" " + sex +" " + age +" " + size+" " + options+" " + ph +" " +"</h6>");
								dog=	new MyDog(randomDog,name,breed,sex,age,size,options,ph);
								dog.setDesc(description);
								dog.setShelterID(shelterId);
						// System.out.println("photo0000 " + ph);
					}
					
				
				 model.addAttribute("dog", dog);
		 }catch (Exception e) {
				System.out.println(e);
				
				return "errorpage";
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
			//HttpGet getPage = new HttpGet("/pet.find?format=xml&key=688cf0271f4f3125175bf1d9a9f8973f&location=48326&animal=dog&output=basic");
			//HttpGet getPage = new HttpGet("/pet.find?format=xml&key=688cf0271f4f3125175bf1d9a9f8973f&location=48326&animal=dog&breed="+breed+"&output=basic");
	
			// execute the http request and get the http response back
			HttpResponse resp = http.execute(host, getPage);

			//String result = EntityUtils.toString(resp.getEntity());

			String randomDogResult = "";
			String xmlString = EntityUtils.toString(resp.getEntity());
			//System.out.println(xmlString);
			
			//builds the factory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			
			InputSource inStream = new InputSource();
			inStream.setCharacterStream(new StringReader(xmlString));

			//"parse" 
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
			/*NodeList n9 = doc.getElementsByTagName("shelterId");
			NodeList n10 = doc.getElementsByTagName("address1");
			NodeList n11 = doc.getElementsByTagName("address2");
			NodeList n12 = doc.getElementsByTagName("city");
			NodeList n13 = doc.getElementsByTagName("zip");
			NodeList n14 = doc.getElementsByTagName("phone");
			NodeList n15 = doc.getElementsByTagName("email");*/
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
				//NodeList childNode = photoElement.getChildNodes();
				//for (int j = 0; j < childNode.getLength(); j++) {
					//org.w3c.dom.Element optionsEle = (org.w3c.dom.Element) childNode.item(j);
					
					 
					 randomDogResult += ("<h6>" + randomDog+ " " + name +" " +
								breed +" " + sex +" " + age +" " + size+" " + options+" " + ph +" " +"</h6>");
								dogList.add(new MyDog(randomDog,name,breed,sex,age,size,options,ph));
					// System.out.println("photo0000 " + ph);
				}
				//org.w3c.dom.Element photo=(org.w3c.dom.Element)childNode.item(1);
				
				
			//	org.w3c.dom.Element shelterIdElement = (org.w3c.dom.Element) n9.item(i);
			//	String shelterId = shelterIdElement.getFirstChild().getNodeValue().trim();
				
			//	org.w3c.dom.Element address1Element = (org.w3c.dom.Element) n10.item(i);
			//	String address1 = address1Element.getFirstChild().getNodeValue().trim();
				
			//	org.w3c.dom.Element address2Element = (org.w3c.dom.Element) n11.item(i);
			//	String address2 = address2Element.getFirstChild().getNodeValue().trim();
				
			//	org.w3c.dom.Element cityElement = (org.w3c.dom.Element) n12.item(i);
			//	String city = cityElement.getFirstChild().getNodeValue().trim();
				
			//	org.w3c.dom.Element zipElement = (org.w3c.dom.Element) n13.item(i);
			//	String zip = zipElement.getFirstChild().getNodeValue().trim();
				
			//	org.w3c.dom.Element phoneElement = (org.w3c.dom.Element) n14.item(i);
			//	String phone = phoneElement.getFirstChild().getNodeValue().trim();
				
			//	org.w3c.dom.Element emailElement = (org.w3c.dom.Element) n15.item(i);
			//	String email = emailElement.getFirstChild().getNodeValue().trim();
			
				
				
			

			//model.addAttribute("pagedata", randomDogResult);
			 model.addAttribute("dogs", dogList);
		} catch (Exception e) {
			System.out.println(e);
		
			return "errorpage";
		}
		return "DogSearch";
	}
}
