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
import EsingleThreadsModel.CommentDAO;
import EsingleThreadsModel.CommentInfo;
import EsingleThreadsModel.ThreadDAO;
import EsingleThreadsModel.ThreadDetailInfo;
import EsingleThreadsModel.UserAccountInfo;

/**
 * Servlet implementation class ThreadDetailServlet
 */
@WebServlet("/ThreadDetailServlet")
public class ThreadDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThreadDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
				
		if(session != null && session.getAttribute("log") != null ) {
			UserAccountInfo login = (UserAccountInfo)session.getAttribute("log");
			if(login != null) {
				request.setAttribute("admin", login.getAdministrator());//会員or管理者
			}
		} else {
			request.setAttribute("admin", 2);//ゲストユーザー
		}
						
		try {
			String stringThreadCode = request.getParameter("threadCode");
			int threadCode = 0;
			if(stringThreadCode != null) {//あったらセッションのスレッドコードを上書き
				threadCode = Integer.parseInt(stringThreadCode);
				session.setAttribute("threadCodeNow",threadCode);
			}
			
			threadCode = (Integer)session.getAttribute("threadCodeNow");
			
			ThreadDAO daoT = new ThreadDAO();
			ThreadDetailInfo threadDetail = daoT.getThreadDetail(threadCode);
			request.setAttribute("sendThreadDetail", threadDetail);
			
			CommentDAO daoC = new CommentDAO();
			ArrayList<CommentInfo> comments = daoC.getComments(threadCode);
			request.setAttribute("sendComments", comments);
			
			CategoryDAO daoCa = new CategoryDAO();
			ArrayList<CategoryInfo> categories = daoCa.getCategories();
			request.setAttribute("sendCategories", categories);				
			
		}catch(NumberFormatException e) {;
			e.printStackTrace();
		}				
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/threadDetail/threadDetail.jsp");
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
