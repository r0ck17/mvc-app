package by.javaguru;

import by.javaguru.dto.UserDto;
import by.javaguru.entity.User;
import by.javaguru.exception.ValidationException;
import by.javaguru.service.UserService;
import by.javaguru.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private static final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            req.getRequestDispatcher(JspHelper.getPath("menu")).forward(req, resp);
        } else {
            resp.sendRedirect(getServletContext().getContextPath());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String login = ((User) session.getAttribute("user")).getLogin();
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String age = req.getParameter("age");

        UserDto createUserDto = new UserDto(login, password, email, name, age);

        try {
            userService.save(createUserDto);
            Optional<User> user = userService.getUserByLogin(login);
            session.setAttribute("user", user.get());
            session.setAttribute("flash", "information updated successfully.");
            resp.sendRedirect(getServletContext().getContextPath() + "/user");
        } catch (ValidationException e) {
            session.setAttribute("errors", e.getErrors());
            resp.sendRedirect("user");
        }
    }
}
