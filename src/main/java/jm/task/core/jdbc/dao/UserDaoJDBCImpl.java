package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE user (id  BIGINT, name NVARCHAR(20), lastName NVARCHAR(20), age TINYINT)";
        Connection connection = Util.getConnection();
        try (connection) {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                //throw new RuntimeException(ex);
            }
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE user";
        Connection connection = Util.getConnection();
        try (connection) {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                //throw new RuntimeException(ex);
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)";
        Connection connection = Util.getConnection();
        try (connection) {
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, name);
            pStatement.setString(2, lastName);
            pStatement.setByte(3, age);

            pStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                //throw new RuntimeException(ex);
            }
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM user WHERE id=?";
        Connection connection = Util.getConnection();
        try (connection) {
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, id);

            pStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                //throw new RuntimeException(ex);
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM user";
        Connection connection = Util.getConnection();
        try (connection) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                //throw new RuntimeException(ex);
            }
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE user";
        Connection connection = Util.getConnection();
        try (connection) {
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                //throw new RuntimeException(ex);
            }
        }
    }
}
