package edu.school21.cinema.repositories;

import edu.school21.cinema.DatabaseConnection;
import edu.school21.cinema.filters.NameFilter;
import edu.school21.cinema.models.User;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements Serializable {

    private Connection connection;

    public User findUser(HttpServletRequest request) {
        User user = new User();

        try(PreparedStatement st = connection
            .prepareStatement(
                "select login, pass from profile where login = ? and pass = ?;")) {

            st.setString(1, request.getParameter("login"));
            st.setString(2, request.getParameter("password"));

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("pass"));
            }
            connection.close();
        } catch (Exception e) {
                e.printStackTrace();
        }
        return user;
    }

    public int addUser(HttpServletRequest request) {
        int result = 0;
        try(PreparedStatement st = connection
            .prepareStatement(
                "insert into profile "
                    + "(first_name, last_name, phone_number, pass, login) "
                    + "values (?, ?, ?, ?, ?)")) {
            if(!NameFilter.nameFilter(request.getParameter("firstName"))) {
                return result;
            }
            st.setString(1, request.getParameter("firstName"));
            st.setString(2, request.getParameter("lastName"));
            st.setString(3, request.getParameter("phoneNumber"));
            st.setString(4, request.getParameter("password"));
            st.setString(5, request.getParameter("login"));

            result = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public UserRepository() {
        try {
            connection = DatabaseConnection.initializeDatabase();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
