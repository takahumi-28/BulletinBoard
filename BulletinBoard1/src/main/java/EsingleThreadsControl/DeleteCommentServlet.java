package EsingleThreadsControl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EsingleThreadsModel.CommentDAO;
import EsingleThreadsModel.CommentInfo;

/**
 * Servlet implementation class DeleteCommentServlet
 */
@WebServlet("/AdminFilter/DeleteCommentServlet")
public class DeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/threadDetail/deleteComment.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String stringThreadCode = request.getParameter("threadCode");
		String stringCommentCode = request.getParameter("commentCode");
		
		try {
			int threadCode = Integer.parseInt(stringThreadCode);
			int commentCode = Integer.parseInt(stringCommentCode);
			
			CommentDAO daoCo = new CommentDAO();
			int rsRow = daoCo.deleteComment(threadCode, commentCode);
			if(rsRow > 0) {
				System.out.println(rsRow + "件のコメントを削除しました");
				request.setAttribute("deleteComeMsg", "コメントを削除しました");
			}else {
				System.out.println("うまく処理できませんでした");
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ThreadDetailServlet");
		dispatcher.forward(request, response);
	}

}
