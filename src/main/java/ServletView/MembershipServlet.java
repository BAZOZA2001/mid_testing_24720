package ServletView;

import dao.UserDAO;
import model.MembershipType;
import model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/membership")
public class MembershipServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String userId = request.getParameter("userId");

        if ("delete".equals(action) && userId != null) {
            Long membershipId = Long.parseLong(request.getParameter("membershipId"));
            userDAO.deleteMembership(membershipId);
            response.sendRedirect("listMemberships.jsp?userId=" + userId);
        } else {
            request.getRequestDispatcher("listMemberships.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createMembership(request, response);
        } else if ("edit".equals(action)) {
            editMembership(request, response);
        }
    }

    private void createMembership(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String type = request.getParameter("type");
        String description = request.getParameter("description");
        Long userId = Long.parseLong(request.getParameter("userId"));

        User user = userDAO.getUserbyAuth(userId.toString());
        MembershipType membership = new MembershipType();
        membership.setTypeName(type);
        membership.setDescription(description);
        membership.setUser(user);

        userDAO.addMembership(membership);
        response.sendRedirect("listMemberships.jsp?userId=" + userId);
    }

    private void editMembership(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long membershipId = Long.parseLong(request.getParameter("membershipId"));
        String type = request.getParameter("type");
        String description = request.getParameter("description");

        MembershipType membership = userDAO.getMemberships(membershipId); // Assuming this method exists in UserDAO
        membership.setTypeName(type);
        membership.setDescription(description);

        userDAO.updateMembership(membership); // Assuming this method exists in UserDAO
        response.sendRedirect("listMemberships.jsp?userId=" + membership.getUser().getUserId());
    }
}
