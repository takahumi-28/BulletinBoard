package EsingleThreadsControl;

import java.io.IOException;
import java.net.http.HttpRequest;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import EsingleThreadsModel.UserAccountInfo;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter("/AdminFilter/*")
public class AdminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpServletResponse httpRes = (HttpServletResponse)response;
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = httpReq.getSession(false);
		boolean isAdmin = false;
		if(session.getAttribute("log") != null ) {
			UserAccountInfo loginedUser = (UserAccountInfo)session.getAttribute("log");
			if(loginedUser != null) {
				if(loginedUser.getAdministrator() == 1) {
					isAdmin = true;
				}
			}
		} 
		//管理者権限を持っていなければ、TOP画面に戻る
		if(!isAdmin) {
			httpRes.sendRedirect("/BulletinBoard/ThreadListServlet");
			return;
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
