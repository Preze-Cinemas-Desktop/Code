/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemaapplication.banksystem;

import java.sql.*;
/**
 *
 * @author Tiger
 */
public class BankSystem {
    
    public BankSystem() {
        
    }
    
    public int CheckTranscationInfo(String firstName, String lastName, String cardNum, String dateExpire, String secCode) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","vzw57hw");  
            Statement stmt = con.createStatement();
            String query = "select customerId from customer where firstName='" + firstName + "' and lastName='" + lastName + "' and cardNum='" +
                    cardNum + "' and dateExpire='" + dateExpire + "' and secCode='" + secCode + "';";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next())
                return rs.getInt(1);
            return 0;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return 0;
    }
    
    public int CheckAmount(int customerId) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","vzw57hw");  
            Statement stmt = con.createStatement();
            String query = "select amount from customer where customerId=" + String.valueOf(customerId) + ";";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next())
                return rs.getInt(1);
            return 0;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return 0;
    }
    
    public void doTransaction(int customerId, int totalPrice, int amount) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","vzw57hw");  
            Statement stmt = con.createStatement();
            int newAmount = amount - totalPrice;
            String query = "update customer set amount=" + String.valueOf(newAmount) + " where customerId=" + String.valueOf(customerId) + ";";
            stmt.executeUpdate(query);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
