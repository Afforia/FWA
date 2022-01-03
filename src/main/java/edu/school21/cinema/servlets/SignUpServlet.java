package edu.school21.cinema.servlets;

import edu.school21.cinema.repositories.UserRepository;
import jakarta.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "signUp", value = "/signUp")
public class SignUpServlet extends HttpServlet {

    UserRepository userRepository;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        int res = userRepository.addUser(request);

        PrintWriter writer = response.getWriter();
        if (res == 1) {
            writer.println("<html> <h2>Successfully Created</html>");
        } else if (res == 2) {
            writer.println("<html> <h2>Ops something go wrong</html>");
        } else {
            writer.print("Sorry username or password error");
        }
    }
}
