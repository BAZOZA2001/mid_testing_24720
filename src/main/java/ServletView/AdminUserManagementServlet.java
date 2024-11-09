package ServletView;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.Role;
import model.User;

@WebServlet("/admin/manage-users")
public class AdminUserManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Only allow access for admins
        User admin = (User) request.getSession().getAttribute("loggedInUser");
        if (admin == null || admin.getRole() != Role.MANAGER) {
            response.sendRedirect("login");
            return;
        }

        // Fetch all users from the database
        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.getAllUsers();

        // Set attributes for use in the JSP page
        request.setAttribute("users", users);

        // Forward the request to the user management page
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin_user_management.jsp");
        dispatcher.forward(request, response);
    }
}

