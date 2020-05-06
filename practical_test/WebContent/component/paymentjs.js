/**
 * 
 */
$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	var type = ($("#hidpaymentIDSave").val() == "") ? "POST" : "PUT";
	$.ajax({
		url : "paymentsAPI",
		type : type,
		data : $("#formItem").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onPaymentSaveComplete(response.responseText, status);
		}
	});

});

function onPaymentSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divPaymentGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#hidpaymentIDSave").val("");
	$("#formItem")[0].reset();
}


//DELET implementation
$(document).on("click", ".btnRemove", function(event) {
	
	$.ajax({
		url : "paymentsAPI",
		type : "DELETE",
		data : "payid=" + $(this).data("payid"),
		dataType : "text",
		complete : function(response, status) {
			onpaymentDeleteComplete(response.responseText, status);
		}
	});
	
});

function onpaymentDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divPaymentGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// UPDATE==========================================
$(document).on(	"click",".btnUpdate",function(event) {
					$("#hidpaymentIDSave").val(
							$(this).closest("tr").find('#hidpaymentIDUpdate').val());
					$("#patientID").val(
							$(this).closest("tr").find('td:eq(0)').text());
					$("#doctorID").val(
							$(this).closest("tr").find('td:eq(1)').text());
					$("#date").val(
							$(this).closest("tr").find('td:eq(2)').text());
					$("#amount").val(
							$(this).closest("tr").find('td:eq(3)').text());
					$("#cardnumber").val(
							$(this).closest("tr").find('td:eq(4)').text());
					$("#postalnumber").val(
							$(this).closest("tr").find('td:eq(5)').text());

				});

// CLIENTMODEL=========================================================================
function validateItemForm() {
	// Patient ID
	if ($("#patientID").val().trim() == "") {
		return "Insert Patient ID.";
	}
	// Doctor ID
	if ($("#doctorID").val().trim() == "") {
		return "Insert Doctor ID.";
	}
	// Date-------------------------------
	if ($("#date").val().trim() == "") {
		return "Insert Date.";
	}
	// check the amount, is numerical value
	if ($("#amount").val().trim() == "") {
		return "Insert Payment Amount.";
	}

	var tmpPrice = $("#amount").val().trim();
	if (!$.isNumeric(tmpPrice)) {
		return "Insert a numerical value for Payment amount.";
	}
	// convert to decimal price
	$("#amount").val(parseFloat(tmpPrice).toFixed(2));

	// check the card number is empty and its number
	if ($("#cardnumber").val().trim() == "") {
		return "Insert Item Card Number.";
	}

	var tempCardno = $("#cardnumber").val().trim();
	if (!$.isNumeric(tempCardno)) {
		return "Insert a numerical value for Card Number.";
	}

	// check the postal number is empty and its number
	if ($("#postalnumber").val().trim() == "") {
		return "Insert Item Card Number.";
	}

	var tempPostalno = $("#postalnumber").val().trim();
	if (!$.isNumeric(tempPostalno)) {
		return "Insert a numerical value for Card Number.";
	}

	return true;
}


