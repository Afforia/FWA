package edu.school21.cinema.servlets;

import edu.school21.cinema.DatabaseConnection;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@WebServlet(name = "signIn", value = "/signIn")
public class SignInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
    {
        String login = null;
        String password = null;
        try {
            Connection con = DatabaseConnection.initializeDatabase();

            PreparedStatement st = con
                .prepareStatement("select login, pass from profile where login = ? and pass = ?;");

            st.setString(1, request.getParameter("login"));
            st.setString(2, request.getParameter("password"));

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                login = rs.getString("login");
                password = rs.getString("pass");
            }
            st.close();
            con.close();

            PrintWriter writer = response.getWriter();
            String htmlResponse = "<html>";
            htmlResponse += "<h2>Your username is: " + login + "<br/>";
            htmlResponse += "Your password is: " + password + "</h2>";
            htmlResponse += "</html>";
            writer.println(htmlResponse);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
