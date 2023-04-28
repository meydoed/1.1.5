package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE user (id  BIGINT, name NVARCHAR(20), lastName NVARCHAR(20), age TINYINT)";

        Statement statement = null;
        try {
            statement = Util.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            //throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    //throw new RuntimeException(e);
                }
            }
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE user";

        Statement statement = null;
        try {
            statement = Util.getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            //throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    //throw new RuntimeException(e);
                }
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)";
        try {
            pStatement = Util.getConnection().prepareStatement(sql);

            pStatement.setString(1, name);
            pStatement.setString(2, lastName);
            pStatement.setByte(3, age);

            pStatement.executeUpdate();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
        } finally {
            if (pStatement != null) {
                try {
                    pStatement.close();
                } catch (SQLException e) {
                    //throw new RuntimeException(e);
                }
            }
        }
    }

    public void removeUserById(long id) {
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM user WHERE id=?";
        try {
            pStatement = Util.getConnection().prepareStatement(sql);

            pStatement.setLong(1, id);

            pStatement.executeUpdate();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
        } finally {
            if (pStatement != null) {
                try {
                    pStatement.close();
                } catch (SQLException e) {
                    //throw new RuntimeException(e);
                }
            }
        }
    }

    public List<User> getAllUsers() {
        Statement statement = null;
        List<User> userList = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM user";

        try {
            statement = Util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    //throw new RuntimeException(e);
                }
            }
        }
        return userList;
    }

    public void cleanUsersTable() {
        PreparedStatement pStatement = null;
        String sql = "TRUNCATE TABLE user";
        try {
            pStatement = Util.getConnection().prepareStatement(sql);

            pStatement.executeUpdate();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
        } finally {
            if (pStatement != null) {
                try {
                    pStatement.close();
                } catch (SQLException e) {
                    //throw new RuntimeException(e);
                }
            }
        }
    }
}
