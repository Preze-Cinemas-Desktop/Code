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
import javax.swing.JOptionPane;

/**
 *
 * @author Tiger
 */
public class ExitFrame extends JFrame {
    
    private JLabel messageLbl, dialogLbl;
    private JButton exitBtn;
    
    public ExitFrame() {
        this.setSize(400, 400);
        this.setTitle("Έξοδος");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(null);
        
        messageLbl = new JLabel();
        exitBtn = new JButton("Έξοδος");
        dialogLbl = new JLabel();
    }
    
    public void prepareExitUI() {
        messageLbl.setText("Σας ευχαριστούμε για την συνεργασία");
        messageLbl.setBounds(80, 150, 340, 30);
        exitBtn.setBounds(70, 200, 240, 30);
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
        this.add(messageLbl);
        this.add(exitBtn);
        this.setVisible(true);
    }
}
