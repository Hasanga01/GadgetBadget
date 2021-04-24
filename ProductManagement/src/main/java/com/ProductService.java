package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Product;

@Path("/Product")
public class ProductService {

	

		Product Obj = new Product();

		@GET
	    @Path("/")
	    @Produces(MediaType.TEXT_HTML)
		public String readProduct(){
	 
			return Obj.readProduct();
	 
		}
		
		@POST
		@Path("/") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertProduct(@FormParam("productname") String productname,@FormParam("productcode") String productcode,@FormParam("productquantity") String productquantity,@FormParam("productdescription") String productdescription,@FormParam("productprice") String productprice,@FormParam("productavailable") String productavailable) {
			
			String output = Obj.insertProduct(productname, productcode, productquantity, productdescription, productprice, productavailable);
			return output;
		}
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateProduct(String ProductData)
		{
		//Convert the input string to a JSON object
		 JsonObject Object = new JsonParser().parse(ProductData).getAsJsonObject();
		//Read the values from the JSON object
		 String PID = Object.get("PID").getAsString();
		 String productname = Object.get("productname").getAsString();
		 String productcode = Object.get("productcode").getAsString();
		 String productquantity = Object.get("productquantity").getAsString();
		 String productdescription = Object.get("productdescription").getAsString();
		 String productprice = Object.get("productprice").getAsString();
		 String productavailable = Object.get("productavailable").getAsString();
		 String output = Obj.updateProduct(PID, productname, productcode, productquantity, productdescription, productprice,productavailable);
		
		 return output;
		}
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteProduct(String ProductData)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(ProductData, "", Parser.xmlParser());

		//Read the value from the element <ProductID>
		 String PID = doc.select("PID").text();
		 String output = Obj.deleteProduct(PID);
		return output;
		}


}
