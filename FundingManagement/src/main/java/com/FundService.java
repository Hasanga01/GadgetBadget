package com;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Fund;

@Path("/Fund")
public class FundService {

	

		Fund Obj = new Fund();

		@GET
	    @Path("/")
	    @Produces(MediaType.TEXT_HTML)
		public String readFund(){
	 
			return Obj.readFund();
	 
		}
		
		@POST
		@Path("/") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertFund(@FormParam("name") String name,@FormParam("address") String address,@FormParam("phone") String phone,@FormParam("e_mail") String e_mail,@FormParam("description") String description) {
			
			String output = Obj.insertFund(name, address, phone, e_mail, description);
			return output;
		}
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateFund(String FundData)
		{
		//Convert the input string to a JSON object
		 JsonObject Object = new JsonParser().parse(FundData).getAsJsonObject();
		//Read the values from the JSON object
		 String fund_id = Object.get("fund_id").getAsString();
		 String name = Object.get("name").getAsString();
		 String address = Object.get("address").getAsString();
		 String phone = Object.get("phone").getAsString();
		 String e_mail = Object.get("e_mail").getAsString();
		 String description = Object.get("description").getAsString();
		 String output = Obj.updateFund(fund_id, name, address, phone, e_mail, description);
		
		 return output;
		}
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteFund(String FundData)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(FundData, "", Parser.xmlParser());

		//Read the value from the element <ProductID>
		 String fund_id = doc.select("fund_id").text();
		 String output = Obj.deleteFund(fund_id);
		return output;
		}


}
