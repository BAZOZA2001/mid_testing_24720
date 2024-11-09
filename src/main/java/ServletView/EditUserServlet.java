package ServletView;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.Role;
import model.User;

@WebServlet("/admin/edit-user")
public class EditUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        User admin = (User) request.getSession().getAttribute("loggedInUser");
        if (admin == null || admin.getRole() != Role.MANAGER) {
            response.sendRedirect("login");
            return;
        }

        // Fetch the user to be edited
        String userId = request.getParameter("id");
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserById(UUID.fromString(userId)); // Using getUserById(UUID)

        // Set attributes for the edit page
        request.setAttribute("user", user);

        // Forward the request to the edit page
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit_user.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Process the updated user details
        String userId = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userName = request.getParameter("userName");
        String role = request.getParameter("role");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserById(UUID.fromString(userId)); // Corrected method call

        // Update the user
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);
        user.setRole(Role.valueOf(role));

        // Save the updated user
        userDAO.update(user);

        // Redirect to the user management page
        response.sendRedirect("admin/manage-users");
    }
}
