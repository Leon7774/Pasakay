import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLTest {
    public static DatabaseConnection connection = new DatabaseConnection();
    public static Connection dbConnection = connection.getConnection();

    public static void main(String[] args) throws SQLException {
        createAgent("Mike", "Tyson", 1);
    }

    public static void createAgent(String first_name, String last_name, int userID) throws SQLException {
        String query = "INSERT INTO agents(first_name, last_name, user_id) VALUES(?, ?, ?)";
        PreparedStatement statement = dbConnection.prepareStatement(query);
        statement.setString(1, first_name);
        statement.setString(2, last_name);
        statement.setInt(3, userID);
        statement.executeUpdate();
    }
}
