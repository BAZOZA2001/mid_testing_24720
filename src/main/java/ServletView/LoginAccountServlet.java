package ServletView;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginAccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        // Create a new User object and set the username and password
        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setPassword(password);

        // Initialize the UserDAO and check if user exists
        UserDAO dao = new UserDAO();
        User foundUser = dao.getUserById(newUser);

        // Set the response content type
        response.setContentType("text/html");

        // Check if user is found and output appropriate message
        if (foundUser != null) {
            response.getWriter().println("<h2>User logged in successfully</h2>");
        } else {
            response.getWriter().println("<h2>Invalid credentials</h2>");
        }
    }
}
