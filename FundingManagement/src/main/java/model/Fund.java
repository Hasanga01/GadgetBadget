package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public class Fund {
	
	private Connection connect()
	 {
	 
		Connection con = null;
	
	try{
	    Class.forName("com.mysql.cj.jdbc.Driver");

	    //Provide the correct details: DBServer/DBName, username, password
	    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
	    
	 }catch (Exception e){
		 e.printStackTrace();}
	     return con;
	 } 
	
	public String insertFund(String fname , String faddress, String fphone ,String femail,String fdesc)
	 {
	 
		String output = "";
	 
		try{
	 
			Connection con = connect();
	
			if (con == null){
				return "Error while connecting to the database for inserting.";
				}
	 
			// create a prepared statement
	
			String query = " INSERT INTO `fund`(`fund_id`, `name`, `address`, `phone`, `e_mail`, `description`)  VALUES  (?, ?, ?,?, ?, ?)";
	 
			PreparedStatement preparedStmt = con.prepareStatement(query);
	        // binding values
	        preparedStmt.setInt(1, 0);
	        preparedStmt.setString(2, fname);
	        preparedStmt.setString(3,faddress );
	        preparedStmt.setString(4, fphone);
	        preparedStmt.setString(5, femail);
	        preparedStmt.setString(6, fdesc);
	       
	        
	        // execute the statement
	
	        preparedStmt.execute();
	        con.close();
	        output = "Inserted successfully";
	
		}catch (Exception e){
	 
			output = "Error while inserting the fund";
	        System.err.println(e.getMessage());
	 
		}
	
		return output;
	 } 
	
	public String readFund(){
	 
		String output = "";
	 
		try{
	
			Connection con = connect();
	 
			if (con == null){
				return "Error while connecting to the database for reading."; }
	 
			// Prepare the html table to be displayed
	
			output = "<table border='1'><tr><th>Funding body Name</th><th>Funding body Address</th>" +
	                "<th>Funding body phone</th>" +
	                "<th>Funding body email</th>" +
	                "<th>Funding body description</th><th>Update</th><th>Remove</th></tr>";

	 
			String query = "SELECT * from fund ";
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        // iterate through the rows in the result set
	     
	        while (rs.next()){
	            String fund_id=rs.getString("fund_id");
	        	String name = rs.getString("name");
	            String address = rs.getString("address");
	            String phone = rs.getString("phone");
	            String e_mail = rs.getString("e_mail");
	            String description = rs.getString("description");
	            
	            
	            
	            // Add into the html table
	
	            output += "<tr><td><input id='hidFundIDUpdate' name='hidFundIDUpdate' type='hidden' value='" + fund_id + "'>"
	            		 + name + "</td>";
	            output += "<td>" + address + "</td>";
	            output += "<td>" + phone + "</td>";
	            output += "<td>" + e_mail + "</td>";
	            output += "<td>" + description + "</td>";
	            
	
	            // buttons
	           output +="<td><input name='btnUpdate' type='button' value='Update' class='btn btn-success'></td>"
	        			 + "<td><form method='post' action='fund.jsp'>"
	        			 +"<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	        			 + "<input name='hidFundIDDelete' type='hidden' value='" + fund_id + "'> </form></td></tr>";
	  
	        }
	 
	        con.close();
	        // Complete the html table
	        output += "</table>";
	 
		}catch (Exception e){
	 
			output = "Error while reading the fund.";
	        System.err.println(e.getMessage());
	 
		}
	
		return output;
	 } 
	
	public String updateFund(String fund_id , String fname , String faddress, String fphone ,String femail,String fdesc){
	 
		String output = "";
	 
		try{
	 
			Connection con = connect();
	 
			if (con == null){
				return "Error while connecting to the database for updating.";
				}
	 
			// create a prepared statement
	        String query = "UPDATE `fund` SET `name`=?,`address`=?,`phone`=?,`e_mail`=?,`description`=?WHERE `fund_id`=?";
	        PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	        // binding values
	        preparedStmt.setString(1, fname);
	        preparedStmt.setString(2, faddress);
	        preparedStmt.setString(3, fphone);
	        preparedStmt.setString(4, femail);
	        preparedStmt.setString(5, fdesc);
	       
	        preparedStmt.setInt(6, Integer.parseInt(fund_id));
	
	        // execute the statement
	        preparedStmt.execute();
	        con.close();
	        output = "Updated successfully";
	 
		}catch (Exception e){
	 
			output = "Error while updating the fund.";
	        System.err.println(e.getMessage());
	 
		}
	
		return output;
	 } 
	
	public String deleteFund(String fund_id){
	 
		String output = "";
	
		try {
	 
			Connection con = connect();
	
			if (con == null){
				return "Error while connecting to the database for deleting.";
				}
	
			 // create a prepared statement
	         String query = "DELETE FROM `fund` WHERE `fund_id`=?";
	         PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	         // binding values
	         preparedStmt.setInt(1, Integer.parseInt(fund_id));
	 
	         // execute the statement
	         preparedStmt.execute();
	         con.close();
	         output = "Deleted successfully";
	
		}catch (Exception e){
			
	 
			output = "Error while deleting the fund.";
	        System.err.println(e.getMessage());
	
		}
	
		return output;
	 } 
}



