package ServletView;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.Role;
import model.User;

@WebServlet("/admin/delete-user")
public class DeleteUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        User admin = (User) request.getSession().getAttribute("loggedInUser");
        if (admin == null || admin.getRole() != Role.MANAGER) {
            response.sendRedirect("login");
            return;
        }

        String userId = request.getParameter("id");
        UserDAO userDAO = new UserDAO();

        // Delete the user by ID
        userDAO.delete(UUID.fromString(userId));

        // Redirect to the user management page
        response.sendRedirect("admin/manage-users");
    }
}

