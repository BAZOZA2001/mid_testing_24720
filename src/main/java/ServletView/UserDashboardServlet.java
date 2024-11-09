package ServletView;

import java.io.IOException;
import java.util.List;
import model.MembershipType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.Borrower;
import model.User;

@WebServlet("/user-dashboard")
public class UserDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the current user from session
        User user = (User) request.getSession().getAttribute("loggedInUser");

        // Fetch borrowings and memberships for the user from the database
        UserDAO userDAO = new UserDAO();
        List<Borrower> borrowings = userDAO.getBorrowings(user);
        List<MembershipType> memberships = userDAO.getMemberships(user);

        // Set attributes for use in the JSP page
        request.setAttribute("borrowings", borrowings);
        request.setAttribute("memberships", memberships);

        // Forward the request to the user dashboard page
        RequestDispatcher dispatcher = request.getRequestDispatcher("user_dashboard.jsp");
        dispatcher.forward(request, response);
    }
}

