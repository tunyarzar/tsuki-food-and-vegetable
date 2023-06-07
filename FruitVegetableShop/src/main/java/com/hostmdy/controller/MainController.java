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
import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import com.hostmdy.model.Fruit;
import com.hostmdy.model.FruitDAO;
import com.hostmdy.model.User;

/**
 * Servlet implementation class MainController
 */
public class MainController extends HttpServlet {
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
	public MainController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mode = request.getParameter("mode");

		if (mode == null) {
			mode = "LIST";
		}

		switch (mode) {
		case "LIST":
			showFruit(request, response);
			break;

			/*case "LOAD":
			loadPage(request, response);
			break;

		case "BUY":
			buyFruit(request, response);
			break;*/

		default:
			showFruit(request, response);
			break;
		}
	}

	/*private void buyFruit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		Fruit fruit = fruitDAO.getFruitById(id);
		request.setAttribute("fruit", fruit);
		RequestDispatcher dispatch = request.getRequestDispatcher("fruit-buy.jsp");
		dispatch.forward(request, response);
	}

	private void loadPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		Fruit fruit = fruitDAO.getFruitById(id);
		request.setAttribute("fruit", fruit);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		RequestDispatcher dispatch = request.getRequestDispatcher("fruit-buy.jsp");
		dispatch.forward(request, response);

	}*/


	protected void showFruit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Fruit> fruitList = fruitDAO.getAllFruit();
		request.setAttribute("fruitList", fruitList);

		RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");

		dispatch.forward(request, response);

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
