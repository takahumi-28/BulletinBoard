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

import EsingleThreadsModel.UserAccountInfo;
import EsingleThreadsModel.UserAccountInfoDAO;

/**
 * Servlet implementation class AccountCorrectConfirmServlet
 */
@WebServlet("/AccountCorrectConfirmServlet")
public class AccountCorrectConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountCorrectConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		UserAccountInfo after = (UserAccountInfo)session.getAttribute("after");
		UserAccountInfo logined = (UserAccountInfo)session.getAttribute("log");
		
		UserAccountInfoDAO dao = new UserAccountInfoDAO();
		
		String msg = dao.ModifyConfirm(after, logined);
		request.setAttribute("message", msg);
		session.removeAttribute("after");
		
		ArrayList<UserAccountInfo> accounts = dao.UserList();
		request.setAttribute("sendAccounts", accounts);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/account/accountList.jsp");
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
