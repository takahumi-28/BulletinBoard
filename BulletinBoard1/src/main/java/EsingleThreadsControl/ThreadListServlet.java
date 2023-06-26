package EsingleThreadsControl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import EsingleThreadsModel.CategoryDAO;
import EsingleThreadsModel.CategoryInfo;
import EsingleThreadsModel.ThreadDAO;
import EsingleThreadsModel.ThreadInfo;
import EsingleThreadsModel.UserAccountInfo;

/**
 * Servlet implementation class ThreadListServlet
 */
@WebServlet("/ThreadListServlet")
public class ThreadListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThreadListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/thread/threadList.jsp");
		
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("log") != null ) {
			UserAccountInfo login = (UserAccountInfo)session.getAttribute("log");
			if(login != null) {
				request.setAttribute("admin", login.getAdministrator());
			}
		} else {
			request.setAttribute("admin", 2);
		}
		
		CategoryDAO daoCa = new CategoryDAO();
		ArrayList<CategoryInfo> categories = daoCa.getCategories();
		request.setAttribute("sendCategories", categories);
		
		//スレッドの表示
		ThreadDAO daoT = new ThreadDAO();
		ArrayList<ThreadInfo> threads = daoT.getThreadTitles();
		//カテゴリーで検索表示
		String selectedCategoryCode = request.getParameter("categoryCode");
		if(selectedCategoryCode != null) {
			try {
				int categoryCode = Integer.parseInt(selectedCategoryCode);
				threads = daoT.getThreadTitlesByCategory(categoryCode);
			}catch(NumberFormatException e) {
				e.printStackTrace();
			} 
		}
		//キーワードで検索表示
		String searchedThreadTitle =request.getParameter("searchedThreadTitle");
		if(searchedThreadTitle != null) {
			threads = daoT.getThreadTitlesBykeyword(searchedThreadTitle);
		}
		
		request.setAttribute("sendThreads", threads);
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
