package EsingleThreadsControl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import EsingleThreadsModel.IdMakerDAO;
import EsingleThreadsModel.UserAccountInfo;
import EsingleThreadsModel.UserAccountInfoDAO;

/**
 * Servlet implementation class AccountRegistConfirmServlet
 */
@WebServlet("/AccountRegistConfirmServlet")
public class AccountRegistConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountRegistConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		UserAccountInfo makeuser = (UserAccountInfo)session.getAttribute("make");
		
		UserAccountInfoDAO dao = new UserAccountInfoDAO();
		String confirm = dao.IdConfirm(makeuser);
		
		request.setAttribute("message", confirm);
		
		ArrayList<UserAccountInfo> accounts = dao.UserList();
		request.setAttribute("sendAccounts", accounts);
		
		IdMakerDAO idmake = new IdMakerDAO();
		int i = idmake.IdSwap();
		System.out.println(i);
//		session.removeAttribute("make");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/account/accountList.jsp");
		dispatcher.forward(request, response);
	}

}
