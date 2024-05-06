package view;

import controller.ConnectDB;
import controller.ThreadController;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class RankView extends JFrame {
    private Vector<User> users = new Vector<>();
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
        ConnectDB connectDB = new ConnectDB();
        List<String[]> data = connectDB.getRankData();
        DefaultTableModel model = new DefaultTableModel(data.toArray(new Object[0][0]) , new  Object[]{"name" , "point"});

        rankJtable = new JTable(model);


//        rankJtable.setBounds(0,0,300 , 500);
//        rankJtable.setBackground(Color.RED);

        buttonBack  = new JButton("Back");
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThreadController.openMenuView();
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


}
