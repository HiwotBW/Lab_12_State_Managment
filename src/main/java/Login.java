import dao.UserDAO;
import models.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Login extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userDAO = new UserDAO();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(userDAO.authenticateUser(username, password)){
            req.getSession().setAttribute("loggedInUser", new User(username, password));
            if (req.getParameter("remember_me") != null) {
                Cookie cookie_username = new Cookie("username", username);
                cookie_username.setMaxAge(60 * 60 * 24 * 31);
                resp.addCookie(cookie_username);

                Cookie cookie_remember_me = new Cookie("remember_me", "true");
                cookie_remember_me.setMaxAge(60 * 60 * 24 * 31);
                resp.addCookie(cookie_remember_me);

                Cookie cookie_coupon = new Cookie("Coupon", "$100");
                cookie_coupon.setMaxAge(60 * 60 * 24 * 31);
                resp.addCookie(cookie_coupon);
            } else {
                for (Cookie cookie : req.getCookies()) {
                    if (cookie.getName().equals("username") || cookie.getName().equals("remember_me")) {
                        cookie.setMaxAge(0);
                        cookie.setValue("");
                        cookie.setPath("/");
                        resp.addCookie(cookie);
                    }
                }
            }

            resp.sendRedirect("index.jsp");
        }
        else{

            req.setAttribute("message", "Invalid username and/or password.");
            resp.sendRedirect("login.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        req.getSession().invalidate();
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("username") || cookie.getName().equals("remember_me")) {
                cookie.setMaxAge(0);
                cookie.setValue("");
                cookie.setPath("/");
                resp.addCookie(cookie);
            }
        }
        req.getRequestDispatcher("login.jsp");


    }
}
