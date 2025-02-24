/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemaapplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Tiger
 */
public class DownloadTicketsFrame extends JFrame {
    
    private JButton downloadTicketsBtn, exitBtn;
    private JLabel messageLbl, dialogLbl;
    
    public DownloadTicketsFrame() {
        this.setSize(400, 400);
        this.setTitle("Download Tickets");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(null);
        
        messageLbl = new JLabel();
        downloadTicketsBtn = new JButton("Download Tickets");
        exitBtn = new JButton("Exit");
        dialogLbl = new JLabel();
    }
    
    public void prepareDownloadTicketsUI(int userId, int movieId) {
        messageLbl.setBounds(50, 150, 140, 30);
        messageLbl.setText("Payment Successful");
        messageLbl.setBounds(130, 150, 140, 30);
        downloadTicketsBtn.setBounds(70, 200, 240, 30);
        downloadTicketsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema", "root", "vzw57hw");  
                    Statement stmt = con.createStatement();
                    String query = "select firstName, lastName, email, phoneNumber, username, movieName, timeView, dateView, type, hallName, numSeats, totalPrice "
                            + "from reservation, user, movie, hall where reservation.userId = user.userId and reservation.movieId = movie.movieId "
                            + "and movie.hallId = hall.hallId and reservation.userId=" + userId + " and reservation.movieId=" + movieId + ";";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        String firstName = rs.getString(1);
                        String lastName = rs.getString(2);
                        String email = rs.getString(3);
                        String phoneNumber = rs.getString(4);
                        String username = rs.getString(5);
                        String movieName = rs.getString(6);
                        String timeView = rs.getString(7);
                        String dateView = rs.getString(8);
                        String type = rs.getString(9);
                        String hallName = rs.getString(10);
                        int numSeats = rs.getInt(11);
                        int totalPrice = rs.getInt(12);
                        BufferedWriter buffer = new BufferedWriter(new FileWriter("Tickets" + username + ".txt"));
                        buffer.write("Tickets");
                        buffer.newLine();
                        buffer.newLine();
                        buffer.write("First Name: " + firstName);
                        buffer.newLine();
                        buffer.write("Last Name: " + lastName);
                        buffer.newLine();
                        buffer.write("Email: " + email);
                        buffer.newLine();
                        buffer.write("Mobile Phone: " + phoneNumber);
                        buffer.newLine();
                        buffer.write("Username: " + username);
                        buffer.newLine();
                        buffer.write("Movie Name: " + movieName);
                        buffer.newLine();
                        buffer.write("Show Time: " + timeView);
                        buffer.newLine();
                        buffer.write("Show Date: " + dateView);
                        buffer.newLine();
                        buffer.write("Movie Type: " + type);
                        buffer.newLine();
                        buffer.write("Screening Hall: " + hallName);
                        buffer.newLine();
                        buffer.write("Number of Tickets Selected: " + numSeats);
                        buffer.newLine();
                        buffer.write("Payment Amount: " + totalPrice);
                        buffer.close();
                        ExitFrame frame10 = new ExitFrame();
                        frame10.prepareExitUI();
                    }
                } catch (SQLException exception) {
                    System.out.println(exception.getMessage());
                } catch (IOException exception2) {
                    System.out.println(exception2.getMessage());
                }
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
             public void windowClosing(WindowEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Exit the application?");
                if (i == JOptionPane.YES_OPTION) 
                    System.exit(0);
                else if (i == JOptionPane.CANCEL_OPTION)
                    dialogLbl.setText("CANCEL");
                else if (i == JOptionPane.NO_OPTION)
                    dialogLbl.setText("NO");               
            }
        });
        this.add(dialogLbl);
        this.add(messageLbl);
        this.add(downloadTicketsBtn);
        this.setVisible(true);
    }
}
