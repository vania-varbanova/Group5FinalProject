package services;

import lombok.Data;
import lombok.SneakyThrows;
import utils.ConsoleLogger;

import java.sql.*;

public class DatabaseService {
    private final String DATABASE_URL = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11654489";
    private final String USERNAME = "sql11654489";
    private final String PASSWORD = "TiCIWe4ymT";
    private final ConsoleLogger logger;

    public DatabaseService() {
        logger = new ConsoleLogger();
    }

    @SneakyThrows
    public void deleteUserWithId(String id) {
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        Statement stopForeignKey = connection.createStatement();
        stopForeignKey.execute("SET FOREIGN_KEY_CHECKS=0");
        String deleteUserStatement = "DELETE FROM users WHERE user_id= ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteUserStatement);
        preparedStatement.setString(1, id);
        boolean isSuccessful = preparedStatement.execute();

        if (isSuccessful == false) {
            logger.logSuccessfullMessage(String.format("User with %s id successfully deleted.", id));
            logger.logLineSeparator();
        }

        Statement setForeignKeyValidation = connection.createStatement();
        setForeignKeyValidation.execute("SET FOREIGN_KEY_CHECKS=1");
        connection.close();

        Thread.sleep(3000);
    }
}
