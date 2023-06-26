package EsingleThreadsControl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EsingleThreadsModel.CategoryDAO;
import EsingleThreadsModel.CategoryInfo;

/**
 * Servlet implementation class CategoryListServlet
 */
@WebServlet("/AdminFilter/CategoryListServlet")
public class CategoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryDAO daoCa = new CategoryDAO();
		 ArrayList<CategoryInfo> sendCategories = daoCa.getCategories();
		
		 //検索ワードがあれば
		String searchedCategoryName = request.getParameter("searchedCategoryName");
		if(searchedCategoryName != null) {
			sendCategories = daoCa.getCategoriesByKeyword(searchedCategoryName);
		}
		
		request.setAttribute("sendCategories", sendCategories);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/category/categoryList.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
