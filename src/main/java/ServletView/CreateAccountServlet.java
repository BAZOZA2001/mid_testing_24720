package ServletView;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.Gender;
import model.Role;
import model.User;

import java.io.IOException;

@WebServlet("/UserServlet")
public class CreateAccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String genderInput = request.getParameter("gender");
        Gender gender = Gender.valueOf(genderInput.toUpperCase()); // assuming Gender is an enum

        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String userName = request.getParameter("userName");
        String roleInput = request.getParameter("role");
        Role role = Role.valueOf(roleInput.toUpperCase()); // assuming Role is an enum

        // Initialize User object without constructor arguments
        User newUser = new User();

        // Set each attribute individually using setters
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setGender(gender);
        newUser.setPhoneNumber(phoneNumber);
        newUser.setPassword(password);
        newUser.setUserName(userName);
        newUser.setRole(role);

        // Assuming location is optional and can remain null if not provided
        newUser.setLocation(null);

        // Save user with UserDAO
        UserDAO dao = new UserDAO();
        dao.save(newUser);

        // Set the response content type and return a success message
        response.setContentType("text/html");
        response.getWriter().println("<h2>User created successfully!</h2>");
        response.getWriter().println("<p>User Details:</p>");
        response.getWriter().println("<p>" + newUser + "</p>");
    }
}
