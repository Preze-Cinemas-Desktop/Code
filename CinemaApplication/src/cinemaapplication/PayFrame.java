/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemaapplication;

import cinemaapplication.banksystem.BankSystem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Tiger
 */
public class PayFrame extends JFrame {
    
    private JLabel infoLbl, messageLbl, firstNameLbl, lastNameLbl, cardNumLbl, dateExpireLbl, secCodeLbl;
    private JTextField firstNameTxt, lastNameTxt, cardNumTxt, dateExpireTxt, secCodeTxt;
    private JButton payBtn;
    private JLabel dialogLbl;
    
	public PayFrame() {
		this.setSize(550, 650);
		this.setTitle("Payment");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLayout(null);
		
		messageLbl = new JLabel();
		infoLbl = new JLabel("Payment Details");
		firstNameLbl = new JLabel("First Name");
		firstNameTxt = new JTextField();
		lastNameLbl = new JLabel("Last Name");
		lastNameTxt = new JTextField();
		cardNumLbl = new JLabel("Card Details");
		cardNumTxt = new JTextField();
		dateExpireLbl = new JLabel("Expiration Date");
		dateExpireTxt = new JTextField();
		secCodeLbl = new JLabel("Sec. Code");
		secCodeTxt = new JTextField();
		payBtn = new JButton("Pay");
		dialogLbl = new JLabel();
	}

    
    public void preparePayUI(int totalPrice, int userId, int movieId, int numTickets) {
        infoLbl.setBounds(200, 35, 140, 30);
        firstNameLbl.setBounds(190, 75, 140, 30);
        firstNameTxt.setBounds(190, 110, 140, 30);
        lastNameLbl.setBounds(190, 150, 140, 30);
        lastNameTxt.setBounds(190, 185, 140, 30);
        cardNumLbl.setBounds(190, 225, 140, 30);
        cardNumTxt.setBounds(190, 260, 140, 30);
        dateExpireLbl.setBounds(190, 300, 140, 30);
        dateExpireTxt.setBounds(190, 335, 140, 30);
        secCodeLbl.setBounds(190, 375, 140, 30);
        secCodeTxt.setBounds(190, 410, 140, 30);
        payBtn.setBounds(190, 470, 140, 30);
        messageLbl.setBounds(190, 520, 440, 30);
        payBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameTxt.getText();
                String lastName = lastNameTxt.getText();
                String cardNum = cardNumTxt.getText();
                String dateExpire = dateExpireTxt.getText();
                String secCode = secCodeTxt.getText();
                int customerId, oldAmount;
                BankSystem bank = new BankSystem();
                customerId = bank.CheckTranscationInfo(firstName, lastName, cardNum, dateExpire, secCode);
                if (customerId == 0) {
                    firstNameTxt.setText("");
                    lastNameTxt.setText("");
                    cardNumTxt.setText("");
                    dateExpireTxt.setText("");
                    secCodeTxt.setText("");
                    messageLbl.setText("Incorrect input of payment details");
                } else {
                    oldAmount = bank.CheckAmount(customerId);
                    if (oldAmount < totalPrice) {
                        messageLbl.setText("The balance is not sufficient for the transaction");
                    } else {
                        bank.doTransaction(customerId, totalPrice, oldAmount);
                        DownloadTransactionReceiptFrame frame8 = new DownloadTransactionReceiptFrame();
                        frame8.prepareDownloadTransactionReceiptUI(customerId, totalPrice, oldAmount, userId, movieId, numTickets);
                    }
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
        this.add(infoLbl);
        this.add(firstNameLbl);
        this.add(firstNameTxt);
        this.add(lastNameLbl);
        this.add(lastNameTxt);
        this.add(cardNumLbl);
        this.add(cardNumTxt);
        this.add(dateExpireLbl);
        this.add(dateExpireTxt);
        this.add(secCodeLbl);
        this.add(secCodeTxt);
        this.add(payBtn);
        this.add(messageLbl);
        this.add(dialogLbl);
        this.setVisible(true);
    }
}
