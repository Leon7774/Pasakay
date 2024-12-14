import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLTest {
    public static DatabaseConnection connection = new DatabaseConnection();
    public static Connection dbConnection = connection.getConnection();

    public static void main(String[] args) throws SQLException {
        if(deleteAgent(8)){
            System.out.println("Agent deleted");
        }else{
            System.out.println("Agent not found");
        }
    }

    public static boolean createAgent(String first_name, String last_name, int userID) {
        try{
            String query = "INSERT INTO agents(first_name, last_name, user_id) VALUES(?, ?, ?)";
            PreparedStatement statement = dbConnection.prepareStatement(query);
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setInt(3, userID);
            statement.executeUpdate();
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    public static boolean deleteAgent(int agent_id) {
        try{
            // First checks if an agent matches the given agent_id
            String query1 = "SELECT * FROM agents WHERE id = ?";
            PreparedStatement statement1 = dbConnection.prepareStatement(query1);
            statement1.setInt(1, agent_id);
            ResultSet rs = statement1.executeQuery();
            if (!rs.next()) {
                return false;
            }

            String query2 = "DELETE FROM agents WHERE agent_id = ?";
            PreparedStatement statement2 = dbConnection.prepareStatement(query2);
            statement2.setInt(1, agent_id);
            statement2.executeUpdate();
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    public static boolean editAgent(int agent_id, String first_name, String last_name, int userID) {

    }
}
