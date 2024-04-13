package view;

import controller.MouseClickController;
import model.User;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JFrame {
        private User user;
        private  JTextField userName ;
        private  JButton OkButton;
        private  JButton exitButton;
        private JLabel backgroundPanel;
        public  Login(){
            setTitle("Login");
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            setLocation(400,200);

            // code
            JLabel login = new JLabel("Login");
            login.setBounds(140,150,100,30);
            login.setForeground(new Color(51,255,255));
            login.setFont(new Font("Arial" , Font.BOLD , 20));
            userName = new JTextField(15);
            userName.setBounds(100,200,150,30);
            OkButton = new JButton("OK");
            OkButton.setBounds(100,250,150,30);


            //  button tro ve man hinh chinh . :
            exitButton = new JButton();
            exitButton.setIcon(new ImageIcon("src/main/java/view/img/exit.png"));
            exitButton.setBounds(0,0,30,30);
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MouseClickController.openMenuView();
                    dispose();
                }
            });

            backgroundPanel = new JLabel();
            //  them background cho menu
            backgroundPanel.setIcon(new ImageIcon("src/main/java/view/img/startImg.jpg"));
            backgroundPanel.setLayout(null);
            backgroundPanel.add(login);
            backgroundPanel.add(userName);
            backgroundPanel.add(OkButton);
            backgroundPanel.add(exitButton);
            setContentPane(backgroundPanel);

            pack();
            setResizable(false);
            setVisible(true);
        }

    public static void main(String[] args) {
        new Login();
    }
}
