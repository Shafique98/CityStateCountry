package com.shafiq.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.shafiq.helper.DbCon;
import com.shafiq.pojo.City;
import com.shafiq.pojo.Country;
import com.shafiq.pojo.State;

/**
 * Servlet implementation class GetCountryStateServlet
 */
@WebServlet("/GetCountryStateServlet")
public class GetCountryStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("GetCountryStateServlet");
		try (PrintWriter out = response.getWriter()) {
			CountryStateLogic csl = new CountryStateLogic(DbCon.getCon());
			String op = request.getParameter("operations");
			if (op.equals("country")) {
				List<Country> cList = csl.getAllCountry();
				Gson json = new Gson();
				String countryList = json.toJson(cList);
//				response.setContentType("text/html");
				response.getWriter().write(countryList);
			}
			if (op.equals("state")) {		
				int id = Integer.parseInt(request.getParameter("id"));
				List<State> sList = csl.getStateByCountryId(id);
				Gson json = new Gson();
				String stateList = json.toJson(sList);
//				response.setContentType("text/html");
				response.getWriter().write(stateList);
			}
			if (op.equals("city")) {
				int id = Integer.parseInt(request.getParameter("id"));
				List<City> ciList = csl.getCityByStateId(id);
				Gson json = new Gson();
				String cityList = json.toJson(ciList);
//				response.setContentType("text/html");
				response.getWriter().write(cityList);
			}

//			String op = request.getParameter("operation");
//
//			if (op.equals("country")) {
//				List<Country> clist = csd.getAllCountry();
//				Gson json = new Gson();
//				String countryList = json.toJson(clist);
//				response.setContentType("text/html");
//				response.getWriter().write(countryList);
//			}
//
//			if (op.equals("state")) {
//				int id = Integer.parseInt(request.getParameter("id"));
//				List<State> slist = csd.getStateByCountryId(id);
//				Gson json = new Gson();
//				String countryList = json.toJson(slist);
//				response.setContentType("text/html");
//				response.getWriter().write(countryList);
//			}
//
//			if (op.equals("city")) {
//				int id = Integer.parseInt(request.getParameter("id"));
//				List<City> citylist = csd.getCityByStateId(id);
//				Gson json = new Gson();
//				String countryList = json.toJson(citylist);
//				response.setContentType("text/html");
//				response.getWriter().write(countryList);
//			}
		System.out.println(op);
		}
	}
}