/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemaapplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Tiger
 */
public class CinemaFrame extends JFrame {
    
    private JButton loginBtn, subscriptionBtn;
    private JLabel dialogLbl;
    
    public CinemaFrame() {
        loginBtn = new JButton("Είσοδος Χρήστη");
        subscriptionBtn = new JButton("Εγγραφή");
        dialogLbl = new JLabel();
    }
    
    public void prepareUI() {
        this.setSize(550, 550);
        this.setTitle("Εφαρμογή Διαχείρισης Κρατήσεων Εισιτηριών Κινηματογράφου");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(null);
        loginBtn.setBounds(190, 200, 140, 30);
        subscriptionBtn.setBounds(190, 240, 140, 30);
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == loginBtn) {
                    LoginSubscriptionFrame frame2 = new LoginSubscriptionFrame();
                    frame2.prepareLoginUI();
                }
            }
        });
        subscriptionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == subscriptionBtn) {
                    LoginSubscriptionFrame frame2 = new LoginSubscriptionFrame();
                    frame2.prepareSubscriptionUI();
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
        this.add(loginBtn);
        this.add(subscriptionBtn);
        this.add(dialogLbl);
        this.setVisible(true);
        
    }
}
