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
import javax.swing.JTextField;
import java.sql.*;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author Tiger
 */
public class LoginSubscriptionFrame extends JFrame {

    private JLabel usernameLbl, passwordLbl;
    private JTextField usernameTxt;
    private JPasswordField passwordTxt;
    private JLabel firstNameLbl, lastNameLbl, emailLbl, phoneNumberLbl;
    private JTextField firstNameTxt, lastNameTxt, emailTxt, phoneNumberTxt;
    private JLabel messageLbl, dialogLbl;
    private JLabel errorFirstNameLbl, errorLastNameLbl, errorEmailLbl, errorPhoneNumberLbl;
    private JLabel errorUsernameLbl, errorPasswordLbl;
    private JButton subscriptionBtn, loginBtn;
        
    public LoginSubscriptionFrame() {
        this.setSize(550, 650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(null);
        
        usernameLbl = new JLabel("Username");
        usernameTxt = new JTextField();
        passwordLbl = new JLabel("Password");
        passwordTxt = new JPasswordField();
        firstNameLbl = new JLabel("First Name");
        firstNameTxt = new JTextField();
        lastNameLbl = new JLabel("Last Name");
        lastNameTxt = new JTextField();
        emailLbl = new JLabel("Email");
        emailTxt = new JTextField();
        phoneNumberLbl = new JLabel("Mobile Phone");
        phoneNumberTxt = new JTextField();
        subscriptionBtn = new JButton("Sign Up");
        loginBtn = new JButton("User Login");
        messageLbl = new JLabel();
        errorFirstNameLbl = new JLabel();
        errorLastNameLbl = new JLabel();
        errorEmailLbl = new JLabel();
        errorPhoneNumberLbl = new JLabel();
        errorUsernameLbl = new JLabel();
        errorPasswordLbl = new JLabel();
        dialogLbl = new JLabel();
    }
    
    public void prepareLoginUI() {
        this.setTitle("User Login");
        usernameLbl.setBounds(190, 170, 140, 30);
        usernameTxt.setBounds(190, 200, 140, 30);
        passwordLbl.setBounds(190, 240, 140, 30);
        passwordTxt.setBounds(190, 270, 140, 30);
        loginBtn.setBounds(190, 330, 140, 30);
        messageLbl.setBounds(110, 370, 330, 30);
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema", "root", "vzw57hw");  
                    Statement stmt = con.createStatement();
                    String username = usernameTxt.getText();
                    String password = passwordTxt.getText();
                    String query = "select userId from user where username='" + username + "' and password='" + password + "';"; 
                    passwordTxt.setEchoChar('*');
                    ResultSet rs = stmt.executeQuery(query);
                    if (!rs.next()) {
                        messageLbl.setText("Incorrect username or password entry");
                        usernameTxt.setText("");
                        passwordTxt.setText("");
                    } else {
                        int userId = rs.getInt(1);
                        ChooseMovieFrame frame3 = new ChooseMovieFrame();
                        frame3.prepareChooseMovieUI(userId, username);
                    }
                    con.close();  
                } catch (SQLException exception) {
                    System.out.println(exception.getMessage());
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
        this.add(usernameLbl);
        this.add(usernameTxt);
        this.add(passwordLbl);
        this.add(passwordTxt);
        this.add(loginBtn);
        this.add(messageLbl);
        this.add(dialogLbl);
        this.setVisible(true);
    }
    
    public void prepareSubscriptionUI() {
        this.setTitle("Sign Up");
        firstNameLbl.setBounds(190, 45, 140, 30);
        firstNameTxt.setBounds(190, 80, 140, 30);
        errorFirstNameLbl.setBounds(335, 80, 200, 30);
        lastNameLbl.setBounds(190, 120, 140, 30);
        lastNameTxt.setBounds(190, 155, 140, 30);
        errorLastNameLbl.setBounds(335, 155, 200, 30);
        emailLbl.setBounds(190, 195, 140, 30);
        emailTxt.setBounds(190, 230, 140, 30);
        errorEmailLbl.setBounds(335, 230, 200, 30);
        phoneNumberLbl.setBounds(190, 270, 140, 30);
        phoneNumberTxt.setBounds(190, 305, 140, 30);
        errorPhoneNumberLbl.setBounds(335, 305, 200, 30);
        usernameLbl.setBounds(190, 345, 140, 30);
        usernameTxt.setBounds(190, 380, 140, 30);
        errorUsernameLbl.setBounds(335, 380, 200, 30);
        passwordLbl.setBounds(190, 420, 140, 30);
        passwordTxt.setBounds(190, 455, 140, 30);
        errorPasswordLbl.setBounds(335, 455, 200, 30);
        subscriptionBtn.setBounds(190, 505, 140, 30);
        messageLbl.setBounds(210, 545, 330, 30);
        subscriptionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema", "root", "vzw57hw");  
                    Statement stmt = con.createStatement();
                    String firstName = firstNameTxt.getText();
                    String lastName = lastNameTxt.getText();
                    String email = emailTxt.getText();
                    String phoneNumber = phoneNumberTxt.getText();
                    String username = usernameTxt.getText();
                    String password = passwordTxt.getText();
                    boolean invalidFirstName = containsDigits(firstName);
                    boolean invalidLastName = containsDigits(lastName);
                    boolean validEmail = (email.endsWith("@gmail.com") || email.endsWith("@outlook.com") || email.endsWith("@hotmail.com"));
                    boolean invalidPhoneNumber = containsLetters(phoneNumber);
                    boolean validUsername = (containsDigits(username) && containsUpperCases(username));
                    boolean validPassword = (containsDigits(password) && containsUpperCases(password));
                    boolean validSubscription = true;
                    if (invalidFirstName) {
                        if (!firstName.isEmpty()) {
                            firstNameTxt.setText("");
                            errorFirstNameLbl.setText("Incorrect first name entry");
                        }
                        validSubscription = false;
                        messageLbl.setText("Failed Registration");
                    } else {
                        errorFirstNameLbl.setText("");
                    }
                    if (invalidLastName) {
                        if (!lastName.isEmpty()) {
                            lastNameTxt.setText("");
                            errorLastNameLbl.setText("Incorrect last name entry"); 
                        }
                        validSubscription = false;
                        messageLbl.setText("Failed Registration");
                    } else {
                        errorLastNameLbl.setText("");
                    }
                    if (!validEmail) {
                        if (!email.isEmpty()) {
                            emailTxt.setText("");
                            errorEmailLbl.setText("Incorrect email entry");
                        } else {
                            errorEmailLbl.setText("");
                        }
                        validSubscription = false;
                        messageLbl.setText("Failed Registration");
                    } else {
                        errorEmailLbl.setText("");
                    }
                    if (invalidPhoneNumber) {
                        if (!phoneNumber.isEmpty()) {
                            phoneNumberTxt.setText("");
                            errorPhoneNumberLbl.setText("Incorrect mobile phone entry");
                        }
                        validSubscription = false;
                        messageLbl.setText("Failed Registration");
                    } else {
                        errorPhoneNumberLbl.setText("");
                    }
                    if (!validUsername) {
                        if (!username.isEmpty()) {
                            usernameTxt.setText("");
                            errorUsernameLbl.setText("Incorrect username entry");
                        } else {
                            errorUsernameLbl.setText("");
                        }
                        validSubscription = false;
                        messageLbl.setText("Failed Registration");
                    } else {
                        errorUsernameLbl.setText("");
                    }
                    if (!validPassword) {
                        if (!password.isEmpty()) {
                            passwordTxt.setText("");
                            errorPasswordLbl.setText("Incorrect password entry");
                        } else {
                            errorPasswordLbl.setText("");
                        }
                        validSubscription = false;
                        messageLbl.setText("Failed Registration");
                    } else {
                        errorPasswordLbl.setText("");
                    }
                    if (validSubscription) {
                        String query = "insert into user (firstName, lastName, email, phoneNumber, username, password) values ('" + 
                         firstName + "', '" + lastName + "', '" + email + "', '" + phoneNumber + "', '" + username + "', '" 
                         + password + "');"; 
                        stmt.executeUpdate(query);
                        messageLbl.setText("Registration Successful");
                    }
                    con.close();  
                } catch (SQLException exception) {
                    System.out.println(exception.getMessage());
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
        this.add(firstNameLbl);
        this.add(firstNameTxt);
        this.add(lastNameLbl);
        this.add(lastNameTxt);
        this.add(emailLbl);
        this.add(emailTxt);
        this.add(phoneNumberLbl);
        this.add(phoneNumberTxt);
        this.add(usernameLbl);
        this.add(usernameTxt);
        this.add(passwordLbl);
        this.add(passwordTxt);
        this.add(subscriptionBtn);
        this.add(messageLbl);
        this.add(errorFirstNameLbl);
        this.add(errorLastNameLbl);
        this.add(errorEmailLbl);
        this.add(errorPhoneNumberLbl);
        this.add(errorUsernameLbl);
        this.add(errorPasswordLbl);
        this.add(dialogLbl);
        this.setVisible(true);
    }
    
    private boolean containsLetters(String textField) {
        for (int i = 0; i < textField.length(); i++) {
            if (Character.isLetter(textField.charAt(i)))
                return true;
        }
        return false;
    }
    
    private boolean containsDigits(String textField) {
        for (int i = 0; i < textField.length(); i++) {
            if (Character.isDigit(textField.charAt(i)))
                return true;
        }
        return false;
    }
    
    private boolean containsUpperCases(String textField) {
        for (int i = 0; i < textField.length(); i++) {
            if (Character.isUpperCase(textField.charAt(i)))
                return true;
        }
        return false;
    }
}
