package com.weeklymealplanner;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RecipeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String action = null;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		this.action = servletConfig.getInitParameter("action");
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		RequestDispatcher requestDispatcher;

		switch (action) {
		case "addpage":
			requestDispatcher = request
					.getRequestDispatcher("CreateRecipe.jsp");
			requestDispatcher.forward(request, response);
			break;
		case "deletepage":
			List<Recipe> deleteList = recipeArray(request, response);
			requestDispatcher = request
					.getRequestDispatcher("DeleteRecipe.jsp");
			request.setAttribute("recipeList", deleteList);
			requestDispatcher.forward(request, response);
			break;
		case "updatepage":
			List<Recipe> alist = recipeArray(request, response);
			requestDispatcher = request
					.getRequestDispatcher("UpdateRecipe.jsp");
			request.setAttribute("recipeList", alist);
			requestDispatcher.forward(request, response);
			break;
		case "readpage":
			List<Recipe> readList = recipeArray(request, response);
			requestDispatcher = request.getRequestDispatcher("ReadRecipe.jsp");
			request.setAttribute("recipeList", readList);
			requestDispatcher.forward(request, response);
			break;
		default:
			doPost(request, response);
		}

	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		switch (action) {
		case "add":
			addRecipe(request, response);
			response.sendRedirect("CreateRecipe");
			break;
		case "delete":
			deleteRecipe(request, response);
			response.sendRedirect("DeleteRecipe");
			break;
		case "update":
			updateRecipe(request, response);
			response.sendRedirect("UpdateRecipe");
			break;
		case "read":
			break;
		}
	}

	public void addRecipe(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Recipe rec = new Recipe(request.getParameter("recipeName"),
				request.getParameter("recipeMethod"),
				request.getParameter("recipeIngredients"));
		try {
			pm.makePersistent(rec);
		} finally {
			pm.close();
		}
	}

	public void updateRecipe(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.getWriter().write(
				"<html><body><H1>" + request.getParameter("recipeID")
						+ "</H1></body></html>");
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Recipe rec = pm.getObjectById(Recipe.class,
					Long.parseLong(request.getParameter("recipeID")));
			rec.setName(request.getParameter("recipeName"));
			rec.setMethod(request.getParameter("recipeMethod"));
			rec.setIngredients(request.getParameter("recipeIngredients"));
		} finally {
			pm.close();
		}

	}

	public void deleteRecipe(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.getWriter().write(
				"<html><body><H1>" + request.getParameter("recipeID")
						+ "</H1></body></html>");
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Recipe rec = pm.getObjectById(Recipe.class,
					Long.parseLong(request.getParameter("recipeID")));
			pm.deletePersistent(rec);
		} finally {
			pm.close();
		}

	}

	/*
	 * public ArrayList<Recipe> listRecipe(HttpServletRequest request,
	 * HttpServletResponse response) throws IOException { PersistenceManager pm
	 * = PMF.get().getPersistenceManager();
	 * 
	 * Extent<Recipe> ex = pm.getExtent(Recipe.class, true); Iterator<Recipe>
	 * iter = ex.iterator();
	 * 
	 * ArrayList<Recipe> recList = new ArrayList<Recipe>();
	 * 
	 * while (iter.hasNext()) { Recipe obj = (Recipe) iter.next();
	 * recList.add(obj); } return recList; }
	 */
	@SuppressWarnings("unchecked")
	public List<Recipe> recipeArray(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query q = pm.newQuery(Recipe.class);
		q.setFilter(null);
		q.setOrdering(null);
		q.declareParameters(null);

		try {
			List<Recipe> results = (List<Recipe>) q.execute();
			if (!results.isEmpty()) {
				return results;
			} else {
				return null;
			}
		} finally {
			q.closeAll();
		}
	}
}