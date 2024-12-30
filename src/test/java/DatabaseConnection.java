import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection("jdbc:mysql://localhost/Pasakay", "root", "(pass)");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("MySQL JDBC Driver not found!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error connecting to the database.");
        }

        System.out.println(
                "Connected to the database successfully."
        );

        return databaseLink;
    }
}
