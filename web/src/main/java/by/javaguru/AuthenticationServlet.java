package by.javaguru;

import by.javaguru.service.UserService;
import by.javaguru.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/")
public class AuthenticationServlet extends HttpServlet {
    private static final UserService userService = UserService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        req.getRequestDispatcher(JspHelper.getPath("index")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (userService.isCorrectUserDataAuth(login, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", userService.getUserByLogin(login).get());
            resp.sendRedirect(getServletContext().getContextPath() + "/user");
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("flash", "incorrect login or password.");
            resp.sendRedirect(getServletContext().getContextPath());
        }
    }
}
