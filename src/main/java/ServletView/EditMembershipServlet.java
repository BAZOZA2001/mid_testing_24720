package ServletView;

import javax.servlet.*;
import javax.servlet.http.*;

import dao.UserDAO;
import model.MembershipType;

import java.io.IOException;

public class EditMembershipServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("membershipId"));
        String name = request.getParameter("membershipName");
        int duration = Integer.parseInt(request.getParameter("membershipDuration"));
        double fee = Double.parseDouble(request.getParameter("membershipFee"));

        if (name != null && duration > 0 && fee >= 0) {  // Basic validation
            MembershipType membership = userDAO.getMembershipById(id);
            if (membership != null) {
                membership.setTypeName(name);
                membership.setDuration(duration);
                membership.setFee(fee);

                userDAO.updateMembership(membership);
                response.sendRedirect("membershipList.jsp");
            } else {
                response.sendRedirect("editMembership.jsp?error=Membership Not Found");
            }
        } else {
            response.sendRedirect("editMembership.jsp?error=Invalid Input");
        }
    }
}
