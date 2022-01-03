package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;

public class SignInServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext =
            (ApplicationContext) context.getAttribute("springContext");
        this.userRepository = springContext.getBean(UserRepository.class);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
        throws IOException {
        User user = userRepository.findUser(request);

        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html>";
        htmlResponse += "<h2>Your username is: " + user.getLogin() + "<br/>";
        htmlResponse += "Your password is: " + user.getPassword() + "</h2>";
        htmlResponse += "</html>";
        writer.println(htmlResponse);
    }
}
