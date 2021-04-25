

package model;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Payment {
	
	// connect to the data base
		private Connection connect() {
			Connection con = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");

				// Provide the correct details: DBServer/DBName, username, password
				
				con= DriverManager.getConnection("jdbc:mysql://localhost:3306/payment?useTimezone=true&serverTimezone=UTC", "root", "");
			
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error at line 24");
			}
			return con;
		}
		
		
		//=========================  Insert payment data  =======================	
		public String insertPayment(int Payment_Id, int order_id, Date payment_date,String amount ,String postal_address, String postal_code, String payment_type)
		 {
		 
			String output = "";
		
		try
		 {
		 Connection con = connect();
		 
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 
		 // create a prepared statement
		 String query = " insert into payment(`Payment_Id`,`order_id`,`payment_date`,`amount`,`postal_address`,`postal_code`,`payment_type`)" + " values (?, ?,?,?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setInt(2, order_id);
		 preparedStmt.setDate(3, payment_date);
		 preparedStmt.setString(4, amount);
		 preparedStmt.setString(5, postal_address);
		 preparedStmt.setString(6, postal_code);
		 preparedStmt.setString(7, payment_type);
		 

		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 
		 }
		 
		catch (Exception e)
		{
		 output = "Error while inserting the item.";
		 System.err.println(e.getMessage());
		
		}
		 return output;
		}

		
		
		//======================== Read payment details ============================
		public String readPayment()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		
		 // Prepare the html table to be displayed
		 output = "<table border='1'>"
		 		+ "<tr>"
				+"<th>Payment_ID</th>"
		 		+ "<th>Order_ID</th>"
		 		+ "<th>Payment_date</th>"
		 		+ "<th>Amount</th>"
		 		+ "<th>Postal_Address</th>"
		 		+"<th>Postal_Code</th>"
		 		+"<th>Payment_type</th>"
		 	    +"<th>Update</th>"
		 	    + "<th>Remove</th>"
		 	    + "</tr>";

		 String query = "select * from payment";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String Payment_Id   = Integer.toString(rs.getInt("Payment_Id"));
		 String order_id   = Integer.toString(rs.getInt("order_id "));
		 Date payment_date = rs.getDate("payment_date");
		 String amount = rs.getString("amount");
		 String postal_address = rs.getString("postal_address");
		 String postal_code = rs.getString("postal_code");
		 String payment_type = rs.getString("payment_type");
		 

		 
		 // Add into the html table
		 output += "<tr><td>" + Payment_Id + "</td>";
		 output += "<td>" + order_id + "</td>";
		 output += "<td>" + payment_date + "</td>";
		 output += "<td>" + amount + "</td>";
		 output += "<td>" + postal_address + "</td>";
		 output += "<td>"+ postal_code +"</td>";
		 output += "<td>" + payment_type  + "</td>";
		 

		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>" + "<td><form method='post' action='Payment.jsp'>" + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
		 + "<input name='itemID' type='hidden' value='" + Payment_Id
		 + "'>" + "</form></td></tr>";
		 }
		 con.close();
		 
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
		 System.out.println("Error at line 139");
		 }
		 return output;
		 }
		
		
		
		
		//======================== Update payment details ==========================
		public String updatePayment(String PId,String oid,String pdate ,String pamount,String paddress, String pcode,String ptype)
		{
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 // create a prepared statement
			 String query = "UPDATE payment SET order_id=?, payment_date=?, amount=?, postal_address=?, postal_code=?, payment_type=?   WHERE Payment_Id=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 
			 // binding values
			 
			 preparedStmt.setInt(1, Integer.parseInt(oid));
			 preparedStmt.setString(2, pdate);
			 preparedStmt.setString(3, pamount);
			 preparedStmt.setString(4, paddress);
			 preparedStmt.setString(5, pcode);
			 preparedStmt.setString(6, ptype);
			 preparedStmt.setInt(7, Integer.parseInt(PId));
			 
			 
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Updated successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while updating the item.";
			 System.err.println(e.getMessage());
			 System.out.println("Error at line 182");
			 }
			 return output;
			 }
		
		
		
		//========================= Delete payment data ========================
		public String deletePayment(String Payment_Id )
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for deleting."; 
			 }
		 // create a prepared statement
		 String query = "delete from payment where Payment_Id =?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(Payment_Id));
		 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the item.";
		 System.err.println(e.getMessage());
		 System.out.println("Error at line 216");
		 }
		 return output;
		 }
		
		
		
		//======================View payment details =====================
		public String viewProfilePayment(int Payment_Id)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		
		 // Prepare the html table to be displayed
		
		 
		 output = "<table border='1'>"
			 		+ "<tr>"
					+"<th>Payment_ID</th>"
			 		+ "<th>Order_ID</th>"
			 		+ "<th>Payment_date</th>"
			 		+ "<th>Amount</th>"
			 		+ "<th>Postal_Address</th>"
			 		+"<th>Postal_Code</th>"
			 		+"<th>Payment_type</th>"
			 	    +"<th>Update</th>"
			 	    + "<th>Remove</th>"
			 	    + "</tr>";
	 

		 String query = "select * from payment where 	Payment_ID=? ";
		 PreparedStatement stmt = con.prepareStatement(query);

		 stmt.setInt(1, Payment_Id);
		 ResultSet rs = stmt.executeQuery();
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String Payment_ID  = Integer.toString(rs.getInt("Payment_ID"));
		 String order_id  = Integer.toString(rs.getInt("order_id"));
		 Date payment_date = rs.getDate("payment_date");
		 String amount = rs.getString("amount");
		 String postal_address = rs.getString("postal_address");
		 String postal_code = rs.getString("postal_code");
		 String payment_type = rs.getString("payment_type");
		 
		 
		 // Add into the html table
		 output += "<tr><td>" + Payment_ID + "</td>";
		 output += "<td>" + order_id + "</td>";
		 output += "<td>" + payment_date + "</td>";
		 output += "<td>" + amount + "</td>";
		 output += "<td>" + postal_address + "</td>";
		 output += "<td>"+ postal_code +"</td>";
		 output += "<td>" +payment_type  + "</td>";
		 

		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>" + "<td><form method='post' action='Payment.jsp'>" + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
		 + "<input name='Payment_ID' type='hidden' value='" + Payment_ID
		 + "'>" + "</form></td></tr>";
		 }
		 con.close();
		 
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }

	
		
}		
		

		
		
		
		
