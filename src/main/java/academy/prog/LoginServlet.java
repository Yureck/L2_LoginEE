package academy.prog;

import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    static final String LOGIN = "admin";
    static final String PASS = "Adminius$4";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String age = request.getParameter("age");
        int valAge = validAge(age);
        String message="";

        if (valAge >= 18 && isValidPassword(password) && LOGIN.equals(login) && PASS.equals(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user_login", login);
            response.sendRedirect("index.jsp");
        } else if (!LOGIN.equals(login)) {
            message = "Login was entered incorrectly!";
        }else if (!isValidPassword(password)) {
            message = "Password wasn't entered correctly or the password is too short (" +
                      "less than 10 symbols) or it isn't strong enough";
        } else if (valAge < 18) {
            message = "The age should be over 18 or was entered incorrectly!";
        }
        PrintWriter pw = response.getWriter();
        pw.println("<html><head><title>Wrong data</title></head><body><p style=\"color: red; font-size: 1.5rem\">"+
                message+"</p>"+
                "<p>Click this link to<a href=\"/login?a=exit\".> enter the data again </a></p></body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String a = request.getParameter("a");
        HttpSession session = request.getSession(false);

        if ("exit".equals(a) && (session != null))
            session.removeAttribute("user_login");

        response.sendRedirect("index.jsp");
    }

    private static boolean isValidPassword(String pass) {
        String pat = "(?=.*\\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[\\da-zA-Z!@#$%^&*]{10,}";
        return pass.matches(pat);
    }

    private static int validAge(String age) {
        int tAge;
        try {
            tAge = Integer.valueOf(age);
        } catch (NumberFormatException e) {
            System.out.println(e);
            return 0;
        }
        return tAge;
    }
}
