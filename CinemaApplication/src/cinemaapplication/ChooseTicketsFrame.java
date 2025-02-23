/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemaapplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Tiger
 */
public class ChooseTicketsFrame extends JFrame {
    
    private JLabel infoLbl, movieLbl;
    private JButton addTicketsBtn, removeTicketsBtn, chooseTicketsBtn;
    private JTextArea ticketsPriceTxt, numTicketsTxt, totalTxt;
    private JLabel dialogLbl;
    
    public ChooseTicketsFrame() {
        this.setSize(600, 400);
        this.setTitle("Επιλογή Εισιτηρίων");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(null);
        
        infoLbl = new JLabel("Τιμή                            Εισιτήρια                         Σύνολο");
        addTicketsBtn = new JButton("+");
        removeTicketsBtn = new JButton("-");
        numTicketsTxt = new JTextArea("0");
        ticketsPriceTxt = new JTextArea();
        totalTxt = new JTextArea("0");
        chooseTicketsBtn = new JButton("Επιλογή Εισιτηρίων");
        movieLbl = new JLabel();
        dialogLbl = new JLabel();
    }
    
    public void prepareChooseTicketsUI(int userId, int movieId) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema","root","vzw57hw");  
            Statement stmt = con.createStatement();
            String query = "select movieName, timeView , dateView, type, hallName, ticketPrice from movie, hall where movie.hallId = hall.hallId and movieId='" + movieId + "';";
            ResultSet rs = stmt.executeQuery(query);      
            infoLbl.setBounds(130, 100, 300, 30);
            removeTicketsBtn.setBounds(210, 140, 50, 30);
            numTicketsTxt.setBounds(265, 140, 20, 30);
            addTicketsBtn.setBounds(290, 140, 50, 30);
            ticketsPriceTxt.setBounds(120, 140, 50, 30);
            totalTxt.setBounds(380, 140, 50, 30);
            chooseTicketsBtn.setBounds(200, 200, 160, 30);
            ticketsPriceTxt.setEditable(false);
            numTicketsTxt.setEditable(false);
            totalTxt.setEditable(false);
            while (rs.next()) {
                ticketsPriceTxt.setText(Integer.toString(rs.getInt(6)));
                String movieName = rs.getString(1);
                String timeView = rs.getString(2);
                String dateView = rs.getString(3);
                String type = rs.getString(4);
                String hallName = rs.getString(5);
                ticketsPriceTxt.setText(Integer.toString(rs.getInt(6)));
                String movie = movieName + " " + timeView + " " + dateView + " " + type + " " + hallName;
                movieLbl.setText(movie);
            }
            movieLbl.setBounds(80, 40, 500, 30);
            addTicketsBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int ticketPrice = Integer.parseInt(ticketsPriceTxt.getText());
                    int numTickets = Integer.parseInt(numTicketsTxt.getText());
                    int totalPrice = Integer.parseInt(totalTxt.getText());
                    if (numTickets < 9) {
                        numTickets++;
                        totalPrice += ticketPrice;
                        numTicketsTxt.setText(String.valueOf(numTickets));
                        totalTxt.setText(String.valueOf(totalPrice));
                    }
                }
            } );
            removeTicketsBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int ticketPrice = Integer.parseInt(ticketsPriceTxt.getText());
                    int numTickets = Integer.parseInt(numTicketsTxt.getText());
                    int totalPrice = Integer.parseInt(totalTxt.getText());
                    if (numTickets > 0) {
                        numTickets--;
                        totalPrice -= ticketPrice;
                        numTicketsTxt.setText(String.valueOf(numTickets));
                        totalTxt.setText(String.valueOf(totalPrice));
                    }
                }
            } );
            chooseTicketsBtn.addActionListener(new ActionListener() {
                @Override 
                public void actionPerformed(ActionEvent e) {
                    CheckAvailabilityFrame frame6 = new CheckAvailabilityFrame();
                    frame6.prepareCheckAvailabilityUI(userId, movieId, Integer.parseInt(numTicketsTxt.getText()), Integer.parseInt(totalTxt.getText()));
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
            this.add(infoLbl);
            this.add(removeTicketsBtn);
            this.add(numTicketsTxt);
            this.add(addTicketsBtn);
            this.add(ticketsPriceTxt);
            this.add(totalTxt);
            this.add(chooseTicketsBtn);
            this.add(movieLbl);
            this.add(dialogLbl);
            this.setVisible(true);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
