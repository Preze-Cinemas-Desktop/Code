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
public class DownloadTransactionReceiptFrame extends JFrame {
    
    private JButton downloadReceiptBtn, exitBtn;
    private JLabel messageLbl, dialogLbl;
    
    public DownloadTransactionReceiptFrame() {
        this.setSize(400, 400);
        this.setTitle("Λήψη Αποδεικτικού Συναλλαγής");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(null);
        
        messageLbl = new JLabel();
        downloadReceiptBtn = new JButton("Λήψη Αποδεικτικού Συναλλαγής");
        exitBtn = new JButton("Έξοδος");
        dialogLbl = new JLabel();
    }
    
    public void prepareDownloadTransactionReceiptUI(int customerId, int totalPrice, int oldAmount, int userId, int movieId, int numTickets) {
        try {
            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema","root","vzw57hw");  
            Statement stmt1 = con1.createStatement();
            String query1 = "insert into reservation (userId, movieId, numSeats, totalPrice) values (" + String.valueOf(userId)
                + "," + String.valueOf(movieId) + "," + String.valueOf(numTickets) + "," + String.valueOf(totalPrice) + ");";
            stmt1.executeUpdate(query1);
            Statement stmt2 = con1.createStatement();
            String query2 = "select availableSeats from movie where movieId=" + String.valueOf(movieId) + ";";
            ResultSet rs = stmt2.executeQuery(query2);
            while (rs.next()) {
                int availableSeats = rs.getInt(1);
                availableSeats -= numTickets;
                Statement stmt3 = con1.createStatement();
                String query3 = "update movie set availableSeats=" + String.valueOf(availableSeats) + " where movieId=" + String.valueOf(movieId) + ";";
                stmt3.executeUpdate(query3);
            }
            messageLbl.setText("Επιτυχής Πληρωμή");
            messageLbl.setBounds(130, 150, 140, 30);
            downloadReceiptBtn.setBounds(70, 200, 240, 30);
            downloadReceiptBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String firstName, lastName, cardNum, dateExpire, secCode;
                        int newAmount;
                        Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","vzw57hw");  
                        Statement stmt2 = con2.createStatement();
                        String query2 = "select * from customer where customerId=" + String.valueOf(customerId) + ";";
                        ResultSet rs = stmt2.executeQuery(query2);
                        while(rs.next()) {
                            firstName = rs.getString(2);
                            lastName = rs.getString(3);
                            cardNum = rs.getString(4);
                            dateExpire = rs.getString(5);
                            secCode = rs.getString(6);
                            newAmount = rs.getInt(7);
                            BufferedWriter buffer = new BufferedWriter(new FileWriter("TransactionReceipt" + firstName + ".txt"));
                            buffer.write("Αποδεικτικό Συναλλαγής");
                            buffer.newLine();
                            buffer.newLine();
                            buffer.write("Όνομα: " + firstName);
                            buffer.newLine();
                            buffer.write("Επώνυμο: " + lastName);
                            buffer.newLine();
                            buffer.write("Στοιχείας κάρτας: " + cardNum);
                            buffer.newLine();
                            buffer.write("Ημερομηνία Λήξης: " + dateExpire);
                            buffer.newLine();
                            buffer.write("Sec. Code: " + secCode);
                            buffer.newLine();
                            buffer.write("Ημερομηνία Συναλλαγής: 2023-08-31");
                            buffer.newLine();
                            buffer.write("Ποσό Πληρωμής: " + String.valueOf(totalPrice));
                            buffer.newLine();
                            buffer.write("Παλιό Υπόλοιπο: " + String.valueOf(oldAmount));
                            buffer.newLine();
                            buffer.write("Νέο Υπόλοιπο: " + String.valueOf(newAmount));
                            buffer.close();
                            DownloadTicketsFrame frame9 = new DownloadTicketsFrame();
                            frame9.prepareDownloadTicketsUI(userId, movieId);
                            
                        }
                    } catch (IOException exception1) {
                        System.out.println(exception1.getMessage());
                    } catch (SQLException exception2) {
                        System.out.println(exception2.getMessage());
                    }
                    
                }
            });
            this.addWindowListener(new WindowAdapter() {
            @Override
             public void windowClosing(WindowEvent e) {
                    int i = JOptionPane.showConfirmDialog(null, "Έξοδος από την εφαρμογή;");
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
            this.add(downloadReceiptBtn);
            this.setVisible(true);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            messageLbl.setText("Υπάρχει ενεργή κράτηση");
            messageLbl.setBounds(110, 130, 240, 30);
            exitBtn.setBounds(110, 200, 140, 30);
            exitBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int i = JOptionPane.showConfirmDialog(null, "Έξοδος από την εφαρμογή;");
                    if (i == JOptionPane.YES_OPTION) 
                        System.exit(0);
                    else if (i == JOptionPane.CANCEL_OPTION)
                        dialogLbl.setText("CANCEL");
                    else if (i == JOptionPane.NO_OPTION)
                        dialogLbl.setText("NO");               
                }
            });
            this.add(dialogLbl);
            this.add(exitBtn);
            this.add(messageLbl);
            this.setVisible(true);
        }
    }
}
