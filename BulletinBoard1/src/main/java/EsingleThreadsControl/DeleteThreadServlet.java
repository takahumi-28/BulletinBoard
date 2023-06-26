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
import EsingleThreadsModel.CommentDAO;
import EsingleThreadsModel.CommentInfo;
import EsingleThreadsModel.ThreadDAO;
import EsingleThreadsModel.ThreadDetailInfo;

/**
 * Servlet implementation class DeleteThread
 */
@WebServlet("/AdminFilter/DeleteThreadServlet")
public class DeleteThreadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteThreadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
				
		try {
			String stringThreadCode =  request.getParameter("deletedThreadCode");
			int threadCode = 0;
			if(stringThreadCode != null) {
				threadCode = Integer.parseInt(stringThreadCode);
			}
			
			ThreadDAO daoT = new ThreadDAO();
			ThreadDetailInfo threadDetail = daoT.getThreadDetail(threadCode);
			request.setAttribute("sendThreadDetail", threadDetail);
			
			CommentDAO daoC = new CommentDAO();
			ArrayList<CommentInfo> comments = daoC.getComments(threadCode);
			request.setAttribute("sendComments", comments);	
		
		}catch(NumberFormatException e) {;
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/thread/deleteThread.jsp");
		dispatcher.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		ThreadDAO dao = new ThreadDAO();
		String stringThreadCode =  request.getParameter("deletedThreadCode");
		String deletedThreadTitle = request.getParameter("deletedThreadTitle");
		try {
			int rsRow = dao.deleteThread(Integer.parseInt(stringThreadCode));
			if(rsRow != 0) {
				System.out.println(rsRow + "件削除しました");
			}else {
				System.out.println("うまく行きませんでした");
			}
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		
		request.setAttribute("sendDeletedThreadTitle", deletedThreadTitle);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ThreadListServlet");
		dispatcher.forward(request, response);
	}

}
