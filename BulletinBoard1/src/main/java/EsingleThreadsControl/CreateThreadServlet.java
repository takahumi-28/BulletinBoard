package EsingleThreadsControl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EsingleThreadsModel.CategoryDAO;
import EsingleThreadsModel.CategoryInfo;

/**
 * Servlet implementation class CreateThreadServlet
 */
@WebServlet("/MemberFilter/CreateThreadServlet")
public class CreateThreadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateThreadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		CategoryDAO daoCa = new CategoryDAO();
		ArrayList<CategoryInfo> categories = daoCa.getCategories();
		request.setAttribute("sendCategories", categories);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/thread/createThread.jsp");
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
