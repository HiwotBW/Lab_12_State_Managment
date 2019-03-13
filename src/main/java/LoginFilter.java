import dao.UserDAO;
import models.User;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getSession() == null) {
            req.getSession().invalidate();
            resp.sendRedirect("login.jsp");
        } else {
            if(req.getSession().getAttribute("loggedInUser") != null){
                for (Cookie cookie : req.getCookies()) {
                    if (cookie.getName().equals("username") || cookie.getName().equals("remember_me")) {
                        UserDAO userDAO = new UserDAO();

                        req.getSession().setAttribute("User", userDAO.getUserByUserName(cookie.getValue()));
                    }
                }
                chain.doFilter(req, resp);
            }
            else {
                resp.sendRedirect("login.jsp");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
