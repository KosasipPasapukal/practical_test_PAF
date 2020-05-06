package com;

import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class paymentsAPI
 */
@WebServlet("/paymentsAPI")
public class paymentsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// initialize a server-model class object
	payment payobj = new payment();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public paymentsAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = payobj.payAppointment(
				request.getParameter("patientID"),
				request.getParameter("doctorID"),
				request.getParameter("date"),
				request.getParameter("amount"),
				request.getParameter("cardnumber"),
				request.getParameter("postalnumber"));
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = payobj.updatePaymentdetails(paras.get("hidpaymentIDSave").toString(),
				paras.get("patientID").toString(),
				paras.get("doctorID").toString(),
				paras.get("date").toString(),
				paras.get("amount").toString(),
				paras.get("cardnumber").toString(),
				paras.get("postalnumber").toString());
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = payobj.paymentDelete(paras.get("payid").toString());
		response.getWriter().write(output); 
	}
	
	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request) {
			Map<String, String> map = new HashMap<String, String>();
			try {
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
				String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
				scanner.close();
				String[] params = queryString.split("&");
				for (String param : params) {
					String[] p = param.split("=");
					map.put(p[0], p[1]);
				}
			} catch (Exception e) {
			}
			return map;
		}

}
