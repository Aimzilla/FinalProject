package com.finalproject.myapp;

import java.io.StringReader;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.finalproject.myapp.Shelter;

@Controller
public class ShelterDetailController {

@RequestMapping(value = "shelterdetail", method = RequestMethod.GET)
public String getShelter(Model model, HttpServletRequest request,HttpSession session) {
    try {
        HttpClient http = HttpClientBuilder.create().build();

        // address that we want to call
        HttpHost host = new HttpHost("api.petfinder.com", 80, "http");
        // http method: get
      HttpGet getPage = new HttpGet("/shelter.get?id="+request.getParameter("shelterid")+"&&format=xml&key=688cf0271f4f3125175bf1d9a9f8973f");
    
        //getPage.setHeader("X-Mashape-Key", "put key here");
      
        // execute the http request and get the http response back
        HttpResponse resp = http.execute(host, getPage);
        String result = "";
        String xmlString = EntityUtils.toString(resp.getEntity());
        System.out.println("shelter details" + xmlString);
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
       //ArrayList<Shelter> lst = new ArrayList<Shelter>();
       Shelter shelter=null;
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
       
        shelter= new Shelter(id,name,address1,address2,city,state,zip,country,phone,email);
       
        result += ("<h6>" + "SHELTER NAME :  " + id + " NAME  : " + name + " address1  : " 
        + address1 +" address2  : " + address2 +" CITY  : " + city +" state  : " + state
        +" zip  : " + zip +" country  : " + country +" phone  : " + phone +" email  : " + email +"</h6>");
        System.out.println(result );
       }            

       model.addAttribute("shelter", shelter);
        
    } catch (Exception e) {
        System.out.println(e);
         
    	return "errorpage";
    }
    return "ShelterInfoForm";
    

}
}
