package EsingleThreadsControl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import EsingleThreadsModel.CommentDAO;
import EsingleThreadsModel.PostedComment;
import EsingleThreadsModel.UserAccountInfo;

/**
 * Servlet implementation class ConfirmPostCommentServlet
 */
@WebServlet("/MemberFilter/ConfirmPostCommentServlet")
public class ConfirmPostCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmPostCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/threadDetail/confirmPostComment.jsp");
		dispatcher.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String comment = request.getParameter("sendComment");
		
		String userId = null;
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("log") != null) {
			UserAccountInfo loginedUser = (UserAccountInfo)session.getAttribute("log");
			userId =  loginedUser.getUserId();
		}
		
		int threadCode = 0;
		if(session != null && session.getAttribute("threadCodeNow") != null) {
			threadCode = (Integer)session.getAttribute("threadCodeNow");
		}
		
		CommentDAO daoCo = new CommentDAO();
		int rsRow =  daoCo.postComment(new PostedComment(threadCode,userId ,comment));
		if(rsRow != 0) {
			System.out.println(rsRow + "件処理できました。");
		}else {
			System.out.println("うまく行きませんでした");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ThreadDetailServlet");
		dispatcher.forward(request, response);
	}

}
