package org.hotel.UI;

import org.hotel.Service.AuthenticationService;

import javax.swing.*;
import java.awt.*;

public class LoginUI {

    private final AuthenticationService authenticationService = new AuthenticationService();

    public LoginUI(){
        JFrame frame = new JFrame("HOTEL APP - LOGIN PAGE");

        JTextField usertf = new JTextField("email");
        usertf.setBounds(160, 150, 200,30);

        JTextField passtf = new JTextField("password");
        passtf.setBounds(160, 200, 200,30);

        JButton loginBtn = new JButton("LOGIN");
        loginBtn.setBounds(200, 250, 100,40);

        loginBtn.addActionListener(b->{
            if(authenticationService.getEmailAndPass(usertf.getText(), passtf.getText())){
                frame.dispose();
                new HomeUI();
            }
            else {
                JOptionPane.showMessageDialog(frame, "Invalid Credentials!!!");
            }
        });

        frame.add(usertf);
        frame.add(passtf);
        frame.add(loginBtn);


        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
