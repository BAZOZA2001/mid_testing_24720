package ServletView;

import javax.servlet.*;
import javax.servlet.http.*;

import dao.UserDAO;
import model.MembershipType;

import java.io.IOException;

public class AddMembershipServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("membershipName");
        int duration = Integer.parseInt(request.getParameter("membershipDuration"));
        double fee = Double.parseDouble(request.getParameter("membershipFee"));

        if (name != null && duration > 0 && fee >= 0) {  // Basic validation
            MembershipType membership = new MembershipType();
            membership.setTypeName(name);
            membership.setDuration(duration);
            membership.setFee(fee);

            userDAO.addMembership(membership);
            response.sendRedirect("membershipList.jsp");
        } else {
            response.sendRedirect("addMembership.jsp?error=Invalid Input");
        }
    }
}
