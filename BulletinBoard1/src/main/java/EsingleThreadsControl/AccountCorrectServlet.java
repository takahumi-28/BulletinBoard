package EsingleThreadsControl;

import java.io.IOException;

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
 * Servlet implementation class AccountCorrectServlet
 */
@WebServlet("/AccountCorrectServlet")
public class AccountCorrectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountCorrectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String modify = request.getParameter("modify");
		UserAccountInfoDAO dao = new UserAccountInfoDAO();
		UserAccountInfo before = dao.findUserById(modify);
		
		System.out.println(modify);
		System.out.println(before.getUserId());
		
		HttpSession session = request.getSession();
		session.setAttribute("before", before);
		request.setAttribute("correct", modify);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/account/modifyAccount.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("modifyId");
		String mail = request.getParameter("modifyEmail");
		String pass = request.getParameter("modifyPass");
		String name = request.getParameter("modifyName");
		String year = request.getParameter("modifyBirthYear");
		String month = request.getParameter("modifyBirthMonth");
		String day = request.getParameter("modifyBirthDay");
		String gender = request.getParameter("modifyGender");
		String admin = request.getParameter("modifyAdmin");
		String lock = request.getParameter("modifyLock");
		
		int sex = Integer.parseInt(gender);
		int adm = Integer.parseInt(admin);
		
		String birth = year + "-" + month + "-" + day;
		HttpSession session = request.getSession();
		UserAccountInfo before = (UserAccountInfo)session.getAttribute("before");
		session.removeAttribute("before");
		
		UserAccountInfo after = new UserAccountInfo(id, 
													mail, 
													pass, 
													name, 
													birth, 
													sex, 
													adm, 
													before.getResister_user(), 
													before.getResister_date(), 
													before.getUpdate_user(), 
													before.getUpdate_date(), 
													before.getUser_delete(), 
													before.getUser_lock() 
													);
		request.setAttribute("sendBefore", before);
		request.setAttribute("sendAfter", after);
		session.setAttribute("after", after);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/account/confirmModifyAccount.jsp");
		dispatcher.forward(request, response);
	}

}
