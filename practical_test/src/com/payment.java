package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class payment {

	public static Connection getconnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "Sliitstudent123");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	// implementing the payment logic and generate success massage and generate HTML
	// table with help of multiple MediaType
	public String payAppointment(String patientID, String doctorID, String date, String amount, String cardnumber,
			String postalnumber) {
		String output = "";

		try {
			Connection con = getconnection();

			if (con == null)

			{
				return "Error while connecting to the database for inserting appointment details.";
			}
			// create a prepared statement
			String query = " insert into successpayment (payID,patientID,doctorID,date,amount,cardnumber,postalnumber)"
					+ " values (?,?,?,?,?,?,?)";

			java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values to appointment table
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, patientID);
			preparedStmt.setString(3, doctorID);
			preparedStmt.setString(4, date);
			preparedStmt.setDouble(5, Double.parseDouble(amount));
			preparedStmt.setInt(6, Integer.parseInt(cardnumber));
			preparedStmt.setInt(7, Integer.parseInt(postalnumber));

			preparedStmt.execute();

			con.close();
			String newPay = readpayment();
			output = "{\"status\":\"success\", \"data\": \"" + newPay + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the Payment.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// reading the payment details and produces the html tble
	public String readpayment() {
		String output = "";
		try {

			Connection con = getconnection();

			if (con == null) {

				return "Error while connecting to the database for reading the appointmentdetails.";

			}
			output = "<table border='1' align='center' cellpadding='5' cellspacing='5' border='1' class='table-dark'><tr>"
					+ "<th>PatientID</th>" + "<th>DocID</th>" + "<th>Date</th>" + "<th>Amount</th>" + "<th>card No</th>"
					+ "<th>postal No</th>" + "<th>Update</th>" + "<th>Delete</th></tr>";

			String query = "select * from successpayment";

			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				String payID = Integer.toString(rs.getInt("payID"));
				String patientID = rs.getString("patientID");
				String doctorID = rs.getString("doctorID");
				String date = rs.getString("date");
				String amount = Double.toString(rs.getDouble("amount"));
				String cardnumber = Integer.toString(rs.getInt("cardnumber"));
				String postlnumber = Integer.toString(rs.getInt("postalnumber"));

				output += "<tr><td><input id='hidpaymentIDUpdate' name='hidpaymentIDUpdate' type='hidden' value='"
						+ payID + "'>" + patientID + "</td>";
				output += "<td>" + doctorID + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + cardnumber + "</td>";
				output += "<td>" + postlnumber + "</td>";

				// buttons event performances
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-payid='"
						+ payID + "'></td></tr>";

			}

			con.close();

			output += "</table>";

		} catch (Exception e) {

			output = "An error occurred while reading the appointment details. ";
			System.err.println(e.getMessage());

		}

		return output;

	}

	// update added amount
	// MediaType
	public String updatePaymentdetails(String payID, String patientID, String doctorID, String date, String amount,
			String cardnumber, String postalnumber) {
		String output = "";

		try {

			Connection con = getconnection();

			if (con == null) {

				return "Error occured while updating the appointment details.";
			}

			// create a prepared statement

			String query = "UPDATE successpayment SET patientID=?,doctorID=?,date=?, amount=?, cardnumber=?,postalnumber=? where payID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values to query

			preparedStmt.setString(1, patientID);
			preparedStmt.setString(2, doctorID);
			preparedStmt.setString(3, date);
			preparedStmt.setDouble(4, Double.parseDouble(amount));
			preparedStmt.setInt(5, Integer.parseInt(cardnumber));
			preparedStmt.setInt(6, Integer.parseInt(postalnumber));
			preparedStmt.setInt(7, Integer.parseInt(payID));

			// Executeing the statement
			preparedStmt.execute();
			con.close();
			String newPay = readpayment();
			output = "{\"status\":\"success\", \"data\": \"" + newPay + "\"}";

		}

		catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the Payment.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// implement the delete payment and generate HTML table
	public String paymentDelete(String payID) {

		String output = "";

		try {

			Connection con = getconnection();

			if (con == null) {

				return "Error occured while deleting the payment details.";
			}

			// create a prepared statement
			String query = "delete from successpayment where payID=?";
			java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(payID));

			// executing the statements
			preparedStmt.execute();

			con.close();

			String newPay = readpayment();
			output = "{\"status\":\"success\", \"data\": \"" + newPay + "\"}";

		} catch (Exception e) {

			output = "{\"status\":\"error\", \"data\":\"Error while Deleting the Payment.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
