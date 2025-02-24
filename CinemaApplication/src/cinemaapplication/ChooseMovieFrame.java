/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemaapplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author Tiger
 */
public class ChooseMovieFrame extends JFrame {

    private JButton lotrBtn, swBtn, potcBtn, cwBtn, jwBtn, bachelor2Btn, deadpool2Btn;
    private JButton missionBtn, spidermanBtn, barbieBtn, titanicBtn, fast5Btn;
    private JLabel usernameLbl, dialogLbl;
    
    public ChooseMovieFrame() {
        this.setSize(1200, 1200);
        this.setTitle("Επιλογή Ταινίας");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(null);
        
        lotrBtn = new JButton();
        swBtn = new JButton();
        potcBtn = new JButton();
        cwBtn = new JButton();
        jwBtn = new JButton();
        bachelor2Btn = new JButton();
        deadpool2Btn = new JButton();
        missionBtn = new JButton();
        spidermanBtn = new JButton();
        barbieBtn = new JButton();
        titanicBtn = new JButton();
        fast5Btn = new JButton();
        usernameLbl = new JLabel();
        dialogLbl = new JLabel();
    }
    
    public void prepareChooseMovieUI(int userId, String username) {
        int coordinate;
        usernameLbl.setBounds(550, 5, 300, 30);
        usernameLbl.setText("Καλώς ορίσατε " + username);
        coordinate = setMoviePoster(userId, lotrBtn, "Lord of the Rings", "LOTR.png", 5, 40);
        coordinate = setMoviePoster(userId, swBtn, "Star Wars : The Force Awakens","SW.png", 5 + coordinate, 40);
        coordinate = setMoviePoster(userId, potcBtn, "Pirates of the Caribbean : At Worlds End","POTC.png", 5 + 2 * coordinate, 40);
        coordinate = setMoviePoster(userId, cwBtn, "Captain America : Civil War","CW.png", 5 + 3 * coordinate, 40);
        coordinate = setMoviePoster(userId, jwBtn, "Jurassic World","JW.png", 5 + 4 * coordinate, 40);
        coordinate = setMoviePoster(userId, bachelor2Btn, "Bachelor 2","Bachelor2.png", 5 + 5 * coordinate, 40);
        coordinate = setMoviePoster(userId, deadpool2Btn, "Deadpool 2","Deadpool2.png", 5, 60 + coordinate + 110);
        coordinate = setMoviePoster(userId, missionBtn, "Mission Impossible","Mission.png", 5 + coordinate, 60 + coordinate + 160);
        coordinate = setMoviePoster(userId, spidermanBtn, "Spiderman : No Way Home","Spiderman.png", 5 + 2 * coordinate, 60 + coordinate + 160);
        coordinate = setMoviePoster(userId, barbieBtn, "Barbie","Barbie.png", 5 + 3 * coordinate, 60 + coordinate + 160);
        coordinate = setMoviePoster(userId, titanicBtn, "Titanic","Titanic.png", 5 + 4 * coordinate, 60 + coordinate + 160);
        coordinate = setMoviePoster(userId, fast5Btn, "Fast 5","Fast5.png", 5 + 5 * coordinate, 60 + coordinate + 160);
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
        this.add(usernameLbl);
        this.add(lotrBtn);
        this.add(swBtn);
        this.add(potcBtn);
        this.add(cwBtn);
        this.add(jwBtn);
        this.add(bachelor2Btn);
        this.add(deadpool2Btn);
        this.add(missionBtn);
        this.add(spidermanBtn);
        this.add(barbieBtn);
        this.add(titanicBtn);
        this.add(fast5Btn);
        this.add(dialogLbl);
        this.setVisible(true);
    }
    
    private int setMoviePoster(int userId, JButton movieBtn, String movie, String iconPng, int x, int y) {
        ImageIcon icon = new ImageIcon(iconPng);
        int width = icon.getIconWidth();
        int height = icon.getIconHeight();
        movieBtn.setIcon(new ImageIcon(icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH)));
        movieBtn.setBounds(x, y, width, height);
        movieBtn.setBorder(null);
        movieBtn.setLocation(x, y);
        movieBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseTimeViewFrame frame4 = new ChooseTimeViewFrame();
                frame4.prepareChooseTimeViewUI(userId, movie);
            }
        });
        if (x + width < 1200)
            return width;
        else
            return height;
    }
    
    
}
