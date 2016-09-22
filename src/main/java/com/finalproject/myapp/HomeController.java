package com.finalproject.myapp;

import java.io.StringReader;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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

import com.finalproject.myapp.MyDog;
import com.finalproject.myapp.Shelter;
import com.finalproject.myapp.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value ={ "/","home"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}


	@RequestMapping(value = "sayGoodBye", method = RequestMethod.GET)
	public String mainMethod(Model model) {
		// logger.info("Welcome home! The client locale is {}.", locale);

		// Date date = new Date();
		// DateFormat dateFormat =
		// DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG,
		// locale);

		// String formattedDate = dateFormat.format(date);

		model.addAttribute("message", "Good Bye!");

		return "helloworld";
	}
	@RequestMapping(value = "findshelter", method = RequestMethod.GET)
	public String getShelter(Model model) {
	    try {
	        HttpClient http = HttpClientBuilder.create().build();

	        // address that we want to call
	        HttpHost host = new HttpHost("api.petfinder.com", 80, "http");
	        // http method: get
	      HttpGet getPage = new HttpGet("/shelter.find?location=48201&&format=xml&key=688cf0271f4f3125175bf1d9a9f8973f");

	        //getPage.setHeader("X-Mashape-Key", "put key here");
	      
	        // execute the http request and get the http response back
	        HttpResponse resp = http.execute(host, getPage);
	        String result = "";
	        String xmlString = EntityUtils.toString(resp.getEntity());
	        System.out.println(xmlString);
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = factory.newDocumentBuilder();
	        InputSource inStream = new InputSource();
	        inStream.setCharacterStream(new StringReader(xmlString));
	        Document doc = db.parse(inStream);
	        
	      String id = "empty";
	       NodeList nl = doc.getElementsByTagName("id");
	       NodeList n2 = doc.getElementsByTagName("name");
	       NodeList n3 = doc.getElementsByTagName("address1");
	       NodeList n4 = doc.getElementsByTagName("address2");
	       NodeList n5 = doc.getElementsByTagName("city");
	       NodeList n6 = doc.getElementsByTagName("state");
	       NodeList n7 = doc.getElementsByTagName("zip");
	       NodeList n8 = doc.getElementsByTagName("country");
	       NodeList n9 = doc.getElementsByTagName("phone");
	       NodeList n10 = doc.getElementsByTagName("email");
	       System.out.println(nl.getLength());
	       ArrayList<Shelter> lst = new ArrayList<Shelter>();
	       for (int i = 0; i < nl.getLength(); i++) {
	    	   id="";
	       org.w3c.dom.Element idElement = (org.w3c.dom.Element) nl.item(i);
	      // id = idElement.getFirstChild().getNodeValue().trim(); 
	       if (idElement.getFirstChild()!=null)
	    	   id = idElement.getFirstChild().getNodeValue().trim();
	       System.out.println(id);
	       
	       String name ="";
	       org.w3c.dom.Element nameElement = (org.w3c.dom.Element) n2.item(i);
	       if (nameElement.getFirstChild()!=null)
	    	 name = nameElement.getFirstChild().getNodeValue().trim();
	      
	       System.out.println(name);
	       String address1 ="";
	       org.w3c.dom.Element address1Element = (org.w3c.dom.Element) n3.item(i);
	       if (address1Element.getFirstChild()!=null)
	       address1 = address1Element.getFirstChild().getNodeValue().trim();
	       
	       System.out.println(address1);
	       String address2 ="";
	       org.w3c.dom.Element address2Element = (org.w3c.dom.Element) n4.item(i);
	       if (address2Element.getFirstChild()!=null)
	           address2 = address2Element.getFirstChild().getNodeValue().trim();
	       
	       System.out.println(address2);
	       String city="";
	       org.w3c.dom.Element cityElement = (org.w3c.dom.Element) n5.item(i);
	       if (cityElement.getFirstChild()!=null)
	    	   city = cityElement.getFirstChild().getNodeValue().trim();
	         System.out.println(city);
	       
	         String state="";
	       org.w3c.dom.Element stateElement = (org.w3c.dom.Element) n6.item(i);
	       if (stateElement.getFirstChild()!=null)
	    	   state = stateElement.getFirstChild().getNodeValue().trim();
	       
	       System.out.println(state);
	       
	       String zip="";
	       org.w3c.dom.Element zipElement = (org.w3c.dom.Element) n7.item(i);
	       if (zipElement.getFirstChild()!=null)
	    	   zip = zipElement.getFirstChild().getNodeValue().trim();
	       
	       System.out.println(zip);
	       
	       String country="";
	       org.w3c.dom.Element countryElement = (org.w3c.dom.Element) n8.item(i);
	       if (countryElement.getFirstChild()!=null)
	    	   country = countryElement.getFirstChild().getNodeValue().trim();
	       
	       //System.out.println(country);
	      
	       String phone="";
	       org.w3c.dom.Element phoneElement = (org.w3c.dom.Element) n9.item(i);
	       if (phoneElement.getFirstChild()!=null)
	        phone = phoneElement.getFirstChild().getNodeValue().trim();
	       
	       System.out.println(phone);
	       String email="";
	       org.w3c.dom.Element emailElement = (org.w3c.dom.Element) n10.item(i);
	       if (emailElement.getFirstChild()!=null)
	    	   email = emailElement.getFirstChild().getNodeValue().trim();
	       
	       System.out.println(email);
	       
	       lst.add(new Shelter(id,name,address1,address2,city,state,zip,country,phone,email));
	       
	        result += ("<h6>" + "SHELTER NAME :  " + id + " NAME  : " + name + " address1  : " 
	        + address1 +" address2  : " + address2 +" CITY  : " + city +" state  : " + state
	        +" zip  : " + zip +" country  : " + country +" phone  : " + phone +" email  : " + email +"</h6>");
	        System.out.println(result );
	       }            

	        model.addAttribute("pagedata", result);
	        model.addAttribute("shelter", lst);
	        
	        
	    } catch (Exception e) {
	        System.out.println(e);
	         
	    	return "errorpage";
	    }
	    return "Start";

	}
		@RequestMapping(value = "getRandomDog", method = RequestMethod.GET)
		public String getRandomDog(Model model, HttpServletRequest request,HttpSession session) {
			try {
				System.out.println("User  is  ");
				//getting user from login controller
				if(((User)session.getAttribute("user")).getEmail()!=null)
				System.out.println("User  is  " + ((User)session.getAttribute("user")).getEmail());	
				HttpClient http = HttpClientBuilder.create().build();

				// address that we want to call
				HttpHost host = new HttpHost("api.petfinder.com", 80, "http");
				// http method: get
				
				HttpGet getPage = new HttpGet("/pet.find?format=xml&key=688cf0271f4f3125175bf1d9a9f8973f&location=48326&animal=dog&output=basic");
				
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
					
				 model.addAttribute("dogs", dogList);
			} catch (Exception e) {
				System.out.println(e);
			
				return "errorpage";
			}
			return "DogSearch";

		}
		
	
}
