<%@page import="com.payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>
<body>

<!-- form for the payment insertion and the update performance -->
	<div class="row container" style="margin:0">
		<div class="jumbotron col-12">
	      <h1 style="font-align:center">Payment Details Management</h1>
		</div>
  		<div class="col-4">
			<form id="formItem" name="formItem" method="post" action="items.jsp">
	  
				Patient ID: <input id="patientID" name="patientID" type="text"class="form-control form-control-sm"> <br>
		
	    		Doctor ID: <input id="doctorID" name="doctorID" type="text"class="form-control form-control-sm"> <br> 

	    		Date: <input id="date" name="date" type="date" class="form-control form-control-sm"> <br>
	    
	    		Amount: <input id="amount" name="amount" type="text"class="form-control form-control-sm"> <br>
	    
	    		Card Number: <input id="cardnumber" name="cardnumber" type="text"class="form-control form-control-sm"> <br> 
	    
	    		Postal Number: <input id="postalnumber" name="postalnumber" type="text"class="form-control form-control-sm"> <br>
	    
				<input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary"> 
		
				<input type="hidden"id="hidItemIDSave" name="hidItemIDSave" value="">
			</form>
 		</div>
 		<div class="col-8">
 		     <%
 		         payment payment1 = new payment();
 		         out.print(payment1.readpayment());
 		     %>
 		</div>
	</div>
</body>
</html>