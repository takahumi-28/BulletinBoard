package EsingleThreadsControl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import EsingleThreadsModel.CategoryDAO;
import EsingleThreadsModel.ModifiedCategory;
import EsingleThreadsModel.UserAccountInfo;

/**
 * Servlet implementation class ConfirmModifyCategoryServlet
 */
@WebServlet("/AdminFilter/ConfirmModifyCategoryServlet")
public class ConfirmModifyCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmModifyCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("log") != null) {
			UserAccountInfo loginedUser = (UserAccountInfo)session.getAttribute("log");
			request.setAttribute("sendLoginedUserName", loginedUser.getUserName());
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/category/confirmModifyCategory.jsp");
		dispatcher.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String categoryCode = request.getParameter("sendCategoryCode");
		String categoryName = request.getParameter("sendCategoryName");
		String categoryNameKana = request.getParameter("sendCategoryNameKana");
		String userId = null;
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("log") != null) {
			UserAccountInfo loginedUser = (UserAccountInfo)session.getAttribute("log");
			userId =  loginedUser.getUserId();
		}
		
		CategoryDAO daoCa = new CategoryDAO();
		int rsRow = 0;
		try {
			rsRow = daoCa.modifyCategory(new ModifiedCategory(Integer.parseInt(categoryCode),categoryName,categoryNameKana,userId));
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		if(rsRow != 0) {
			System.out.println(rsRow + "件のカテゴリーを修正しました。");
		}else {
			System.out.println("うまく行きませんでした");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("CategoryListServlet");
		dispatcher.forward(request, response);
	}

}
