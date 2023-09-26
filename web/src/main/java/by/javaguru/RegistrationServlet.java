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

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        req.getRequestDispatcher(JspHelper.getPath("registration")).forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String age = req.getParameter("age");

        UserDto createUserDto = new UserDto(login, password, email, name, age);
        HttpSession session = req.getSession();

        try {
            userService.save(createUserDto);
            Optional<User> user = userService.getUserByLogin(login);
            session.setAttribute("user", user.get());
            session.setAttribute("flash", "You have been successfully registered.");
            resp.sendRedirect(getServletContext().getContextPath() + "/user");
        } catch (ValidationException e) {
            session.setAttribute("errors", e.getErrors());
            resp.sendRedirect("registration");
        }
    }
}
