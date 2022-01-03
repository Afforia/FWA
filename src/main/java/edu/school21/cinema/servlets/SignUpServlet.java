package edu.school21.cinema.servlets;

import edu.school21.cinema.repositories.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "signUp", value = "/signUp")
public class SignUpServlet extends HttpServlet {

    UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = new UserRepository();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        int res = userRepository.addUser(request);

        PrintWriter writer = response.getWriter();
        if (res == 1) {
            writer.println("<html> <h2>Successfully Created</html>");
        } else if (res == 2) {
            writer.println("<html> <h2>Ops something go wrong</html>");
        }
    }
}
