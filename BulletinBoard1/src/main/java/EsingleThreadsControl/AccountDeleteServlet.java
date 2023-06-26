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

import EsingleThreadsModel.CommentDAO;
import EsingleThreadsModel.ThreadDAO;
import EsingleThreadsModel.UserAccountInfo;
import EsingleThreadsModel.UserAccountInfoDAO;

/**
 * Servlet implementation class AccountDeleteServlet
 */
@WebServlet("/AccountDeleteServlet")
public class AccountDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String delete = request.getParameter("delete");
		UserAccountInfoDAO dao = new UserAccountInfoDAO();
		UserAccountInfo deleteUser = dao.findUserById(delete);
		
		HttpSession session = request.getSession();
		session.setAttribute("delete", deleteUser);
		request.setAttribute("goDelete", deleteUser);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/account/deleteAccount.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		UserAccountInfo deleteUser = (UserAccountInfo)session.getAttribute("delete");
		
		UserAccountInfoDAO dao = new UserAccountInfoDAO();
		String msg = dao.DeleteUser(deleteUser);
		
		request.setAttribute("message", msg);
		session.removeAttribute("delete");
		ThreadDAO removeDao = new ThreadDAO();
		CommentDAO rejectDao = new CommentDAO();
		
		int i = removeDao.deleteUserThread(deleteUser);
		int j = rejectDao.deleteUserComment(deleteUser);
		
		System.out.println(i);
		System.out.println(j);
		
		ArrayList<UserAccountInfo> accounts = dao.UserList();
		request.setAttribute("sendAccounts", accounts);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/account/accountList.jsp");
		dispatcher.forward(request, response);
	}

}
