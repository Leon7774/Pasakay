package main.objects;

import java.util.ArrayList;
import java.util.List;

public class Account{
    private static int userID;
    private static String firstName;
    private static String lastName;
    private static String userName;
    private static List<Agent> agentList;
    private static double balance;
    private static ArrayList<CarType> carTypeList = new ArrayList<CarType>();
    private static List<Rental> rentalList = new ArrayList<>();

    public static void setAccount(int userID2, String firstName2, String lastName2, String userName2, double balance) {
        userID = userID2;
        firstName = firstName2;
        lastName = lastName2;
        userName = userName2;

        agentList = new ArrayList<>();
        rentalList = new ArrayList<>();
        carTypeList = new ArrayList<>();
        balance = 0;
    }

    public static int getUserID() {
        return userID;
    }

    public static void setUserID(int userID) {
        Account.userID = userID;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        Account.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        Account.lastName = lastName;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        Account.userName = userName;
    }

    public static void printActiveAccount() {
        System.out.println(Account.getFirstName());
    }

    public static void addAgent(Agent agent) {
        agentList.add(agent);
    }

    public static void addRentals(Rental rental) {
        rentalList.add(rental);
    }

    public static List<Agent> getAgentList() {
        return agentList;
    }

    public static ArrayList<CarType> getCarTypeList() {
        return carTypeList;
    }
    public static List<Rental> getRentalsList () {return rentalList;}

    public static Agent findAgentById(int id) {
        for (Agent a : agentList) {
            if (a.getAgentID() == id) {
                return a;
            }
        }
        return null;
    }

    public static Agent findAgentByID(int agentID) {
        for (Agent agent : agentList) {
            if(agent.getAgentID() == agentID) {
                return agent;
            }
        }

        return null;
    }
}
