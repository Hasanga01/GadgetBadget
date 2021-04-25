


package com;
import model.Payment;
import java.sql.Date;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

//For JSON
import com.google.gson.*;

@Path("/payments")

public class PaymentService {

	Payment paymentObj= new Payment();
	
		 //Read All payment path
	
		 @GET
		 @Path("/")
		 @Produces(MediaType.TEXT_HTML)
		 public String readPaymentService()
		 
		  {
			 
		 	 return paymentObj.readPayment();
		 	 
		  }
		 
        //Insert All Payment Path
		 
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String insertPayment(
		 @FormParam("Payment_Id") int Payment_Id,
		 @FormParam("order_id") int order_id,
		 @FormParam("payment_date") Date payment_date,
		 @FormParam("amount") String amount,
		 @FormParam("postal_address") String postal_address,
		 @FormParam("postal_code") String postal_code,
		 @FormParam("payment_type") String payment_type
		 ) 
		

	{
		 String output = paymentObj.insertPayment( Payment_Id,  order_id, payment_date ,amount, postal_address,  postal_code, payment_type );
		return output;
	}

		//Update All payment path
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		
		
		public String updatePayment(String paymentData) {
		
			 //Convert the input string to a JSON object
			
			 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();//Read the values from the JSON object			 
			 String Payment_Id  = paymentObject.get("Payment_Id").getAsString();
			 String order_id = paymentObject.get("order_id").getAsString();
			 String payment_date = paymentObject.get("payment_date").getAsString();
			 String amount = paymentObject.get("amount").getAsString();
			 String postal_address = paymentObject.get("postal_address").getAsString();
			 String postal_code = paymentObject.get("postal_code").getAsString();
			 String payment_type = paymentObject.get("payment_type").getAsString();
			

			String output = paymentObj.updatePayment( Payment_Id, order_id,  payment_date,  amount,  postal_address, postal_code, payment_type ) ;
			return output;
		}
	
		//Delete All payment Path
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteItem(String paymentData)
		{
			
			
		//Convert the input string to an XML document
			
		 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

		//Read the value from the element <itemID>
		 
		 String Payment_Id  = doc.select("	Payment_Id ").text();
		 String output = paymentObj.deletePayment(Payment_Id);
		return output;
		
		}

		//View Buyer Profile Path
		@POST
		@Path("/viewProfileBuyers")
		@Produces(MediaType.TEXT_HTML)
		public String  ViewBuyerDetails(@FormParam("Payment_Id") int Payment_Id) {
			return paymentObj.viewProfilePayment(Payment_Id);
		}		
}