package main.util;

import main.objects.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SQLHandlerUtil {
    public static DatabaseConnection dbconnection = new DatabaseConnection();
    public static Connection connection1 = dbconnection.getConnection();

    public static List<CarType> types = new ArrayList<CarType>();

    public static void WriteUser(String firstname, String lastname, String username, String password) throws SQLException {

        String query = "INSERT INTO user " +
                "(first_name,last_name,username,password) " +
                "VALUES ('"+firstname+"','"+lastname+"','"+username+"','"+password+"');";

        Statement statement = connection1.createStatement();
        statement.executeUpdate(query);
    }

    //IMPORTANT! Returns user based on username
    public static void findUser(String username) throws SQLException {
        String query = "SELECT * FROM user WHERE username = '" + username + "'";

        Statement statement = connection1.createStatement();

        ResultSet rs = statement.executeQuery(query);

        // JDBC needs to move to next line before retrieving data, otherwise error will occur, since the ResultSet object index starts at -1
        // Checks if a user matching the argument username exists. An empty ResultSet means that no matching user was found.
        if (rs.next()) {
            int userID = rs.getInt("user_ID");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String inusername = rs.getString("username");
            double balance = rs.getDouble("balance");

            // Sets the active account in the system
            Account.setAccount(userID, firstName, lastName, inusername, balance);

            System.out.println(Account.getFirstName());

            // SQL Query to grab the agents of the user
            String propTableQuery = "SELECT * FROM agents JOIN user " +
                    "ON agents.user_id = user.user_id " +
                    "WHERE user.user_id = " + Account.getUserID();

            // RS2 contains the table of agents for the active user. Use rs2.next to grab each row. Remember that rs2.next can only move forward in a table
            ResultSet rs2 = statement.executeQuery(propTableQuery);

            //TODO Finish this shit

            // Goes through each row and grabs their data
            while(rs2.next()) {
                String first_name = rs2.getString("first_name");
                String last_name = rs2.getString("last_name");
                String address = rs2.getString("address");
                int age = rs2.getInt("age");
                int contactNumber = rs2.getInt("contact_number");
                int agentID = rs2.getInt("agent_id");

                Agent agent = new Agent(first_name, last_name, agentID, address, age, contactNumber);
                Account.addAgent(agent);
            }

            String propTableQuery2 = "Select rentals.rental_id, rentals.agent_id, rentals.renter_id, rentals.car_id, " +
                             "rentals.rental_start_date, rental_end_date, total_cost " +
                             "FROM rentals JOIN agents " +
                             "ON rentals.agent_id = agents.agent_id JOIN user " +
                             "ON agents.user_id = user.user_id " +
                             "WHERE user.user_id = " + Account.getUserID();

            ResultSet rs3 = statement.executeQuery(propTableQuery2);

            while(rs3.next()) {

                int rental_id = rs3.getInt("rental_id");
                int agent_id = rs3.getInt("agent_id");
                int renter_id = rs3.getInt("renter_id");
                int car_id = rs3.getInt("car_id");
                String rental_start_date = rs3.getString("rental_start_date");
                String rental_end_date = rs3.getString("rental_end_date");
                double total_cost = rs3.getDouble("total_cost");

                Rentals rental = new Rentals(agent_id, renter_id, car_id, LocalDate.parse(rental_start_date), LocalDate.parse(rental_end_date), total_cost);
                rental.setId(rental_id);
                Account.addRentals(rental);
            }

        } else {
            System.out.println("User not found");
        }
    }

    public static Agent addAgent(String first_name, String last_name, int age, String address, int contactNumber) throws SQLException {
        String query = "INSERT INTO agents(first_name, last_name, age, address, contact_number, user_id) VALUES(?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = connection1.prepareStatement(query);
        statement.setString(1, first_name);
        statement.setString(2, last_name);
        statement.setInt(3, age);
        statement.setString(4, address);
        statement.setInt(5, contactNumber);
        statement.setInt(6, Account.getUserID());
        statement.executeUpdate();

        Agent agent = new Agent(first_name, last_name, age, address, contactNumber);
        return agent;
    }

    // Grabs all the cars of an agent
    public static List<Car> getAgentCars(int agent_id) throws SQLException {
        List<Car> carList = new ArrayList<>();
        String query = "SELECT * FROM car WHERE agent_id = ?";
        PreparedStatement statement = connection1.prepareStatement(query);
        statement.setInt(1, agent_id);

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            int car_id = rs.getInt("car_id");
            int year = rs.getInt("year");
            int car_type_id = rs.getInt("car_type_id");
            boolean car_currentlyRented = rs.getBoolean("car_currentlyRented");
            String model = rs.getString("model");
            String make = rs.getString("make");
            String color = rs.getString("color");
            double daily_rate = rs.getDouble("daily_rate");

            Car car = new Car(year, car_type_id, car_currentlyRented, model, make, color, daily_rate);
            car.setCar_id(car_id);
            carList.add(car);
        }
        return carList;
    }

    public static boolean updateAgent(int agent_Id, String first_name, String last_name, int age, String address, int contactNumber) {
        try {
            String query = "UPDATE agents SET first_name = ?, last_name = ?, age = ?, address = ?, contact_number = ? WHERE agent_id = " + agent_Id;
            PreparedStatement statement = connection1.prepareStatement(query);
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setInt(3, age);
            statement.setString(4, address);
            statement.setInt(5, contactNumber);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Agent Update Error: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteAgent(int agent_Id) {

        try {

            String query = "DELETE FROM agents WHERE agent_id = " + agent_Id;
            PreparedStatement statement = connection1.prepareStatement(query);
            int result = statement.executeUpdate();

            if(result > 0) return true;
            else
                System.out.println(result);
                return false;
        }

        catch (Exception e) {

            System.out.println("Agent Delete Error: " + e.getMessage());
            return false;
        }
    }

    public static Car addCar(int agent_id, int car_year, int car_type_Id, boolean car_currentlyRented, String car_model, String make, String car_color, double daily_rate) throws SQLException {
        String query = "INSERT INTO car(agent_id, year, car_type_id, car_currentlyRented, model, make, color, daily_rate) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = connection1.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, agent_id);
        statement.setInt(2, car_year);
        statement.setInt(3, car_type_Id);
        statement.setBoolean(4, car_currentlyRented);
        statement.setString(5, car_model);
        statement.setString(6, make);
        statement.setString(7, car_color);
        statement.setDouble(8, daily_rate);

        statement.executeUpdate();

        // Retrieve the generated keys (car_id) if needed
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int car_id = generatedKeys.getInt(1); // Get the generated car_id

            // Create a new Car object
            Car newCar = new Car(car_year, car_type_Id, car_currentlyRented, car_model, make, car_color, daily_rate);
            newCar.setCar_id(car_id); // Set the generated car_id

            return newCar;
        } else {
            throw new SQLException("Creating car failed, no ID obtained.");
        }
    }

    // Loads the Car Types into the static Car Type List
    public static void loadCarType() throws SQLException {
        String query = "SELECT * FROM car_type ORDER BY car_type_id ASC";
        Statement statement = connection1.createStatement();
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {
            int carTypeID = rs.getInt("car_type_id");
            String carTypeName = rs.getString("type_name");
            int passengerCount = rs.getInt("passenger_capacity");
            String terrain = rs.getString("terrain");

            CarType carType = new CarType(carTypeID, carTypeName, passengerCount, terrain);
            Account.getCarTypeList().add(carType);
        }
    }

    public static boolean updateCar(int car_year, int car_type_Id, String car_model, String make, String car_color, double daily_rate, int car_id) throws SQLException {

        String query = "UPDATE car SET car_type_id = ?, make = ?, model = ?, year = ?, color = ?, daily_rate = ? WHERE car_id = ?";
        PreparedStatement statement = connection1.prepareStatement(query);
        statement.setInt(1, car_type_Id);
        statement.setString(2, make);
        statement.setString(3, car_model);
        statement.setInt(4, car_year);
        statement.setString(5, car_color);
        statement.setDouble(6, daily_rate);
        statement.setInt(7, car_id);
        statement.executeUpdate();

        findUser(Account.getUserName());
        return true;
    }

    public static boolean deleteCar(int car_id) throws SQLException {

        String query = "DELETE FROM car WHERE car_id = ?";
        PreparedStatement statement = connection1.prepareStatement(query);
        statement.setInt(1, car_id);
        statement.executeUpdate();

        findUser(Account.getUserName());
        return true;
    }

    public static Rentals addRental(int agent_id, int renter_id, int car_id, LocalDate rent_start, LocalDate rent_end, double totalCost) throws SQLException {

        String query = "INSERT INTO rentals(agent_id, renter_id, car_id, rental_start_date, rental_end_date, total_cost) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection1.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, agent_id);
        statement.setInt(2, renter_id);
        statement.setInt(3, car_id);
        statement.setDate(4, Date.valueOf(rent_start));
        statement.setDate(5, Date.valueOf(rent_end));
        statement.setDouble(6, totalCost);
        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {

            int rental_id = generatedKeys.getInt(1);

            Rentals newRental = new Rentals(agent_id, renter_id, car_id, rent_start, rent_end, totalCost);
            newRental.setId(rental_id);

            query = "UPDATE car SET car_currentlyRented = ? WHERE car_id = ?";
            statement = connection1.prepareStatement(query);
            statement.setBoolean(1, true);
            statement.setInt(2, car_id);
            statement.executeUpdate();

            return newRental;
        }

        else {

            throw new SQLException("Creating a rental failed, no ID obtained.");
        }
    }

    public static boolean deleteRental(int rental_id, int renter_id) throws SQLException {

        String query = "DELETE FROM rentals WHERE rental_id = ?";
        PreparedStatement statement = connection1.prepareStatement(query);
        statement.setInt(1, rental_id);
        statement.executeUpdate();

        query = "DELETE FROM renter WHERE renter_id = ?";
        statement = connection1.prepareStatement(query);
        statement.setInt(1, rental_id);
        statement.executeUpdate();

        findUser(Account.getUserName());
        return true;
    }

    public static Renter addRenter(String firstName, String lastName, String status, String sex, int age, int contact_number, int license_number) throws SQLException {

        String query = "INSERT INTO renter(first_name, last_name, age, status, sex, contact_number, license_number) VALUES(?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = connection1.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        statement.setInt(3, age);
        statement.setString(4, status);
        statement.setString(5, sex.trim());
        statement.setInt(6, contact_number);
        statement.setInt(7, license_number);
        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();

        if(generatedKeys.next()) {

            int renter_id = generatedKeys.getInt(1);

            Renter newRenter = new Renter(renter_id, firstName, lastName, status, sex, age, contact_number, license_number);
            return newRenter;
        }

        else {

            throw new SQLException("Creating a renter failed, no ID obtained.");
        }
    }

    public static Car getOneCar(int car_id) throws SQLException {

        String query = "SELECT * FROM car WHERE car_id = ?";
        PreparedStatement statement = connection1.prepareStatement(query);
        statement.setInt(1, car_id);

        ResultSet rs = statement.executeQuery();

        rs.next();

        int year = rs.getInt("year");
        int car_type_id = rs.getInt("car_type_id");
        boolean car_currentlyRented = rs.getBoolean("car_currentlyRented");
        String model = rs.getString("model");
        String make = rs.getString("make");
        String color = rs.getString("color");
        double daily_rate = rs.getDouble("daily_rate");

        Car car = new Car(year, car_type_id, car_currentlyRented, model, make, color, daily_rate);
        car.setCar_id(car_id);
        return car;
    }

    public static CarType getCarType (int car_type_id) throws SQLException {

        String query = "SELECT * FROM car_type WHERE car_type_id = ?";
        PreparedStatement statement = connection1.prepareStatement(query);
        statement.setInt(1, car_type_id);
        ResultSet rs = statement.executeQuery();

        rs.next();

        int id = rs.getInt("car_type_id");
        String type_name = rs.getString("type_name");
        int capacity = rs.getInt("passenger_capacity");
        String terrain = rs.getString("terrain");

        return new CarType(id, type_name, capacity, terrain);
    }

    public static boolean unAssignCar(int car_id) throws SQLException {

        String query = "UPDATE car SET agent_id = NULL WHERE car_id = ?";
        PreparedStatement statement = connection1.prepareStatement(query);
        statement.setInt(1, car_id);
        statement.executeUpdate();

        query = "UPDATE rentals SET agent_id = NULL WHERE car_id = ?";
        statement = connection1.prepareStatement(query);
        statement.setInt(1, car_id);
        statement.executeUpdate();

        findUser(Account.getUserName());

        return true;
    }

    public static boolean assignCar(int car_id, int agent_id) throws SQLException {

        String query = "UPDATE car SET agent_id = ? WHERE car_id = ?";
        PreparedStatement statement = connection1.prepareStatement(query);
        statement.setInt(1, agent_id);
        statement.setInt(2, car_id);
        statement.executeUpdate();

        query = "UPDATE rentals SET agent_id = ? WHERE car_id = ?";
        statement = connection1.prepareStatement(query);
        statement.setInt(1, agent_id);
        statement.setInt(2, car_id);
        statement.executeUpdate();

        findUser(Account.getUserName());

        return true;
    }

    // TODO
    // AGENT QUERIES ---

    // Get total income of an agent

}

