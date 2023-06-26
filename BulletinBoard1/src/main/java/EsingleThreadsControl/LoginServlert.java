package EsingleThreadsControl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import EsingleThreadsModel.TryLoginUser;
import EsingleThreadsModel.UserAccountInfo;
import EsingleThreadsModel.UserAccountInfoDAO;

/**
 * Servlet implementation class RoginServlert
 */
@WebServlet("/LoginServlet")
public class LoginServlert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("eMail");
		String pass = request.getParameter("pass");
		
		TryLoginUser user = new TryLoginUser(email, pass);
		UserAccountInfoDAO dao = new UserAccountInfoDAO();
		UserAccountInfo logined = dao.findUserByMailPass(user);
		
		RequestDispatcher dispatcher;
		if(logined != null && !logined.equals("")) {
			HttpSession session = request.getSession();
			session.setAttribute("log", logined);
			
			String msg = "ログインしました。";
			request.setAttribute("message", msg);
			request.setAttribute("admin", logined.getAdministrator());
			dispatcher = request.getRequestDispatcher("ThreadListServlet");
		} else {
			String msg = "パスワードまたはユーザー名が間違っています。";
			request.setAttribute("message", msg);
			request.setAttribute("admin", 2);
			dispatcher = request.getRequestDispatcher("ThreadListServlet");
		}
		dispatcher.forward(request, response);
	}

}
