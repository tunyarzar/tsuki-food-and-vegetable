package com.hostmdy.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import com.hostmdy.model.Fruit;
import com.hostmdy.model.FruitDAO;
import com.hostmdy.model.User;

/**
 * Servlet implementation class FruitController
 */
public class FruitController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/fruitandvegetableshop")
	private DataSource dataSource;

	private FruitDAO fruitDAO;

	@Override
	public void init() throws ServletException {
		fruitDAO = new FruitDAO(dataSource);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FruitController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user != null) {

			String mode = request.getParameter("mode");

			if (mode == null) {
				mode = "LIST";
			}

			switch (mode) {
			case "LIST":
				showFruit(request, response);
				break;

			case "CREATE":
				createFruit(request, response);
				break;

			case "LOAD":
				loadFruit(request, response);
				break;

			case "UPDATE":
				updateFruit(request, response);
				break;

			case "BUYVIEW":
				viewBuyFruit(request, response);
				break;
				
			case "BUY":
				buyFruit(request, response);
				break;
				
				
			case "DELETE":
				deleteFruit(request, response);
				break;

			default:
				showFruit(request, response);
				break;
			}

		} else {
			response.sendRedirect("login");
		}
	}


		private void viewBuyFruit(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException{
		// TODO Auto-generated method stub
			
			Integer id = Integer.parseInt(request.getParameter("id"));
			Fruit fruit = fruitDAO.getFruitById(id);
			request.setAttribute("fruit", fruit);

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			request.setAttribute("user", user);

			RequestDispatcher dispatch = request.getRequestDispatcher("fruit-buy.jsp");
			dispatch.forward(request, response);
		
	}

		private void buyFruit(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// TODO Auto-generated method stub
			//get id and qty to update the quantity of fruit from db
			Integer id = Integer.parseInt(request.getParameter("id"));
			System.out.println("Id is "+id);
			Integer buyQty=Integer.parseInt(request.getParameter("qty"));
			System.out.println("Buy Qty is"+buyQty);
			
			//get original qty
			Fruit fruit = fruitDAO.getFruitById(id);
			Integer originalQty=fruit.getQty();
			System.out.println("OriginalQty"+originalQty);
			Integer newQty=originalQty-buyQty;
			System.out.println("newqty"+newQty);
			//update the existing row in db
			int rowUpdated=fruitDAO.buyFruit(id,newQty);
			
			
			
			if (rowUpdated > 0) {
				showFruit(request, response);
			} else {
				request.setAttribute("errorTitle", "Fruit Buying Fail");
				request.setAttribute("errorMessage", "Please check your data and buy again");

				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("user");
				request.setAttribute("user", user);

				RequestDispatcher dispatcher = request.getRequestDispatcher("error-page.jsp");
				dispatcher.forward(request, response);

			}
		}
	
	protected void showFruit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Fruit> fruitList = fruitDAO.getAllFruit();
		request.setAttribute("fruitList", fruitList);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		RequestDispatcher dispatch = request.getRequestDispatcher("main");

		dispatch.forward(request, response);
	}

	protected void createFruit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		Integer qty = Integer.parseInt(request.getParameter("qty"));
		String rawDate = request.getParameter("date");
		LocalDate date = LocalDate.parse(rawDate);
		Double price = Double.parseDouble(request.getParameter("price"));
		String cm = request.getParameter("cm");

		Fruit fruit = new Fruit(name, qty, date, price, cm);

		int rowUpdated = fruitDAO.createFruit(fruit);

		if (rowUpdated > 0) {
			showFruit(request, response);
		} else {
			request.setAttribute("errorTitle", "Fruit Creation Fail");
			request.setAttribute("errorMessage", "Please check your data and create again");

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			request.setAttribute("user", user);

			RequestDispatcher dispatcher = request.getRequestDispatcher("error-page.jsp");
			dispatcher.forward(request, response);

		}
	}

	protected void loadFruit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer id = Integer.parseInt(request.getParameter("id"));
		Fruit fruit = fruitDAO.getFruitById(id);
		request.setAttribute("fruit", fruit);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		RequestDispatcher dispatch = request.getRequestDispatcher("fruit-update.jsp");
		dispatch.forward(request, response);

	}

	protected void updateFruit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer id = Integer.parseInt(request.getParameter("id"));

		String name = request.getParameter("name");
		Integer qty = Integer.parseInt(request.getParameter("qty"));
		String rawDate = request.getParameter("date");
		LocalDate date = LocalDate.parse(rawDate);
		Double price = Double.parseDouble(request.getParameter("price"));
		String cm = request.getParameter("cm");

		Fruit fruit = new Fruit(id,name, qty, date, price, cm);

		int rowUpdated = fruitDAO.updateFruit(fruit);

		if (rowUpdated > 0) {
			showFruit(request, response);
		} else {
			request.setAttribute("errorTitle", "Fruit Update Fail");
			request.setAttribute("errorMessage", "Please check your data and update again");

			RequestDispatcher dispatcher = request.getRequestDispatcher("error-page.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void deleteFruit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer id = Integer.parseInt(request.getParameter("id"));
		int rowUpdated = fruitDAO.deleteFruit(id);

		if (rowUpdated > 0) {
			showFruit(request, response);
		} else {
			request.setAttribute("errorTitle", "Fruit Delete Fail");
			request.setAttribute("errorMessage", "Please check your data and delete again");

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			request.setAttribute("user", user);

			RequestDispatcher dispatcher = request.getRequestDispatcher("error-page.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
