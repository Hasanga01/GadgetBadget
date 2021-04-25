package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Research 
{
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost/gbsys", "root", "Thakshila+5");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public String insertResearch(String researchName, String researchField, String fundTotal, String completeStatus) 
	 { 
		String output = ""; 
		try
		{ 
			 Connection con = connect(); 
			 
			 if (con == null) 
			 {return "Error while connecting to the database for inserting."; } 
	 
			 // create a prepared statement
			 String query = " insert into research (`researchID`,`researchName`,`researchField`,`fundTotal`,`completeStatus`)" + " values (?, ?, ?, ?, ?)"; 
	 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, researchName); 
			 preparedStmt.setString(3, researchField); 
			 preparedStmt.setDouble(4, Double.parseDouble(fundTotal)); 
			 preparedStmt.setString(5, completeStatus); 
	
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
	
	
	public String readResearch() 
	 { 
		String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
		 
			 if (con == null) 
			 {return "Error while connecting to the database for reading."; } 
		 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Research Name</th><th>Research Field</th>" +
			 "<th>Fund Total</th>" + 
			 "<th>Complete Status</th>" +
			 "<th>Update</th><th>Remove</th></tr>"; 
		 
			 String query = "select * from research"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
		
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
				 String researchID = Integer.toString(rs.getInt("researchID")); 
				 String researchName = rs.getString("researchName"); 
				 String researchField = rs.getString("researchField"); 
				 String fundTotal = Double.toString(rs.getDouble("fundTotal")); 
				 String completeStatus = rs.getString("completeStatus"); 
			
				 // Add into the html table
				 output += "<tr><td>" + researchName + "</td>"; 
				 output += "<td>" + researchField + "</td>"; 
				 output += "<td>" + fundTotal + "</td>"; 
				 output += "<td>" +   completeStatus + "</td>"; 
			
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
				 + "<td><form method='post' action='research.jsp'>"
				 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				 + "<input name='researchID' type='hidden' value='" + researchID 
				 + "'>" + "</form></td></tr>"; 
			 } 
			 con.close(); 
		 
			 // Complete the html table
			 output += "</table>"; 
		 } 
		 
		 catch (Exception e) 
		 { 
			 output = "Error while reading the research."; 
			 System.err.println(e.getMessage()); 
		 } 
		 
		 return output; 
	 } 
		
	
	 public String updateResearch(String rID, String name, String field, String fundTotal, String completeStatus)
	 { 
		 String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
			 
			 if (con == null) 
			 {return "Error while connecting to the database for updating."; } 
	 
			 // create a prepared statement
			 String query = "UPDATE research SET researchName=?,researchField=?,fundTotal=?,completeStatus=? WHERE researchID=?"; 
	 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
	
			 // binding values
			 preparedStmt.setString(1, name); 
			 preparedStmt.setString(2, field); 
			 preparedStmt.setDouble(3, Double.parseDouble(fundTotal)); 
			 preparedStmt.setString(4, completeStatus); 
			 preparedStmt.setInt(5, Integer.parseInt(rID)); 
	 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while updating the research."; 
			 System.err.println(e.getMessage()); 
		 } 
	 
		 return output; 
	 } 
	
	
	 public String deleteItem(String researchID) 
	 { 
		 String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for deleting."; } 
	 
			 // create a prepared statement
			 String query = "delete from research where researchID=?"; 
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(researchID)); 
	 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Deleted successfully"; 
		 } 
	 
		 catch (Exception e) 
		 { 
			 output = "Error while deleting the research."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 } 
}
