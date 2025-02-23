/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemaapplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Tiger
 */
public class CheckAvailabilityFrame extends JFrame {
    
    private JButton nextBtn, exitBtn;
    private JLabel messageLbl, dialogLbl;
    
    public CheckAvailabilityFrame() {
        this.setSize(400, 400);
        this.setTitle("Έλεγχος Διαθεσιμότητας");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(null);
        
        nextBtn = new JButton("Επόμενο");
        exitBtn = new JButton("Έξοδος");
        messageLbl = new JLabel();
        dialogLbl = new JLabel();
    }
    
    public void prepareCheckAvailabilityUI(int userId, int movieId, int numTickets, int totalPrice) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema","root","vzw57hw");  
            Statement stmt1 = con.createStatement();
            String query1 = "select availableSeats from movie where movieId=" + String.valueOf(movieId) + ";";
            ResultSet rs1 = stmt1.executeQuery(query1);
            while (rs1.next()) {
                int availableSeats = rs1.getInt(1);
                if (numTickets <= availableSeats) {
                    messageLbl.setText("Επιτυχής Κράτηση");
                    messageLbl.setBounds(130, 150, 140, 30);
                    nextBtn.setBounds(110, 200, 140, 30);
                    nextBtn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                PayFrame frame7 = new PayFrame();
                                frame7.preparePayUI(totalPrice, userId, movieId, numTickets);
                            }
                        });
                } else {
                    if (availableSeats == 0) {
                        messageLbl.setText("Έλλειψη διαθεσιμότητας");
                        messageLbl.setBounds(130, 150, 140, 30);
                        exitBtn.setBounds(110, 200, 140, 30);
                        exitBtn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                System.exit(0);
                            }
                        });
                    } else {
                        messageLbl.setText(String.valueOf(availableSeats) + " θέσεις έχουν μείνει");
                        messageLbl.setBounds(130, 150, 140, 30);
                        nextBtn.setBounds(40, 200, 140, 30);
                        exitBtn.setBounds(200, 200, 140, 30);
                        nextBtn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                System.out.println("PSOFA");
                            }
                        });
                        exitBtn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                System.exit(0);
                            }
                        });
                    }
                }
            }
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
            this.add(messageLbl);
            this.add(nextBtn);
            this.add(exitBtn);
            this.add(dialogLbl);
            this.setVisible(true);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
