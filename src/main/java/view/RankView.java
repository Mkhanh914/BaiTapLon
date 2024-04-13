package view;

import javax.swing.*;
import java.awt.*;

public class RankView extends JFrame {
    private  JTable rankJtable;
    private  JLabel rankLabel;
    private  JLabel backgroundJlable;
    public   RankView(){
        setTitle("rank");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(400,200);
        //  table =
       // edit backround ;
        rankLabel = new JLabel("RANK");
        rankLabel.setFont(new Font("Arial" , Font.BOLD , 25));
        rankLabel.setBounds(100,0,10,10);

        backgroundJlable  = new JLabel();
//        backgroundJlable.setIcon(new ImageIcon("src/main/java/view/img/startImg.jpg"));
        rankJtable = new JTable(5,2);
        rankJtable.setBounds(0,0,200 , 400);
        rankJtable.setBackground(Color.RED);

        backgroundJlable.setLayout(new BorderLayout());
        backgroundJlable.add(rankLabel , BorderLayout.NORTH);
        backgroundJlable.add(rankJtable,  BorderLayout.CENTER);
        setContentPane(backgroundJlable);


        pack();
        setVisible(true);
    }

//    public static void main(String[] args) {
//        new RankView();
//    }
}
