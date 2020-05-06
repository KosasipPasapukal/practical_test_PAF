<%@page import="com.payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Details Management</title>
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="views/bootstrap.min.css">
    <script type="text/javascript" src="component/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="component/paymentjs.js"></script>
</head>
<body>

<!-- form for the payment insertion and the update performance -->
	<div class="row container" style="margin:0">
		<div class="jumbotron col-12">
	      <h1 style="text-align:center">Payment Details Management</h1>
		</div>
  		<div class="col-4">
			<form id="formPay" name="formPay" method="post">
	  
				Patient ID: <input id="patientID" name="patientID" type="text"class="form-control form-control-sm"> <br>
		
	    		Doctor ID: <input id="doctorID" name="doctorID" type="text"class="form-control form-control-sm"> <br> 

	    		Date: <input id="date" name="date" type="date" class="form-control form-control-sm"> <br>
	    
	    		Amount: <input id="amount" name="amount" type="text"class="form-control form-control-sm"> <br>
	    
	    		Card Number: <input id="cardnumber" name="cardnumber" type="text"class="form-control form-control-sm"> <br> 
	    
	    		Postal Number: <input id="postalnumber" name="postalnumber" type="text"class="form-control form-control-sm"> <br>
	    
				<input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary"> 
		
				<input type="hidden"id="hidpaymentIDSave" name="hidpaymentIDSave" value="">
			</form>
			
			<!--  Alert messages for the events-->
			<div id="alertSuccess" class="alert alert-success"></div> 
			<div id="alertError" class="alert alert-danger">
	
			</div>
 		</div>
 		<div id="divPaymentGrid" class="col-8">
 		     <%
 		         payment payment1 = new payment();
 		         out.print(payment1.readpayment());
 		     %>
 		</div>
	</div>

	<!--blank space  -->
	<div style="height:60px">
	</div>
</body>
</html>