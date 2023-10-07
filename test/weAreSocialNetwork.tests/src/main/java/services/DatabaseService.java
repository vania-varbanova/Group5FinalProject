package services;

import utils.ConsoleLogger;

import java.sql.*;

public class DatabaseService {
    private static final String DATABASE_URL = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11650045";
    private static final String USERNAME = "sql11650045";
    private static final String PASSWORD = "SNuijeQ5aI";

    public void deleteUserWithId(String id) throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        Statement stopForeignKey = connection.createStatement();
        stopForeignKey.execute("SET FOREIGN_KEY_CHECKS=0");
        String deleteUserStatement = "DELETE FROM users WHERE user_id= ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteUserStatement);
        preparedStatement.setString(1, id);
       boolean isSuccessful =  preparedStatement.execute();
       if(isSuccessful == false){
           ConsoleLogger.log(String.format("User with %s id successfully deleted;", id));
       }
        Statement setForeignKeyValidation = connection.createStatement();
        setForeignKeyValidation.execute("SET FOREIGN_KEY_CHECKS=1");
        connection.close();
    }
}
