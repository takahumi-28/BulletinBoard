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
import EsingleThreadsModel.CreatedThread;
import EsingleThreadsModel.ThreadDAO;
import EsingleThreadsModel.UserAccountInfo;

/**
 * Servlet implementation class ConfirmCreateThreadServlet
 */
@WebServlet("/MemberFilter/ConfirmCreateThreadServlet")
public class ConfirmCreateThreadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmCreateThreadServlet() {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/thread/confirmCreateThread.jsp");
		dispatcher.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String threadTitle 	= request.getParameter("sendThreadTitle");
		String categoryName = request.getParameter("sendCategoryName");
		String threadDetail = request.getParameter("sendThreadDetail");
		String userId = null;
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("log") != null) {
			UserAccountInfo loginedUser = (UserAccountInfo)session.getAttribute("log");
			userId =  loginedUser.getUserId();
		}
		
		CategoryDAO daoCa = new CategoryDAO();
		int categoryCode =  daoCa.getCategoryCodeByName(categoryName);
		
		ThreadDAO dao = new ThreadDAO();
		int rsRow =  dao.createThread(new CreatedThread(userId,threadTitle,threadDetail,categoryCode,0));
		if(rsRow != 0) {
			System.out.println(rsRow + "件処理しました。");
		}else {
			System.out.println("うまく行きませんでした");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ThreadListServlet");
		dispatcher.forward(request, response);	
	}

}
