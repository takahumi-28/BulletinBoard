package EsingleThreadsControl;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import EsingleThreadsModel.IdMakerDAO;
import EsingleThreadsModel.UserAccountInfo;

/**
 * Servlet implementation class AccountRegistServlet
 */
@WebServlet("/AccountRegistServlet")
public class AccountRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountRegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		IdMakerDAO dao = new IdMakerDAO();
		String id = dao.PreIdMake();
		
		request.setAttribute("sendId", id);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/account/resisterAccount.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String mail = request.getParameter("resisterEmail");
		String pass = request.getParameter("resisterPass");
		String name = request.getParameter("resisterName");
		String year = request.getParameter("resisterBirthYear");
		String month = request.getParameter("resisterBirthMonth");
		String day = request.getParameter("resisterBirthDay");
		String gender = request.getParameter("resisterGender");
		String admin = request.getParameter("resisterAdmin");
		
		int sex = Integer.parseInt(gender);
		int adm = Integer.parseInt(admin);
		
		IdMakerDAO dao = new IdMakerDAO();
		String id = dao.PreIdMake();
		
		HttpSession session = request.getSession();
		UserAccountInfo login = (UserAccountInfo)session.getAttribute("log");
		
		String datetime = year + "-" + month + "-" + day;
		LocalDate today = LocalDate.now();
		String todays = String.valueOf(today);
		
		UserAccountInfo makeuser = new UserAccountInfo( id, mail, pass, name, datetime,	sex, adm, login.getUserId(), todays, null, null, 0, 0	);
		session.setAttribute("make", makeuser);
		
		request.setAttribute("sendId", id);
		request.setAttribute("sendMail", mail);
		request.setAttribute("sendPass", pass);
		request.setAttribute("sendName", name);
		request.setAttribute("sendYear", year);
		request.setAttribute("sendMounth", month);
		request.setAttribute("sendDay", day);
		request.setAttribute("sendGender", gender);
		request.setAttribute("sendAdmin", admin);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/account/confirmResisterAccount.jsp");
		dispatcher.forward(request, response);
	}

}
