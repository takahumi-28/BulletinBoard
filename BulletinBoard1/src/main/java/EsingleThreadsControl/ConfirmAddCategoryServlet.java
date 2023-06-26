package EsingleThreadsControl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import EsingleThreadsModel.AddedCategory;
import EsingleThreadsModel.CategoryDAO;
import EsingleThreadsModel.UserAccountInfo;

/**
 * Servlet implementation class ConfirmAddCategoryServlet
 */
@WebServlet("/AdminFilter/ConfirmAddCategoryServlet")
public class ConfirmAddCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmAddCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/category/confirmAddCategory.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String categoryName 	= request.getParameter("additionCategory");
		String categoryNameKana = request.getParameter("additionCategoryKana");
		String userId = null;
		HttpSession session = request.getSession(false);
		if(session.getAttribute("log") != null) {
			UserAccountInfo loginedUser = (UserAccountInfo)session.getAttribute("log");
			userId =  loginedUser.getUserId();
		}
		
		CategoryDAO daoCa = new CategoryDAO();
		int rsRow = daoCa.addCategory(new AddedCategory(categoryName,categoryNameKana,userId));
		if(rsRow != 0) {
			System.out.println(rsRow + "件作成できました");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("CategoryListServlet");
		dispatcher.forward(request, response);
	}

}
