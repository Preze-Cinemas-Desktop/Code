/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemaapplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import java.sql.*;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author Tiger
 */
public class ChooseTimeViewFrame extends JFrame {
    
    private JRadioButton[] radioBtnArray;
    private int[] movieIdsArray;
    private JButton chooseTimeViewBtn;
    private ButtonGroup bg;
    private JLabel dialogLbl;
      
    public ChooseTimeViewFrame() {
        this.setSize(600, 500);
        this.setTitle("Choose Show Time");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(null);
        radioBtnArray = new JRadioButton[3];
        movieIdsArray = new int[3];
        bg = new ButtonGroup();
        chooseTimeViewBtn = new JButton("Choose Show Time");
        dialogLbl = new JLabel();
    }
    
    public void prepareChooseTimeViewUI(int userId, String movie) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema", "root", "vzw57hw");  
            Statement stmt = con.createStatement();
            String query = "select movieId, movieName, timeView, dateView, type, hallName, ticketPrice from movie, hall "
                + "where movie.hallId = hall.hallId and movieName='" + movie + "';";
            ResultSet rs = stmt.executeQuery(query);
            int i = 0;
            int y = 0;
            
            while(rs.next()) {
                int movieId = rs.getInt(1);
                String movieName = rs.getString(2);
                String timeView = rs.getString(3);
                String dateView = rs.getString(4);
                String type = rs.getString(5);
                String hallName = rs.getString(6);
                int ticketPrice = rs.getInt(7);
                String queryResult = movieName + " " + timeView + " " + dateView + " " + type + " " + hallName + " " + 
                       Integer.toString(ticketPrice) + "$\n";
                radioBtnArray[i] = new JRadioButton(queryResult);
                movieIdsArray[i] = movieId;
                radioBtnArray[i].setBounds(10, 150 + y, 500, 30);
                y += 40;  
                i++;
            }
            chooseTimeViewBtn.setBounds(200, 400, 180, 30);
            chooseTimeViewBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int numRadioBtns = bg.getButtonCount();
                    for (int i = 0; i < numRadioBtns; i++) {
                        if (radioBtnArray[i].isSelected()) {
                            ChooseTicketsFrame frame5 = new ChooseTicketsFrame();
                            frame5.prepareChooseTicketsUI(userId, movieIdsArray[i]);
                        }
                    }
                }
            });
            for (int j = 0; j < i; j++) {
                bg.add(radioBtnArray[j]);
                this.add(radioBtnArray[j]);
            }
            con.close();
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
            this.add(chooseTimeViewBtn);
            this.add(dialogLbl);
            this.setVisible(true);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
