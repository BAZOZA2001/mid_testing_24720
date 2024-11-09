package ServletView;

import dao.UserDAO;
import model.Borrower;
import model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/borrowing")
public class BorrowingServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String userId = request.getParameter("userId");

        if ("delete".equals(action) && userId != null) {
            Long borrowingId = Long.parseLong(request.getParameter("borrowingId"));
            userDAO.deleteBorrowing(borrowingId);
            response.sendRedirect("listBorrowings.jsp?userId=" + userId);
        } else {
            request.getRequestDispatcher("listBorrowings.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createBorrowing(request, response);
        } else if ("edit".equals(action)) {
            editBorrowing(request, response);
        }
    }

    private void createBorrowing(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bookTitle = request.getParameter("bookTitle");
        String borrowingDate = request.getParameter("borrowingDate");
        Long userId = Long.parseLong(request.getParameter("userId"));

        User user = userDAO.getUserById(userId.toString());
        Borrower borrower = new Borrower();
        borrower.setBook(bookTitle);
        borrower.setBorrowId(borrowingDate);
        borrower.setReader(user);

        userDAO.addBorrowing(borrower);
        response.sendRedirect("listBorrowings.jsp?userId=" + userId);
    }

    private void editBorrowing(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long borrowingId = Long.parseLong(request.getParameter("borrowingId"));
        String bookTitle = request.getParameter("bookTitle");
        String borrowingDate = request.getParameter("borrowingDate");

        Borrower borrower = userDAO.getBorrowingById(borrowingId); // Assuming this method exists in UserDAO
        borrower.setBook(bookTitle);
        borrower.setBorrowId(borrowingDate);

        userDAO.updateBorrowing(borrower); // Assuming this method exists in UserDAO
        response.sendRedirect("listBorrowings.jsp?userId=" + borrower.getReader().getUserId());
    }
}
