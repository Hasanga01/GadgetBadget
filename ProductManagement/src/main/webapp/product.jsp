<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="model.Product" %>


<%
if (request.getParameter("productname") != null)
{
	Product Obj = new Product();
	 String stsMsg="";
	if (request.getParameter("hidProductIDSave") == "")
	 {
	 
	 stsMsg = Obj.insertProduct(request.getParameter("productname"),
	 request.getParameter("productcode"),
	 request.getParameter("productquantity"),
	 request.getParameter("productdescription"),
	 request.getParameter("productprice"),
	 request.getParameter("productavailable"));
	 
	 } else{
		 stsMsg =Obj.updateProduct(request.getParameter("hidProductIDSave"),
				 request.getParameter("productname"),
				 request.getParameter("productcode"),
				 request.getParameter("productquantity"),
				 request.getParameter("productdescription"),
				 request.getParameter("productprice"),
				 request.getParameter("productavailable"));
		 
		
	 }
	 session.setAttribute("statusMsg", stsMsg);
}

//Delete item----------------------------------
if (request.getParameter("hidProductIDDelete") != null)
{
	Product Obj = new Product();
String stsMsg = Obj.deleteProduct(request.getParameter("hidProductIDDelete"));
session.setAttribute("statusMsg", stsMsg);
}
%>

<html>
<head>
<link rel="stylesheet" href="views/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>Product Management</title>
</head>

<body>

<h1>Product Management</h1>
<form method="post" action="product.jsp">
<div class="container">
 <div class="row">
 <div class="col">

 Product Name: <input name="productname" type="text"  class="form-control"><br><br>
 Product Code: <input name="productcode" type="text"  class="form-control"><br><br>
 Product Quantity: <input name="productquantity" type="text"  class="form-control"><br><br>
 Product Description: <input name="productdescription" type="text"  class="form-control"><br><br>
 Product Price: <input name="productprice" type="text"  class="form-control"><br><br>
 Product Availability: <input name="productavailable" type="text"  class="form-control"><br><br>
 <input name="btnSubmit" type="submit" value="Save"class="btn btn-primary">
<input type="hidden" id="hidProductIDSave" name="hidProductIDSave" value="">
 </div>
 </div>
</div>
</form>
<div class="alert alert-success">
 <%
 out.print(session.getAttribute("statusMsg"));
 %>
</div>
<br>
<%
Product Obj = new Product();
out.print(Obj.readProduct());
%>

</body>
</html>