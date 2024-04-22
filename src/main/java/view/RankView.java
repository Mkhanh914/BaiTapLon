package view;

import controller.MouseClickController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RankView extends JFrame {
    private  JTable rankJtable;
    private JButton buttonBack ;
    private  JLabel rankLabel;
    private  JLabel backgroundJlable;
    public   RankView(){
        setTitle("rank");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,400);
        setLocation(400,200);
        //  table =
       // edit backround ;
        rankLabel = new JLabel("Xếp Hạng");
        rankLabel.setFont(new Font("Arial" , Font.BOLD , 25));
        rankLabel.setBounds(100,0,10,10);

        backgroundJlable  = new JLabel();
//        backgroundJlable.setIcon(new ImageIcon("src/main/java/view/img/startImg.jpg"));
        rankJtable = new JTable(5,2);
        rankJtable.setBounds(0,0,200 , 400);
//        rankJtable.setBackground(Color.RED);

        buttonBack  = new JButton("Back");
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MouseClickController.openMenuView();
                dispose();
            }
        });



        backgroundJlable.setLayout(new BorderLayout());
        backgroundJlable.add(rankLabel , BorderLayout.NORTH);
        backgroundJlable.add(rankJtable,  BorderLayout.CENTER);
        backgroundJlable.add(buttonBack, BorderLayout.SOUTH);
        setContentPane(backgroundJlable);


//        pack();
        setVisible(true);
    }

//    public static void main(String[] args) {
//        new RankView();
//    }
}
