/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignment;

/**
 *
 * @author cyber
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class login extends JFrame implements ActionListener
{
    JLabel userLabel = new JLabel("Username:");
    JLabel passwordLabel = new JLabel("Password:");
    JTextField userText = new JTextField(10);
    JPasswordField passwordText = new JPasswordField(10);
    JButton loginButton = new JButton("Login");
    JPanel jp1=new JPanel();
    JPanel jp2=new JPanel();
    JPanel jp3=new JPanel();
        
        
    login()
    {
        setLayout(new BorderLayout());
        setSize(300, 150);
        loginButton.addActionListener(this);
        jp1.setLayout(new FlowLayout());
        jp2.setLayout(new FlowLayout());
        jp3.setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userLabel.setBounds(10, 10, 80, 25);
        //userText.setBounds();
        passwordLabel.setBounds(10, 50, 80, 25);
        //passwordText.setBounds(100, 50, 165, 25);
        jp1.add(userLabel);
        jp1.add(userText);
        jp2.add(passwordLabel);
        jp2.add(passwordText);
        add(jp1,BorderLayout.NORTH);
        add(jp2,BorderLayout.CENTER);
        add(jp3,BorderLayout.SOUTH);
        //loginButton.setBounds(10, 80, 255, 25);
        jp3.add(loginButton);
        setTitle("Login Frame");
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        
        String username = userText.getText();
        String password = new String(passwordText.getPassword());
         if ("admin".equals(username) && "password".equals(password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    Main_Menu jf=new Main_Menu();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password.");
                }
    }
    
}
class Main_Menu extends JFrame implements ActionListener
{
    JLabel userLabel = new JLabel("Welcome");
    JButton addButton = new JButton("Add St.");
    JButton viewButton = new JButton("view st.");
    JButton updateButton = new JButton("update st.");
    JButton deleteButton = new JButton("delete st.");
    JButton searchButton = new JButton("search st.");

    JPanel jp1=new JPanel();
    
        
        
    Main_Menu()
    {
        setLayout(new BorderLayout());
        setSize(300, 200);
        addButton.addActionListener(this);
        viewButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        searchButton.addActionListener(this);
        
        jp1.setLayout(new FlowLayout());
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userLabel.setBounds(10, 10, 80, 25);
        
        jp1.add(addButton);
        jp1.add(viewButton);
        jp1.add(updateButton);
        jp1.add(deleteButton);
        jp1.add(searchButton);
        
        add(userLabel,BorderLayout.NORTH);
        add(jp1,BorderLayout.CENTER);
        
        setTitle("Main Menu");
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        
        
    }
    
}
public class Assignment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        login frame=new login();
        
    }
    
}
