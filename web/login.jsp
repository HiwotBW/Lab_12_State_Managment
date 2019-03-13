<%@ page import="dao.UserDAO" %>
<%@ page import="models.User" %>
<%@ page import="javax.jws.soap.SOAPBinding" %><%--
  Created by IntelliJ IDEA.
  User: HiwotBishaw
  Date: 3/13/2019
  Time: 12:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    private User getUserName(HttpServletRequest request) {
        User user = new User("", "");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("username")) {

                    UserDAO userDAO = new UserDAO();
                    user = userDAO.getUserByUserName(cookie.getValue());
                }
            }
        }
        return user;
    }

    private String isRememberMe(HttpServletRequest request) {
        String _remember_me = "";
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("remember_me")) {
                    if (cookie.getValue().equals("true"))
                        _remember_me = "checked=\"checked\"";
                }
            }
        }
        return _remember_me;
    }
%>
<html>
<head>
    <title>My Application</title>
</head>
<body>
<form action="login" method="post">
    <input name="username" value="<%=getUserName(request).getUsername()%>"><br/>
    <input type="password" name="password" value="<%=getUserName(request).getPassword()%>"/><br/>
    <label><input name="remember_me" type="checkbox" <%=isRememberMe(request)%>> Remember me</label><br/>
    <input type="submit" value="Login"/>

</form>
</body>
</html>
